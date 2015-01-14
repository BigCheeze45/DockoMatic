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
    private final String similarity_metric;
    private final boolean useFgroups;
    private final int numMostSimilar;


    //TODO alert user of the format.  3 lines to be ignored before the counts line
    public static Molecule createQueryMol(String sdf_file_name, int numBins, double stepSize, ArrayList<FunctionalGroup> fGroups) {
        Molecule molecule = null;
        BufferedReader reader = null;

        File sdf_file = new File(sdf_file_name);

        if (sdf_file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(sdf_file));
                String id = reader.readLine().trim();

                for (int i = 0; i < Mapper.NUM_SKIP_LINES; i++) {
                    reader.readLine();
                }
                molecule = Mapper.parseMolecule(reader, id);
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

    public QueryDriver(boolean useFuncGroups, String distribution_test, int numTops) {
        this.similarity_metric = distribution_test;
        this.useFgroups = useFuncGroups;
        this.numMostSimilar = numTops;
    }
    
    public String query(File search_folder, Molecule queryMol){
        String rval = "";
        MappedMolecule query_target = new MappedMolecule(queryMol);
        
        ArrayList<SimilarityScore> result = search(search_folder, query_target);
        
        for(SimilarityScore score : result){
            rval += score.toString() + "\n";
        }
        
        return rval;
    }

    private ArrayList<SimilarityScore> search(File search_folder, MappedMolecule query_target) {

        File clusterMeta = new File(search_folder, ClusterManager.clusterInfo);
        ArrayList<SimilarityScore> result = new ArrayList<SimilarityScore>();
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
                            metaScores.add(new SimilarityScore((clusterNum++) + "", center.getDistanceFrom(query_target,similarity_metric)));
                        }
                    }
                }
                reader.close();
                Collections.sort(metaScores);  //is descending order due to SimilarityScore.compareTo()

                double percDiff;
                for (int j = 0; j < clusterNum; j++) {
                    //search clusters within .05 of the top score
                    percDiff = (metaScores.get(j).getScore() - metaScores.get(0).getScore()) / Math.max(.0001, metaScores.get(0).getScore());
                    if (percDiff < SIM_THRESHOLD) {
                        result = mergeLists(result,search(new File(search_folder, "Cluster" + metaScores.get(j).ID),query_target));
                    }
                }

            } else {
                result = mergeLists(result, search_leaf_cluster(search_folder, query_target));
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
        return result;

    }
    
    private ArrayList<SimilarityScore> search_leaf_cluster(File search_folder, MappedMolecule query_target){
        ArrayList<SimilarityScore> simScoresList = null;
        
        String[] files = search_folder.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.contains(Mapper.MAPPED_SUFFIX);
            }

        });

        if (files != null) {
            
            simScoresList = new ArrayList<SimilarityScore>();
            
            for (String file : files) {
                ArrayList<MappedMolecule> mapped_molecules = ClusterWorker.parseMappedMolecules(new File(search_folder, file));

                for (MappedMolecule mol : mapped_molecules) {
                    SimilarityScore score = new SimilarityScore(mol.getID(), HistogramClusterCenter.calcDistTest(query_target.getHistogram(), mol.getHistogram(), similarity_metric));
                    addToList(score,simScoresList,numMostSimilar);
                } 
            }   
        }
        
        return simScoresList;
    }
    
    private ArrayList<SimilarityScore> mergeLists(ArrayList<SimilarityScore> list1, ArrayList<SimilarityScore> list2){
        ArrayList<SimilarityScore> result;
        
        if(list2 == null){
            result = list1;
        }else{
            result = new ArrayList<SimilarityScore>(list2.size());
            
            int index1 = 0;
            int index2 = 0;
            
            while(result.size() < list2.size()){
                if(list1.size() > index1 && list1.get(index1).compareTo(list2.get(index2)) < 0){  //list 1's value is lower
                    result.add(list1.get(index1++));
                }else{  //list 2 wins ties
                    result.add(list2.get(index2++));
                }
            }    
        }
        
        return result;
    }
    
    
    private void addToList(SimilarityScore simScore, ArrayList<SimilarityScore> list, int capacity){
        if(list.size() == capacity){
            if(simScore.score < list.get(capacity-1).score){
                list.add(simScore);
                Collections.sort(list);
                list.remove(capacity);
            }
        }else{
            list.add(simScore);
            if(list.size() == capacity){
                Collections.sort(list);
            }
        }
    }

    private class SimilarityScore implements Comparable<SimilarityScore> {

        final String ID;
        final double score;

        public SimilarityScore(String ID, double score) {
            this.ID = ID;
            this.score = score;
        }

        public double getScore() {
            return score;
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
            return ID + "\t" + score;
        }
    }
}
