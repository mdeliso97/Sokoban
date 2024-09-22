package sokoban;
import sokoban.*;
import tile.ITile;
import org.junit.jupiter.api.Test;
import movable.Player;
import indications.*;
import tile.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests the player such like his movements, what he might do and don't do (e.g moving through a wall)
 *
 */

public class PlayerTest {
	
	@Test
	public void testMove() {
		Player player = new Player(); //created the player
		
		Game game = mock(Game.class);
		ITile mockTile = mock(ITile.class);
		ITile mockTileUp = mock(ITile.class);
		ITile mockTileRight = mock(ITile.class);
		ITile mockTileDown = mock(ITile.class);
		ITile mockTileLeft = mock(ITile.class);
		Up up = mock(Up.class);
		Down down= mock(Down.class);
		Left left = mock(Left.class);
		Right right = mock(Right.class); //mocked the game, some tiles and indications
		
		
		when(game.getTile(2, 3)).thenReturn(mockTile); //prepared the game for the joinGame of the player

		when(mockTile.moveAndLand(up)).thenReturn(mockTileUp);
		when(mockTileUp.moveAndLand(right)).thenReturn(mockTileRight);
		when(mockTileRight.moveAndLand(down)).thenReturn(mockTileDown);
		when(mockTileDown.moveAndLand(left)).thenReturn(mockTileLeft);//prepared the moveAndLand of the tiles
		
		when(mockTileUp.positionX()).thenReturn(1);
		when(mockTileUp.positionY()).thenReturn(3);
		when(mockTileRight.positionX()).thenReturn(1);
		when(mockTileRight.positionY()).thenReturn(4);
		when(mockTileDown.positionX()).thenReturn(2);
		when(mockTileDown.positionY()).thenReturn(4);
		when(mockTileLeft.positionX()).thenReturn(2);
		when(mockTileLeft.positionY()).thenReturn(3); //just stubbed the positions of the mocked tiles

		when(game.isInRangeX(2)).thenReturn(true);
		when(game.isInRangeY(3)).thenReturn(true); //stubbed for the asserts of joinGame 
		
		
		player.joinGame(game, 2, 3);
		
		
		player.move(up);
		assertEquals(3, player.positionY());
		assertEquals(1, player.positionX());
		assertEquals(mockTileUp, player.getTile());//checked if the position is correct for the move up from mockTile
		
		player.move(right);
		assertEquals(4, player.positionY());
		assertEquals(1, player.positionX());
		assertEquals(mockTileRight, player.getTile());//checked if the position is correct for the move right from mockTileUp
		
		player.move(down);
		assertEquals(4, player.positionY());
		assertEquals(2, player.positionX());
		assertEquals(mockTileDown, player.getTile());//checked if the position is correct for the move down from mockTileRight
		
		player.move(left);
		assertEquals(3, player.positionY());
		assertEquals(2, player.positionX());
		assertEquals(mockTileLeft, player.getTile());//checked if the position is correct for the move left from mockTileDown
	}
	
	@Test
	public void testMoveAgainstWall() {
		Game game = mock(Game.class);
		ITile start = mock(ITile.class);
		Down down= mock(Down.class); //mocked some classes
		Player player = new Player(); //created a player

		when(game.isInRangeX(3)).thenReturn(true);
		when(game.isInRangeY(3)).thenReturn(true);
		when(game.isInRangeX(2)).thenReturn(true);//stubbed the isInRange of the mocked game

		Wall w = new Wall(game,3,3);//created a wall

		when(game.getTile(2, 3)).thenReturn(start);
		ITile end = w.landHereOrGoHome(2, 3, down);
		when(start.moveAndLand(down)).thenReturn(end);//stubbed the method called from the mocked clasess
		
		player.joinGame(game, 2, 3);
		
		player.move(down);
		
		assertEquals(start, end);
		assertEquals(start, player.getTile());//asserts that he did not moved
		
	}
}
