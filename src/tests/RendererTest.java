package tests;
import sokoban.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests if the dependencies of {@link Renderer} are taken. Checks if render(board) outputs
 * the same as the .sok level. Tests are done for Basic1.sok, Basic2.sok and also basic3.sok.
 * 
 * @throws FileNotFoundException if the .sok file is not found
 * @throws WrongNumberOfPlayersException if the number of {@link Box} and {@link Goal} is different
 * @throws WrongNumberOfBoxOrGoalException if the number of {@link Player} is different then 1
 * @throws IllegalTileException if there is an illegal Tile
 */

public class RendererTest {
	
	@Test
	public void testRenderBasic1() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Parser p = new Parser();;
		String a="basic1";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 
						"#######\n" +
						"#     #\n" + 
						"#P    #\n" +
						"###B  #\n" +
						"###  G#\n" + 
						"#######\n";
		

		String test = r.render(game);
		assertEquals(expected, test);
	}
	
	@Test
	public void testRenderBasic2() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {
		Parser p = new Parser();;
		String a="basic2";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 	"########\n" + 
							"#      #\n" +
							"#  B  P#\n" +
							"###B   #\n" + 
							"#   ####\n" + 
							"#    GG#\n" + 
							"########\n";
		
		
		String test = r.render(game);
		assertEquals(expected, test);
	}
	
	@Test
	public void testRenderBasic3() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Parser p = new Parser();;
		String a="basic3";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 
						"#######\n" +
						"#C    #\n" + 
						"#    P#\n" +
						"###  B#\n" +
						"###  G#\n" + 
						"#######\n";
		

		String test = r.render(game);
		assertEquals(expected, test);
	}
	
	@Test
	public void testRenderBasic5() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		Parser p = new Parser();;
		String a="basic5";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 
						"##################\n" +
						"#               P#\n" + 
						"## ###############\n" +
						"#              BG#\n" +
						"############## ###\n" + 
						"#GB              #\n" + 
						"################B#\n" +
						"#C               #\n" +
						"#G   B # #GB    G#\n" + 
						"##################\n";
		

		String test = r.render(game);
		assertEquals(expected, test);
	}
	
	@Test
	public void testRenderBasic6() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Parser p = new Parser();;
		String a="basic6";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 
						"###############\n" +
						"#            P#\n" + 
						"#     B   #####\n" +
						"###B          #\n" +
						"#         G   #\n" + 
						"########## ####\n" +
						"#G            #\n" +
						"######   # B  #\n" +
						"#G   #   #  B #\n" +
						"###C ##### ####\n" +
						"###         ###\n" +
						"#G          ###\n" +
						"###        ####\n" +
						"###############\n";
		

		String test = r.render(game);
		assertEquals(expected, test);
	}
	
	@Test
	public void testRenderBasic7() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Parser p = new Parser();;
		String a="basic7";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 
						"###############\n" +
						"#####  ###  G##\n" + 
						"#G           ##\n" +
						"####  #### C  #\n" +
						"#        ## GG#\n" + 
						"#####G    #####\n" +
						"#   ###      P#\n" +
						"# B #   #######\n" +
						"# B #   #    ##\n" +
						"#   #   #   B##\n" +
						"##  #   #  # ##\n" +
						"#       BB    #\n" +
						"####   #  #   #\n" +
						"###############\n";
		

		String test = r.render(game);
		assertEquals(expected, test);
	}
	
	@Test
	public void testRenderBasic8() throws FileNotFoundException, WrongNumberOfPlayersException, WrongNumberOfBoxOrGoalException, IllegalTileException {

		Parser p = new Parser();;
		String a="basic8";
		Scanner scan = new Scanner(System.in);
		Game game = p.parser("./levels/"+a+".sok", scan);
		Renderer r = new Renderer();
		String expected = 
						"###############\n" +
						"#    #G  G#   #\n" + 
						"#  BB #  #  B #\n" +
						"#   B #  # BB #\n" +
						"#     # B#    #\n" + 
						"#   ##G  G##  #\n" +
						"#   #   #     #\n" +
						"##         #  #\n" +
						"## #### ### ###\n" +
						"#   ##G P#G  ##\n" +
						"#   ###  #  BG#\n" +
						"##     B      #\n" +
						"## #  # ## G#G#\n" +
						"###############\n";
		

		String test = r.render(game);
		assertEquals(expected, test);
	}
}
