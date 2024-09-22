package sokoban;

import sokoban.*;
import tile.*;
import movable.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import indications.Down;
import indications.Left;
import indications.Right;
import indications.Up;

import static org.mockito.Mockito.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

/**
 * Tests if the Main relationship between box and Goal is well implemented. In other words, it checks if the game
 * is really over, if the number of boxes and the number of boxes on goals matches.
 *
 */

public class BoxTest {
	
	/**
     * @throws FileNotFoundException if the .sok file is not found
     * @throws WrongNumberOfPlayersException if the number of {@link Box} and {@link Goal} is different
     * @throws WrongNumberOfBoxOrGoalException if the number of {@link Player} is different then 1
	 * @throws IllegalTileException 
	 */

	@Test
	public void testWin() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Scanner scan = new Scanner(System.in);
		Game game = spy(new Game( 50, 50, scan));
		Box one = mock(Box.class);
		Box two = mock(Box.class);
		Box three = mock(Box.class);
		Box four = mock(Box.class);
		Player player = mock(Player.class);
		ITile tile = mock(ITile.class);//mocked 4 boxes and maked a spy game
		
		when(one.toChar()).thenReturn('B');
		when(two.toChar()).thenReturn('B');
		when(three.toChar()).thenReturn('B');
		when(four.toChar()).thenReturn('B'); //stubbed toChar of the boxes
		
		game.addBox(one, 10,10, tile);
		game.addBox(two, 20, 20, tile);
		game.addBox(three, 30, 30, tile);
		game.addBox(four, 40, 40, tile); //added the boxes on the game
		
		
		Goal first = new Goal(game, 1, 2);
		Goal second =  new Goal(game, 2, 3);
		Goal third =  new Goal(game, 4, 4);
		Goal fourth = new Goal(game, 3, 1); //created 4 goals
		
		assertEquals(4, game.getGoalCounter()); //checks if the goals in the game are really 4
		
		assertFalse(game.isGameOver()); //Checks that the game is not over
		
		first.enter(one);
		second.enter(two);
		third.enter(three);
		fourth.enter(four); //now each box is in a goal
		
		assertEquals(4, game.getBoxOnGoalCounter());
		
		game.setPlayer(player, 48,47);
		
		game.play();//it is called just to show that at the end of the game it's printed something other than the board
		assertTrue(game.isGameOver());//checks if the goals are 4 and if the game is really over
	}
	@Test
	public void testBoxMove() {
		
		Game game = mock(Game.class);
		ITile start = mock(ITile.class);
		Box box = new Box();
		ITile mockTileUp = mock(ITile.class);
		ITile mockTileRight = mock(ITile.class);
		ITile mockTileDown = mock(ITile.class);
		ITile mockTileLeft = mock(ITile.class);
		Up up = mock(Up.class);
		Down down= mock(Down.class);
		Left left = mock(Left.class);
		Right right = mock(Right.class); //mocked the game, some tiles and indications
		
		when(game.isInRangeX(anyInt())).thenReturn(true);
		when(game.isInRangeY(anyInt())).thenReturn(true);
		when(game.getTile(83, 91)).thenReturn(start);
		when(start.moveAndLand(up)).thenReturn(mockTileUp);
		when(mockTileUp.moveAndLand(right)).thenReturn(mockTileRight);
		when(mockTileRight.moveAndLand(down)).thenReturn(mockTileDown);
		when(mockTileDown.moveAndLand(left)).thenReturn(mockTileLeft);//prepared the moveAndLand of the tiles
		
		box.joinGame(game, 83, 91);
		assertEquals(start, box.getTile());
		
		box.move(up);
		assertEquals(mockTileUp, box.getTile());
		
		box.move(right);
		assertEquals(mockTileRight, box.getTile());
		
		box.move(down);
		assertEquals(mockTileDown, box.getTile());
		
		box.move(left);
		assertEquals(mockTileLeft, box.getTile());	
	}
}
