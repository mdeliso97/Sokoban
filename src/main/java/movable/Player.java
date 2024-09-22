package movable;

import indications.Indication;
import sokoban.Game;
import tile.*;

/**
 * Defines the moving object Player. This object must be able to move through the board and also move {@link Box} in the 
 * game. Its main responsibilities are: enter the object player in a {@link ITile} giving the coordinates through the method
 * joinGame from {@link IMovable#joinGame(Game, int, int)}, replace his current position if it's moving by an {@link Empty} {@link ITile} 
 * (so it holds nothing), move boxes by pushing them (simply moving into a {@link ITile} which is occupied by a {@link Box} which is also 
 * moved in the same direction the player moved) and setting his representation symbol 'P' as player in the board. Like {@link Box}, the 
 * player isn't able to move through {@link Wall}, also if he tries to push a {@link Box} which has a {@link Wall} in the same direction, 
 * both can't move. In the game is allowed only one player. If there are two {@link Box} in the same way the {@link Player} wants to move,
 * all three will be moved simultaneously.
 *
 */

public class Player implements IMovable{

	private ITile tile;
	
/**
 * Defines the method {@link IMovable#joinGame(Game, int, int)} which allows a new player to join the game (be placed into a {@link ITile}). 
 * There are also the parameters checks, which can't be null (in case of {@link Game} and {@link ITile}) and the coordinates 
 * must be in range of the board (isInRange methods).
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
 * Enables the player to move around the board. Checks whether the tile is null, if not leaves the current
 * tile and tries to enter in the next one in which is moved, for more details @see {@link ITile#moveAndLand(Indication)}
 * 
 * @param ind {@link Indication} in which the player moves
 * 
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

/**
 * Defines the player as character 'P'
 * 
 */
	
	@Override
	public char toChar() {
		return 'P';
	}
	@Override
	public ITile getTile() {
		assert tile != null;
		assert !(tile instanceof Wall);
		return tile;
	}
	

	@Override
	public String toString() {
		return "I am the Player and I am in a " + tile.toString();
	}

}
