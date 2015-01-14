package mutantScreening;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import mainWindow.messageWindowTopComponent;
import mainWindow.openerTopComponent;

public class GAutilities {

    private final int autodock_cycles;				//cyc
    private final File outputDirectory;				//outDir
    private int firstJobNumber;					//currJobNumber ... this should never change after being initialized
    private int job_count = 0;					//the number of jobs ran so far (for dock_X directories)
    private final String ligFile;                 	//path to .pdb file of ligand
    private final String recFile;						//path to receptor file
    private final String boxFile;						//path to .gpf file
    private final String swarmCmd;					//openerTopComponent.swmCmdOpts.getText()
    private final HashMap<String, Double> map;
    private final String[] origSeq;
    private final int smoothNum;
    private final String base_peptide;

    public GAutilities(int cyc, int smoothNum, File outDir, String ligFile, String recFile, String boxFile, String swarmCmd, String[] origSeq) {
        this.autodock_cycles = cyc;			//cyc
        map = new HashMap<String, Double>();
        this.outputDirectory = outDir;		//outDir

        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdirs()) {
                toLog("Unable to create the output directory: " + outputDirectory.getAbsolutePath());
                System.exit(1);
            }
        }

        this.ligFile = ligFile;         //path to .pdb file of ligand
        String[] tmp = ligFile.split(File.pathSeparator);
        this.base_peptide = tmp[tmp.length - 1];  //get the base peptides file name
        this.recFile = recFile;			//path to receptor file
        this.boxFile = boxFile;			//path to .gpf file
        this.swarmCmd = swarmCmd;		//swarm commands from the GUI
        this.origSeq = origSeq;
        this.smoothNum = smoothNum;
    }

    //determine what work has already been done.  update map accordingly
    public void getPreviousResults(ArrayList<Organism> population, int generationSize) {
        int index = this.getResultsRange();
        if (index == -1) {
            firstJobNumber = 0;
        } else {
            updateMap(0, index);
            firstJobNumber = index + 1;

            for (String key : map.keySet()) {
                population.add(new Organism(key, map.get(key)));
            }

            Collections.sort(population);
            int endPoint = population.size();

            for (int i = generationSize; i < endPoint; i++) {
                population.remove(generationSize); //arraylist is getting smaller so same index refers to next element
            }
        }
    }

    public void getFitnessScores(ArrayList<Organism> organisms) {
        int startIndex = firstJobNumber + job_count;
        int numJobs = organisms.size();			//elites will not be in the population

        mkSubdirs(startIndex, numJobs);
        genCmdFile(startIndex, numJobs, organisms);
        startDockJobs();
        waitFor(startIndex, numJobs);
        updateMap(startIndex, numJobs);
        //now update the energies
        Organism org;
        for (int i = 0; i < organisms.size(); i++) {
            org = organisms.get(i);
            if (map.containsKey(org.getGenotype())) {
                org.setFitness(map.get(org.getGenotype()));
            } else {
                org.setFitness(100.0);
            }
        }

        job_count += numJobs;
    }

    private void mkSubdirs(int start, int numJobs) {
        File subDir;
        String subDirName;
        String base = "";

        try {
            base = outputDirectory.getCanonicalPath();
        } catch (java.io.IOException e) {
            toLog(e.getLocalizedMessage());
        }

        if (base.length() > 0) {
            base += File.separator;
        }

        for (int i = start; i < start + numJobs; i++) {
            subDirName = base + "dock_" + String.format("%0"+openerTopComponent.PADDING+"d", i);
            subDir = new File(subDirName);
            subDir.mkdir();
        }
    }

    //generate the command file to be used by swarm
    private void genCmdFile(int start, int numJobs, ArrayList<Organism> organisms) {
        String script_path = getDockomaticScriptPath();
        // Make sure dockOmatic.pl is executable, since Netbeans changes permissions when creating distributions
        File tmpfile = new File(script_path);
        tmpfile.setExecutable(true);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory.getCanonicalPath() + File.separator+"swarmCmds.txt"));

            int orgIndex = 0;

            for (int i = start; i < start + numJobs; i++) {
                writer.write(script_path + " -p " + ligFile + organisms.get(orgIndex++).toCmdString(origSeq) + " -o " + outputDirectory + "/dock_"
                        + String.format("%0"+openerTopComponent.PADDING+"d",i) + " -r " + recFile + " -b " + boxFile + " -g " + autodock_cycles + "\n");
            }

            writer.close();
        } catch (IOException ex) {
            toLog(ex.getLocalizedMessage());
        }
    }

    //looks at output folder and determines the first and last job indices.
    // return result[0] = first folders number  result[1] = second folders number
    private int getResultsRange() {

        int result = -1;
        String[] dockFiles = outputDirectory.list();
        int num;

        for (int i = 0; i < dockFiles.length; i++) {
            if (dockFiles[i].startsWith("dock_")) {
                num = Integer.parseInt(dockFiles[i].substring("dock_".length()+1));
                result = Math.max(result, num);
            }
        }
        return result;
    }

    private void updateMap(int start, int numJobs) {
        double runTotal;
        String tmpStr = "ERROR";

        String outPath = outputDirectory.getAbsolutePath();

        RankFileFilter filter = new RankFileFilter();

        File outDirectory;

        for (int i = start; i < start + numJobs; i++) {

            outDirectory = new File(outPath + "/dock_" + String.format("%0"+openerTopComponent.PADDING+"d",i));
            runTotal = 0.0;

            if (outDirectory.exists()) {

                String[] contents = outDirectory.list(filter);

                //go through all files in directory
                for (int j = 0; j < contents.length; j++) {

                    if (j == 0) {  //run once
                        tmpStr = parseLigand(contents[j]);
//	            		 map.put(tmpStr, 0.0);
                    }

                    for (int k = 1; k <= smoothNum; k++) {  //look for files		
                        if (contents[j].contains("_rank_" + k + ".pdb")) {
                            runTotal += parseEnergy(outPath + File.separator +"dock_" + String.format("%0"+openerTopComponent.PADDING+"d",i) + File.separator + contents[j]);
                            break;
                        }
                    }
                }
                if (contents.length > 0) { //handle failed docking case
                    runTotal /= Math.min(smoothNum, contents.length);
                    map.put(tmpStr, runTotal);
                }
            }
        }

    }

    private void waitFor(int start, int numJobs) {
        try {

            String base = outputDirectory.getCanonicalPath() + File.separator + "dock_";
            File temp;
            String[] contents;

            ArrayList<Integer> jobsLeft = new ArrayList<Integer>();

            // load all jobs into the list
            for (int index = start; index < (start + numJobs); index++) {
                jobsLeft.add(index);
            }

            int MAX_ROUNDS = 10;
            long sleepTime = 60000;

            int numStagnantRounds = 0;
            int numLeft = jobsLeft.size();
            boolean proceed = true;
            int index;

            while (proceed) {
                // wait before checking folders again
                Thread.sleep(sleepTime);

                index = 0;

                while (index < jobsLeft.size()) {
                    temp = new File(base + jobsLeft.get(index));
                    contents = temp.list(new RankFileFilter());

                    if (contents.length != 0) {
                        jobsLeft.remove(index);  //elements shift down, so we don't need to move the index up
                    } else {
                        index++;
                    }
                }

                if (jobsLeft.isEmpty()) {
                    proceed = false;
                } else if (jobsLeft.size() < .05 * numJobs) {  //only 5% of jobs are remaining
                    if (jobsLeft.size() < numLeft) {		  //more jobs have completed since last round
                        numStagnantRounds = 0;
                        numLeft = jobsLeft.size();
                    } else if (numStagnantRounds >= MAX_ROUNDS) {	  //MAX_ROUNDS rounds without change, probably an error
                        proceed = false;
                    } else {
                        numStagnantRounds++;
                    }
                }
                //TODO remove the following line
                toLog("There are " + jobsLeft.size() + " jobs left.");

            }

            if (jobsLeft.size() > 0) {
                String moveIt = "Continuing on, the following jobs did not complete: \n";
                for (int k = 0; k < jobsLeft.size(); k++) {
                    moveIt += jobsLeft.get(k) + ", ";
                }
                toLog(moveIt);
            }

        } catch (IOException e) {
            toLog(e.getLocalizedMessage());
        } catch (InterruptedException e) {
            toLog(e.getLocalizedMessage());
        }

    }

    private String parseLigand(String ligand) {
        /*First, parse the ligand name and create a correct acid for Chromosome creation. The ligand argument will
         look like this: MII_N5Q_H9N_L10M_E11Y_H12N_L15M.pdb_rank_1.pdb.  I need to transform it into QNMYNM
         */
        String rval = "";
        String[] fields;
        if (ligand.contains(base_peptide)) {
            fields = new String[1];
            fields[0] = "";
        } else {
            String tempString = ligand.substring(ligand.indexOf('_') + 1, ligand.indexOf('.')); //trim the string
            fields = tempString.split("_");
        }
        int fIndex = 0;

        //second clause handles the case where originalSeq contains X1 and X10 - X19
        for (int j = 0; j < origSeq.length; j++) {
            if (fIndex < fields.length && fields[fIndex].contains(origSeq[j]) && fields[fIndex].length() - 1 == origSeq[j].length()) {
                rval += fields[fIndex].charAt(fields[fIndex].length() - 1);
                fIndex++;
            } else {
                rval += origSeq[j].charAt(0);
            }
        }

        return rval;

    }

    private double parseEnergy(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));

            String temp = reader.readLine();
            while (!temp.contains("Estimated Free Energy of Binding")) {
                temp = reader.readLine();
            }

            int lower = temp.indexOf('=');
            int upper = temp.indexOf("kcal/mol");

            reader.close();

            return Double.parseDouble(temp.substring(lower + 1, upper));

        } catch (FileNotFoundException ex) {
            toLog(ex.getLocalizedMessage());
            return 100.0;
        } catch (NoSuchElementException ex1) {
            toLog(ex1.getLocalizedMessage());
            return 100.0;
        } catch (IOException e) {
            toLog(e.getLocalizedMessage());
            return 100.0;
        }
    }
    
    protected static void toLog(String text){
//        System.out.println(text);
        messageWindowTopComponent.appendText(text);
    }

    /**
     *
     */
    private void startDockJobs() {
        String line, errorLog = "";
        try {
            Process procID = Runtime.getRuntime().exec(swarmCmd, null, outputDirectory);
            BufferedReader in = new BufferedReader(new InputStreamReader(procID.getErrorStream()));
            while ((line = in.readLine()) != null) {
                errorLog += line + "\n";
            }
            toLog(errorLog);

        } catch (IOException ex) {
            toLog(ex.getLocalizedMessage());
        }
    }

    public String printBest(int numBest) {
        String result = "";

        ArrayList<Organism> genealogy = new ArrayList<Organism>();

        for (String key : map.keySet()) {
            genealogy.add(new Organism(key, map.get(key)));
        }
        numBest = Math.min(numBest, genealogy.size());

        Collections.sort(genealogy);

        for (int i = 0; i < numBest; i++) {
            result += genealogy.get(i).toString() + "\n";
        }

        return result;
    }

    private String getDockomaticScriptPath() {
        String classPath = GAutilities.class.getResource("GAutilities.class").getPath();
        int start = classPath.indexOf(":") + 1;  //ignore file: start
        int end = classPath.indexOf("modules" + File.separator);
        String script_path = classPath.substring(start,end);        
        script_path += "lib" + File.separator + "dockOmatic.pl";

        return script_path;
    }

    private class RankFileFilter implements FilenameFilter {

        @Override
        public boolean accept(File directory, String fileName) {
            boolean rval = false;
            if (fileName.contains("_rank_") && fileName.contains(".pdb")) {
                rval = true;
            }
            return rval;
        }
    }

    public void syncUp(HashSet<String> tested_genotypes) {
        for (String key : map.keySet()) {
            tested_genotypes.add(key);
        }
    }
}