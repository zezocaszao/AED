package objects;

import java.util.List;
import java.util.Random;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends SpecialObject implements Movable, Interactable {
	private Point2D position;
	private static final Random random = new Random();

	public Bat(Point2D position) {
		super(position, 10);
		this.position = position;
	}

	@Override
	public String getName() {
		return "Bat";
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
	    // Obter a posição abaixo
	    Point2D below = position.plus(Direction.DOWN.asVector());
	    GameEngine engine = GameEngine.getInstance();
	    List<Element> elementsB = engine.getElementsAtPosition(below, engine.getCurrentRoom().getElements());
	    
	    // Se forem escadas, o bat desce
	    for (Element e : elementsB) {
	        if (e.getName().equals("Stairs")) {
	            position = below;
	            setPosition(position);
	            return;
	        }
	    }

	    // Calcular os movimentos aleatórios 
	    Direction[] moves = { Direction.LEFT, Direction.RIGHT };
	    Vector2D randomv = moves[random.nextInt(moves.length)].asVector();
	    
	    // Verificar as posições aleatórias    
	    Point2D newP = position.plus(randomv); 
	    List<Element> elements = engine.getElementsAtPosition(newP, engine.getCurrentRoom().getElements());

	    if (isMovable(elements, newP)) {
	        position = newP;
	        setPosition(position);
	    }
	  
	    // Tratar a interação    
	    for (Element element : elements) {
	        if (element.getName().equals("JumpMan")) {
	            interact((Character) element);
	        }
	    }
	}



	public boolean isMovable(List<Element> elements, Point2D position) {
		int i = position.getX();
		int j = position.getY();

		if (i >= 0 && i < 10 && j >= 0 && j < 10) {
	        for (Element element : elements) {
	            if (element.getName().equals("Wall") ) {
	                return false;
	            }
	        }
	        return true;
	        }
	        return false;
	    }
	
	
	    @Override
	    public void interact(Character c) {
	        if (c.getName().equals("JumpMan")) {
	            c.takeDamage(10);
	            ImageGUI.getInstance().setStatusMessage("Bat Attack! Hero's health: " + c.getHealth() + "/100");
	            GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
	            ImageGUI.getInstance().removeImage(this);
	        }
	    }
}
	 

