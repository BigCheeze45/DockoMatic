package mutantScreening;

import java.util.ArrayList;
import java.util.Random;

public class Organism implements Comparable<Organism>{
	
	private String genotype;
	private double fitness;
	
	public Organism(String genotype){
		this.genotype = genotype.toUpperCase();
		this.fitness = 100.0;
		
	}
	
	public Organism(String genotype, double fitness){
		this.genotype = genotype.toUpperCase();
		this.fitness = fitness;
	}
	
	public void setFitness(double fitness){
		this.fitness = fitness;
	}
	
	public String getGenotype(){
		return this.genotype.toString();  //just to be safe, don't return a reference to the instance variable
	}
	
	public double getFitness(){
		return this.fitness;
	}
	
	@Override
	public String toString(){
		return this.genotype + "\t" + this.fitness;
	}
	
	@Override
	public boolean equals(Object obj){
		Organism other = (Organism) obj;
		return other.genotype.equals(genotype);
	}
	
	public String toCmdString(String[] origSeq) {
		String result = "";

		for (int i = 0; i < origSeq.length; i++) {
			if (origSeq[i].charAt(0) != genotype.charAt(i))
				result += ":" + origSeq[i]+ genotype.charAt(i);
		}
		return result;
	}

	
	public Organism[] crossover(Organism mate, int numPoints, Random rand){
		
		String matesGenotype = mate.getGenotype();
		
		String[] childrenGenes = {"", ""};
		
		boolean flag = true;
		
		ArrayList<Integer> crossoverPoints = generateCrossoverPoints(numPoints, rand);
		
		for(int i =0; i < this.genotype.length(); i++){
			if(crossoverPoints.contains(i)){
				flag = !flag;
			}
			
			if (flag) {
				childrenGenes[0] += this.genotype.charAt(i);
				childrenGenes[1] += matesGenotype.charAt(i);
			} else {
				childrenGenes[1] += this.genotype.charAt(i);
				childrenGenes[0] += matesGenotype.charAt(i);
			}	
		}

		Organism[] result = {new Organism(childrenGenes[0]) , new Organism(childrenGenes[1])};
		
		return result;
	}
	
	/**
	 * Generates a genotype which is a (possible) mutation of this organisms genotype
	 * @param rand
	 * @param mutation_rate
	 * @param substitutionPools
	 * @return
	 */
    public String mutate(Random rand, double mutation_rate, String[] substitutionPools)
    {
    	String mutatedGenotype = "";
    	char temp;
    						
		for (int i = 0; i < this.genotype.length(); i++) {

			if (rand.nextDouble() <= adjustRate(mutation_rate, substitutionPools[i])) {
				temp = substitutionPools[i].charAt(rand.nextInt(substitutionPools[i].length()));
			} else {
				temp = this.genotype.charAt(i);
			}

			mutatedGenotype += temp;
		}
		
		return mutatedGenotype;
		
    }
    
    /**
     * The mutation rate for a genetic algorithm specifies the probability that any gene
     * will mutate into another gene.  In this instance, the ways in which a gene can mutate 
     * are specified by the substitution pool associated with it.  Since this pool contains the 
     * original gene, we cannot simply grab a random gene from the pool whenever a mutation occurs.
     * If we did, the effective mutation rate would be lower than the parameter specified.  That is,
     * Effective Rate = mutation rate * ( 1 - (1 / number of genes in substitution pool))
     * This method adjusts the effective mutation rate so that it matches the parameter. 
     * @param originalRate
     * @param substitutionPool
     * @return the adjusted substitution rate
     */
    private double adjustRate(double originalRate, String substitutionPool){
    	
    	double result = originalRate / (1 - (1.0 / substitutionPool.length()));
    	
    	return result;
    }

    //For sorting the population by fitness
	@Override
	public int compareTo(Organism other) {
		int result = 0;
		
		if(this.fitness > other.getFitness())
			result = 1;
		else if(this.fitness < other.getFitness())
			result = -1;
		
		return result;
	}
	
	/**
	 * Returns an ArrayList of numPoints integers in the range [0,this.genotype.length()-1 )
	 * @param numPoints the number of random integers to generate
	 * @return a list of numPoints integers in the range [0, this.genotype.length()-1 )
	 */
	private ArrayList<Integer> generateCrossoverPoints(int numPoints, Random rand){
		
		numPoints = Math.max(numPoints, 1);  							 // lower bounds check
		numPoints = Math.min(numPoints, this.genotype.length() -1); 	 // upper bounds check
		
		ArrayList<Integer> result = new ArrayList<Integer>(numPoints);
	
		int temp;
		
		while(result.size() < numPoints){
			temp = rand.nextInt(this.genotype.length()-1);
			if(!result.contains(temp)){
				result.add(temp);
			}
		}
		
		return result;	
	}

}
