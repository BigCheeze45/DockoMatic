package kMeanCluster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import map.MappedMolecule;
import org.openide.util.Exceptions;


/**
 * Poorly written class
 * 
 * A lot of logic is duplicated between this class and the Cluster Manager/Daemon/worker.
 * 
 * Could definitely use a little abstraction to clean some stuff up.
 * @author tlong
 */
public class ClusterInMemory {

    private File outputFolder;
    private final int numClusters[];
    private final String similarity_metric;
    private final ArrayList<ClusterCenter> centers;
    private final ArrayList<MappedMolecule> space;
    private final boolean verbosity;

    public ClusterInMemory(File outputFile, int[] numClusters, String sim_metric, boolean verbose) {
        this.outputFolder = outputFile;
        this.numClusters = numClusters;
        this.similarity_metric = sim_metric;
        this.centers = new ArrayList<ClusterCenter>();
        this.space = new ArrayList<MappedMolecule>();
        this.verbosity = verbose;
    }

    public static void main(String[] args) {

        if (args.length < 6) {
            usage();
        }

        int num = 0;

        boolean verbose = false;
        if (args[0].contains("-v")) {
            verbose = true;
            num++;
        }

        File baseDirectory = new File(args[num++]);
        String[] folders = args[num++].split(",");
        String fileIndicator = args[num++];
        String sim_metric = args[num++];
        String[] clusterNumbers = args[num++].split(",");

        int[] numClusters = new int[clusterNumbers.length];
        for (int i = 0; i < clusterNumbers.length; i++) {
            numClusters[i] = Integer.parseInt(clusterNumbers[i]);
        }

        File[] files = new File[folders.length];
        for (int i = 0; i < files.length; i++) {
            files[i] = new File(baseDirectory, folders[i]);
        }

        for (File file : files) {
            ClusterInMemory worker = new ClusterInMemory(file, numClusters, sim_metric, verbose);
            worker.cluster(file, new ContainsFilter(fileIndicator), 0);
        }

        if (verbose) {
            System.out.println("Clustering is complete");
        }
    }

    private boolean cluster(File dataFolder, FilenameFilter filter, int depth) {

        boolean result = true;

        if (depth < numClusters.length) {

            if (verbosity) {
                System.out.println("Clustering folder " + dataFolder + " at depth " + depth);
            }

            this.outputFolder = dataFolder;
            this.centers.clear();
            this.space.clear();

            //make output cluster directories
            for (int i = 0; i < numClusters[depth]; i++) {
                new File(outputFolder, ClusterManager.FILE_PREFIX + i).mkdir();
            }

            if (populateSpace(dataFolder, filter)) {
                initializeCenters(depth);

                int num_cycles = Integer.parseInt(ClusterDaemon.configurations.get(ClusterDaemon.KMEANS_CYCLES));
                for(int i =0; i < num_cycles; i++){
                    run(false, depth);
                }

                //Write all clusters to file and then delete files from outer folder if successful
                if (run(true, depth)) {
                    removeDataFiles(dataFolder, filter);
                    for (int i = 0; i < numClusters[depth]; i++) {
                        cluster(new File(dataFolder, ClusterManager.FILE_PREFIX + i), filter, depth + 1);
                    }
                }
            } else {
                if (verbosity) {
                    System.out.println("No data found in " + dataFolder + ". Moving on");
                }
            }
        }

        return result;
    }

    private static void removeDataFiles(File dataFolder, FilenameFilter filter) {
        String[] files = dataFolder.list(filter);

        if (files != null) {
            for (int index = 0; index < files.length; index++) {  //for each data file in the directory
                new File(dataFolder, files[index]).delete();
            }
        }
    }

    private static void usage() {
        System.err.println("Usage: java ClusterInMemory [-verbose] <folderPath> <folderName1,folderName2,folderName3,...,folderNameN> <dataFileIndicator> <# bins> <conv threshold> <k1,k2,kn>");
        System.exit(1);
    }

    private boolean populateSpace(File directory, FilenameFilter filter) {

        if (verbosity) {
            System.out.println("Populating the universe");
        }
        boolean result = false;

        String[] files = directory.list(filter);

        if (files != null && files.length > 0) {
            for (String file : files) {  //for each data file in the directory
                if (verbosity) {
                    System.out.println("Populating the universe with Clusterables from " + file);
                }
                this.space.addAll(ClusterWorker.parseMappedMolecules(new File(directory, file)));
            }
            result = true;
        }
        return result && !space.isEmpty();
    }

    private void initializeCenters(int depth) {
        if (verbosity) {
            System.out.println("Initializing " + numClusters[depth] + " Clusters");
        }
        Random rand = new Random();

        //randomly choose the cluster centers from the space ... guarantee no empty clusters
        MappedMolecule temp;
        for (int i = 0; i < numClusters[depth]; i++) {
            temp = space.get(rand.nextInt(space.size()));
            ClusterCenter center = new HistogramClusterCenter(temp.getHistogram());
            this.centers.add(center);
        }
    }

    /**
     * Performs an iteration of K-means clustering with the entire search space
     * in memory
     *
     * @return true if the convergence criteria has not been met, false
     * otherwise
     */
    private boolean run(boolean finalRun, int depth) {
        if (verbosity) {
            System.out.println("Starting a clustering iteration");
        }
        boolean result = true;

        ArrayList<ClusterCenter> newCenters = null;
        ArrayList<ArrayList<MappedMolecule>> Clusters = null;

        if (finalRun) {
            Clusters = new ArrayList<ArrayList<MappedMolecule>>(); //Clusters.get(i) contains the histograms which are part of the cluster
            for (int i = 0; i < numClusters[depth]; i++) {
                Clusters.add(new ArrayList<MappedMolecule>());
            }
        } else {
            newCenters = new ArrayList<ClusterCenter>(); //newCenters.get(i) contains running totals for cluster i
            for (int i = 0; i < numClusters[depth]; i++) {
                newCenters.add(new HistogramClusterCenter());
            }
        }

        double minDistance, tempDistance;
        int closest = 0;

        if (verbosity) {
            System.out.println("Calculating the distance from the centers for each Clusterable");
        }
        for (MappedMolecule mol : space) {

            minDistance = Double.MAX_VALUE;

            for (int i = 0; i < centers.size(); i++) {
                tempDistance = centers.get(i).getDistanceFrom(mol, similarity_metric);
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    closest = i;
                }
            }

            if (finalRun) {
                Clusters.get(closest).add(mol);
            } else {
                newCenters.get(closest).updateTotalWith(mol);
            }
        }

        if (finalRun) {
            if (verbosity) {
                System.out.println("Writing clustered data to output folder");
            }
            try {
                ClusterWorker.writeClusters(Clusters, outputFolder, 0);
                writeMetaFile(centers, new File(outputFolder, ClusterManager.clusterInfo));
            } catch (IOException e) {
                result = false;
                Exceptions.printStackTrace(e);
            }
        } else {
            if (verbosity) {
                System.out.println("Calculating new centers");
            }
            //calculate new centers
            for (int j = 0; j < numClusters[depth]; j++) {
                newCenters.get(j).averageValues();
            }
            
            //replace old centers with new ones
            for (int i = 0; i < numClusters[depth]; i++) {
                centers.set(i, newCenters.get(i));
            }
        }

        return result;  //return answer to 'Do we need to iterate again to convergence"
    }

    private static void writeMetaFile(ArrayList<ClusterCenter> newCenters, File writeFile) throws IOException {
        String toPrint = "";

        for (ClusterCenter center : newCenters) {
            toPrint += center + "\n";
        }

        BufferedWriter bwrite = new BufferedWriter(new FileWriter(writeFile));
        bwrite.write(toPrint);
        bwrite.flush();
        bwrite.close();
    }

    public static String getClassPath() {
        String rval = ClusterInMemory.class.getResource("ClusterInMemory.class").getPath();
        rval = rval.substring(rval.indexOf(":") + 1, rval.indexOf(".jar") + ".jar".length());
        return rval;
    }

}
