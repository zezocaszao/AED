package objects;

import pt.iscte.poo.utils.Point2D;

public class Wall extends Element {
	private Point2D position;
	
	public Wall(Point2D position) {
		super(position);
		this.position = position;
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}


}
