package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class SpecialObject extends Element implements Interactable {
	private int value; //impact 
	
    public SpecialObject(Point2D position, int value) {
		super(position);
		this.value = value;
	}

    public int getValue() {
        return value;
    }  

}
