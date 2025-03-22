package objects;

import java.util.List;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Banana extends SpecialObject implements Movable {
	private Point2D position;
	
    public Banana(Point2D position) {
		super(position, 5);
		this.position = position;
	}

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int getLayer() {
        return 2;
    }

	@Override
	public void interact(Character c) {
		if(c.getName().equals("JumpMan")) {
			c.takeDamage(getValue());
			ImageGUI.getInstance().setStatusMessage("Banana attack! Hero's health: " + c.getHealth() + "/100");
			GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
			ImageGUI.getInstance().removeImage(this);
		}
	}

	@Override
	public void move() {
		Vector2D v = Direction.DOWN.asVector();
         position = position.plus(v);
        List<Element> elementsN = GameEngine.getInstance()
                .getElementsAtPosition(position, GameEngine.getInstance().getCurrentRoom().getElements());
        for (Element element : elementsN) {
            if (element instanceof Manel) {
                this.interact((Manel) element);
            }
        }

        setPosition(position);
     
        
	}
	
	@Override
	public boolean isMovable(List<Element> elementsList, Point2D position) {
		return true;
	}

}