package breakout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import utils.Commons;





public class Ga implements Commons {

	private static final int POPULATION_SIZE = 100;
	private static final int NUM_GENERATIONS = 100;
	private static final double MUTATION_RATE = 0.2;
	private static Random random = new Random();

	public Ga() {

		FeedForwardNeuralNetwork[] population = new FeedForwardNeuralNetwork[POPULATION_SIZE];
		FeedForwardNeuralNetwork chosen_one = new FeedForwardNeuralNetwork(BREAKOUT_STATE_SIZE, 200, BREAKOUT_NUM_ACTIONS);
		//long seeds[] = new long [NUM_GENERATIONS] ;
		//int seed_index = 0;
		long seed = 69;
		
		
//chosen_one.getRun();

		for (int i = 0; i < POPULATION_SIZE; i++) {

			FeedForwardNeuralNetwork guy = new FeedForwardNeuralNetwork(BREAKOUT_STATE_SIZE,200, BREAKOUT_NUM_ACTIONS);
			guy.initializeWeights();
			population[i] = guy;
			

		}
		for (int i = 0; i < NUM_GENERATIONS; i++) {
			int min = 100;
			int max = 1000;
			seed =69; //(long) Math.floor(Math.random() * (max - min + 1) + min);//
			System.out.printf("Seed Value: %d - ", (int) seed);
			//seeds[i] = seed;
			
			
			FeedForwardNeuralNetwork[] newPopulation = new FeedForwardNeuralNetwork[POPULATION_SIZE];
			for (int j = 0; j < POPULATION_SIZE/2 ; j++) {
				FeedForwardNeuralNetwork parent1 = selectParent(population);
				FeedForwardNeuralNetwork parent2 = selectParent(population);
				FeedForwardNeuralNetwork child = crossover(parent1, parent2);
				mutate(child);
				
				
				child.run(seed);//seeds[i]
				newPopulation[j] = child;
				if (newPopulation[j].getFitness() > chosen_one.getFitness()) {
					chosen_one = newPopulation[j];
					//seed_index = i;
				}
			
			}
			
			for (int n = 0; n < POPULATION_SIZE/2; n++){
			    newPopulation[n + POPULATION_SIZE/2] = population[n];
			}
			population = newPopulation;

		

			System.out.println("Generation " + i + ": " + population[0].getFitness());
			

			
		}

		Arrays.sort(population);


		System.out.println("Best solution found: " + chosen_one.getFitness());
		//System.out.printf("Seed Index: %d | Seed Value %d \n", (int) seed_index, (int) seeds[seed_index]);		
		
		BreakoutBoard board = new BreakoutBoard(chosen_one, false, (int) seed );//seeds[seed_index]

        // Set the seed for randomness
        board.setSeed( seed);//(int) seeds[seed_index]

        // Run the simulation
        board.runSimulation();

        // Print the fitness obtained
        System.out.println("Fitness: " + board.getFitness());
        new Breakout(chosen_one,(int) seed );//(int) seeds[seed_index]
        Breakout.showControllerPlaying(chosen_one,(int) seed);//(int) seeds[seed_index]

	}

	public static void main(String[] args) {

		new Ga();
	}

	private FeedForwardNeuralNetwork selectParent(FeedForwardNeuralNetwork[] population) {
	    ArrayList<FeedForwardNeuralNetwork> tournament = new ArrayList<>();
	    for (int i = 0; i < 10; i++) {
	        tournament.add(population[random.nextInt(POPULATION_SIZE)]);
	    }
	    Collections.sort(tournament);
	    return tournament.get(0);
	}


	private FeedForwardNeuralNetwork crossover(FeedForwardNeuralNetwork parent1,
			FeedForwardNeuralNetwork parent2) {

		double[] childa = new double[parent1.getChromosomeSize()];

		for (int i = 0; i < parent1.getChromosomeSize(); i++) {
			if (random.nextDouble() < 0.5) {
				childa[i] = parent1.getChromosome()[i];

			} else {
				childa[i] = parent2.getChromosome()[i];
			}
		}
		FeedForwardNeuralNetwork childfinal = new FeedForwardNeuralNetwork(BREAKOUT_STATE_SIZE, 200, BREAKOUT_NUM_ACTIONS,childa);
		return childfinal;
	}

	private void mutate(FeedForwardNeuralNetwork child) {
	    if (random.nextDouble() < MUTATION_RATE) {
	        int chr1 = random.nextInt(child.getChromosomeSize());
	        int chr2 = random.nextInt(child.getChromosomeSize());
	        double x = child.getChromosome()[chr1];
	        child.getChromosome()[chr1] = child.getChromosome()[chr2];
	        child.getChromosome()[chr2] = x;
	    }
	}

	}