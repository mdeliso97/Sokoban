package movable;

import indications.Indication;
import sokoban.Game;
import tile.ITile;
import tile.Wall;

/**
 * Defines the moving object Box. This object must be able to be moved by a {@link Player} such like replicating the
 * same movement which was done by the {@link Player} (same direction). Its main responsibilities are: enter Boxes in
 * the game as object with the method joinGame from {@link IMovable#joinGame(Game, int, int)}, replace his current position if moved from 
 * {@link Player}, of course move (important: the box isn't allowed to move independently, it's an object which can be 
 * moved by other objects, can't be moved like a {@link Player}) and setting his representation symbol 'B'. Like
 * {@link Player}, Boxes aren't able to move through {@link Wall}. The number of boxes in the game must match the amount 
 * of {@link tile.Goal}. Boxes can be moved into Tiles which are occupied by a {@link tile.Goal} and that's also the requirement to
 * win the game.
 *
 */

public class Box implements IMovable{
	
	private ITile tile;

/**
 * Defines the method {@link IMovable#joinGame(Game, int, int)} which allows a new box to join the game (be placed into a {@link ITile}).
 * There are also checked the parameters, which can't be null in case of {@link Game} and {@link ITile} and the coordinates must be
 * in the range of the board (isInRange methods).
 * 
 */
	
	@Override
	public void joinGame(Game game, int x, int y) {
		assert game !=null;
		assert game.isInRangeX(x)&&game.isInRangeY(y);
		tile = game.getTile(x, y);
		tile.enter(this);
		assert tile != null;
		assert !(tile instanceof Wall);
	}

	@Override
	public int positionX() {
		assert tile != null;
		assert !(tile instanceof Wall);
		return tile.positionX();
	}

	@Override
	public int positionY() {
		assert tile != null;
		assert !(tile instanceof Wall);
		return tile.positionY();
	}

/**
 * Enables the Box to be moved from a player. Checks whether the tile is null, if not leaves the current
 * tile and tries to enter in the next one in which is moved, for more details @see {@link ITile#moveAndLand(Indication)}.
 *  
 * @param ind {@link Indication} in which the box moves
 */
	
	@Override
	public void move(Indication ind) {
		assert tile != null;
		assert !(tile instanceof Wall);
		tile.leave(this);
		tile = tile.moveAndLand(ind);
		tile.enter(this);
		assert tile != null;
		assert !(tile instanceof Wall);
	}

	@Override
	public ITile getTile() {
		assert tile != null;
		assert !(tile instanceof Wall);
		return tile;
	}

/**
 * Defines the Box as character 'B'
 * 
 */
	
	@Override
	public char toChar() {
		return 'B';
	}
	
	@Override
	public String toString() {
		assert tile != null;
		return "I am a Box and I am in a " + tile.toString();
	}

}
