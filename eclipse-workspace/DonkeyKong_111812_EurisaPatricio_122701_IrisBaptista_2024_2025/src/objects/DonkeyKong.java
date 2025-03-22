package objects;

import java.util.List;
import java.util.Random;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class DonkeyKong extends Character implements Movable, Interactable {
	private Point2D position;

	public DonkeyKong(Point2D position) {
		super(position, 100, 10);
		this.position = position;
	}

	@Override
	public String getName() {
		return "DonkeyKong";
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
	public void move() {
		Direction[] d = { Direction.LEFT, Direction.RIGHT };
		Random random = new Random();
		int randomInt = random.nextInt(d.length);
		Vector2D v = d[randomInt].asVector();

		int i = position.getX() + v.getX();
		int j = position.getY() + v.getY();

		List<Element> eList = GameEngine.getInstance().getElementsAtPosition(new Point2D(i, j),
				GameEngine.getInstance().getCurrentRoom().getElements());
		if (isMovable(eList, new Point2D(i, j))) {
			this.position = position.plus(v);
		} else {
			System.out.println("Invalid DK Move!");
		}
		if (random.nextDouble() < 0.2) {
			dropBanana();
		}
	}

	public boolean isMovable(List<Element> elementsList, Point2D position) {
		int i = position.getX();
		int j = position.getY();

		if (i >= 0 && i < 10 && j >= 0 && j < 10) {
			for (Element elm : elementsList) {
				if (elm.getName().equals("DoorClosed"))
					return false;
				if (elm.getName().equals("JumpMan")) {
					this.interact((Character) elm);
					return false;
				}
				if (elm.getName().equals("Princess"))
					return false;
				if (elm.getName().equals("Wall"))
					return false;
				if (elm.getName().equals("DonkeyKong"))
					return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void interact(Character c) {
		if (c.getName().equals("JumpMan")) {
			c.takeDamage(15);
			ImageGUI.getInstance().setStatusMessage("DonkeyKong attacks! Hero's health: " + c.getHealth() + "/100");
		}
	}

	private void dropBanana() {
		Banana banana = new Banana(position);
		GameEngine.getInstance().getCurrentRoom().getElements().add(banana);
		ImageGUI.getInstance().addImage(banana);
	}
}