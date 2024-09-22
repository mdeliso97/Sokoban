package sokoban;

/**
 * Is thrown at {@link Parser} if the file .sok has not exactly one Player 'P'
 *
 */

public class WrongNumberOfPlayersException extends Exception {

	private static final long serialVersionUID = -7548029530131288196L;	
	
	@Override
	public String toString() {
		return "Illegal tile Exception: i am thrown if the .sok file has not exactly one Player 'P'. My serialVersionUID is " + serialVersionUID;
	}

}
