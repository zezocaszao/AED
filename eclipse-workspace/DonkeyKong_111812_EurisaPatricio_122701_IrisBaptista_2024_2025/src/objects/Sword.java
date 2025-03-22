package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Sword extends SpecialObject {
	private Point2D position;

	public Sword(Point2D position) {
		super(position, 10);
		this.position = position;
	}

	@Override
	public String getName() {
		return "Sword";
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
		if (c.getName().equals("JumpMan")) {
			c.boostAttackPower(getValue());
			ImageGUI.getInstance().setStatusMessage("Hero's attack power has been enhanced! Hero's power: " + c.getAttackPower());
			GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
			ImageGUI.getInstance().removeImage(this);
		}
	}
}