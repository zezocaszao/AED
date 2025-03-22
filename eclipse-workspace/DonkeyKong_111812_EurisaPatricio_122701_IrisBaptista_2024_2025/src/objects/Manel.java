package objects;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Manel extends Character implements Movable, Interactable {
	private Point2D position;
	private boolean isFalling = false;
	public int lifes;
	private List<Droppable> objects = new ArrayList<>();

	public Manel(Point2D initialPosition) {
		super(initialPosition, 100, 10);
		position = initialPosition;
		lifes = 3;
	}

	@Override
	public String getName() {
		return "JumpMan";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 1;
	}

	public int getLifes() {
		return lifes;
	}

	public void addObject(Droppable k) {
		objects.add(k);
	}

	public void setPosition(Point2D p) {
		position = p;
	}

	@Override
	public void move() {
		Vector2D v = Direction.directionFor(ImageGUI.getInstance().keyPressed()).asVector();
		int i = position.getX() + v.getX();
		int j = position.getY() + v.getY();
		Point2D newPosition = position.plus(v);
		// verificar banana
		List<Element> elementsN = GameEngine.getInstance().getElementsAtPosition(newPosition,
				GameEngine.getInstance().getCurrentRoom().getElements());
		for (Element element : elementsN) {
			if (element instanceof Banana) {
				((Banana) element).interact(this);
			}
		}

		if (isMovable(GameEngine.getInstance().getCurrentRoom().getElements(), new Point2D(i, j))) {
			position = position.plus(v);
			Element e = trapsNearby();
			if (e != null)
				((Interactable) e).interact(this);
		} else {
			System.out.println("Invalid Manel Move!");
		}
		fall();
	}

	private Element trapsNearby() {
		GameEngine engine = GameEngine.getInstance();
		List<Element> elements = engine.getCurrentRoom().getElements();

		Point2D down = position.plus(Direction.DOWN.asVector());
		List<Element> elementsA = engine.getElementsAtPosition(down, elements);
		for (Element e : elementsA)
			if (e.getName().equals("Trap") || e instanceof HiddenTrap)
				return e;

		Point2D left = position.plus(Direction.LEFT.asVector());
		List<Element> elementsB = engine.getElementsAtPosition(left, elements);
		for (Element e : elementsB)
			if (e.getName().equals("Trap") || e instanceof HiddenTrap)
				return e;

		Point2D right = position.plus(Direction.RIGHT.asVector());
		List<Element> elementsC = engine.getElementsAtPosition(right, elements);
		for (Element e : elementsC)
			if (e.getName().equals("Trap") || e instanceof HiddenTrap)
				return e;

		return null;
	}

	private boolean hasSupport(Point2D position) {
		List<Element> elementsA = GameEngine.getInstance().getCurrentRoom().getElements();
		List<Element> elementsB = GameEngine.getInstance().getElementsAtPosition(position, elementsA);

		for (Element el : elementsB) {
			if (el.getName().equals("Stairs") || el.getName().equals("Wall") || el.getName().equals("Trap")) {
				return true; // has found support
			}
		}
		return false; // no support
	}

	private void fall() {
		if (isFalling) {
			return; // if falling, all good
		}
		// if not, checks if there's support
		Point2D below = position.plus(Direction.DOWN.asVector());
		if (!hasSupport(below)) {
			isFalling = true; // starts falling
		}
	}

	public void processFall() {
		if (!isFalling) {
			return; // if it's not falling, no need to process fall
		}
		// else, process fall
		Point2D below = position.plus(Direction.DOWN.asVector());
		// verify if there's no banana
		List<Element> elementsB = GameEngine.getInstance().getElementsAtPosition(below,
				GameEngine.getInstance().getCurrentRoom().getElements());
		for (Element element : elementsB) {
			if (element instanceof Banana) {
				((Banana) element).interact(this);
			}
		}
		if (hasSupport(below)) {
			isFalling = false;
		} else {
			position = below;
		}
	}

	@Override
	public boolean isMovable(List<Element> elementsList, Point2D position) {
		
		List<Element> eList = GameEngine.getInstance().getElementsAtPosition(position, elementsList);
		List<Element> nowList = GameEngine.getInstance().getElementsAtPosition(this.position, elementsList);
		
		//Direction nextBlock = Direction.directionFor(ImageGUI.getInstance().keyPressed());
		
		int i = position.getX();
		int j = position.getY();
		
		//posiçao abaixo
		Point2D below = new Point2D(i,j-1);
		List<Element> belowList = GameEngine.getInstance().getElementsAtPosition(below, elementsList);
		
		if (i >= 0 && i < 10 && j >= 0 && j < 10) {
			if (eList.size() == 1 && eList.get(0).getName() == "Floor" && belowList.size() == 1) {


			for (Element elm : eList) {
				if (elm.getName().equals("Wall") || elm.getName().equals("Trap")) {
					return false;
				}
				if (elm instanceof Climbable) {
					return true;
				}
				if (elm.getName().equals("DonkeyKong")) {
					this.interact((DonkeyKong) elm);
					return false;
				}
				if (elm instanceof Bomb) {
					((Bomb) elm).interact(this);
					return true;
				}
				if (elm instanceof Interactable) {
					((Interactable) elm).interact(this);
					return true;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void interact(Character c) {
		if (c.getName().equals("DonkeyKong")) {
			c.takeDamage(getAttackPower());
			if (c.getHealth() != 0)
				ImageGUI.getInstance().setStatusMessage("Hero attacks! DonkeyKong's health: " + c.getHealth() + "/100");
		} else {
			attack(c);
		}
	}

	public void drop() {
		for (Droppable d : objects) {
			if (d instanceof Bomb) {
				((Bomb) d).setPosition(position);
				GameEngine.getInstance().getCurrentRoom().getElements().add((Element) d);
				ImageGUI.getInstance().addImage((Element) d);
				ImageGUI.getInstance().setStatusMessage("Bomb has been dropped! Exploding in 5 seconds");
				d.drop();
			}
		}
	}
}