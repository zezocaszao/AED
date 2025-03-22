package objects;

import pt.iscte.poo.utils.Point2D;

public class Floor extends Element {
	private Point2D position;
	
	public Floor(Point2D position) {
		super(position);
		this.position = position;
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return position;
	}


}
