package objects;

import pt.iscte.poo.utils.Point2D;

public class Stairs extends Element implements Climbable {
   
    public Stairs(Point2D position) {
		super(position);
	}

    @Override
    public String getName() {
        return "Stairs";
    }

    @Override
    public int getLayer() {
        return 0;
    }

	@Override
	public boolean isClimbable(Character c) {
		return c.getName().equals("JumpMan");
	}
    
    
}