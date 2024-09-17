package tile;

import movable.*;
import sokoban.Game;


/**
 * Extends the {@link Empty}, because of that they are very similar. It stores a {@link IMovable}, a {@link Game}
 * and its coordinates on the board of the game (which all of them must be valid). Each time a Goal is created it 
 * is called {@link Game#increaseGoalCounter()}, which keeps track of the number of goals on the game. Each time a 
 * {@link Box} enters in a goal, it is called {@link Game#increaseBoxOnGoalCounter()}. Each time a box leaves a goal,
 * it is called {@link Game#decreaseBoxOnGoalCounter()}. A Goal recognize a box by checking the instance of the movable
 * object that are trying to enter or leave a goal object.
 * The character representing this class is 'G'.
 *
 */

public class Goal extends Empty{

	public Goal(Game g, int x, int y) {
		super(g, x, y);
		g.increaseGoalCounter();
	}
	
	@Override
	protected char getCharLabel() {
		if(isOccupied()) {
			if(movable instanceof Box) return 'C';
			
		return movable.toChar();
		}
	return 'G';
	}
	
	@Override
	public void enter(IMovable mov) {
		assert mov!=null;
		this.movable = mov;
		if(mov instanceof Box) game.increaseBoxOnGoalCounter();
		assert invariant();
	}
	@Override
	public void leave(IMovable mov) {
		assert this.movable == mov;
		this.movable = null;
		if(mov instanceof Box) game.decreaseBoxOnGoalCounter();
		assert invariant();
	}
	@Override
	public char toChar() {
		return getCharLabel();
	}
	
	@Override
	public String toString() {
		return "Goal tile with X position " + positionX() + "and Y position " + positionY() + 
				". I am in the following game: " + game.toString();
	}
}
