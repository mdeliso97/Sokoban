package sokoban;
/**
 * Is thrown at {@link Parser} if the file .sok has an Illegal tile on it, which is not accepted from our parser
 *
 */
public class IllegalTileException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "Illegal tile Exception: i am thrown if the .sok file has an illegal tile. My serialVersionUID is " + serialVersionUID;
	}

}
