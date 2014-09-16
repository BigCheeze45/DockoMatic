package query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import kMeanCluster.ClusterCenter;
import kMeanCluster.ClusterManager;
import kMeanCluster.ClusterWorker;
import kMeanCluster.HistogramClusterCenter;
import map.FunctionalGroup;
import map.MappedMolecule;
import map.Mapper;
import map.Molecule;
import org.openide.util.Exceptions;

public class QueryDriver {

    private final static double SIM_THRESHOLD = .01;
    private final MappedMolecule query_target;
    private final String similarity_metric;
    private final boolean useFgroups;
    private final ArrayList<SimilarityScore> simScoresList ;


    //TODO alert user of the format.  3 lines to be ignored before the counts line
    public static Molecule createQueryMol(String sdf_file_name, int numBins, double stepSize, ArrayList<FunctionalGroup> fGroups) {
        Molecule molecule = null;
        BufferedReader reader = null;

        File sdf_file = new File(sdf_file_name);

        if (sdf_file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(sdf_file));
                Long cid = 0L;

                for (int i = 0; i <= Mapper.NUM_SKIP_LINES; i++) {
                    reader.readLine();
                }
                molecule = Mapper.parseMolecule(reader, cid);
                Mapper.prepareMolecule(molecule, numBins, stepSize, fGroups);

            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        return molecule;
    }

    public QueryDriver(Molecule query_molecule, boolean useFuncGroups, String distribution_test) {

        if (useFuncGroups) {
            throw new UnsupportedOperationException("Searching by functional groups is not yet supported.");
        } else {
            this.query_target = new MappedMolecule(query_molecule);
        }

        simScoresList = new ArrayList<SimilarityScore>();
        this.similarity_metric = distribution_test;
        this.useFgroups = useFuncGroups;
    }

    public void query(File search_folder) {

        File clusterMeta = new File(search_folder, ClusterManager.clusterInfo);
        BufferedReader reader = null;

        try {
            if (clusterMeta.exists()) {

                System.out.println("Scanning contents of " + clusterMeta + " file");

                reader = new BufferedReader(new FileReader(clusterMeta));
                ClusterCenter center;
                ArrayList<SimilarityScore> metaScores = new ArrayList<SimilarityScore>();
                String line;
                int clusterNum = 0;

                while ((line = reader.readLine()) != null) {
                    if (line.length() > 3) {  //account for empty lines
                        System.out.println("Determining cluster similarity with cluster " + clusterNum);

                        if (useFgroups) {

                        } else {
                            center = new HistogramClusterCenter(line);
                            metaScores.add(new SimilarityScore(clusterNum++, center.getDistanceFrom(query_target,similarity_metric)));
                        }
                    }
                }
                reader.close();
                Collections.sort(metaScores);  //is descending order due to SimilarityScore.compareTo()

                for (SimilarityScore score : metaScores) {
                    System.out.println(score);
                }

                double percDiff;
                for (int j = 0; j < clusterNum; j++) {
                    //search clusters within .05 of the top score
                    percDiff = (metaScores.get(j).getScore() - metaScores.get(0).getScore()) / Math.max(.0001, metaScores.get(0).getScore());
                    if (percDiff < SIM_THRESHOLD) {
                        query(new File(search_folder, "Cluster" + metaScores.get(j).getId()));
                    }
                }

            } else {
                String[] files = search_folder.list(new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                        return name.contains(Mapper.MAPPED_SUFFIX);
                    }

                });

                if (files != null) {
                    for (String file : files) {
                        ArrayList<MappedMolecule> mapped_molecules = ClusterWorker.parseMappedMolecules(new File(search_folder, file));

                        for (MappedMolecule mol : mapped_molecules) {
                            simScoresList.add(new SimilarityScore(mol.getID(), HistogramClusterCenter.calcDistTest(query_target.getHistogram(), mol.getHistogram(), similarity_metric)));
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            Exceptions.printStackTrace(e);
        } catch (IOException e) {
            Exceptions.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

    }
    
    public String getMostSimilar(int numMols){
        
        Collections.sort(simScoresList);
        numMols = Math.min(numMols, simScoresList.size());
        
        String rval = "";
        for(int i = 0; i < numMols; i++){
            rval += simScoresList.get(i) + "\n";
        }
           
        return rval;
    }

    private class SimilarityScore implements Comparable<SimilarityScore> {

        private final long cid;
        private final double score;

        public SimilarityScore(long CID, double score) {
            this.cid = CID;
            this.score = score;
        }

        public double getScore() {
            return score;
        }

        public long getId() {
            return cid;
        }

        //Opposite of what one might expect because we want Collections.sort to use descending order
        @Override
        public int compareTo(SimilarityScore arg0) {
            int result = 0;
            if (score - arg0.getScore() > 0) {
                result = 1;
            } else if (score - arg0.getScore() < 0) {
                result = -1;
            }
            return result;
        }

        @Override
        public String toString() {
            return cid + "\t" + score;
        }
    }
}
