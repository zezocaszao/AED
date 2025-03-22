package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class BadMeat extends SpecialObject {
	private Point2D position;
	
    public BadMeat(Point2D position) {
		super(position, 10);
		this.position = position;
	}

    @Override
    public String getName() {
        return "BadMeat";
    }

    @Override
    public int getLayer() {
        return 1;
    }
    
    @Override
    public Point2D getPosition() {
    	return position;
    }

	@Override
	public void interact(Character c) {
		if(c.getName().equals("JumpMan")) {
			c.takeDamage(getValue());
			ImageGUI.getInstance().setStatusMessage("Hero has eaten some bad meat! Hero's health: " + c.getHealth() + "/100");
			GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
			ImageGUI.getInstance().removeImage(this);
		}
			
	}
}
