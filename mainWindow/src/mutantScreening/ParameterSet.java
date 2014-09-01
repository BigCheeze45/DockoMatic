/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mutantScreening;

import static mutantScreening.mutantScreeningWizardAction.DELIM;

/**
 *
 * @author tlong
 */
public class ParameterSet {
    boolean exhaustiveScreen;
    int topX;
    int num_crossover_cycles;
    int num_mutation_cycles;
    int num_crossover_points;
    int cluster_size;
    int smooth_num;
    int dockCycles;
    double elitism;
    double mutation_rate;
    String origSiteAcids;
    int[] substitution_sites;
    String[] constraint_set;

    public ParameterSet(){
        smooth_num = 1;
    }

    //set origSiteAcids and mutation indices from a list of form A11,E13,...
    public void setSitesAndSeq(String acidANDindex){
        String[] tokens = acidANDindex.split(DELIM);
        origSiteAcids = "";
        substitution_sites = new int[tokens.length];
        for(int i = 0; i < tokens.length; i++){
            origSiteAcids += tokens[i].charAt(0);
            substitution_sites[i] = Integer.parseInt(tokens[i].substring(1)) - 1;
        }
    }

    /**
     * @return the exhaustiveScreen
     */
    public boolean isExhaustiveScreen() {
        return exhaustiveScreen;
    }

    /**
     * @return the topX
     */
    public int getTopX() {
        return topX;
    }

    /**
     * @return the num_crossover_cycles
     */
    public int getNum_crossover_cycles() {
        return num_crossover_cycles;
    }

    /**
     * @return the num_mutation_cycles
     */
    public int getNum_mutation_cycles() {
        return num_mutation_cycles;
    }

    /**
     * @return the num_crossover_points
     */
    public int getNum_crossover_points() {
        return num_crossover_points;
    }

    /**
     * @return the cluster_size
     */
    public int getCluster_size() {
        return cluster_size;
    }

    /**
     * @return the smooth_num
     */
    public int getSmooth_num() {
        return smooth_num;
    }

    /**
     * @return the dockCycles
     */
    public int getDockCycles() {
        return dockCycles;
    }

    /**
     * @return the elitism
     */
    public double getElitism() {
        return elitism;
    }

    /**
     * @return the mutation_rate
     */
    public double getMutation_rate() {
        return mutation_rate;
    }

    /**
     * @return the origSiteAcids
     */
    public String getOrigSiteAcids() {
        return origSiteAcids;
    }

    /**
     * @return the substitution_sites
     */
    public int[] getSubstitution_sites() {
        return substitution_sites;
    }

    /**
     * @return the constraint_set
     */
    public String[] getConstraint_set() {
        return constraint_set;
    }
    
}
