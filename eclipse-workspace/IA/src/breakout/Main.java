package breakout;

import utils.GameController;
import utils.Commons;

public class Main {

	public static void main(String[] args) {
		GameController c = new FeedforwardNeuralNetwork(Commons.BREAKOUT_STATE_SIZE, 3, Commons.BREAKOUT_NUM_ACTIONS);

        BreakoutBoard b = new BreakoutBoard(c, false, 213123123);
        b.setSeed(213123123);
        b.runSimulation();
        b.getFitness();

        new Breakout(c,10);
	}
}
