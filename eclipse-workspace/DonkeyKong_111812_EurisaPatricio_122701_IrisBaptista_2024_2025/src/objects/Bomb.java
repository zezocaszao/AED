package objects;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends SpecialObject implements Interactable, Droppable {
	private Point2D position;
	public boolean isDropped = false;

	public Bomb(Point2D position) {
		super(position, 5);
		this.position = position;
	}

	@Override
	public String getName() {
		return "Bomb";
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
		if(!isDropped) {
			if (c.getName().equals("JumpMan")) {
				((Manel) c).addObject(this);
				ImageGUI.getInstance().removeImage(this);
			}
		} else {
			explode();
		}
		
	}

	@Override
	public void drop() {
		isDropped = true;
	}
	
	public void explode() {
		
	}
}
