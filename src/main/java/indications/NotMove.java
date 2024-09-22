package indications;

/**
 * Is a subclass of {@link Indication} needed for moving objects such like Boxes and player which in specific situation 
 * have to not move. It stores the indication to not move on the board. If asked it returns (0,0) coordinates.
 * It's used if the given input from the human (real world player) is not valid from the syntax of 
 * {@link ParserIndication#parse(String)}.
 * 
 */

public class NotMove extends Indication {
	
	
	/**
	 *  Gives the amount of moves which a moving object has to do (0 in all direction).
	 *  
	 */	
	
	public NotMove() {
		super(0,0);
	}

	@Override
	public String toString() {
		return "NotMove. X=0;Y=0";
	}
}
