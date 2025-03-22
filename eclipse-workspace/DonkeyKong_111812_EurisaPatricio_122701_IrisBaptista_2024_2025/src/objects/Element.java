package objects;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.gui.ImageTile;

public abstract class Element implements ImageTile {
	private Point2D position;

	public Element(Point2D position) {
		this.position = position;
	}

	public Point2D getPosition() {
		return this.position;
	}

	public void setPosition(Point2D coordenadas) {
		position = coordenadas;
	}

}