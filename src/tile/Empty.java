package tile;

import sokoban.*;
import indications.Indication;
import movable.IMovable;

/**
 * Default {@link ITile} type
 *
 * An empty tile allows only one {@link IMovable} on it (like the others, excepts {@link Wall}, where it is not allowed).
 * If a movable object lands on such a tile and it is already occupied, then it will be first asked at the movable on top of 
 * this tile to move in the same direction of the first movable. If he can not (because there is a wall), this tile will send 
 * the first movable back where it came from.
 * This class is the base of {@link Goal} and {@link Wall}. It defines handy methods, such as 
 * {@link Empty#landHereOrGoHome(int, int, Indication)}, {@link Empty#moveAndLand(Indication)}, {@link Empty#leave(IMovable)}
 * and {@link Empty#enter(IMovable)}. It is used by {@link Parser} (when it parses a new {@link Game}),by a game when it is
 * created and during {@link Game#play()}.
 * Its main responsibility is to store a movable, its coordinate and the game in which the board is stored. If a movable wants
 * to enter in it or wants to leave it, empty can do it. If a movable wants to move in a tile next to this, empty will send it
 * where it needs to be sent, by asking the board to find the given Tile.
 */

public class Empty implements ITile, IPrintable{
	protected Game game;
	protected IMovable movable;
	protected int xAxis, yAxis;
	
	public boolean invariant() {
		return game.isInRangeX(xAxis) && game.isInRangeY(yAxis) && game != null;
	}
	
	public Empty (Game g, int x, int y) {
		assert g != null;
		game = g;
		xAxis = x;
		yAxis = y;
		assert game != null;
		assert game.isInRangeX(x) && game.isInRangeY(y);
	}
	
	/**
	 * Returns the character which represents the class.
	 * For {@link Empty} it is a white space ' ', for {@link Wall} it is '#', for {@link Goal} it is 'G'.
	 * 
	 * @return the character label representing the class
	 */
	
	protected char getCharLabel() {
		return ' ';
	}

	@Override
	public int positionX() {
		assert xAxis > -1;
		return xAxis;
	}

	@Override
	public int positionY() {
		assert yAxis > -1;
		return yAxis;
	}

	@Override
	public ITile moveAndLand(Indication ind) {
		assert ind != null;
		return  game.findTile(xAxis, yAxis, ind).landHereOrGoHome(xAxis,yAxis, ind);
	}

	@Override
	public void enter(IMovable mov) {
		assert !isOccupied();
		this.movable = mov;
		assert invariant();
	}

	@Override
	public void leave(IMovable mov) {
		assert this.movable==mov;
		this.movable = null;
		assert invariant();
	}

	@Override
	public boolean isOccupied() {
		return movable != null;
	}

	@Override
	public ITile landHereOrGoHome(int x, int y, Indication ind) {
		assert game.isInRangeX(x) && game.isInRangeY(y);
		assert ind !=null;
		if(this.isOccupied()) movable.move(ind);
		return this.isOccupied()? game.getTile(x,y): this ;
	}
	@Override
	public char toChar() {
		return isOccupied()? movable.toChar(): getCharLabel();
	}
	
	@Override
	public String toString() {
		return "Empty tile with X position " + positionX() + "and Y position " + positionY() + 
				". I am in the following game: " + game.toString();
	}
	

}
