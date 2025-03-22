package objects;

import java.util.List;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Trap extends SpecialObject implements Interactable {
   private Point2D position;
   
   public Trap(Point2D position) {
	   super(position, 5);
	   this.position = position;
   }

    @Override
    public String getName() {
        return "Trap";
    }

    @Override
    public int getLayer() {
        return 0;
    }
    
    @Override
    public Point2D getPosition() {
    	return position;
    }
    
    @Override
	public void interact(Character c) {
    	Point2D up = position.plus(Direction.UP.asVector());
    	GameEngine engine = GameEngine.getInstance();
	    List<Element> elementsB = engine.getElementsAtPosition(up, engine.getCurrentRoom().getElements());
	    
    	if(c.getName().equals("JumpMan")) {
			c.takeDamage(20);
			ImageGUI.getInstance().setStatusMessage("Trap! Hero's health: " + c.getHealth() + "/100");

		}
			
	}
}