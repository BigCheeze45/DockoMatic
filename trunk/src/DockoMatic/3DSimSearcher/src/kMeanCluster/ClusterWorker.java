package kMeanCluster;

import edu.boisestate.SimSearcher.SimSearchUtilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import map.MappedMolecule;
import map.Mapper;
import map.Typed_Angle;
import org.openide.util.Exceptions;

public class ClusterWorker {

    public final static String CLUSTER_FILE_PREFIX = "process";

    public static void main(String[] args) {

        if (args.length < 7) {
            usage();
        }
        //Read from ClusterCenters file, store centers
        int argNum = 0;

        File dataFolder = new File(args[argNum++]);

        if (!dataFolder.exists()) {
            System.err.println("Data folder " + dataFolder + " does not exist, unable to proceed.");
            System.exit(1);
        }

        File mainFolder = new File(args[argNum++]);
        File outputFolder = new File(args[argNum++]);
        char option = args[argNum++].charAt(0);
        int numClusters = Integer.parseInt(args[argNum++]);
        String dist_test = args[argNum++];
        int id = Integer.parseInt(args[argNum++]);
        String file_list = args[argNum++];
        boolean verbose = args.length > 7;

        String workingFolder = mainFolder + File.separator + ClusterManager.FILE_PREFIX;

        ArrayList<ClusterCenter> currentCenters = new ArrayList<ClusterCenter>();
        ArrayList<ClusterCenter> newCenters = null;
        ArrayList<ArrayList<MappedMolecule>> Clusters = null;

        if (option == 'w') {
            newCenters = new ArrayList<ClusterCenter>(); //newCenters.get(i) contains running totals for cluster i
            for (int i = 0; i < numClusters; i++) {
                newCenters.add(new HistogramClusterCenter());
            }
        } else {
            Clusters = new ArrayList<ArrayList<MappedMolecule>>(); //Clusters.get(i) contains the mapped molecules which are part of the cluster
            for (int i = 0; i < numClusters; i++) {
                Clusters.add(new ArrayList<MappedMolecule>());
            }
        }

        try {
            /* Read and store current cluster centers */

            if (verbose) {
                System.out.println("Worker: Reading cluster centers.");
            }
            BufferedReader ccReader = new BufferedReader(new FileReader(new File(workingFolder, ClusterManager.clusterInfo)));

            for (int i = 0; i < numClusters; i++) {
                currentCenters.add(new HistogramClusterCenter(ccReader.readLine()));
            }

            ccReader.close();

            /* Read and store file names which need to be processed by this worker*/
            if (verbose) {
                System.out.println("Worker: Reading partition.");
            }
            String[] files = file_list.split(SimSearchUtilities.DELIM);

            /* Update local values, which will be aggregated later, for files */
            ArrayList<MappedMolecule> currentList;
            double minDistance, tempDistance;
            int closest = 0;

            for (String file : files) {
                if (verbose) {
                    System.out.println("Worker: Parsing histograms from " + file);
                }
                
                currentList = parseMappedMolecules(new File(dataFolder, file));

                //K-means clustering logic
                for (MappedMolecule mol : currentList) {

                    minDistance = Double.MAX_VALUE;

                    for (int i =0; i < currentCenters.size(); i++) {
                        tempDistance = currentCenters.get(i).getDistanceFrom(mol, dist_test);
                        if (tempDistance < minDistance) {
                            minDistance = tempDistance;
                            closest = i;
                        }
                    }

                    /*Update partial sum or add molecule to the cluster*/
                    if (option == 'w') {
                        newCenters.get(closest).updateTotalWith(mol);
                    } else {
                        Clusters.get(closest).add(mol);
                    }
                }
            }

            if (verbose) {
                System.out.println("Worker: Done parsing all histograms, writing " + (option == 'w' ? "partial" : "final") + " results to file.");
            }

            if (option == 'w') {
                writeNewCenters(newCenters, new File(workingFolder, "partialSum_" + id + ".txt"));
            } else {
                writeClusters(Clusters, outputFolder, id);
            }

        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        }
    }

    protected static void writeClusters(ArrayList<ArrayList<MappedMolecule>> clusters, File writeDir, int id) throws IOException {

        if (!writeDir.exists()) {
            writeDir.mkdir();
        }

        for (int i = 0; i < clusters.size(); i++) {
            BufferedWriter bwrite = new BufferedWriter(new FileWriter(new File(writeDir, ClusterManager.FILE_PREFIX + i + File.separator + CLUSTER_FILE_PREFIX + id + Mapper.MAPPED_SUFFIX))); //TODO hard coded value
            for (MappedMolecule mol : clusters.get(i)) {
                bwrite.write(mol.toString());
            }
            bwrite.flush();
            bwrite.close();
        }
    }

    private static void usage() {
        String usage = "usage: java ClusterWorker <data folder> w|c <# clusters> <# bins per histogram> <id> <list> [-verbose]";
        System.err.println(usage);
        System.exit(1);
    }

    protected static void writeNewCenters(ArrayList<ClusterCenter> newCenters, File writeFile) throws IOException {
        String toPrint = "";

        for (ClusterCenter center : newCenters) {
            toPrint += center + "\n";
        }

        BufferedWriter bwrite = new BufferedWriter(new FileWriter(writeFile));
        bwrite.write(toPrint);
        bwrite.flush();
        bwrite.close();
    }

    /**
     * Creates histogram data from a file which was transformed by the Mapper
     * class.
     *
     * @param file the file which contains histogram data generated by the
     * Mapper class
     * @return A list of Clusterable items
     */
    public static ArrayList<MappedMolecule> parseMappedMolecules(File data_file) {
        BufferedReader bread = null;
        
        try {

            ArrayList<MappedMolecule> list = new ArrayList<MappedMolecule>();

            String line;
            MappedMolecule current_molecule = null;
            ArrayList<Typed_Angle> typed_angles = new ArrayList<Typed_Angle>();
            boolean typed_angle_line = false;
            
            bread = new BufferedReader(new FileReader(data_file));

            while ((line = bread.readLine()) != null) {
                if(line.contains(MappedMolecule.ID_TAG)){
                    if(current_molecule != null){  //just finished parsing a molecule, add it to the list
                        list.add(current_molecule);
                    }
                    String id = line.substring(MappedMolecule.ID_TAG.length());
                    current_molecule = new MappedMolecule(id);
                }else if(line.contains(MappedMolecule.HISTOGRAM_TAG)){  //more complicated to handle NaN issues
                    double[] histogram = safeHistogramParse(line.substring(MappedMolecule.HISTOGRAM_TAG.length()).trim());
                    if(histogram != null){
                        current_molecule.setHistogram(histogram);
                    }
                }else if(line.contains(MappedMolecule.TYPED_ANGLES_START_TAG)){
                    typed_angle_line = true;
                }else if(line.contains(MappedMolecule.TYPED_ANGLES_END_TAG)){
                    current_molecule.setTypedAngles(typed_angles.toArray(new Typed_Angle[typed_angles.size()]));
                    typed_angles.clear();
                    typed_angle_line = false;
                }else if(typed_angle_line){
                    typed_angles.add(new Typed_Angle(line));
                }
            }

            bread.close();

            return list;

        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        }finally{
            try{
                if(bread != null){
                    bread.close();
                }
            }catch(IOException ex){
                Exceptions.printStackTrace(ex);
            }
        }
        return null;
    }
    
    private static double[] safeHistogramParse(String line){
        double[] histogram = null;
        if(line.contains("NaN")){
            System.err.println("Encountered a NaN");
        }else{
            String[] fields = line.split(",");
            histogram = new double[fields.length];
            for(int i = 0; i < histogram.length; i++){
                histogram[i] = Double.parseDouble(fields[i]);
            }
        }
        return histogram;
    }
    
    public static String getClassPath() {
        String rval = ClusterInMemory.class.getResource("ClusterInMemory.class").getPath();
        rval = rval.substring(rval.indexOf(":") + 1, rval.indexOf(".jar") + ".jar".length());
        return rval;
    }

}
