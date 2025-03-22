package objects;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;

public class DoorClosed extends Element implements Interactable {
	   
	    public DoorClosed(Point2D position) {
			super(position);
		}

	    @Override
	    public String getName() {
	        return "DoorClosed";
	    }

	    @Override
	    public int getLayer() {
	        return 0;
	    }
	    
	    public void interact(Character c) {
	    	if(c.getName() == "JumpMan") {
	    		ImageGUI.getInstance().setStatusMessage("Level completed! Next level...");
	    		GameEngine.getInstance().getCurrentRoom().update();
	    	}
	    }
	
}
