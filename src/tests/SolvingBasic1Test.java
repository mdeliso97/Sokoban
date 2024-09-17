package tests;
import sokoban.*;
import indications.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
/**
 * Solved the basic1.sok. Before each movement of the player, there is printed the board and in which direction he went 
 * (if he goes more times than one in the same direction, it is written only once, but it is printed how many times did he 
 * moved in the given direction). It is checked sometimes if the game is over. At the end of the match it is called the 
 * {@link Game#play()}, just to show that at the end of a play, it is printed something that says that the game is over.
 *
 */
public class SolvingBasic1Test {
	
	/**
	 * 
     * @throws FileNotFoundException if the .sok file is not found
     * @throws WrongNumberOfPlayersException if the number of {@link Box} and {@link Goal} is different
     * @throws WrongNumberOfBoxOrGoalException if the number of {@link Player} is different then 1
	 * @throws IllegalTileException  if there is an illegal Tile
	 */
	@Test
	public void estSolvingBasic1() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException  {
		Parser p = new Parser();
		String a="basic1";
		
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);; //prepared the game
		
		Up up = new Up();
		Down down = new Down();
		Left left = new Left();
		Right right = new Right(); //prepared some indications
		
		
		System.out.print("Initial SetUp\n");
		
		game.printMe();


		System.out.print("The player is going 3 times right\n");
		assertFalse(game.isOver());
		game.movePlayer(right); //goes right
		game.printMe();
		
		game.movePlayer(right);//goes right
		game.printMe();
		
		game.movePlayer(right);//goes right
		game.printMe();
		

		System.out.print("The player is going 2 times down\n");
		assertFalse(game.isOver());
		game.movePlayer(down);//goes down
		game.printMe();
		
		game.movePlayer(down);//goes down
		game.printMe();
		

		System.out.print("The player is going left\n");
		assertFalse(game.isOver());
		game.movePlayer(left);//goes left
		game.printMe();
		

		System.out.print("The player is going up\n");
		game.movePlayer(up);//goes up
		assertFalse(game.isOver());
		game.printMe();

		System.out.print("The player is going right\n");
		game.movePlayer(right);
		game.printMe();

		System.out.print("The player is going 2 times up\n");
		game.movePlayer(up);//goes up
		assertFalse(game.isOver());
		game.printMe();
		
		game.movePlayer(up);//goes up
		game.printMe();

		System.out.print("The player is going 2 times left\n");
		assertFalse(game.isOver());//goes left
		game.movePlayer(left);
		game.printMe();
		
		game.movePlayer(left);//goes left
		assertFalse(game.isOver());
		game.printMe();

		System.out.print("The player is going down\n");
		game.movePlayer(down);//goes down
		assertFalse(game.isOver());
		game.printMe();

		System.out.print("The player is going 2 times right\n");
		game.movePlayer(right);//goes right
		assertFalse(game.isOver());
		game.printMe();
		
		game.movePlayer(right);//goes right
		game.printMe();

		System.out.print("The player is going up\n");
		game.movePlayer(up);//goes up
		game.printMe();

		System.out.print("The player is going right\n");
		game.movePlayer(right);//goes right
		assertFalse(game.isOver());
		game.printMe();

		System.out.print("The player is going 2 times down\n");
		assertFalse(game.isOver());
		game.movePlayer(down);//goes down
		
		assertFalse(game.isOver());
		game.printMe();
		
		assertFalse(game.isOver());
		game.movePlayer(down);//goes down

		game.play();//end Game message
		assertTrue(game.isOver()); //game is finally over
	}
}
