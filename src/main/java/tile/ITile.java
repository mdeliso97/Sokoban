package tile;
import indications.Indication;
import movable.IMovable;
import sokoban.*;

/**
 * API for all types of tiles.
 *
 * A tile must always be part of a {@link Game}.
 */

public interface ITile extends IPrintable {
	
	/**
	 * Obtain x position in the host {@link Game}.
	 *
	 * @return x position in the game
	 */
	
	int positionX();
	
	/**
	 * Obtain y position in the host {@link Game}.
	 *
	 * @return y position in the game
	 */
	
	int positionY();
	
	/**
	 * Tries to move a {@link IMovable} away from this square and land on
	 * the {@link ITile} next from the original one on the direction of the given {@link Indication}.
	 * It does this, by calling {@link Game#findTile(int, int, Indication)} to find the new {@link ITile},
	 * than call the {@link ITile#landHereOrGoHome(int, int, Indication)} of it.
	 *
	 * @param ind {@link Indication} in which the {@link IMovable} should move
	 * @return {@link ITile} on which the {@link IMovable} rests after landing and executing all actions
	 */
	
	ITile moveAndLand(Indication ind);
	
	/**
	 * Stores the given {@link IMovable} in this {@link ITile}, should be called when landing on a {@link ITile}.
	 * If the movable is a {@link movable.Box} and the tile is a {@link Goal}, it calls {@link Game#increaseBoxOnGoalCounter()}.
	 * Also each time a box enters a goal it is checked from the goal if the number of boxes on goals is the same of 
	 * the number of boxes, in which case it calls {@link Game#makeGameOver()}.
	 * The goal checks if the movable is a box, by looking at its {@link IMovable#toChar()}.
	 *
	 * @param mov the {@link IMovable} that lands on this {@link ITile}
	 */
	
	void enter(IMovable mov);
	
	/**
	 * Removes the given {@link IMovable} from the {@link ITile}, should be called when leaving a {@link ITile}.
	 * If the movable is a box and the tile is a goal, it calls {@link Game#decreaseBoxOnGoalCounter()}.
	 *
	 * @param mov the {@link IMovable} that leaves this {@link ITile}
	 */
	
	void leave(IMovable mov);
	
	/**
	 * Checks whether there's still free place for {@link IMovable} on this {@link ITile}.
	 * If it is a {@link Wall} it will return always true, because they should not be accessible.
	 *
	 * @return true, if this tile can not register movable
	 */
	
	boolean isOccupied();
	
	/**
	 * Land on this {@link ITile} and execute any associated logic.
	 *
	 * In particular, this should include testing whether there is free place
	 * on the tile and performing other actions, such as increasing or decreasing the boxOnGoalCounter of {@link Game}
	 * If the tile is occupied from an other {@link IMovable}, it first tells it to move in the same direction of the first movable,
	 * then checks if it did. Only then can the other movable land on this tile. Otherwise (if the movable on this tile did not 
	 * moved or the tile is a {@link Wall}, the movable (trying to land here) is sent where he came from.
	 *
	 * @param x coordinate of the tile, from which the movable came from
	 * @param y coordinate of the tile, from which the movable came from
	 * @param ind indication, in which the movable is trying to move
	 * @return the tile on which the movable should rest after executing all associated actions
	 */
	
	ITile landHereOrGoHome(int x, int y, Indication ind);

}
