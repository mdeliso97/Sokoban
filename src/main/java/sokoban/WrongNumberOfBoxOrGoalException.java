package sokoban;
/**
 * Is thrown at {@link Parser} if the file .sok has not the same amount of goals 'G'  and boxes 'B'
 *
 */
public class WrongNumberOfBoxOrGoalException extends Exception {

	private static final long serialVersionUID = -4671565654363829356L;

	@Override
	public String toString() {
		return "Illegal tile Exception: i am thrown if the .sok file has not the same amount of goals 'G'  and boxes 'B'. My serialVersionUID is " + serialVersionUID;
	}
}
