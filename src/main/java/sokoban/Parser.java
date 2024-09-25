package sokoban;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import tile.*;
import movable.*;

/**
 * Parses the given level in levels folder and returns a 2D Array of {@link Tile}.
 * Accepts file ".sok", translates what follows:
 * 'P' as {@link Player}, '#' as {@link Wall}, ' ' as {@link Empty}, 'B' as {@link Box} in an {@link Empty},
 * 'C' as {@link Box} in a {@link Goal}, 'G' as {@link Goal} without a {@link Box} on top of it. All others 
 * characters will throw an {@link IllegalTileException}.
 * Accepts only Games with exactly 1 Player and the number of Goals and Boxes must be the same. The first 
 * line of a ".sok" file must be the board dimension (two integers separated by a white space). If the given
 * dimensions doesn't correspond to the real board dimension, the board will miss characters (if the given 
 * dimensions are smaller) or add Nulls to the board (if the given dimension is bigger). The first parameter
 * must be the Width and the second parameter the Height.
 * It's discouraged to write a path of a file which does not exist.
 * It is possible to parse a game from a String by calling {@link Parser#parserContent(String, Scanner)}.
 * The given String must be respect the above rules the same way a .sok file does.
 * 
 */

public class Parser {
	
	/**
	 * Takes the parameters in .sok files using the scan function and outputs the whole file
	 * in the array result as strings. 
	 * It's discouraged to write a path of a file which does not exist.
	 * 
	 * @param a is the file name
	 * @return a translation of file in an array of strings
	 * @throws FileNotFoundException if the file is not found
	 */
	
	public static String load(String a) throws FileNotFoundException {
		assert a !=null;
        ArrayList<String> s = new ArrayList<>();
        	try (Scanner fileScan = new Scanner(new File(a))){
            	while (fileScan.hasNextLine()) 
            	{
                	s.add(fileScan.nextLine());
            	}
        }
        	String result = "";
        	for (String p : s) result += p + "\n";
        	return result;
	}
	
	/**
	 * Parses the symbols in the .sok files and translates them in {@link ITile} array.
	 * It's discouraged to write a path of a file which does not exist.
	 * 
	 * @param levelParser it's the name of the file .sok in levels's folder 
	 * @param scanner it is the scanner which need to be passed from {@link SokobanPlay}
	 * @return board of {@link ITile}
	 * @throws FileNotFoundException if the .sok file is not found
	 * @throws WrongNumberOfBoxOrGoalException if the number of {@link Box} and {@link Goal} is different
	 * @throws WrongNumberOfPlayersException if the number of {@link Player} is different then 1
	 * @throws NumberFormatException if the string does not contain a parsable integer.
	 */
	
	public Game parser(String levelParser, Scanner scanner) throws IllegalTileException, FileNotFoundException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		assert levelParser !=null;
		
		String content = load(levelParser);
		
		return parserContent(content, scanner);
	}

	/**
	 * Parsers the content of a String with the format of written in {@link Parser}
	 * @author Giorgio
	 * @param content from which the new {@link Game} is going to be parsed
	 * @param scanner that need to be passed to the new {@link Game}
	 * @return a new {@link Game} which is parsed based on the given string. This game has the same Scanner given
	 * @throws WrongNumberOfBoxOrGoalException if the number of {@link Box} and {@link Goal} is different
	 * @throws WrongNumberOfPlayersException if the number of {@link Player} is different then 1
	 * @throws NumberFormatException if the string does not contain a parsable integer.
	 */
	
	public Game parserContent(String content, Scanner scanner)
			throws WrongNumberOfPlayersException, IllegalTileException, WrongNumberOfBoxOrGoalException {
		
		Player player = null;
		String[] contentList = content.split("\n");
		String[] length = contentList[0].split("\\s");
		int xAxis = getNumberFromString(length[1]);
		int yAxis = getNumberFromString(length[0]);
		
		Game game = new Game(xAxis, yAxis, scanner);

		for(int i = 1; i < xAxis + 1; i++) {
			for(int k = 0; k < yAxis; k++) {
				switch(contentList[i].charAt(k)) {

					case '#': {
						game.setTile(i-1,k, new Wall(game, i-1,k));
						break;
					}

					case ' ': {
						game.setTile(i-1,k, new Empty(game, i-1,k));
						break;
					}

					case 'B': {
						Box box = new Box();
						game.addBox(box, i-1, k, new Empty(game,i-1,k));
						break;
					}

					case 'G': {
						game.setTile(i-1,k, new Goal(game, i-1,k));
						break;
					}

					case 'P': {
						if (player != null) throw new WrongNumberOfPlayersException();
						player = new Player();
						game.setPlayer(player, i-1,k);
						break;
					}
					case 'C': {
						Box box = new Box();
						game.addBox(box, i-1, k, new Goal(game,i-1,k));
						break;
			    	}
					default: {
						System.out.println("Illegal Tile\n");
						throw new IllegalTileException();
					}

				}
			}
		}
		
		if(game.getNumberOfBoxes() != game.getGoalCounter()) throw new WrongNumberOfBoxOrGoalException();
		if(player == null) throw new WrongNumberOfPlayersException();
		return game;
	}
	
	/**
	 * Interprets the given String and returns the numbers which are given in it.
	 * 
	 * @param Number String of a number
	 * @return integer of the number given as a string
	 */
	private static int getNumberFromString (String Number)
	{
		return Integer.parseInt(Number);
	}
	
	
	@Override
	public String toString() {
		return "I am the parser: I can parse a game";
	}
}



