package indications;

/**
 * Is a subclass of {@link Indication} needed for moving objects such like Boxes and player. It stores 
 * the indication to go up on the board. If asked it returns (-1,0) coordinates.
 * 
 */

public class Up extends Indication {

/**
 *  Gives the amount of moves which a moving object can do (1 move up). The movable objects are allowed to move
 *  into one Tile at a time (so by one).
 *  
 */
	
	public Up() {
		super(-1,0);
	}
	
	@Override
	public String toString() {
		return "Up. X=-1;Y=0";
	}
}
