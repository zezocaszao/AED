package breakout;

import java.util.Random;

import utils.Commons;
import utils.GameController;

public class FeedforwardNeuralNetwork implements Commons, GameController, Comparable<FeedforwardNeuralNetwork> {
	private int inputDim;
	private int hiddenDim;
	private int outputDim;
	private double[][] inputWeights;
	private double[] hiddenBiases;
	private double[][] outputWeights;
	private double[] outputBiases;
	private double fitness;

	public FeedforwardNeuralNetwork(int inputDim, int hiddenDim, int outputDim) {
		this.inputDim = inputDim;
		this.hiddenDim = hiddenDim;
		this.outputDim = outputDim;
		this.inputWeights = new double[inputDim][hiddenDim];
		this.hiddenBiases = new double[hiddenDim];
		this.outputWeights = new double[hiddenDim][outputDim];
		this.outputBiases = new double[outputDim];
	}

	public FeedforwardNeuralNetwork(int inputDim, int hiddenDim, int outputDim, double[] values) {
		this(inputDim, hiddenDim, outputDim);
		int offset = 0;
		for (int i = 0; i < inputDim; i++) {
			for (int j = 0; j < hiddenDim; j++) {
				inputWeights[i][j] = values[i * hiddenDim + j];
			}
		}
		offset = inputDim * hiddenDim;
		for (int i = 0; i < hiddenDim; i++) {
			hiddenBiases[i] = values[offset + i];
		}
		offset += hiddenDim;
		for (int i = 0; i < hiddenDim; i++) {
			for (int j = 0; j < outputDim; j++) {
				outputWeights[i][j] = values[offset + i * outputDim + j];
			}
		}
		offset += hiddenDim * outputDim;
		for (int i = 0; i < outputDim; i++) {
			outputBiases[i] = values[offset + i];
		}

	}

	public int getChromossomeSize() {
		return inputWeights.length * inputWeights[0].length + hiddenBiases.length
				+ outputWeights.length * outputWeights[0].length + outputBiases.length;
	}

	public double[] getChromossome() {
		double[] chromossome = new double[getChromossomeSize()];
		int offset = 0;
		for (int i = 0; i < inputDim; i++) {
			for (int j = 0; j < hiddenDim; j++) {
				chromossome[i * hiddenDim + j] = inputWeights[i][j];
			}
		}
		offset = inputDim * hiddenDim;
		for (int i = 0; i < hiddenDim; i++) {
			chromossome[offset + i] = hiddenBiases[i];
		}
		offset += hiddenDim;
		for (int i = 0; i < hiddenDim; i++) {
			for (int j = 0; j < outputDim; j++) {
				chromossome[offset + i * outputDim + j] = outputWeights[i][j];
			}
		}
		offset += hiddenDim * outputDim;
		for (int i = 0; i < outputDim; i++) {
			chromossome[offset + i] = outputBiases[i];
		}

		return chromossome;

	}

	public void initializeWeights() {
		// Randomly initialize weights and biases
		Random random = new Random();
		for (int i = 0; i < inputDim; i++) {
			for (int j = 0; j < hiddenDim; j++) {
				inputWeights[i][j] = random.nextDouble() - 0.5;
			}
		}
		for (int i = 0; i < hiddenDim; i++) {
			hiddenBiases[i] = random.nextDouble() - 0.5;
			for (int j = 0; j < outputDim; j++) {
				outputWeights[i][j] = random.nextDouble() - 0.5;
			}
		}
		for (int i = 0; i < outputDim; i++) {
			outputBiases[i] = random.nextDouble() - 0.5;
		}
	}

	public int forward(int[] d2) { //recebe vetor de 7 valores do estado atual
	    // Compute output given input
	    double[] hidden = new double[hiddenDim];
	    for (int i = 0; i < hiddenDim; i++) {
	        double sum = 0;
	        for (int j = 0; j < inputDim; j++) {
	            double d = d2[j];
	            sum += d * inputWeights[j][i];
	        }
	        hidden[i] = Math.max(0, sum + hiddenBiases[i]); // ReLU activation function
	    }
	    
	    double[] output = new double[outputDim];
	    for (int i = 0; i < outputDim; i++) {
	        double sum = 0;
	        for (int j = 0; j < hiddenDim; j++) {
	            sum += hidden[j] * outputWeights[j][i];
	        }
	        output[i] = sum + outputBiases[i]; // No activation function applied
	    }
	    
	    // Softmax activation function for output normalization
	    double maxVal = output[0];
	    int maxIndex = 0;
	    for (int i = 1; i < outputDim; i++) {
	        if (output[i] > maxVal) {
	            maxVal = output[i];
	            maxIndex = i;
	        }
	    }
	    
	    return maxIndex;
	}




	public void calculatefitness(int seed) {

		BreakoutBoard b = new BreakoutBoard(this, false , seed);//this
		b.runSimulation();
		this.fitness = b.getFitness();

	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public int nextMove(int[] currentState) {
		return forward(currentState);
	}

	public int getInputDim() {
		return inputDim;
	}

	public void setInputDim(int inputDim) {
		this.inputDim = inputDim;
	}

	public int getHiddenDim() {
		return hiddenDim;
	}

	public void setHiddenDim(int hiddenDim) {
		this.hiddenDim = hiddenDim;
	}

	public int getOutputDim() {
		return outputDim;
	}

	public void setOutputDim(int outputDim) {
		this.outputDim = outputDim;
	}

	public double[][] getInputWeights() {
		return inputWeights;
	}

	public void setInputWeights(double[][] inputWeights) {
		this.inputWeights = inputWeights;
	}

	public double[] getHiddenBiases() {
		return hiddenBiases;
	}

	public void setHiddenBiases(double[] hiddenBiases) {
		this.hiddenBiases = hiddenBiases;
	}

	public double[][] getOutputWeights() {
		return outputWeights;
	}

	public void setOutputWeights(double[][] outputWeights) {
		this.outputWeights = outputWeights;
	}

	public double[] getOutputBiases() {
		return outputBiases;
	}

	public void setOutputBiases(double[] outputBiases) {
		this.outputBiases = outputBiases;
	}

	@Override

	public int compareTo(FeedforwardNeuralNetwork other) {
		return (int) (other.getFitness() - this.getFitness());
	}

	public void getRun(long seed) {

		BreakoutBoard b = new BreakoutBoard(this, false, seed);
		b.setSeed((int) seed);
		b.runSimulation();
		fitness = b.getFitness();

	}

}
