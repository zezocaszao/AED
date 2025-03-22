package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.Manel;
import objects.Wall;
import objects.Princess;
import objects.Element;
import objects.DonkeyKong;
import objects.DoorClosed;
import objects.Floor;
import objects.Stairs;
import objects.Sword;
import objects.Trap;
import objects.GoodMeat;
import objects.Banana;
import objects.Bat;
import objects.Bomb;
import objects.HiddenTrap;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Room {
	private Point2D heroStartingPosition;
	private Manel manel;

	private List<Element> elements = new ArrayList<>(); // em vez de ir percorrer a lista toda, ir buscar pela position
	private String nextRoom = "room1.txt";

	public Room() {
		createRoom("rooms/" + nextRoom);
	}

	public void createRoom(String fileName) {
		if (elements.size() != 0)
			elements.clear();
		createBackground();
		try {
			Scanner scanner = new Scanner(new File(fileName));
			int y = 0;
			if (!fileName.equals("rooms/room2.txt")) {
				nextRoom = scanner.nextLine();
				String[] s = nextRoom.split(";");
				nextRoom = s[1];
			}

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (line.isEmpty()) {
					System.err.println("Erro: Falta uma linha inteira no arquivo " );
					ImageGUI.getInstance().dispose();
				}
				for (int x = 0; x < line.length(); x++) {
					char c = line.charAt(x);
					Point2D position = new Point2D(x, y);

					switch (c) {
					case 'W': // Wall
						Wall w = new Wall(position);
						elements.add(w);
						ImageGUI.getInstance().addImage(w);
						break;
					case 'H': // Manel
						heroStartingPosition = position;
						manel = new Manel(heroStartingPosition);
						elements.add(manel);
						ImageGUI.getInstance().addImage(manel);
						break;
					case 'P': // Princess
						Princess p = new Princess(position);
						elements.add(p);
						ImageGUI.getInstance().addImage(p);
						break;
					case 'G': // DonkeyKong
						DonkeyKong d = new DonkeyKong(position);
						elements.add(d);
						ImageGUI.getInstance().addImage(d);
						break;
					case '0': // ClosedDoor
						DoorClosed o = new DoorClosed(position);
						elements.add(o);
						ImageGUI.getInstance().addImage(o);
						break;
					case 'S': // Stairs
						Stairs st = new Stairs(position);
						elements.add(st);
						ImageGUI.getInstance().addImage(st);
						break;
					case 's': // Sword
						Sword sw = new Sword(position);
						elements.add(sw);
						ImageGUI.getInstance().addImage(sw);
						break;
					case 'm': // GoodMeat
						GoodMeat m = new GoodMeat(position);
						elements.add(m);
						ImageGUI.getInstance().addImage(m);
						break;
					case 't': // Trap
						Trap t = new Trap(position);
						elements.add(t);
						ImageGUI.getInstance().addImage(t);
						break;
					case 'B': // Bat
						Bat bt = new Bat(position);
						elements.add(bt);
						ImageGUI.getInstance().addImage(bt);
						break;
					case 'o': // Bomb
						Bomb bm = new Bomb(position);
						elements.add(bm);
						ImageGUI.getInstance().addImage(bm);
						break;
					case 'b': // Banana
						Banana b = new Banana(position);
						elements.add(b);
						ImageGUI.getInstance().addImage(b);
						break;
					case 'h': // HiddenTrap
						HiddenTrap h = new HiddenTrap(position);
						elements.add(h);
						ImageGUI.getInstance().addImage(h);
						break;
					case ' ':
						break;
					default:
						System.out.println("Unknown element in room...:" + c);
						break;
					}
				}
				y++;	
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro a abrir ficheiro " + fileName);
			fileName = newFileName();
			createRoom(fileName);
		}
	}

	private String newFileName() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Escreva o nome do arquivo para o próximo nível: ");
		String s = scanner.nextLine();
		scanner.close();
		return s;
	}


	public void moveManel() {
		manel.move();
	}
	
	public Manel getManel() {
		return manel;
	}

	public List<Element> getElements() {
		return elements;
	}

	public String getNextRoom() {
		return nextRoom;
	}

	private void createBackground() {
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				ImageGUI.getInstance().addImage(new Floor(new Point2D(x, y)));
				elements.add(new Floor(new Point2D(x, y)));
			}
		}
	}

	public void update() { 
		ImageGUI.getInstance().clearImages();
		GameEngine.getInstance().resetGameTimer();
		manel = null;
		createRoom("rooms/" + nextRoom);
		
	}
	
	public void reload() { // working
		ImageGUI.getInstance().clearImages();
		elements.remove(manel);
		manel.setPosition(heroStartingPosition);
		elements.add(manel);
		ImageGUI.getInstance().addImages(elements);
	}
}