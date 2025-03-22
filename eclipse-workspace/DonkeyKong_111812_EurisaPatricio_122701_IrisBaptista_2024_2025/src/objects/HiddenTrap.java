package objects;

import pt.iscte.poo.utils.Point2D;

public class HiddenTrap extends SpecialObject implements Interactable {
   private Point2D position;
   private int interaction = 0;
   
   public HiddenTrap(Point2D position) {
	   super(position, 5);
	   this.position = position;
   }

    @Override
    public String getName() {
    	if(interaction == 0)
    		return "Wall";
    	else
    		return "Trap";
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
	public void interact(Character c) {
		if(c.getName() == "JumpMan") {
			c.takeDamage(getValue());
			interaction++;
		}			
	}
    
}