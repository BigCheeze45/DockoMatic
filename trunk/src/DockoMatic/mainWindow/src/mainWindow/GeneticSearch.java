package mainWindow;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Design ideas: For each site, there will be a separate PMF table. This will increase memory usage but also performance since we will not have to derive the information
 * for each mutation.
 * 
 * 
 * 
 * Notes:
 * Need to ensure that the original amino acid from a site (the one from the sequence) is always in the substitution pool for that site
 * Fix updateTrack()  [DONE]
 * Update fitness function to include job creation/submission and analysis of results
 * Look into population growth... is it really stopping at MAX_POPULATION
 *  
 * @author Thomas Long
 */
public class GeneticSearch {

private final double ELITE_NUM = .2;        //the top ELITE_NUM percent of a generation will be carried over between generations
    private final double RAND_NUM = .2;          // RAND_NUM percent of a generation will move to next generation (by chance)

    private final int MAX_POPULATION = 500;  //Maximum population
    private final int MAX_GENERATION = 100; //Generations to run
    private final double mutation_rate = .05;  //rate at which mutation occurs
    
    
    private int numElites;  // equals original population size * ELITE_NUM
    private int numRandoms; //equals original population size * RAND_NUM
    
    private int[] sites;                    //the sites that we will be mutating at
    private String[] subPools;              //the list of amino acids that we will be mutating with.
                                    //subPools[i] is the set of amino acids that will be substituted at the site sites[i]
    
    /*The following table in known as PAM-1. And entry mutation[i][j] represents the historical probability of acid i mutating into acid j. The ACIDS string contains the 
     * label for vertical and horizontal axis'. Therefore, the diagonal represents the historical probability of an acid never changing.*/
    private final String ACIDS = "ARNDCQEGHILKMFPSTWYV";
    private double[][] mutationPMF = {  {0.989,0.0005,0.0005,0.0006,0.0012,0.0009,0.0011,0.0012,0.0005,0.0002,0.0005,0.0006,0.0009,0.0002,0.001,0.0029,0.0014,0.0001,0.0002,0.0017},
                                		{0.0004,0.9907,0.0005,0.0002,0.0002,0.0016,0.0004,0.0003,0.0008,0.0001,0.0002,0.003,0.0002,0,0.0003,0.0005,0.0005,0.0004,0.0003,0.0002},
		                                {0.0003,0.0004,0.9888,0.0018,0.0002,0.0008,0.0005,0.0006,0.0013,0.0001,0.0001,0.001,0.0001,0.0001,0.0002,0.0013,0.0008,0.0001,0.0003,0.0001},
		                                {0.0004,0.0002,0.0021,0.9905,0,0.0007,0.0028,0.0005,0.0006,0,0,0.0005,0,0,0.0003,0.0007,0.0005,0,0.0001,0},
		                                {0.0003,0.0001,0.0001,0,0.9946,0,0,0.0001,0.0001,0.0001,0.0001,0,0.0001,0.0001,0,0.0003,0.0001,0.0001,0.0001,0.0002},
		                                {0.0004,0.0011,0.0007,0.0005,0.0001,0.9856,0.0018,0.0002,0.0014,0.0001,0.0003,0.0014,0.0006,0.0001,0.0004,0.0005,0.0005,0.0001,0.0001,0.0002},
		                                {0.0008,0.0005,0.0006,0.003,0,0.0028,0.989,0.0002,0.0007,0.0001,0.0001,0.0015,0.0003,0,0.0004,0.0007,0.0005,0.0001,0.0001,0.0003},
		                                {0.0011,0.0004,0.0009,0.0007,0.0002,0.0004,0.0003,0.9952,0.0003,0,0.0001,0.0003,0.0001,0,0.0002,0.001,0.0002,0.0002,0.0001,0.0001},
		                                {0.0001,0.0004,0.0007,0.0003,0.0001,0.0009,0.0003,0.0001,0.9895,0.0001,0.0001,0.0003,0.0002,0.0002,0.0001,0.0002,0.0002,0.0001,0.0009,0.0001},
		                                {0.0002,0.0001,0.0002,0,0.0002,0.0002,0.0001,0,0.0002,0.9878,0.0022,0.0002,0.0026,0.0007,0.0001,0.0001,0.0005,0.0002,0.0002,0.0042},
		                                {0.0005,0.0004,0.0002,0,0.0003,0.0008,0.0002,0.0001,0.0003,0.0035,0.9919,0.0003,0.0048,0.0022,0.0004,0.0003,0.0004,0.0005,0.0005,0.0019},
		                                {0.0005,0.0033,0.0013,0.0005,0,0.0022,0.0015,0.0002,0.0008,0.0002,0.0002,0.9883,0.0005,0.0001,0.0004,0.0006,0.0009,0.0001,0.0002,0.0002},
		                                {0.0003,0.0001,0.0001,0,0.0002,0.0004,0.0001,0,0.0002,0.001,0.0012,0.0002,0.9859,0.0005,0,0.0002,0.0003,0.0001,0.0001,0.0004},
		                                {0.0001,0,0.0001,0,0.0003,0.0001,0,0,0.0004,0.0005,0.001,0,0.0009,0.9923,0,0.0001,0.0001,0.001,0.0028,0.0003},
		                                {0.0006,0.0002,0.0002,0.0002,0,0.0005,0.0003,0.0001,0.0002,0.0001,0.0002,0.0003,0,0.0001,0.9943,0.0006,0.0005,0,0.0001,0.0002},
		                                {0.0023,0.0005,0.0017,0.0008,0.0009,0.0009,0.0007,0.0008,0.0006,0.0001,0.0002,0.0007,0.0004,0.0001,0.0008,0.9862,0.0032,0.0002,0.0004,0.0002},
		                                {0.0011,0.0005,0.0011,0.0006,0.0004,0.0008,0.0005,0.0002,0.0007,0.0006,0.0002,0.0009,0.0007,0.0002,0.0007,0.0033,0.9879,0.0001,0.0002,0.0012},
		                                {0,0.0001,0,0,0.0001,0.0001,0,0,0.0001,0,0.0001,0,0.0001,0.0003,0,0,0,0.9956,0.0004,0},
		                                {0.0001,0.0002,0.0002,0.0001,0.0003,0.0001,0.0001,0,0.0013,0.0001,0.0002,0.0001,0.0002,0.0022,0.0001,0.0002,0.0001,0.001,0.9924,0.0002},
		                                {0.0015,0.0002,0.0001,0,0.0008,0.0003,0.0004,0.0001,0.0002,0.0051,0.0014,0.0003,0.0012,0.0005,0.0002,0.0003,0.0014,0.0001,0.0004,0.9884}  };

	private ArrayList<Chromosome> population;   //the population of organisms
	private Stack<Tracker> trackStack;			//data structure for temporarily storing tracking mutation performance information
	private TrackTable trackTable;				//data structure for storing tracking mutation performance
	private String pdbPATH;                 	//path to .pdb file of ligand, needed for dockomatic jobs
	private double[][][] sitePMFs;				//the mutationPMFs for each site
	private String[] ACID_sites;				//The axis labels for the site PMFs
	private String origSeq;						//The original acids at each substitution site.
	private char tracking;               	    // D = detail, S = summary, P = percentages, N = none
	

	/**
	 * 
	 * @param sites the indices of the substitution sites in the protein
	 * @param origSeq the amino acids in the substitution sites of the base protein
	 * @param subPools  subPools[i] contains the substitution pool for sites[i]
	 * @param PDBpath the path to the base ligand file
	 * @param popSize the initial population size
	 * @param tracking D = detail, S = summary, P = percentages, N = none
	 */
	public GeneticSearch(int[] sites, String origSeq,String[] subPools, String PDBpath, char tracking)
	{   
		if(sites.length == subPools.length && origSeq.length() == sites.length && sites.length > 2)
        {
            this.sites = sites;
            this.subPools = subPools;
            this.pdbPATH = PDBpath;
            this.origSeq = origSeq;
            this.population = new ArrayList<Chromosome>();
            
            
            //detail tracking is too huge (it works but won't print), I am disabling for now
            if(tracking != 'D')
            	this.tracking = tracking;
            else
            	this.tracking = 'S';
            
            
            this.ACID_sites = subPools;  //this is a fortunate consequence of how createPMFS() creates the sub table
            
            numElites = (int) (MAX_POPULATION*ELITE_NUM);       
            numRandoms = (int) (MAX_POPULATION*RAND_NUM);         
            
            if(tracking != 'N')
            {
            	trackTable = new TrackTable(mutationPMF.length,mutationPMF.length,tracking);
            	trackStack = new Stack<Tracker>();
            }
            
            createPMFS();
            //initialize(popSize);
            
        }
        
        else
        {
            //ERROR, invalid input.  THe number of sites should equal the number of pools
        	//Do exhaustive search if we are only looking at 2 or fewer sites
            //DO NOT CONTINUE TO RUN THE PROGRAM
        }
		
	}
	
	/**
	 * 
	 */
	public void run()
	{

		/* Local variables */
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		int generations = 0;

		
		/*
		 * Because I am adding mutants
		 */
		while(generations < MAX_GENERATION) //DO I WANT THIS KIND OF A CONDITION?  SHOULD IT BE A ENERGY NUMBER ???
		{
				evaluateFitness(); //dockomatic
				if(tracking != 'N')
					updateTrack();
				Collections.sort(population);
				crossOver(1,newPopulation);
				getMostFit(numElites, newPopulation); //sort population right now (implemented comparable), then grab top ELITE_NUM
				getRandom(numRandoms, newPopulation);
				population = copy(newPopulation); //NEED TO MAKE A DEEP COPY ????
				newPopulation.clear();
				mutate();
				generations++;
		}
		
		/*  //Your results for testing
		//now evaluate your new population
		evaluateFitness();
		//print results  (test purposes only)
		Collections.sort(population);
		System.out.println("Final population : ");
		for(int i = 0; i < population.size(); i++)
			System.out.println(population.get(i));
		
		System.out.println("\n\n\n\n\n Tracking structure: " + trackTable); */
		
		

	}
	
	
	
	
	//code for autodock incorporation.  Similar to run()
	/**
	 * The vision: Autodock will create a GenAlgSrch object and then LOOP. In this loop we will invoke generate()
	 * and add the results to ligList.  It will generate and start jobs with these ligands using the 
	 * standard dockomatic process.  When all the jobs are finished, there will be a method added to
	 * DockoMatic to get the lowest binding energy of each job/mutant and insert that energy into an
	 * array.  Note that the order of the array should be the same as the ordering of the array returned
	 * by generate().  The method will return this array, which will be passed as an argument into the
	 * updateFitness() method of this class.
	 * 
	 * 
	 * 
	 * @return a list of String representations of ligands to be used to create jobs
	 */
	public ArrayList<String> generate()
	{
        if(!population.isEmpty())
        {
            ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();

            if(tracking != 'N')
                updateTrack();
            Collections.sort(population);
            crossOver(1,newPopulation);
            getMostFit(numElites, newPopulation);
            getRandom(numRandoms, newPopulation);
            population = newPopulation;
            mutate();
        }
        else
            initialize(MAX_POPULATION);
    
		
		return popToString();
	}
	
	//Updates the fitness of the population, assumes ordering is the same
	
	//NEED TO EXPLICITLY MAKE THE ORDER THE SAME (dock_x -> energies[x] or else there might be issues with dock_10 < dock_2) DONE
	
	public void updateFitness(double[] energies)
	{
		if(energies.length == population.size())
		{
			for(int i = 0; i < energies.length; i ++)
				population.get(i).setEnergy(energies[i]);
		}
		else
		{
			//ERROR, things are not working like we had hoped
		}
		
	}
	
	
    
    /** Creates the original population of random organisms
     * 
     * @param popSize the number of organisms in the original population 
     */
    private void initialize(int popSize)
    {
        Random randGenerator = new Random();
        String organism;
        for(int i = 0; i < popSize; i++)
        {
            organism = "";

            /*Create a new string to represent the acid substitutions for the mutant (Chromosome)
             To create this string, use the substitution pool for each site. This is implemented as
             subPools[j] = the set of acids to substitute at site j.  Pick one random acid for each
             site and then create a create a chromosome with that string.*/
            for(int k = 0; k < subPools.length; k++)
                organism += subPools[k].charAt(randGenerator.nextInt(subPools[k].length()));
             
            population.add(new Chromosome(organism));
            
        }
    }
    
    /**
     * This program will invoke mutation on every iteration and on every site. However, most of the 
     * time the mutation will be trivial since an acid will be replaced with itself.
     * If mutation rate is X, then the diagonal should be a vector of (1 - X)'s. That way a non-trivial
     * mutation occurs with frequency that is dependent on the mutation rate.
     * 
     */
    private void createPMFS()
    {
    	sitePMFs = new double[sites.length][][];
    	for(int j = 0; j < sites.length; j++)
    	{
    		sitePMFs[j] = createRow(subPools[j]);
    	}
    }
    
  /**
   * 
   * @param pool
   * @return
   */
    private double[][] createRow(String pool)
    {
    	double[][] table = new double[pool.length()][pool.length()];
    	
    	for(int i = 0; i < pool.length(); i++)
    	{
    		for(int j = 0; j < pool.length(); j++)
    			table[i][j] = mutationPMF[getAcidIndex(pool.charAt(i))][getAcidIndex(pool.charAt(j))];
    		
    		table[i] = normalize(table[i],i);
    	
    	}
    	
    	return table;
    }
    
    /**
     * This method changes the row so that the probability of a trivial switch ( X for X) is
     * 1 - mutation rate and the probability of a non-trivial switch is mutation_rate.  Relative probability
     * for non-trivial switches is maintained
     * @param row
     * @param rowNum
     * @return
     */
    private double[] normalize(double[] row, int rowNum)
    {
    	double[] normTable = new double[row.length];
    	double sum = 0;
    	
    	for(int i = 0; i < row.length; i++)
    	{
    		sum += (i == rowNum ? 0 :row[i]);  //sum of non-diagonals
    	}
    	for(int i = 0; i < row.length; i++)
    	{
    		if(rowNum == i) //on diagonal
    			normTable[i] = 1.0 - mutation_rate;
    		else
    			normTable[i] = mutation_rate*row[i]/sum;
    	}

    	return normTable;
    }
    
    
    /**
     * elitism
     * @param quantity the number of most fit individuals to add to the next generation
     * @param list the data structure that will hold the organisms of the next generation
     */
    private void getMostFit(int quantity, Collection<Chromosome> list)
    {
    	if(quantity > population.size())
    	{
    		//ERROR, need to handle this
    	}
	
    	//SORTING DONE ABOVE
    	//Collections.sort(population); //sorted into ascending order based on binding energy
    	//best = lowest energy
    	for(int i = 0; i < quantity; i++)
    		list.add(population.get(i));
    }
    /**
     * This is used to preserve genetic diversity.
     * @param quantity the number of random individuals to add to the next generation
     * @param list the data structure that will hold the organisms of the next generation
     */
    private void getRandom(int quantity, ArrayList<Chromosome> list)
    {
    	Random rand = new Random();
    	int count = 0;
    	Chromosome tmpStore;
    	
    	while(count < quantity)
    	{
    		tmpStore = population.get(rand.nextInt(population.size()));
    		
    		if(!list.contains(tmpStore))
    		{
    			list.add(tmpStore);
    			count++;
    		}
    	}
    }
    
    /*
	 * Preserve the original population size.  Thus, I want to factor in those that I am keeping
	 * from Elitism (numElites) and Random carry over (numRandoms).  Also, mutants are going to be added later
	 * so I want to allow for these numbers (mutation_rate).
	 * 
	 * Can I maybe guide the carryover process instead of choosing random points?  Maybe I can generate some
	 * random points (between 0 and sites.length() ) and then try to estimate the fitness of each subsection
	 * so that I breed solutions that have different advantageous subsections?
	 * 
	 * The theory is thus: If I want a superhuman and I can take the best traits of people and combine them,
	 * I do not want to combine two people whose virtues overlap because the result would only be as good as either one.
	 * If I bred two people with diverse virtues then their offspring would (presumably) be more fit than either.
	 * 
	 * To determine the fitness of a section I could
	 * 
	 * Because the fitness evaluation (dockomatic and autodock) is so computationally expensive and is the bottleneck of 
	 * this process, we would like to minimize the use of the fitness function.  Perhaps we can do this by instead creating
	 * a diversity() function that determines the fitness of sections and breeds chromosomes based on the difference between their
	 * good sections.
	 * 
	 * 
	 * This might be done by:
	 * 		look at a subsection (using the random points generated)
	 * 		the fitness of each subsection is the sum of the fitness of all chromosomes with a 'similar' subsection
	 * 		breed chromosomes with a high fitness on different sections.
	 */
    
    
    //Make sure that this is never called with less than 1 sites or it will loop
    
    //Might want to check and see if I want to enforce monogamy or poligamy (spelling?) or what?
    private void crossOver(int numPoints, ArrayList<Chromosome> list)
    {
    	Random rand = new Random();
    	int index_1 = 0, index_2 = 0;
    	
    	if(sites.length <= 3)
    	{
    		numPoints = 1;
    		index_1 = 2;  //added 1 because of substring(inclusion, exclusion)
    	}
    	else
    	{
    	
    		while(index_1 == 1)
    			index_1 = rand.nextInt(sites.length)+1; //add one because of substring exclusion
	    	if(numPoints > 1)
	    	{
	    		while(index_2 == index_1 || index_2 == 1)
	    			index_2 = rand.nextInt(sites.length)+1;
	    	}
	    	
	    	//make index_1 the lower of the two
	    	if(index_1 > index_2)
	    	{
	    		int tmp = index_2;
	    		index_2 = index_1;
	    		index_1 = tmp;
	    	}
    	
    	}

    	
    	/*
    	 * I will implement a rather inert population but it might be advantageous to lower the population size
    	 * as the number of generations increases.
    	 * 
    	 * For a fixed population, I want to crossover 2 parents and create 2 offspring from them. I want to do this for
    	 * the top ( populationSize - (numElites + numRandoms + getNumExpectedMutant()) parents.
    	 * 
    	 */
    	
    	//get parents
    	Stack<Chromosome> parents = new Stack<Chromosome>();
    	
    	int numParents = (population.size() > MAX_POPULATION ? MAX_POPULATION : population.size())-(numElites+numRandoms+getNumExpectedMutant());
    	numParents += (numParents%2==0 ? 0:1); //make sure we have an even number of parents
    	
    	
    	getMostFit(numParents,parents);
    	
    	String mommy, daddy, baby1 = "", baby2 = "";
    	
    	while(!parents.empty())
    	{
    		mommy = parents.pop().acids;
    		daddy = parents.pop().acids;
    		
    		if(numPoints == 1)
    		{
    			baby1 = mommy.substring(0,index_1) + daddy.substring(index_1);
    			baby2 = daddy.substring(0,index_1) + mommy.substring(index_1);
    		}
    		else if(numPoints ==2 )
    		{
    			baby1 = mommy.substring(0,index_1) + daddy.substring(index_1,index_2) + mommy.substring(index_2);
    			baby2 = daddy.substring(0,index_1) + mommy.substring(index_1,index_2) + daddy.substring(index_2);
    		}
    		else
    		{
    			//IMPLEMENT LATER ???  if at all
    		}
    		
    		//create new chromosomes and add to the next generation
    		list.add(new Chromosome(baby1));
    		list.add(new Chromosome(baby2));
    		
    	}
    	
    	
    	
    	
    		
    }
    
    //THIS NEEDS TO BE CHANGED TO CREATE AUTODOCK JOBS.  FOR TESTING, I WILL IMPLEMENT EASY FITNESS FUNCTION
    private void evaluateFitness()
    {
    	for(Chromosome org : population)
    		org.setEnergy(Math.abs("HGQL".hashCode() - org.acids.hashCode()));
    }
    
    /**
     * 
     */
    private void mutate()
    {
        //create table
    	//when we mutate, I want to create a new Tracker object for each mutation to trackStack. That is the only tracking that takes place here.
    	
    	Random randGen = new Random();
    	double ranNum;
    	double[] rowData;  //hold probability information
    	double runTotal;
    	Stack<Integer> tmpPoints = new Stack<Integer>();		//store matrix indices for Tracker objects 
    	
    	int index;
    	String newseq; //record mutations for creation of a new chromosome
    	
    	//could maybe speed this up by ??
    	/* To mutate, do the following.
    	 *
    	 * FOR EACH CHROMOSOME in the POPULATION
    	 * 		FOR EACH SUBSTITUTION SITE (character at index) in the CHROMOSOME (chromosome.acids)
    	 * 			ranNum = rand.nextDouble()
    	 * 			rowData = mutationPMF[getAcidIndex(acids.charAt(index))]  //get the correct row for the site
    	 * 			while(ranNum < runTotal)
    	 * */
    	String acids = "";
    	Chromosome org;
    	for(int i = 0; i < population.size(); i++)
    	{
    		org = population.get(i);
    		acids = org.acids;
    		newseq = acids;
    		
    		//for each site in the chromosome
    		for(int site = 0; site < acids.length(); site++)
    		{
    			ranNum = randGen.nextDouble();
    			rowData = sitePMFs[site][getAcidIndex(acids.charAt(site),site)]; // get the PMF row for this substitution site
    			
    			
    			
    			/*How this works is best understood by imagining that the PMF row for this substitution site is a unit line.
    			 * The sum of the elements in the PMF row is 1 and all elements > 0, so it is a PMF. Let us assume that we are
    			 * looking at a site that has a G in it in the original sequence. Then, if our mutation rate is .1 and our pool
    			 * was "AGF" our PMF row would be:
    			 * 
    			 *   	
    			 *   A .05	
    			 *   G .9	
    			 *   F .05	
    			 * 
    			 * This says that if we randomly chose from a population with the above PMF then we would chose a G 90% of the time, an A
    			 * 5% of the time, and an F 5% of the time.  Thus, adhering to this PMF is equivalent to enforcing a mutation rate of 10%.
    			 * 
    			 * But how do we 'chose' a number without having a population to choose from?  My solution was to generate a random number
    			 * between 0 and 1. We now create a unit line and designate a section of that line to each amino acid so that the length of the
    			 * line segment is equal to the probability for the acid.  For our example, it would look like:
    			 * 
    			 * 0 .05								  .95 1
    			 * |--|------------------------------------|--|
    			 *  A					G					 F
    			 * 
    			 * Note that we don't actually create this table since it can be inferred from the PMF row.
    			 * 
    			 * Now we choose our acid according to what section our random number is in.  The while loop allows us to go from section to
    			 * section until we get to the section that we want.
    			 * 
    			 */
    			
    			index = 0;
    			runTotal = rowData[index];
    			
    			while(ranNum > runTotal)
    			{
    				index++;
    				runTotal += rowData[index];
    			}
    			
    			if(index >= rowData.length) //I think this can happen because of the rounding errors in calculating the PMF row.
    				index = rowData.length -1;
    				
    			if(index != getAcidIndex(acids.charAt(site),site))  //check if a mutation has occurred. if one has, store the point so that we can track later
    			{
    				if(tracking != 'N')
    				{
    					tmpPoints.push(getAcidIndex(ACID_sites[site].charAt(index))); //push on the index (in mutationPMF) of the new amino acid
    					tmpPoints.push(getAcidIndex(acids.charAt(site))); //push on the index (in mutationPMF) of the replaced amino acid
    				}
    				
    				newseq = newseq.substring(0,site) + ACID_sites[site].charAt(index) + newseq.substring(site+1); //update new sequence
    			}
    			
    		}
    		
    		//if there was a mutation, create a new chromosome for it and put that in the new generation
    		if(!newseq.equals(acids))
    		{
    			population.add(new Chromosome(newseq));
    			
	    		//create trackers
	    		if(tracking != 'N')
	    		{
	    			while(!tmpPoints.empty())
	    			{
	    				trackStack.push(new Tracker(tmpPoints.pop(),tmpPoints.pop(),org,population.get(population.size()-1)));
	    			}
	    		}
    		}
    	}
    		

    }
    
    /**
     * 
     */
    private void updateTrack()
    {
    	Tracker TRKR;
    	for(int i = 0; i < trackStack.size(); i++)
    	{
    		TRKR = trackStack.pop();
    		
    		switch (tracking)
    		{
			  case 'D':
				  trackTable.add(TRKR.row,TRKR.col, TRKR.getUpdate()); //add a record to the table for each mutation.
				  break;
			  case 'P':
				if(TRKR.getUpdate() > 0) //the important data is the ratio of positive mutations to all mutations.  this is entry[0]/entry[1]
					trackTable.incrementNumerator(TRKR.row,TRKR.col);  //update numerator (count of positive mutations)
				  
					trackTable.incrementDenominator(TRKR.row,TRKR.col); //update denominator
			  	break;
			  case 'S':
				trackTable.addToValue(TRKR.row,TRKR.col,TRKR.getUpdate()); //update running total
				break;
			  default :
			  	//SHOULD NEVER GET HERE
    		}
    		
    	}
    }
    
    
    /**
     * This method will be used to map between the x and y labels of the various statistical tables
     * and the tables themselves.  Needed for updating the Tracking table and for applying the mutation operator.
     * @param acid the single letter amino acid to search for
     * @return the index in the mutationPMF table of the parameter acid
     */
    private int getAcidIndex(char acid)
    {
    	return ACIDS.indexOf(acid);
    }
    
    private int getAcidIndex(char acid, int site)
    {
    	return ACID_sites[site].indexOf(acid);
    }
    
    private ArrayList<Chromosome> copy(ArrayList<Chromosome> oldList)
    {
    	ArrayList<Chromosome> newList = new ArrayList<Chromosome>();
    	for(Chromosome organism : oldList)
    	{
    		newList.add(organism.copy());
    	}
    	return newList;
    }
    
    public int getNumExpectedMutant()
    {
    	int temp = 0;
    	for(int i = 0; i < ACID_sites.length; i++)
    		temp +=	ACID_sites[i].length();
    	return (int)(temp*mutation_rate);
    }
    
    public ArrayList<String> popToString()
    {
    	ArrayList<String> result = new ArrayList<String>();    	
    	for(int i = 0; i < population.size(); i++)
    		result.add(population.get(i).toString());
    	
    	return result;
    }


/************************** INNER CLASS *********************************/
    /**
     * 
     * @author thomas
     *
     */
    private class Chromosome implements Comparable<Chromosome>
    { 
		double bindEnergy;
		String acids; //stores the mutation Strings 
        
		public Chromosome(String acids)
		{ 
            this.acids = acids.toString();  //don't just reference the String, copy it
            this.bindEnergy = 0.0;
        }
		public Chromosome(String acids, double energy)
		{
			this.acids = acids;
			this.bindEnergy = energy;
		}
		public void setEnergy(double newEnergy)
		{	
            this.bindEnergy = newEnergy;
        }
		public double getEnergy()
		{
			return bindEnergy;
		}
		public Chromosome copy()
		{
			return new Chromosome(acids.toString(),bindEnergy);
		}
		
		//make this equivalent to the contents of results of analogs.py ( path
        @Override
        public String toString()
        {
        	String result = pdbPATH;
        	
        	for(int i = 0; i < sites.length;i++)
        	{
        		result += ":" + origSeq.charAt(i) + sites[i] + this.acids.charAt(i);
        	}
            return result;
        }
		@Override
		public int compareTo(Chromosome arg0) {
			if(bindEnergy == arg0.getEnergy())
				return 0;
			else if(bindEnergy > arg0.getEnergy())
				return 1;
			else
				return -1;
		}
	}
    
/*************************** INNER CLASS*******************************/
    /**
     * 
     * @author thomas
     *
     */
    private class Tracker
    {
    	int row, col;
    	Chromosome old, mutant;
    	
    	public Tracker(int row, int col, Chromosome old, Chromosome mutant)
    	{
    		this.row = row;
    		this.col = col;
    		this.old = old;
    		this.mutant = mutant;
    	}
    	public double getUpdate()
    	{
    		return mutant.getEnergy() - old.getEnergy();
    	}
    	@Override
    	public String toString()
    	{
    		return "row: " + row + ", col: " + col + ", energy: " + getUpdate();
    	}
    
    }
    
/******************************INNER CLASS ********************************************/
    /**
     * 
     * @author Thomas Long
     *
     */
    private class TrackTable
    {
    	private int max_count;				//the number of elements in the big_table if this is a detail (Type = D) structure
    	private char type;				//D = detail structure, S = summary structure, P = proportion structure 
    	private double[][][] cube; //used for proportion and detail tracking
    	private double[][]  table; //used for summary tracking and as an auxiliary structure for detail tracking
    	
    	
    	
    	
    	public TrackTable(int rows, int cols, char type)
    	{
    		this.type = type;
    		
    		if(type == 'S')
    			table = new double[rows][cols];  //primary data structure for this TYPE
    		else if(type == 'P')
    		{
    			cube = new double[2][rows][cols];
    		}
    		else if(type == 'D')  //primary data structure for this TYPE
    		{
    			cube = new double[8][rows][cols];  //primary data structure for this TYPE
    			table = new double[rows][cols];  //auxiliary structure, table[i][j] stores the number of elements in cube[X][i][j] 
    			max_count = 0;
    		}
    		else
    		{
    			//ERROR, this should never happen.  Halt program if this happens
    		}
    	}
    	
    	
    	
    	
    	//The denominator will be held in big_table[1][row][col]
    	public void incrementDenominator(int row, int col)
    	{
    		if(type == 'P')
    			cube[1][row][col]++;
    		else {/*ERROR, this shouldn't ever happen */}
    	}
    	
    	
    	
    	//The numerator will be held in big_table[0][row][col]
    	public void incrementNumerator(int row, int col)
    	{
    		if(type == 'P')
    			cube[0][row][col]++;
    		else { /*ERROR, this shouldn't ever happen */}
    	}
    	
    	
    	
    	//Add a value to the structure, for Detail structures only
    	public void add(int row, int col, double value)
    	{
    		if(type == 'D')
    		{
    			//we need to grow the array
    			if(table[row][col] == cube.length)
    				expand();
    			
    			cube[(int) table[row][col]][row][col] = value;
    			
    			table[row][col]++;
    			
    			if(table[row][col] > max_count)
    				max_count = (int)table[row][col];
    			
    		}
    		else { /*ERROR, this shouldn't ever happen */}
    	}
    	
    	
    	
    	//double the structures capacity
    	private void expand()
    	{
    		double[][][] tempCube = new double[cube.length*2][cube[0].length][cube[0][0].length];
    		
    		for(int i = 0; i < cube.length; i++){
    			for(int j = 0; j < cube[i].length; j++){
    				for(int k = 0; k < cube[i][j].length; k++){
    					tempCube[i][j][k] = cube[i][j][k]; }}};
    					
    		cube = tempCube;
    	}
    	
    	
    	
    	
    	public void addToValue(int row, int col, double value)
    	{
    		if(type == 'S')
    			table[row][col] += value;
    		else { /*ERROR, this shouldn't ever happen */}
    	}
    	
    	
    	
    	
    	public String toString()
    	{
    		String result = "Type: " + type + "\n";
    		
    		DecimalFormat df = new DecimalFormat("###,###.##");
    		
    		if(type == 'S')
    		{
				//VERTICAL AXIS LABELS
    			
    			result += "\t";
    			
    			for(int j = 0; j < table[0].length; j++)
					result += ACIDS.charAt(j) + "\t"; 
				
    			result += "\n";
    			
    			
    			for(int i = 0; i < table.length; i++)
    			{
    				//HORIZONTAL AXIS LABELS
    				result += ACIDS.charAt(i) + "\t";
    				
    				for(int j = 0; j < table[i].length; j++)
    				{
    					result += df.format(table[i][j]) + "\t";
    				}
    				result += "\n";
    			}
    		}
    		else if(type == 'D')
    		{
	    		for(int i = 0; i < max_count; i++)
	    		{
	    			for(int j = 0; j < cube[i].length; j++)
	    			{
	    				result += "[";
	    				
	    				for(int k = 0; k < cube[i][j].length; k++)	
	    						result += df.format(cube[i][j][k]) + " ";
	    				
	    				result += "]\t";
	    			}
	    			result += "\n";
	    		}
    		}
    		else
    		{
    			
				//VERTICAL AXIS LABELS
    			
    			result += "\t";
    			
    			for(int j = 0; j < cube[0].length; j++)
					result += ACIDS.charAt(j) + "\t"; 
				
    			result += "\n";
    			
    			for(int j = 0; j < cube[0].length; j++)
	    		{
    				//HORIZONTAL AXIS LABELS
    				result += ACIDS.charAt(j) + "\t";
    				
	    			for(int k = 0; k < cube[0][0].length; k++)    			
	    				result += (cube[1][j][k] == 0 ? "-\t" : df.format(cube[0][j][k]/cube[1][j][k]) + "\t");
	    			
	    			result += "\n";
				}
    		}
    		
    		return result;
    	}
    }
    
    

}

