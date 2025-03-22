package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Main;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Princess extends Character implements Interactable {

	public Princess(Point2D position) {
		super(position, 100, 5);
	}

	@Override
	public String getName() {
		return "Princess";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	   public void interact(Character c) {
	        ImageGUI gui = ImageGUI.getInstance();
	        gui.setStatusMessage("Princess Rescued!");   
	        int ticks = GameEngine.getInstance().getLastTickProcessed();
	        double ticksInSeconds = ticks * 0.5;
	        int finalScore = (int)(1000 - ticksInSeconds * 10);
	        String name = Main.name;
	        Score.saveScore(name, finalScore);
	         gui.clearImages();

	         for (int y = 0; y < 10; y++) {
	             for (int x = 0; x < 10; x++) {
	                 Floor backgroundTile = new Floor(new Point2D(x, y)); 
	                 gui.addImage(backgroundTile);
	             }
	         }
	         Score.HighScores(); 
	   }

}
