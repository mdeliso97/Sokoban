package tile;

import indications.Indication;
import sokoban.*;

/**
 * Extends the {@link Empty}, because of that they are very similar. It stores a {@link movable.IMovable}, a {@link Game} and
 * its coordinates on the board of the game (which all of them must be valid). Its main difference are:
 * the character representing it (which is '#'),
 * its class invariance (there should be {@link movable.IMovable} on it),
 * {@link ITile#isOccupied()} (which returns always true),
 * {@link ITile#toChar()} (which return only the character label)
 * and {@link ITile#landHereOrGoHome(int, int, Indication)} (which returns only the Tile where the movable came from).
 * <p> It's used as the other tiles by the game and {@link Parser} (when it creates a game from a .sok file) and by the 
 * movable if they try to enter in it, but they are not allowed to do so.
 */

public class Wall extends Empty{

	@Override
	public boolean invariant() {
		return xAxis>0 && yAxis>0 && game != null && movable==null;
	}
	public Wall(Game g, int x, int y) {
		super(g, x, y);
	}
	

	@Override
	protected char getCharLabel() {
		return '#';
	}
	
	@Override
	public boolean isOccupied() {
		return true;
	}
	@Override
	public char toChar() {
		return getCharLabel();
	}

	
	@Override
	public ITile landHereOrGoHome(int x, int y, Indication ind) {
		assert game.isInRangeX(x) && game.isInRangeY(y);
		assert ind !=null;
		return game.getTile(x, y);
	}
	
	@Override
	public String toString() {
		return "Wall tile with X position " + positionX() + "and Y position " + positionY() + 
				". I am in the following game: " + game.toString();
	}
}
