package tests;

import sokoban.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import indications.Down;
import indications.Right;
import indications.Up;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In here are tested the implementations of history class, in detail of the methods {@link History#add(Game game)}, {@link History#undo(Scanner scanner)}
 *  and {@link History#redo(Scanner scanner)}.
 */

public class HistoryTest {

	/**
	 * In this test part is the method add tested, in particular if the the ArrayList is correctly filled after a move and if the
	 * Index in {@link History#add(Game game)} increments correctly after adding elements in the ArrayList.
	 * 
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	
	@Test
	public void testAdd() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Parser p = new Parser();
		Game gameTest = p.parser("./levels/"+a+".sok", scan);
		History history = new History(); // initialized the initial parameters needed for testing
		
		assertEquals(-1, history.getIndex()); // Index should initially be -1 as initialized
		assertEquals("Empty", history.getCurrentSaveInformation()); // The history array should be empty at the beginning
		
		history.add(gameTest); // added a game in the ArrayList

		String start = gameTest.getSaveInformation();
		assertEquals(0, history.getIndex()); // Checks if the index value increments after adding an element in the history
		assertEquals(start, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
        														  // the actual one.
		
		gameTest.movePlayer(new Up()); // player moves up
		String playerMovedUp = gameTest.getSaveInformation();
		
		history.add(gameTest); // added gameTest in the ArrayList
		
		assertEquals(1, history.getIndex()); // Checks if the Index has changed value after a player move.
		assertEquals(playerMovedUp, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
		                                                                  // the actual one.
	}
	
	/**
	 * This is a test for the {@link History#undo(Scanner scanner)}, in particular, there are as always the initial condition checked. After that is checked after moving the player, 
	 * checked the actual situation after the input "undo" of the human, if the game has changed correctly and the actually board is the 
	 * previous one, and also that if the undo input is made multiple times without that the human has moved pore than 1 time, it should do
	 * nothing.
	 * 
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	
	@Test
	public void testUndo() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Parser p = new Parser();
		Game gameTest = p.parser("./levels/"+a+".sok", scan);
		History history = new History(); // initialized the initial parameters needed for testing 
		
		assertEquals(-1, history.getIndex()); // Index should initially be -1 as initialized
		
		history.add(gameTest); // added a game in the ArrayList

		String start = gameTest.getSaveInformation();
		assertEquals(0, history.getIndex()); // Checks if the index value increments after adding an element in the history
		assertEquals(start, history.getCurrentSaveInformation());
		
		gameTest.movePlayer(new Up()); // player moves up
		String playerMovedUp = gameTest.getSaveInformation();
		
		history.add(gameTest); // added a game in the ArrayList
		
		assertEquals(1, history.getIndex()); // Checks if the index value increments after adding an element in the history 
		assertEquals(playerMovedUp, history.getCurrentSaveInformation());
		
		history.tryParser("undo", scan); // Simulates the human tipping undo on the console.
		
		assertEquals(0, history.getIndex()); // Checks if after tipping undo the Index has decremented correctly
		assertEquals(start, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
        													      // the actual one.
		
		history.tryParser("undo", scan); // Simulates the human tipping undo on the console.
		
		assertEquals(0, history.getIndex()); // Checks that after a second time tipping undo the Index doesn't changed.
		assertEquals(start, history.getCurrentSaveInformation()); // Checks that the ArrayList is still the same as above.
	}
	
	/**
	 * This is a test for the method {@link History#redo(Scanner scanner)}, in particular, there are as always the initial condition checked. After that is checked after moving the player, 
	 * checked the actual situation after the input "undo" of the human, if the game has changed correctly and the actually board is the 
	 * undone before, and also that it should do nothing if the undo input is made multiple times without that the human has moved more than 1 time.
	 * 
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	
	@Test
	public void testRedo() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Parser p = new Parser();
		Game gameTest = p.parser("./levels/"+a+".sok", scan);
		History history = new History(); // initialized the initial parameters needed for testing
		
		assertEquals(-1, history.getIndex()); // Index should initially be -1 as initialized
		
		history.add(gameTest); // added a game in the ArrayList

		String start = gameTest.getSaveInformation();
		assertEquals(0, history.getIndex()); // Checks if the index value increments after adding an element in the history.
		assertEquals(start, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
		  														  // the actual one.
		
		gameTest.movePlayer(new Up()); // Player moves up.
		String playerMovedUp = gameTest.getSaveInformation();
		
		history.add(gameTest); // added a game in the ArrayList
		
		assertEquals(1, history.getIndex()); // Checks if the index value increments after adding an element in the history.
		assertEquals(playerMovedUp, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
	      																  // the actual one.
		
		history.tryParser("undo", scan); // Simulates the human tipping undo on the console.
		
		assertEquals(0, history.getIndex()); // Checks if after tipping undo the Index has decremented correctly
		assertEquals(start, history.getCurrentSaveInformation());
		
		history.tryParser("redo", scan); // Simulates the human tipping redo on the console.
		assertEquals(1, history.getIndex()); // Checks if after tipping redo the Index has incremented correctly
		assertEquals(playerMovedUp, history.getCurrentSaveInformation()); // Checks as above.

		history.tryParser("redo", scan); // Simulates the human tipping redo on the console.
		assertEquals(1, history.getIndex()); // Checks if after tipping undo the Index has not changed.
		assertEquals(playerMovedUp, history.getCurrentSaveInformation()); // Checks that the ArrayList doesn't change
	}
	
	/**
	 * Here is tested if th method {@link SokobanPlay#loadGame(Game newGame)} is called by {@link Hisotry}, but as we couldn't test it because of some stuff that
	 * can't be mocked, it's tested indirectly. If History calls loadGame correctly (when she needs this parameter to undo/redo) 
	 * we know that it works fine, and after that it's also checked if the new {@link Game} is correctly actualized in {@link SokobanPlay}.
	 * 
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	
	@Test
	public void testLoadGame() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Parser p = new Parser();
		Game gameTest = p.parser("./levels/"+a+".sok", scan);
		History history = new History();
		SokobanPlay testSokobanPlay = new SokobanPlay(); // initialized the initial parameters needed for testing
		
		history.add(gameTest); // Adds a game in the ArrayList
		String start = gameTest.getSaveInformation();
		
		gameTest.movePlayer(new Up()); // Player moves up.
		
		history.add(gameTest); // Adds a game in the ArrayList
		String playerMovedUp = gameTest.getSaveInformation();
		
		history.tryParser("undo", scan); // Simulates the human tipping undo on the console.
		
		String loadedAfterUndo = testSokobanPlay.getCurrentGame().getSaveInformation(); 
		
		assertEquals(start, loadedAfterUndo); 
		
		history.tryParser("redo", scan); // Simulates the human tipping redo on the console.
		
		String loadedAfterRedo = testSokobanPlay.getCurrentGame().getSaveInformation();
		assertEquals(playerMovedUp, loadedAfterRedo); 
	}
	
	/**
	 * Tests if by calling {@link History#add(Game)} after an {@link History#undo} the ArrayList is upgraded.
	 * That means that if the Human undoes something and gives after that a new input, the old moves should be deleted
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	@Test
	public void testAddAfterUndo() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Parser p = new Parser();
		Game gameTest = p.parser("./levels/"+a+".sok", scan);
		History history = new History(); // initialized the initial parameters needed for testing 
		
		assertEquals(-1, history.getIndex()); // Index should initially be -1 as initialized
		
		history.add(gameTest); // added a game in the ArrayList

		String start = gameTest.getSaveInformation();
		assertEquals(0, history.getIndex()); // Checks if the index value increments after adding an element in the history
		assertEquals(start, history.getCurrentSaveInformation());
		
		gameTest.movePlayer(new Up()); // player moves up
		String playerMovedUp = gameTest.getSaveInformation();
		
		history.add(gameTest); // added a game in the ArrayList
		
		assertEquals(1, history.getIndex()); // Checks if the index value increments after adding an element in the history 
		assertEquals(playerMovedUp, history.getCurrentSaveInformation());
		
		gameTest.movePlayer(new Right()); // player moves up
		String playerMovedRight = gameTest.getSaveInformation();
		
		history.add(gameTest); // added a game in the ArrayList
		
		assertEquals(2, history.getIndex()); // Checks if the index value increments after adding an element in the history 
		assertEquals(playerMovedRight, history.getCurrentSaveInformation());
		
		history.tryParser("undo", scan); // Simulates the human tipping undo on the console.
		
		assertEquals(1, history.getIndex()); // Checks if after tipping undo the Index has decremented correctly
		assertEquals(playerMovedUp, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
        													      // the actual one.
		
		gameTest.movePlayer(new Down()); // New move after the undo is called
		String playerMovedDown = gameTest.getSaveInformation();
		
		history.add(gameTest); // added a game in the ArrayList
		
		assertEquals(2, history.getIndex()); // Checks if the index value increments after adding an element in the history
		assertEquals(playerMovedDown, history.getCurrentSaveInformation()); // Checks if the current value stored in the ArrayList is the same as 
        													      // the actual one and not the old one
	}
}
