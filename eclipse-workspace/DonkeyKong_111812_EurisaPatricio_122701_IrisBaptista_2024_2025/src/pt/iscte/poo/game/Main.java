package pt.iscte.poo.game;

import pt.iscte.poo.gui.ImageGUI;

public class Main {

	public static String name;

	public static void main(String[] args) {
		ImageGUI gui = ImageGUI.getInstance();
		GameEngine engine = GameEngine.getInstance();

		name = gui.showInputDialog("Starting game...", "Insert name:");

		if (name == null || name.trim().isEmpty()) {
			name = "Player";
		}

		gui.setStatusMessage("Good luck " + name + "!");
		gui.registerObserver(engine);
		gui.go();

	}

}
