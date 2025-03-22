package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Main;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public abstract class Character extends Element {
	private int health;
	private int attackPower;

	public Character(Point2D position, int health, int attackPower) {
		super(position);
		this.health = health;
		this.attackPower = attackPower;
	}

	public int getHealth() {
		return health;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void heal(int healthPoints) {
		if (health + healthPoints > 100) {
			health = 100;
			return;
		}
		health += healthPoints;
	}

	public void takeDamage(int damage) {
		if (damage >= health) {
			health = 0;
			if (this.getName() == "JumpMan") {
				int lif = ((Manel) this).getLifes();
				if (lif == 1) {
					ImageGUI.getInstance().setStatusMessage("You died! It's GAME OVER.");
					gameOver();
				} else {
					((Manel) this).lifes--;
					((Manel) this).heal(100);
					ImageGUI.getInstance().showMessage("You died!", "You have " + lif + " lifes.");
					GameEngine.getInstance().getCurrentRoom().reload();
				}

			}
			
			if (this.getName() == "DonkeyKong") {
				ImageGUI.getInstance().setStatusMessage("DonkeyKong is defeated!");
				GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
				ImageGUI.getInstance().removeImage(this);
			}
			return;
		}
		health -= damage;
	}

	public void boostAttackPower(int attackPowerPoints) {
		attackPower += attackPowerPoints;
	}

	public void attack(Character other) {
		other.takeDamage(this.attackPower);
	}
	
	public void gameOver() {
		ImageGUI gui = ImageGUI.getInstance();
		int ticks = GameEngine.getInstance().getLastTickProcessed();
		double ticksInSeconds = ticks * 0.5;
		int finalScore = Math.max((int) (1000 - ticksInSeconds * 10), 0);
		String name = Main.name;
		Score.saveScore(name, finalScore);
		gui.clearImages();

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				Floor backgroundTile = new Floor(new Point2D(x, y)); // Assumindo que 'Floor' representa o fundo
				gui.addImage(backgroundTile);
			}
		}
		
		Score.HighScores();
	}

}