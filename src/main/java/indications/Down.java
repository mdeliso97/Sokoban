package indications;

/**
 * Is a subclass of {@link Indication} needed for moving objects such like Boxes and player. It stores 
 * the indication to go down on the board. If asked it returns (1,0) coordinates.
 * 
 */

public class Down extends Indication {
	
/**
 *  Gives the amount of moves which a moving object can do (1 move down). The movable objects are allowed to move
 *  into one Tile at a time (so by one).
 *  
 */
	
	public Down() {
		super(1,0);
	}
	
	@Override
	public String toString() {
		return "Down. X=1;Y=0";
	}
}
