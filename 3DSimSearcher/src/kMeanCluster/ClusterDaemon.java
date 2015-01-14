package kMeanCluster;

import edu.boisestate.SimSearcher.SimSearchUtilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JTextArea;
import map.MappedMolecule;
import map.Mapper;
import org.openide.util.Exceptions;

/**
 * Aggregates data which is collected
 *
 * @author tlong
 */
/* 
 * It is very important that there are never empty clusters.  That is why initialize chooses histograms from the pool instead of 
 * generated random histograms.  Since a histogram is a center, it will always be in its own cluster.
 */
public class ClusterDaemon {

    public final static String SLEEP_TIME = "sleep_time";
    public final static String MAX_WAIT_CYCLES = "max_wait_cycles";
    public final static String MAX_RETRIES = "max_retries";
    public final static String MAX_WRITE_ATTEMPTS = "max_write_attempts";
    public final static String KMEANS_CYCLES = "kMeans_cyles";

    static HashMap<String, String> configurations;

    private File dataFolder;
    private final File projectFolder;
    private File outputFolder;
    private final int numProcesses;
    private final String similarity_metric;
    private final int[] numClusters; //clusters at each level.  So numClusters[0] = # of top level clusters, numClusters[1] = # of clusters within each top level cluster, ...
    private static boolean verbose;
    private static JTextArea text_area;
    private final static String LOG_PATH = "logFile.txt"; //TODO
    private static BufferedWriter logWriter;

    public final static String MAIN_FOLDER = ".project_folder";

    public ClusterDaemon(boolean verbosity, File dataFolder, File projectFolder, File outputFolder, int numProcesses, String sim_metric,
            int[] numClusters) {

        verbose = verbosity;
        this.dataFolder = dataFolder;
        this.projectFolder = projectFolder;
        try{
            this.logWriter = new BufferedWriter(new FileWriter(new File(projectFolder,LOG_PATH), true));
        }catch(IOException ex){
            Exceptions.printStackTrace(ex);
        }
        this.outputFolder = outputFolder;
        this.similarity_metric = sim_metric;
        this.numProcesses = numProcesses;
        this.numClusters = numClusters;
    }

    public void run() {
        if (cluster(0) && numClusters.length > 1) { //cluster the first level, big data style
            if (clusterLevelTwo(outputFolder)) {  //cluster the second level ... 
                if (numClusters.length > 2) {
                    clusterInMemory(outputFolder);  //anymore clustering can be done in memory...
                }
            }
        }
        printMessage(" ****\tClustering is complete\t****",true);
    }

    //evenly partition the load across all processors and then swarm it up
    private void clusterInMemory(File baseDir) {
        String[] folders = new String[numProcesses]; //create a set of folders for each process
        for (int i = 0; i < folders.length; i++) {
            folders[i] = "";
        }
        int perProc = (numClusters[0] * numClusters[1]) / numProcesses;
        int count = 0;

        //lots of ways to evenly partition files, we partition in chunks for file access speed (just a guess)
        for (int i = 0; i < numClusters[0]; i++) {
            for (int j = 0; j < numClusters[1]; j++) {
                folders[(count++ / perProc) % numProcesses] += ClusterManager.FILE_PREFIX + i + File.separator + ClusterManager.FILE_PREFIX + j + ",";
            }
        }

        try {
            //write and execute swarm commands
            File swarmFile = new File(projectFolder, "swarmCmds.txt");
            BufferedWriter swarmWriter = new BufferedWriter(new FileWriter(swarmFile));
            String class_path = ClusterInMemory.getClassPath();
            String prefix = SimSearchUtilities.getPreamble(class_path, "kMeanCluster.ClusterInMemory");
            prefix += (verbose ? " -v" : "") + " " + baseDir;
            //[-verbose] <folderPath> <folderName1,folderName2,folderName3,...,folderNameN> <dataFileIndicator> <# bins> <conv threshold> <k1,k2,kn>
            String suffix = Mapper.MAPPED_SUFFIX + " " + similarity_metric + " ";

            for (int i = 2; i < numClusters.length; i++) {
                suffix += numClusters[i] + ",";
            }

            for (int i = 0; i < numProcesses; i++) {
                swarmWriter.write(prefix + " " + folders[i] + " " + suffix + "\n");
            }

            swarmWriter.close();

            String swarmCmd = "swarm -f " + swarmFile.getAbsolutePath() + " -n " + numProcesses;

            printMessage("Daemon: Running swarm commands for In Memory Clustering",false);

            Process p = Runtime.getRuntime().exec(swarmCmd);
            p.waitFor();
            BufferedReader errReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;

            while ((line = errReader.readLine()) != null) {
                printMessage(line,true);
            }

            errReader.close();

        } catch (IOException ex) {
            printMessage(ex.getLocalizedMessage(),true);
        } catch (InterruptedException ex) {
            printMessage(ex.getLocalizedMessage(),true);
        }
    }

    /**
     *
     * @param parentDir the directory containing the clusters which will now
     * themselves be clustered
     * @return a list containing the indices of cluster folders which
     * experienced an error and did not complete
     */
    private boolean clusterLevelTwo(File parentDir) {
        boolean success = true;
        for (int i = 0; i < numClusters[0]; i++) {
            this.dataFolder = this.outputFolder = new File(parentDir, ClusterManager.FILE_PREFIX + i);  //cluster within each subcluster
            if (cluster(1)) {
                //success, delete all (_idx.dat) files in dataFolder
                File[] idxFiles = this.dataFolder.listFiles();
                for (int j = 0; j < idxFiles.length; j++) {
                    if (idxFiles[j].getName().contains(Mapper.MAPPED_SUFFIX)) {
                        if (!idxFiles[j].delete()) {
                            printMessage("Daemon: Failed to delete file " + idxFiles[j],false);
                        }
                    }
                }
            } else {
                success = false;
                printMessage("Daemon: Error clustering file " + ClusterManager.FILE_PREFIX + i,true);
            }
        }
        return success;
    }

    private boolean cluster(int depth) {
        boolean result = true;

        File clusterFolder = new File(projectFolder, ClusterManager.FILE_PREFIX);

        if (!clusterFolder.exists()) {
            if (!clusterFolder.mkdir()) {
                printMessage("Daemon: ERROR - Could not create folder " + clusterFolder,true);
                result = false;
            }
        }

        if (result) {
            printMessage("Daemon: Beginning work on " + dataFolder,true);

            File clusterFile = new File(clusterFolder, ClusterManager.clusterInfo);

            ClusterManager manager = new ClusterManager(dataFolder, projectFolder, outputFolder, numProcesses, similarity_metric, numClusters[depth]);

            printMessage("Daemon: Partitioning the data files.",false);

            String[] partitions = SimSearchUtilities.generatePartitions(dataFolder, Mapper.MAPPED_SUFFIX, numProcesses);

            if (partitions[0].isEmpty()) {
                printMessage("Daemon: No data files found at " + dataFolder + ".  Moving on to next cluster.",false);
            } else {

                manager.setPartitions(partitions);
                //initialize the centers with random molecules
                try {
                    printMessage("Daemon: Initializing " + numClusters[depth] + " clusters.",false);
                    
                    initializeCenters(clusterFile, depth);

                    manager.writeSwarm('w');
                    manager.runSwarm();

                    result = waitWithTimeout(clusterFolder, depth, manager);

                    while (result && manager.run()) {
                        result = waitWithTimeout(clusterFolder, depth, manager);
                    }

                    if (result) {
                        result = waitForOutput(depth);
                    }

                } catch (IOException ex) {
                    printMessage(ex.getLocalizedMessage(),true);
                    result = false;
                }
            }
        }

        return result;
    }

    private void initializeCenters(File clusterFile, int depth) throws IOException {
        String[] files = dataFolder.list(new ContainsFilter(Mapper.MAPPED_SUFFIX));
        ArrayList<ClusterCenter> centers = new ArrayList<ClusterCenter>();
        ArrayList<String> chooseFrom = new ArrayList<String>();
        Random rand = new Random();

        //randomly choose the files which will be chosen from
        for (int i = 0; i < numClusters[depth]; i++) {
            chooseFrom.add(files[rand.nextInt(files.length)]);
        }

        for (String file : chooseFrom) {
            ArrayList<MappedMolecule> temp = ClusterWorker.parseMappedMolecules(new File(dataFolder, file));
            ClusterCenter aCenter = new HistogramClusterCenter(temp.get(rand.nextInt(temp.size())).getHistogram());  
            centers.add(aCenter); //choose random element of the file
        }
        ClusterWorker.writeNewCenters(centers, clusterFile);
    }

    private boolean waitForPartialSums(File workDir, long sleepyTime) {
        boolean success = true;
        int maxCycles = Integer.parseInt(configurations.get(MAX_WAIT_CYCLES));
        int cycles = 0;

        try {
            printMessage("Daemon: Swarm jobs submitted, waiting for completion.",false);
            
            String[] files = null;

            do {
                printMessage("Daemon: Still waiting, " + (files == null ? 0 : files.length) + " out of " + numProcesses + " complete. Going to sleep for " + sleepyTime / 1000 + " seconds.",false);
                
                Thread.sleep(sleepyTime);
                files = workDir.list(new ContainsFilter("partialSum_"));
                if (++cycles == maxCycles && files != null && files.length < numProcesses) {  //only wait so long
                    success = false;
                }
            } while (files != null && files.length < numProcesses && success);

            //wait for the file writing to complete
            Thread.sleep(sleepyTime);

        } catch (InterruptedException ex) {
            printMessage(ex.getLocalizedMessage(),true);
            success = false;
        }
        printMessage("Daemon: Swarm jobs are complete, beginning aggregation step.",false);
        
        return success;
    }

    /**
     * Wrapper for the waitForPartialSums method which handles deleting old
     * partial_sum files and resubmitting the previous jobs in the case of
     * waitForPartialSums() being unsuccessful. Waiting time is reduced as the
     * set of molecules being clustered decreases (according to depth).
     *
     * @param workDir the working directory where partial sum files are stored
     * @param depth the depth of the cluster at this point
     * @param manager the ClusterManager object
     * @return whether the round of partial sums was successful
     */
    private boolean waitWithTimeout(File workDir, int depth, ClusterManager manager) {
        boolean success = true;
        int failedAttempts = 0;
        int max_retries = Integer.parseInt(configurations.get(MAX_RETRIES));
        long sleepyTime = Long.parseLong(configurations.get(SLEEP_TIME)) / (depth == 0 ? 1 : numClusters[0]);
        /*
         * assuming that the clusters are relatively equal in size,
         * the size of the data set being dealt with at each level is 1/product(numClusters[k]), for all k in (1,depth).
         * so we should really only wait for TimeATDepth0 / (clustersAtdepth0 * clustersAtDepth1 * ... clustersAtDepth<depth-1>)
         * yet as files get smaller we will have a higher overhead.  So we are going to be conservative
         */
        sleepyTime = Math.max(5000, sleepyTime);  //always wait at least 5 seconds between checks

        while (success && !waitForPartialSums(workDir, sleepyTime)) {  //failure to execute, resubmit the jobs
            if (++failedAttempts == max_retries) {
                System.err.println("Daemon: ERROR - waited " + max_retries + " rounds without success, stopping.");
                printMessage("Daemon: ERROR - waited " + max_retries + " rounds without success, stopping.",true);
                success = false;
            } else {
                manager.deletePartialSumFiles(); //remove partial sum files from ClusterFolder 
                manager.runSwarm();  //rerun swarm commands from last generation
            }
        }

        return success;
    }

    private boolean waitForOutput(int depth) {
        boolean result = true;

        try {
            int files_last_round;
            int attempts = 0;
            long sleepyTime = Long.parseLong(configurations.get(SLEEP_TIME)) * 3;
            int max_write_attempts = Integer.parseInt(configurations.get(MAX_WRITE_ATTEMPTS));
            sleepyTime /= (depth == 0 ? 1 : (this.numClusters[0] / 2));
            sleepyTime = Math.max(5000, sleepyTime);

            printMessage("Daemon: Waiting for files to be moved to cluster folders.",false);
            
            int totalFiles = 0;
            String[] files = null;

            //check nested cluster folders, there should be numPartition files in each one. 
            do {
                printMessage("Daemon: "+totalFiles + " out of " + numProcesses * numClusters[depth] + " files have been written. Going to sleep for " + sleepyTime / 1000 + " seconds.",false);
                
                Thread.sleep(sleepyTime);
                files_last_round = totalFiles;
                totalFiles = 0; //reset
                for (int i = 0; i < numClusters[depth]; i++) { //get total over all cluster folders
                    files = new File(outputFolder, ClusterManager.FILE_PREFIX + i).list(new ContainsFilter(ClusterWorker.CLUSTER_FILE_PREFIX));
                    totalFiles += (files == null ? 0 : files.length);
                }
                if (files_last_round == totalFiles) {  //stagnation
                    if (++attempts == max_write_attempts) {
                        printMessage("Daemon: ERROR when writing files, went " + max_write_attempts + " rounds before getting stuck.",true);
                        return false;
                    }
                } else {
                    attempts = 0;
                }
            } while (totalFiles < numProcesses * numClusters[depth]);

        } catch (InterruptedException ex) {
            printMessage(ex.getLocalizedMessage(),false);
            result = false;
        }
        
        printMessage("Daemon: All clusters have been created in " + outputFolder,true);
        
        return result;
    }

    public void setAdvOptions(HashMap<String,String> map) {
        configurations = new HashMap<String, String>();
        
        if(map != null){
            configurations.putAll(map);
        }
    }
    
    static void printMessage(String message, boolean ignoreVerbosity){
        if(verbose || ignoreVerbosity){
            if(text_area == null){
                System.out.println(message);
            }else{
                text_area.append("\n"+message);
            }
        }
        printToLog(message);
    }

    public void setTextArea(JTextArea textArea) {
        this.text_area = textArea;
    }
    
    private static void printToLog(String str){
        try{
            logWriter.write(str+"\n");
            logWriter.flush();
        }catch(IOException ex){
            Exceptions.printStackTrace(ex);
        }
    }
}
