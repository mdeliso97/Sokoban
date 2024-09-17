package indications;

/**
 * Is a subclass of {@link Indication} needed for moving objects such like Boxes and player. It stores 
 * the indication to go right on the board. If asked it returns (0,1) coordinates.
 * 
 */

public class Right extends Indication {
	
/**
 *  Gives the amount of moves which a moving object can do (1 move right). The movable objects are allowed to move
 *  into one Tile at a time (so by one).
 *  
 */
	
	public Right() {
		super(0,1);
	}
	
	@Override
	public String toString() {
		return "Right. X=0;Y=1";
	}
}
