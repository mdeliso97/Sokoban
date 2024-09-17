package tests;

import org.junit.jupiter.api.Test;
import indications.Left;
import movable.Box;
import movable.Player;
import sokoban.Game;
import tile.Empty;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

/**
 * These tests are made to check if the two movable objects {@link Box} and {@link Player} interact well with each other.
 * We also test if the {@link Box} doesn't move after pushed against a wall.
 * 
 */

public class BoxAndPlayerTest {

	// Checks if after moving player it takes the box position and the box moves in the same direction of player
	
	@Test
	public void testPlayerAgainstBox() {
		Scanner scan = new Scanner(System.in);
		Game game = spy(new Game(100, 100, scan));
		Player player = new Player();
		Box box = new Box();
		Left left = new Left(); // Setting initial parameters
		
		game.addBox(box, 2, 4, new Empty(game, 2, 4));
		game.setPlayer(player, 2,5);
		
		assertEquals(2, box.positionX());
		assertEquals(4, box.positionY());
		assertEquals(2, player.positionX());
		assertEquals(5, player.positionY()); //Checks if the current position of Box and Player are correct
		
		game.movePlayer(left);
		
		assertEquals(2, box.positionX());
		assertEquals(3, box.positionY());
		assertEquals(2, player.positionX());
		assertEquals(4, player.positionY()); //Checks if the box has successfully moved in the same direction of player
	}
	
	// Checks if the player moves against the box which can't move because of the wall
	
	@Test
	public void testPlayerAgainstBoxAgainstWall() {

		Scanner scan = new Scanner(System.in);
		Game game = spy(new Game( 100, 100, scan));
		Player player = new Player();
		Box box = new Box();
		Left left = new Left(); // Setting initial parameters
		
		game.addBox(box, 2, 1, new Empty(game, 2, 1));
		game.setPlayer(player, 2,2);
		
		assertEquals(2, box.positionX());
		assertEquals(1, box.positionY());
		assertEquals(2, player.positionX());
		assertEquals(2, player.positionY()); //Checks if the current position of Box and Player are correct
		
		game.movePlayer(left);
		
		assertEquals(2, box.positionX());
		assertEquals(1, box.positionY());
		assertEquals(2, player.positionX());
		assertEquals(2, player.positionY()); //Checks after trying to move player the position of both is still the same
	}
}
