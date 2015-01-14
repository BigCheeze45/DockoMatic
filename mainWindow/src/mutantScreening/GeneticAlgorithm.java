package mutantScreening;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class GeneticAlgorithm {

    public static int debug = 1;		//global variable for debug printing

    private GAutilities dockEngine;
    private double mutation_rate;   	  //the mutation rate
    private Random randNumGenerator;		//a random number generator

    private HashSet<String> tested_genotypes;	  //store all of the genotype that have been tested  

    private int population_limit;		  //The maximum number of Organisms to allow in a generation
    private int elite_carryover;          //the top elite_carryover organisms will be placed in the next population
    private int dockomatic_limit;		/*	The maximum number of jobs, for each generation, to submit 
     to Dockomatic for fitness evaluation */

    private int generations;
    private int counter;

    private final double DEFAULT_FITNESS = 100.00;
    private final int MAX_ATTEMPTS = 100000;		//might be a bad idea if dockomatic_limit is large

    private int[] sites;                    //the protein sites that we will be mutating at
    private String[] substitution_pools;              /*the list of (Single Letter) amino acids that we will be mutating with.
     /e.g. substitution_pools[i] is the set of amino acids that will be substituted at the site sites[i]*/

    private ArrayList<Organism> population;   	//the population of organisms
    private ArrayList<Organism> new_organisms; 	//to hold the next generations organisms
    private ArrayList<Organism> best_organisms; //to hold the best organisms from the last round, allows elitism * pop_limit < topX
    
    // Parse input values.  This allows us to run the script on the cluster without keeping the GUI or remote terminal open
    public static void main(String[] args) {
        
        if(args.length < 16){
            System.err.println("Usage: java GeneticAlgorithm <substitution sites> <substitution pools> <residue seq> <elitism> <mutation rate> <dock limit>"
                    + " <topX> <stagnant rounds> <# Xover points> <autodock cycles> <smoothNum> <output Directory> <ligFile> <recFile> <boxFile> <swarmCmd>");
            System.exit(1);
        }
        
        final String delim = ",";
        int arg_count = 0;
        
        //Parse substitution sites
        String[] temp = args[arg_count++].split(delim);
        int[] sites = new int[temp.length];
        
        for(int i = 0; i < temp.length; i++){
            sites[i] = Integer.parseInt(temp[i]);
        }
        
        //Parse substitution pools
        String[] subPools = args[arg_count++].split(delim);
        String origSeq = args[arg_count++];
        
        String[] origSeqWithIndex = new String[origSeq.length()];

        for(int i =0; i < origSeq.length(); i++){
                origSeqWithIndex[i] = origSeq.charAt(i) + "" + sites[i];
        }

        //set general Genectic Algorithm parameters
        double elitism = Double.parseDouble(args[arg_count++]);
        double mutation_rate = Double.parseDouble(args[arg_count++]);
        int dock_limit = Integer.parseInt(args[arg_count++]);
        Random rand = new Random();
        GeneticAlgorithm genAlg = new GeneticAlgorithm(elitism, mutation_rate, dock_limit, sites, subPools, rand, 1);
        
        //set GA parameters which pertain to the cycles
        int topX = Integer.parseInt(args[arg_count++]);
        int initSpan = Integer.parseInt(args[arg_count++]);
        int numCrossoverPoints = Integer.parseInt(args[arg_count++]);
        
        //set Docking Engines Parameters
        int autoDock_cycles = Integer.parseInt(args[arg_count++]);
        int smoothNumber = Integer.parseInt(args[arg_count++]);
        File outDir = new File(args[arg_count++]);
        String ligFile = args[arg_count++];
        String recFile = args[arg_count++];
        String boxFile = args[arg_count++];
        String swarmCmd = "";
        
        for(int i = arg_count; i < args.length; i++){
            swarmCmd += args[i] + " ";
        }

        genAlg.createDockEngine(autoDock_cycles, smoothNumber, outDir, ligFile, recFile, boxFile, swarmCmd, origSeqWithIndex);

        genAlg.restoreState();  //restore the GA's state in case of failed execution

        genAlg.initPopulation(null);

        int numJobs = genAlg.cycle(null, initSpan, topX, numCrossoverPoints);  //run with crossover until convergence

        System.out.println("\nDockoMatic's GA completed after " + genAlg.getGenerations() +" generations and " + numJobs +" AutoDock jobs.");
    }
    
    public static String getAbsExecPath() {
        String rval = GeneticAlgorithm.class.getResource("GeneticAlgorithm.class").getPath();
        rval = rval.substring(rval.indexOf(":") + 1, rval.indexOf(".jar") + ".jar".length());
        rval += " mutantScreening.GeneticAlgorithm";
        return rval;
    }

    public GeneticAlgorithm(double elitism_factor, double mutation_rate, int dockomatic_limit, int[] sites, String[] substitution_pools, Random rand, int debug) {

        if (sites.length != substitution_pools.length) {
            System.err.println("There must a substitution pool for every mutation site.  Aborting Genetic Algorithm ... ");
            System.exit(1);
        }

        this.mutation_rate = mutation_rate;
        this.population_limit = (int) (dockomatic_limit / (1 - elitism_factor));  //population_limit = dockomatic_limit + elite_carryover
        this.elite_carryover = population_limit - dockomatic_limit;
        this.dockomatic_limit = dockomatic_limit;
        this.sites = sites;
        this.substitution_pools = substitution_pools;
        this.randNumGenerator = rand;

        this.tested_genotypes = new HashSet<String>();
        this.population = new ArrayList<Organism>();
        this.new_organisms = new ArrayList<Organism>();
        this.best_organisms = new ArrayList<Organism>();
        this.generations = 0;
        this.debug = debug;
    }

    public String printBest(int numBest) {
        String result = "";

        if (dockEngine != null) {
            result = dockEngine.printBest(numBest);
        }
        return result;
    }

    public int getGenerations() {
        return this.generations;
    }

    private void add_randoms(Random random, int addCount) {
        int attempts = 0;

        Organism temp;

        while (addCount > 0 && attempts < MAX_ATTEMPTS) {

            temp = generateRandom(random);

            if (!tested_genotypes.contains(temp.getGenotype())) {
                tested_genotypes.add(temp.getGenotype());
                population.add(temp);
                addCount--;
            }

            attempts++;
        }

    }

    private Organism generateRandom(Random rand) {
        String mutant = "";

        for (String pool : substitution_pools) {
            mutant += pool.charAt(rand.nextInt(pool.length()));
        }

        return new Organism(mutant);
    }

    //fitness proportionate selection
    private void crossOver(int numPoints) {
        double[] cmf = generateCrossoverCMF();

        int kids2generate = dockomatic_limit - estimateOthers();
        int attempts = 0;

        Organism[] parents = new Organism[2];
        Organism[] kids;

        while (kids2generate > 0 && attempts < MAX_ATTEMPTS) {

            parents[0] = population.get(chooseParent(cmf, randNumGenerator));
            parents[1] = population.get(chooseParent(cmf, randNumGenerator));

            kids = parents[0].crossover(parents[1], numPoints, randNumGenerator);

            for (Organism org : kids) {
                if (!tested_genotypes.contains(org.getGenotype())) {
                    tested_genotypes.add(org.getGenotype());
                    new_organisms.add(org);
                    kids2generate--;
                }
            }

            attempts++;
        }

    }

    //estimate how many organisms will be added to the population from mutation and random generation
    private int estimateOthers() {

        int result = (int) (population_limit * .04);  //hard_code random % to .04 (PER experimental results)

		//probability of new mutant from old genotype = P(new) =  1 - ((1 - mutation_rate)^sites.length)
        //expected mutants is thus P(new) * Number of organisms to which mutate() is applied
        double p_newMutant = 1 - Math.pow(1 - mutation_rate, sites.length);

        result += (int) (p_newMutant * population_limit / 2.0);

        return result;
    }

    //take the min of the two in case user wants to use a tiny dock_limit
    private void carryover_elites() {
        for (int index = 0; index < Math.min(this.elite_carryover, population.size()); index++) {
            new_organisms.add(population.get(index));
        }
    }

    private void evaluateFitnessWithTable(HashMap<String, Double> fitnessTable) {

        String genotype;
        double fitness;

        for (Organism org : population) {
            genotype = org.getGenotype();

            fitness = fitnessTable.containsKey(genotype) ? fitnessTable.get(genotype) : DEFAULT_FITNESS;

            org.setFitness(fitness);
        }

    }

    public double calculateTotalScore() {
        double result = 0.0;

        for (Organism org : best_organisms) {
            result += org.getFitness();
        }
        return result;
    }

    //TODO double check for soundness of approach
    private double[] generateCrossoverCMF() {

		//first step : get the total fitness of all organisms in the population.  Only count those with negative energies 
        double totalFitness = 0.0;
        double temp;

        int index = 0;

        while (index < population.size() && (temp = population.get(index).getFitness()) < 0) {
            totalFitness += temp;
            index++;
        }

        //we now know that the first index organisms have a negative energy
        if (index < 1) {
            System.err.println("All energies are positive values.  Assumptions are wrong, please fix GeneticAlgorithm.generateCrossoverPMF()");
            System.exit(1);
        }

        double[] cmf = new double[index];

        cmf[0] = population.get(0).getFitness() / totalFitness;

        for (int i = 1; i < index; i++) {
            cmf[i] = cmf[i - 1] + (population.get(i).getFitness() / totalFitness);
        }

        return cmf;
    }

    //TODO ... restore the counter variable in cycle... restore generation count ...
    //generation count = number of jobs completed / dockomatic_limit
    //To assign a dock_X folder to a generation use:  generation = X / dockomatic_limit  (X in [0,dockomatic_limit))
    public void restoreState() {

        dockEngine.getPreviousResults(population, dockomatic_limit);

        dockEngine.syncUp(tested_genotypes);
    }

    private int chooseParent(double[] cmf, Random rand) {
        double randNum = rand.nextDouble();

        randNum = Math.min(cmf[cmf.length - 1], randNum);  //account for floating point arithmetic

        int index = 0;

        while (cmf[index] < randNum) {
            index++;
        }

        return index;
    }

    /**
     * Mutates the portion of the population in between index 0 (inclusive) and
     * index upToNum (exclusive)
     *
     * @param upToNum
     */
    private void mutateGroup(int upToNum) {
        String mutantGenotype;

        for (int i = 0; i < upToNum; i++) {
            mutantGenotype = population.get(i).mutate(randNumGenerator, mutation_rate, substitution_pools);

            if (!tested_genotypes.contains(mutantGenotype)) {
                population.add(new Organism(mutantGenotype));
                tested_genotypes.add(mutantGenotype);
            }
        }

    }

    public String populationToString() {
        String result = "";

        for (Organism org : population) {
            result += org.toString() + "\n";
        }

        return result;
    }

    public void createDockEngine(int cyc, int smoothNum, File outDir, String ligFile,
            String recFile, String boxFile, String swarmCmd, String[] origSeq) {
        this.dockEngine = new GAutilities(cyc, smoothNum, outDir, ligFile, recFile, boxFile, swarmCmd, origSeq);
    }

    public void initPopulation(HashMap<String, Double> fitnessTable) {
        if (population.size() < dockomatic_limit) {
            add_randoms(randNumGenerator, dockomatic_limit - population.size());
            if (fitnessTable == null) {
                dockEngine.getFitnessScores(createSubmissions());
            } else {
                evaluateFitnessWithTable(fitnessTable);
            }
        }

        Collections.sort(population);
    }

    public int cycle(HashMap<String, Double> fitnessTable, int span, int numTopOrganisms, int numPoints) {
        int numJobs = 0;
        span = Math.max(1, span);   //bounds check
        counter = 0;

        double totalScore = 0.0;
        double lastTotalScore = 0.0;

        //initialize the best scores to all dummies to reduce comparisons in the loop
        for (int i = 0; i < numTopOrganisms; i++) {
            best_organisms.add(new Organism("dummy", 0.0));
        }

        while (totalScore < lastTotalScore || counter < span) {

            if (debug > 0) {
                GAutilities.toLog(this.populationToString());
            }

            carryover_elites();

            if (numPoints > 0) {
                crossOver(numPoints);
            }

            population = new ArrayList<Organism>(new_organisms);
            this.new_organisms.clear();

            if (numPoints > 0) {
                mutateGroup(population.size() / 2);
                add_randoms(randNumGenerator, population_limit - population.size());  //TODO increase for genetic diversity ???

            } else {
                int attempts = 0;

                while (population.size() < population_limit && attempts++ < 1000) {
                    mutateGroup(population.size());
                }
            }

            if (fitnessTable == null) {
                dockEngine.getFitnessScores(createSubmissions());
            } else {
                evaluateFitnessWithTable(fitnessTable);
            }
            Collections.sort(population);

            //update best organisms
            updateBest(numTopOrganisms);

            /* Calculate stats for loop termination as well as user analysis */
            totalScore = calculateTotalScore();

            if (totalScore == lastTotalScore) {
                counter++;
            } else {
                lastTotalScore = totalScore;
                counter = 0;
            }

            numJobs += population.size() - elite_carryover;
            generations++;
        }
        return numJobs;
    }

    /*
     * Assumption: Population has already been sorted, best_organisms is not empty
     */
    private void updateBest(int numTopOrganisms) {
        int index = 0;

        while (index < population.size() && population.get(index).compareTo(best_organisms.get(best_organisms.size() - 1)) <= 0) {
            if (!best_organisms.contains(population.get(index))) {
                best_organisms.add(population.get(index));
                Collections.sort(best_organisms);
                if (best_organisms.size() > numTopOrganisms) {
                    best_organisms.remove(numTopOrganisms);
                }
            }
            index++;
        }
    }

    private ArrayList<Organism> createSubmissions() {
        ArrayList<Organism> newOrganisms = new ArrayList<Organism>();
        int pad = generations == 0 ? 0 : elite_carryover; //first round -> no precalculated elites

        for (int i = 0; i < dockomatic_limit; i++) { //ensure that no more than the specified jobs are submitted
            newOrganisms.add(population.get(i + pad));
        }
        return newOrganisms;
    }

}
