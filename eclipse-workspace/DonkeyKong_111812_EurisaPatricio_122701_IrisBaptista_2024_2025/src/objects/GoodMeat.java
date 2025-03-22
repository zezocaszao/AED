package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class GoodMeat extends SpecialObject {
	private Point2D position;
	
    public GoodMeat(Point2D position) {
		super(position, 20);
		this.position = position;
	}

    @Override
    public String getName() {
        return "GoodMeat";
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
		if(c.getName().equals("JumpMan")) {
			c.heal(getValue());
			ImageGUI.getInstance().setStatusMessage("Hero has been healed! Hero's health: " + c.getHealth() + "/100");
			GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
			ImageGUI.getInstance().removeImage(this);
		}
	}

}
