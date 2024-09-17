package tests;
import sokoban.*;
import tile.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import indications.Down;
/**
 * Tests if the dependencies of {@link Parser} are taken, if all parameters from .sok files
 * are scanned correctly and are equal to what in the levels is given. It also checks if 
 * fail.sok and fail1.sok threw an exception (because they don't respect the conditions).
 * 
 */
public class ParserTest {

	/**
	 * @throws FileNotFoundException if the .sok file is not found
     * @throws WrongNumberOfPlayersException if the number of {@link Box} and {@link Goal} is different
     * @throws WrongNumberOfBoxOrGoalException if the number of {@link Player} is different then 1
	 * @throws IllegalTileException if there is an illegal Tile
	 */
	
	@Test
	public void testBasic1() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Parser p = new Parser();
		String a="basic1";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		
		//Testing Walls around the board
		
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[0][i].toChar());
		}
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[5][i].toChar());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals('#', board[i][0].toChar());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals('#', board[i][6].toChar());
		}
		
		//Testing 2nd line
		
		for(int i = 1; i < 6; i++) {
			assertEquals(' ', board[1][i].toChar());
		}
		
		//Testing 3rd line
		
		for(int i = 2; i < 6; i++) {
			assertEquals(' ', board[2][i].toChar());
		}
		assertEquals('P', board[2][1].toChar());
		  
		//Testing 4th line
		 
		assertEquals('#', board[3][1].toChar());
		assertEquals('#', board[3][2].toChar());
		assertEquals('B', board[3][3].toChar());
		assertEquals(' ', board[3][4].toChar());
		assertEquals(' ', board[3][5].toChar());
		
		//Testing 5th line
		
		assertEquals('#', board[4][1].toChar());
		assertEquals('#', board[4][2].toChar());
		assertEquals(' ', board[4][3].toChar());
		assertEquals(' ', board[4][4].toChar());
		assertEquals('G', board[4][5].toChar());
		
	}
	
	/**
     * @throws FileNotFoundException if the .sok file is not found
     * @throws WrongNumberOfPlayersException if the number of {@link Box} and {@link Goal} is different
     * @throws WrongNumberOfBoxOrGoalException if the number of {@link Player} is different then 1
	 * @throws IllegalTileException  if there is an illegal Tile
	 */
	
	@Test
	public void testBasic2() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		
		Parser p = new Parser();
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		
		//Testing Walls around the board
		
		for(int i = 0; i < 8; i++) {
			assertEquals('#', board[0][i].toChar());
		}
		for(int i = 0; i < 8; i++) {
			assertEquals('#', board[6][i].toChar());
		}
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[i][0].toChar());
		}
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[i][7].toChar());
		}
		
		//Testing 2nd line
		
		for(int i = 1; i < 7; i++) {
			assertEquals(' ', board[1][i].toChar());
		}
		
		//Testing 3rd line
		
		for(int i = 1; i < 3; i++) {
			assertEquals(' ', board[2][i].toChar());
		}
		assertEquals('P', board[2][6].toChar());
		assertEquals('B', board[2][3].toChar());
		assertEquals(' ', board[2][4].toChar());
		assertEquals(' ', board[2][5].toChar());
		  
		//Testing 4th line
		 
		assertEquals('#', board[3][1].toChar());
		assertEquals('#', board[3][2].toChar());
		assertEquals('B', board[3][3].toChar());
		assertEquals(' ', board[3][4].toChar());
		assertEquals(' ', board[3][5].toChar());
		assertEquals(' ', board[3][6].toChar());
		
		//Testing 5th line
		
		assertEquals(' ', board[4][1].toChar());
		assertEquals(' ', board[4][2].toChar());
		assertEquals(' ', board[4][3].toChar());
		assertEquals('#', board[4][4].toChar());
		assertEquals('#', board[4][5].toChar());
		assertEquals('#', board[4][6].toChar());
		
		//Testing 6th line
		
		assertEquals(' ', board[5][1].toChar());
		assertEquals(' ', board[5][2].toChar());
		assertEquals(' ', board[5][3].toChar());
		assertEquals(' ', board[5][4].toChar());
		assertEquals('G', board[5][5].toChar());
		assertEquals('G', board[5][6].toChar());
	}
	
	/**
	 * Checks if the exceptions are thrown correctly if the number of player is different from 1,
	 * if the number of boxes don't match the goals, if a casual file like "ciao.sok" doesn't exist and
	 * if there is an illegal Tile.
	 * 
     * @throws WrongNumberOfPlayersException if the number of {@link Box} and {@link Goal} is different
     * @throws WrongNumberOfBoxOrGoalException if the number of {@link Player} is different then 1
	 * @throws FileNotFoundException thrown if the file isn't found
	 */
	
	@Test
    public void testFail() throws FileNotFoundException {
		
		Parser p = new Parser();
		
		String a="fail1";
		String b="fail";

		Scanner scan = new Scanner(System.in);
		
		Assertions.assertThrows(WrongNumberOfPlayersException.class, () -> {
			p.parser("./levels/"+a+".sok", scan);});
		
		Assertions.assertThrows(WrongNumberOfBoxOrGoalException.class, () -> {
			p.parser("./levels/"+b+".sok", scan);});
		
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			p.parser("./levels/"+"ciao"+".sok", scan);});
		
		Assertions.assertThrows(IllegalTileException.class, () -> {
			p.parser("./levels/basic4.sok", scan);});
		
    }
	@Test
	public void testBasic3() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		Parser p = new Parser();
		String a="basic3";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		
		//Testing Walls around the board
		
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[0][i].toChar());
		}
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[5][i].toChar());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals('#', board[i][0].toChar());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals('#', board[i][6].toChar());
		}
		
		//Testing 2nd line
		
		assertEquals('C', board[1][1].toChar());
		
		for(int i = 2; i < 6; i++) {
			assertEquals(' ', board[1][i].toChar());
		}
		
		//Testing 3rd line
		
		for(int i = 1; i < 5; i++) {
			assertEquals(' ', board[2][i].toChar());
		}
		assertEquals('P', board[2][5].toChar());
		  
		//Testing 4th line
		 
		assertEquals('#', board[3][1].toChar());
		assertEquals('#', board[3][2].toChar());
		assertEquals(' ', board[3][3].toChar());
		assertEquals(' ', board[3][4].toChar());
		assertEquals('B', board[3][5].toChar());
		
		//Testing 5th line
		
		assertEquals('#', board[4][1].toChar());
		assertEquals('#', board[4][2].toChar());
		assertEquals(' ', board[4][3].toChar());
		assertEquals(' ', board[4][4].toChar());
		assertEquals('G', board[4][5].toChar());
		assertTrue(game.notOver());
		//Testing C after Player pushed Box also having another C
		
		game.movePlayer(new Down());
		game.play();
	    assertTrue(game.isOver());
		
        board = game.getBoard();
		
		//Testing Walls around the board
		
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[0][i].toChar());
		}
		for(int i = 0; i < 7; i++) {
			assertEquals('#', board[5][i].toChar());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals('#', board[i][0].toChar());
		}
		for(int i = 0; i < 6; i++) {
			assertEquals('#', board[i][6].toChar());
		}
		
		//Testing 2nd line
		
		assertEquals('C', board[1][1].toChar());
		
		for(int i = 2; i < 6; i++) {
			assertEquals(' ', board[1][i].toChar());
		}
		
		//Testing 3rd line
		
		for(int i = 1; i < 5; i++) {
			assertEquals(' ', board[2][i].toChar());
		}
		assertEquals(' ', board[2][5].toChar());
		  
		//Testing 4th line
		 
		assertEquals('#', board[3][1].toChar());
		assertEquals('#', board[3][2].toChar());
		assertEquals(' ', board[3][3].toChar());
		assertEquals(' ', board[3][4].toChar());
		assertEquals('P', board[3][5].toChar());
		
		//Testing 5th line
		
		assertEquals('#', board[4][1].toChar());
		assertEquals('#', board[4][2].toChar());
		assertEquals(' ', board[4][3].toChar());
		assertEquals(' ', board[4][4].toChar());
		assertEquals('C', board[4][5].toChar());
	}

	@Test
	public void testBasic5() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		Parser p = new Parser();
		String a="basic5";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		
		String line0 = "##################";
		String line1 = "#               P#";
		String line2 = "## ###############";
		String line3 = "#              BG#";
		String line4 = "############## ###"; 
		String line5 = "#GB              #"; 
		String line6 = "################B#";
		String line7 = "#C               #";
		String line8 = "#G   B # #GB    G#"; 
		String line9 = "##################";
		
		//Testing first line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line0.charAt(i), board[0][i].toChar());
		
		//Testing second line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line1.charAt(i), board[1][i].toChar());
		
		//Testing third line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line2.charAt(i), board[2][i].toChar());
		
		//Testing 4th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line3.charAt(i), board[3][i].toChar());

		//Testing 5th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line4.charAt(i), board[4][i].toChar());

		//Testing 6th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line5.charAt(i), board[5][i].toChar());

		//Testing 7th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line6.charAt(i), board[6][i].toChar());

		//Testing 8th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line7.charAt(i), board[7][i].toChar());

		//Testing 9th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line8.charAt(i), board[8][i].toChar());

		//Testing 10th line
		for(int i = 0; i < 18; i++ ) 
			assertEquals(line9.charAt(i), board[9][i].toChar());
	}
	
	@Test
	public void testBasic6() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		Parser p = new Parser();
		String a="basic6";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		String line00 = "###############\n";
		String line01 = "#            P#\n"; 
		String line02 = "#     B   #####\n";
		String line03 = "###B          #\n";
		String line04 = "#         G   #\n";
		String line05 = "########## ####\n";
		String line06 = "#G            #\n";
		String line07 = "######   # B  #\n";
		String line08 = "#G   #   #  B #\n";
		String line09 = "###C ##### ####\n";
		String line10 = "###         ###\n";
		String line11 = "#G          ###\n";
		String line12 = "###        ####\n";
		String line13 = "###############\n";

		//Testing first line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line00.charAt(i), board[0][i].toChar());

		//Testing second line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line01.charAt(i), board[1][i].toChar());

		//Testing third line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line02.charAt(i), board[2][i].toChar());

		//Testing 4th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line03.charAt(i), board[3][i].toChar());

		//Testing 5th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line04.charAt(i), board[4][i].toChar());

		//Testing 6th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line05.charAt(i), board[5][i].toChar());

		//Testing 7th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line06.charAt(i), board[6][i].toChar());

		//Testing 8th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line07.charAt(i), board[7][i].toChar());

		//Testing 9th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line08.charAt(i), board[8][i].toChar());

		//Testing 10th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line09.charAt(i), board[9][i].toChar());

		//Testing 11th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line10.charAt(i), board[10][i].toChar());

		//Testing 12th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line11.charAt(i), board[11][i].toChar());

		//Testing 13th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line12.charAt(i), board[12][i].toChar());
		
		//Testing 13th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line13.charAt(i), board[13][i].toChar());
	}
	
	@Test
	public void testBasic7() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		Parser p = new Parser();
		String a="basic7";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		String line00 = "###############\n";
		String line01 = "#####  ###  G##\n"; 
		String line02 = "#G           ##\n";
		String line03 = "####  #### C  #\n";
		String line04 = "#        ## GG#\n";
		String line05 = "#####G    #####\n";
		String line06 = "#   ###      P#\n";
		String line07 = "# B #   #######\n";
		String line08 = "# B #   #    ##\n";
		String line09 = "#   #   #   B##\n";
		String line10 = "##  #   #  # ##\n";
		String line11 = "#       BB    #\n";
		String line12 = "####   #  #   #\n";
		String line13 = "###############\n";

		//Testing first line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line00.charAt(i), board[0][i].toChar());

		//Testing second line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line01.charAt(i), board[1][i].toChar());

		//Testing third line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line02.charAt(i), board[2][i].toChar());

		//Testing 4th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line03.charAt(i), board[3][i].toChar());

		//Testing 5th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line04.charAt(i), board[4][i].toChar());

		//Testing 6th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line05.charAt(i), board[5][i].toChar());

		//Testing 7th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line06.charAt(i), board[6][i].toChar());

		//Testing 8th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line07.charAt(i), board[7][i].toChar());

		//Testing 9th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line08.charAt(i), board[8][i].toChar());

		//Testing 10th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line09.charAt(i), board[9][i].toChar());

		//Testing 11th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line10.charAt(i), board[10][i].toChar());

		//Testing 12th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line11.charAt(i), board[11][i].toChar());

		//Testing 13th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line12.charAt(i), board[12][i].toChar());
		
		//Testing 14th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line13.charAt(i), board[13][i].toChar());
	}
	
	public void testBasic8() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		Parser p = new Parser();
		String a="basic8";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		
		ITile[][] board = game.getBoard();
		String line00 = "###############\n";
		String line01 = "#    #G  G#   #\n"; 
		String line02 = "#  BB #  #  B #\n";
		String line03 = "#   B #  # BB #\n";
		String line04 = "#     # B#    #\n";
		String line05 = "#   ##G  G##  #\n";
		String line06 = "#   #   #     #\n";
		String line07 = "##         #  #\n";
		String line08 = "## #### ### ###\n";
		String line09 = "#   ##G P#G  ##\n";
		String line10 = "#   ###  #  BG#\n";
		String line11 = "##     B      #\n";
		String line12 = "## #  # ## G#G#\n";
		String line13 = "###############\n";

		//Testing first line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line00.charAt(i), board[0][i].toChar());

		//Testing second line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line01.charAt(i), board[1][i].toChar());

		//Testing third line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line02.charAt(i), board[2][i].toChar());

		//Testing 4th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line03.charAt(i), board[3][i].toChar());

		//Testing 5th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line04.charAt(i), board[4][i].toChar());

		//Testing 6th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line05.charAt(i), board[5][i].toChar());

		//Testing 7th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line06.charAt(i), board[6][i].toChar());

		//Testing 8th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line07.charAt(i), board[7][i].toChar());

		//Testing 9th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line08.charAt(i), board[8][i].toChar());

		//Testing 10th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line09.charAt(i), board[9][i].toChar());

		//Testing 11th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line10.charAt(i), board[10][i].toChar());

		//Testing 12th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line11.charAt(i), board[11][i].toChar());

		//Testing 13th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line12.charAt(i), board[12][i].toChar());
		
		//Testing 14th line
		for(int i = 0; i < game.getWidth(); i++ ) 
			assertEquals(line13.charAt(i), board[13][i].toChar());
	}
}
