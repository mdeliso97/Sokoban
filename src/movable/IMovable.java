package movable;

import tile.ITile;
import sokoban.Game;
import indications.Indication;

/**
 * Holds the parameters which are important to implement the movement to movable object such like {@link Box} and {@link Player}.
 * There is a joinGame method which allows a movable object to enter the board in a given position (which must be in the board
 * dimensions) as x and y coordinates, a {@link Tile} position setter and also getter, a {@link Indication#move} which enables the objects to move
 * and a method which defines the object character.
 *
 */
public interface IMovable {
	
	void joinGame(Game game, int x, int y);

	int positionX();
	
	int positionY();
	
	void move(Indication ind);
	
	ITile getTile();

	char toChar();

}
