package sokoban;
import tile.ITile;

/**
 * Takes any parameter which is read from the levels (.sok files) and prints them
 * as an equally board as standard output without the initial values which define
 * the board dimension.
 * 
 */

public class Renderer {
	
	/**
	 * Prints the board of the game. If there are Null pointers, it prints Null.
	 * 
	 * @param board of {@link ITile}
	 */
	
	public void print(Game game) {
		System.out.println(render(game));
	}
	
	/**
	 * Renders the board as a String by adding each toString of each {@link ITile}.
	 * 
	 * @param board of {@link ITile}
	 * @return the board as a String
	 */
	
	public String render(Game game) {
		assert game != null;
		String result = "";
		ITile [][] board = game.getBoard();
		for(int i = 0; i < game.getWidth(); i++) {
			for(int k = 0; k < game.getHeight(); k++) {
				result += board[i][k].toChar();
			}
			result += "\n";
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "I am the render: I can render a game into a string or print it in the console";
	}

}
