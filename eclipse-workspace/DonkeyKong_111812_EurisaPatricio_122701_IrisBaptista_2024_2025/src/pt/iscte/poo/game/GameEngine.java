package pt.iscte.poo.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objects.BadMeat;
import objects.Banana;
import objects.Bat;
import objects.DonkeyKong;
import objects.Element;
import objects.GoodMeat;
import objects.Manel;
import objects.Movable;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class GameEngine implements Observer {
	private static GameEngine INSTANCE;
	private Room currentRoom = new Room();
	private int lastTickProcessed = 0;
	private int mylastTickProcessed = 0;
	private boolean resetTimer= false;

	private GameEngine() {
		Bats();
		ImageGUI.getInstance().update();
	}

	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	@Override
	public void update(Observed source) {
		
		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			System.out.println("Keypressed " + k);
			
			if (Direction.isDirection(k)) {
				System.out.println("Direction! ");
				currentRoom.moveManel();
			}
			if(k == 66) {
//				currentRoom.getManel()
			}
		}
		
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t ) {//&& resetTimer == false
			processTick();

//			if (resetTimer) {
//	            resetGameTimer();
//	        }
		}
		ImageGUI.getInstance().update();
	}

	void resetGameTimer() {
	    System.out.println("Timer reiniciado!UwU");
	    mylastTickProcessed = 0; 
	    //resetTimer = false;   
	}

//	private void setlastTick(int i) {
//		// TODO Auto-generated method stub
//		lastTickProcessed = i;
//	}

	private void processTick() {
	    System.out.println("Tic Tac : " + mylastTickProcessed);

	    List<Element> auxBanana = new ArrayList<>(); // Lista auxiliar para as bananas que vao ser removidas qnd desaparecerem
	  
		    for (Element k :new ArrayList<>(currentRoom.getElements())) {
	
		        if (k instanceof Movable && !(k instanceof Manel)) {
		            ((Movable) k).move();
		        }
	
		     
		        if (k instanceof Banana) {
		            Banana banana = (Banana) k;
		            banana.move();
		            if (banana.getPosition().getY() >= 10) {
		                auxBanana.add(banana);
		            }
		        }
	
		    
		    }

	    // Remover as bananas no final para n gerar conflito com a iteracao
	    currentRoom.getElements().removeAll(auxBanana);

	   
	    ImageGUI.getInstance().update();
	    mylastTickProcessed++;
	    lastTickProcessed++;
	}


	public Room getCurrentRoom() {
		return currentRoom;
	}

	public int getLastTickProcessed() {
		return lastTickProcessed;
	}

	public List<Element> getElementsAtPosition(Point2D position, List<Element> elements) {
		List<Element> elementsAtposition = new ArrayList<>();

		for (Element element : elements) {
			if (element.getPosition().equals(position)) {
				elementsAtposition.add(element);
			}
		}

		return elementsAtposition;
	}
	


	//bats aleatorios
	private void Bats() {
		Random random = new Random();
		int batCount = random.nextInt(3) + 1;
		for (int i = 0; i < batCount; i++) {
			int x = random.nextInt(10);
			Point2D batPosition = new Point2D(x, 0);
			Bat bat = new Bat(batPosition);
			currentRoom.getElements().add(bat);
			ImageGUI.getInstance().addImage(bat);
		}
	}
}
