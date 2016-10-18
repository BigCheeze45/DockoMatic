package kMeanCluster;

import edu.boisestate.SimSearcher.SimSearchUtilities;
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

public class ClusterManager {

    public static final String clusterInfo = "ClusterMeta.txt";
    public static final String FILE_PREFIX = "Cluster";
    private final File dataFolder;
    private final File outputFolder;
    private final File mainFolder;
    private final File clusterFolder;
    private final int numProcesses;
    private final String distribution_test;
    private final int numClusters;
    private String[] partitions;
    private int cycleCount;

    public ClusterManager(File database, File mainFolder, File output, int numProcesses, String distribution_test, int numClusters) {
        this.dataFolder = database;
        this.outputFolder = output;
        
        /*Make output folders and nested folders*/
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
        for (int i = 0; i < numClusters; i++) {
            new File(outputFolder, FILE_PREFIX + i).mkdir();
        }

        this.mainFolder = mainFolder;
        this.clusterFolder = new File(mainFolder, FILE_PREFIX);
        this.numProcesses = numProcesses;
        this.distribution_test = distribution_test;
        this.numClusters = numClusters;
        cycleCount = 0;
    }
    
    protected void setPartitions(String[] parts){
        this.partitions = parts;
    }

    protected boolean run() {
        
        int total_cycles= Integer.parseInt(ClusterDaemon.configurations.get(ClusterDaemon.KMEANS_CYCLES));

        File clusterMetaFile = new File(clusterFolder, clusterInfo);

        char option = 'w';

        try {

            ArrayList<ClusterCenter> newCenters = new ArrayList<ClusterCenter>();

            for (int i = 0; i < numClusters; i++) {
                newCenters.add(new HistogramClusterCenter());
            }

            ClusterDaemon.printMessage("Manager: Reading cluster centers.",false);

            BufferedReader partReader;
            String line;

            String[] partialSums = clusterFolder.list(new ContainsFilter("partialSum_"));

            if (partialSums != null) {
                int index = 0;
                while (index < partialSums.length) {
                    partReader = new BufferedReader(new FileReader(new File(clusterFolder, partialSums[index])));

                    ClusterDaemon.printMessage("Manager: Reading partial sum file: " + partialSums[index],false);

                    line = partReader.readLine();
                    if (line == null) {
                        ClusterDaemon.printMessage("Manager: partial sum file " + index + " is not populated, sleeping for 1 second.",false);
                        
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            ClusterDaemon.printMessage(e.getLocalizedMessage(),true);
                        }
                    } else {
                        index++;  //increment to next file
                        newCenters.get(0).mergeWith(new HistogramClusterCenter(line));
                        for (int j = 1; j < numClusters; j++) {
                            newCenters.get(j).mergeWith(new HistogramClusterCenter(partReader.readLine()));
                        }
                    }

                    partReader.close();
                }

                deletePartialSumFiles();

                ClusterDaemon.printMessage("Manager: Finished reading all partial sum files, determining new centers.",false);

                //take average value as new centers
                for (int j = 0; j < numClusters; j++) {
                    newCenters.get(j).averageValues();
                }

                ClusterDaemon.printMessage("Manager: Writing new centers to file " + clusterMetaFile,false);

                /*Write new centers to the file*/
                ClusterWorker.writeNewCenters(newCenters, clusterMetaFile);

                if (++cycleCount == total_cycles) {
                    option = 'c';
                    ClusterWorker.writeNewCenters(newCenters, new File(outputFolder, clusterInfo));
                }

                ClusterDaemon.printMessage("Manager: Finished Round " + cycleCount,false);

            } else {
                ClusterDaemon.printMessage("Manager: No partition files were found",false);
            }

            writeSwarm(option);
            runSwarm();

        } catch (FileNotFoundException e) {
            ClusterDaemon.printMessage(e.getLocalizedMessage(),true);
        } catch (IOException e) {
            ClusterDaemon.printMessage(e.getLocalizedMessage(),true);
        }

        return option == 'w';
    }

    protected void deletePartialSumFiles() {

        String[] partialSums = clusterFolder.list(new ContainsFilter("partialSum_"));

        for (String partial_sum : partialSums) {
            if (!(new File(clusterFolder, partial_sum).delete())) {
                ClusterDaemon.printMessage("Manager: Failed to delete " + partial_sum,false);
            }
        }

    }

    protected File writeSwarm(char option) throws IOException {

        File swarmFile = new File(clusterFolder, "swarmCmds.txt");
        BufferedWriter swarmWriter = new BufferedWriter(new FileWriter(swarmFile));
        String class_path = ClusterWorker.getClassPath();
        String template = SimSearchUtilities.getPreamble(class_path, "kMeanCluster.ClusterWorker");
        template += dataFolder + " " + mainFolder + " " + outputFolder + " " + option + " " + numClusters + " " + distribution_test;

        for (int i = 0; i < numProcesses; i++) {
            swarmWriter.write(template + " " + i + " " +partitions[i] + " -v\n");
        }

        swarmWriter.close();

        return swarmFile;
    }

    protected void runSwarm() {
        File swarmFile = new File(clusterFolder, "swarmCmds.txt");

        String swarmCmd = "swarm -f " + swarmFile.getAbsolutePath() + " -n " + numProcesses;
        try {

            ClusterDaemon.printMessage("Manager: Running swarm command: " + swarmCmd,false);

            Process p = Runtime.getRuntime().exec(swarmCmd);
            p.waitFor();
            BufferedReader errReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;

            while ((line = errReader.readLine()) != null) {
                ClusterDaemon.printMessage(line,true);
            }

            errReader.close();

        } catch (IOException e1) {
            ClusterDaemon.printMessage(e1.getLocalizedMessage(),true);
        } catch (InterruptedException e) {
            ClusterDaemon.printMessage(e.getLocalizedMessage(),true);
        }
    }

    public int getCycleCount() {
        return this.cycleCount;
    }

}

class ContainsFilter implements FilenameFilter {

    String base;

    public ContainsFilter(String base) {
        this.base = base;
    }

    @Override
    public boolean accept(File arg0, String name) {
        return name.contains(base);
    }
}