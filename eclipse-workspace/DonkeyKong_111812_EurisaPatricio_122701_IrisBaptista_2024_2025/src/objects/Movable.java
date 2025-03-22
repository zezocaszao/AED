package objects;

import java.util.List;

import pt.iscte.poo.utils.Point2D;

public interface Movable {
	   void move();
	   boolean isMovable(List<Element> elementsList, Point2D position);
}
