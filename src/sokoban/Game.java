package sokoban;

import java.io.FileNotFoundException;
import java.util.*;
import indications.Indication;
import indications.NotMove;
import indications.ParserIndication;
import movable.*;
import tile.*;

/**
 * Representation of a single instance of a Sokoban game.
 *
 * Game is responsible for keeping track of the {@link ITile}, {@link IMovable}
 * gives methods to play the game, query the board, and change board {@link ITile}.
 * It is parsed from a .sok file by {@link Parser}, which the path must be given in 
 * the game by the human.
 */

public class Game {
	private int goalCounter = 0;
	final private int width;
	final private int height;
	private int boxOnGoalCounter = 0;
	private boolean gameOver = false;
	private ITile[][] board;
	private Player player;
	private List<Box> boxList = new ArrayList<>();
	private Renderer renderer = new Renderer();
	private Scanner scan;
	private History history = new History();
	private boolean resetted = false;
	private boolean saveable = true;
	
	/**
 	* Initializes the game with the given size and path of the level.
 	*
 	* Creates a Standard board with {@link Empty} {@link ITile} everywhere and {@link Wall} on the border.
 	* It initializes also a boxList (which is empty and should be filled), a goalCounter, a boxOnGoalCounter,
 	* a {@link Renderer} (in order to print the board) and a {@link Scanner} (in order to scan the inputs of the human).
 	* The player is not initialized, it is only if it's called {@link Game#setPlayer(Player, int, int)} afterwards.
 	*
 	* @param x is the width of the board
 	* @param y is the height of the board
	 * @param scanner which needs to be passed from the Parser
 	*/
	
	public Game(int x, int y, Scanner scanner) {
		assert x > 3;
		assert y > 3;
		width = x;
		height = y;
		this.addInitialTile();
		scan = scanner;
		assert invariant();
	}
	
	private boolean invariant() {
		return width > 3
				&& height >3
				&& board != null;
	}

	/**
	 * Plays (starts) the game. Prints the board each time a move is done, checks at the beginning of the game
	 * if the number of BoxOnGoalCounter and GoalCounter are the same (if so makes the game over). Before that
	 * it add itself in the Hisotry if the boolena saveable is true (it is only false if the input given is not 
	 * parsable from the {@link History#tryParser(String, Scanner)} or {@link ParserIndication#parse(String)}.
	 * Scans the human inputs. The input will be parsed from {@link ParserIndication#parse(String)}, but before that
	 * it will make it in lower case (whatever input is given from the human).
	 * If the {@link Indication} is a {@link NotMove}, it will go back at the beginning of the while loop. If it is an other
	 * type of {@link Indication}, it will move the player with {@link Game#movePlayer(Indication)}. At the end of the loop
	 * it will print the actual situation of the game with {@link Game#printMe()}. In addition we have a
	 * When the game is over, it prints a message, which tells the player that he won the game.
	 * @throws WrongNumberOfPlayersException 
	 * @throws WrongNumberOfBoxOrGoalException 
	 * @throws IllegalTileException 
	 * @throws FileNotFoundException 
	 */
	
	public void play() throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		assert board != null;
		assert player != null;
		assert goalCounter > 0;
		assert boxList.size() > 0;
		assert goalCounter == boxList.size();
		printMe();
		
		while (this.notOver()) {
			
			if(saveable) history.add(this);
			
			if(boxOnGoalCounter == goalCounter) {
				makeGameOver();
				continue;
			}
			
			System.out.println("What's your next move?"); //asks the human to give an input
			String input = scan.nextLine().toLowerCase();
			
			history.tryParser(input, scan);
			
			
			if(!resetted) {
				
				Indication indication = ParserIndication.parse(input);
				
				if (indication instanceof NotMove) { //if the ParserIndication parses it, it means that the input did not respected the syntax
					System.out.println("Not a valid input, please try with a valid one!");
					saveable = false;
					continue;
				}
				saveable = true;
				
				
				this.movePlayer(indication);
				printMe();
			}	
				
			

		}
		if(!resetted) System.out.println("The game is over! Good Job! You win!");
	}
	
	public boolean notOver() {
		return !gameOver;
	}
	
	public boolean isOver() {
		return gameOver;
	}
	
	public int getNumberOfBoxes() {
		return boxList.size();
	}
	
	/**
	 * Checks if the game is over, if not it makes it over. Otherwise it should throw an AssertException because there is 
	 * no sense to make a game over, when it is already over. It is called only by {@link Game#play()} 
	 * if the number of {@link Box} on {@link Goal} matches the amount of {@link Box} .
	 */
	
	public void makeGameOver() {
		assert !gameOver;
		gameOver = true;
		assert invariant();
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	/**
	 * Sets in a given position (which should be in range of the board) as the given {@link ITile}, which should
	 * not be null.
	 * 
	 * @param x first coordinate of the board
	 * @param y second coordinate of the board
	 * @param tile {@link Tile} which we want to set on the board
	 */
	
	public void setTile(int x, int y, ITile tile) {
		assert isInRangeX(x)&&isInRangeY(y);
		assert tile != null;
		this.initTile(x,y,tile);
		assert invariant();
	}

	/**
	 * Overrides a {@link ITile} on the given coordinates of the board as the given one.
	 * As always the given coordinates should be on the board.
	 * 
	 * @param x first coordinate of the board
	 * @param y second coordinate of the board
	 * @param tile {@link ITile} which we want to set on the board
	 */
	
	private void initTile(int x,int y, ITile tile) {
		assert tile != null;
		assert isInRangeX(x)&&isInRangeY(y);
		board[x][y] = tile;
	}
	
	/**
	 * Sets {@link Wall} on the border of the game board.
	 * It is called only by {@link Game#addInitialTile()}
	 */
	
	private void setWalls() {
		for (int i = 0; i< width; i++) {
			initTile(i,0, new Wall(this, i, 0));
			initTile(i,height-1, new Wall(this, i, height-1));
		}
		for (int k = 0; k < height; k++) {
			initTile(0,k, new Wall(this, 0,k));
			initTile(width-1,k, new Wall(this, width-1, k));
		}
		assert invariant();
	}
	
	/**
	 * Prepare an initial standard board of the game (that is a board of {@link Empty} with a 
	 * border of {@link Wall}), it is only called by the constructor of the class.
	 */
	
	private void addInitialTile () {
		assert width > 3 && height > 0;
		board = new ITile[width][height];
		for (int i = 0; i <width; i++) {
			for (int k = 0; k<height; k++) {
				this.initTile(i, k, new Empty(this, i, k) );
			}
		}
		setWalls();
	}
	
	public ITile getTile(int x, int y) {
		assert board[x][y] != null;
		assert isInRangeX(x) && isInRangeY(y);
		return board[x][y];
	}
	
	/**
	 * Sets the {@link Player} of the game, it should be called only one time, because this is not 
	 * a multiplayer game. The player must be not null, he should be in range of the board. Per 
	 * default he is set in a {@link Empty}, in which he joins the game.
	 * 
	 * @param pla player of the game
	 * @param x coordinate on the board, must be within the bounds of the board
	 * @param y coordinate on the board, must be within the bound of the board
	 */
	public void setPlayer(Player pla, int x, int y) {
		assert pla != null;
		assert player == null;
		assert isInRangeX(x) && isInRangeY(y);
		this.setTile(x,y, new Empty(this, x,y));
		player = pla;
		player.joinGame(this, x, y);;
		assert invariant();
	}

	public void increaseGoalCounter() {
		goalCounter++;
		assert invariant();
	}
	
	public int getGoalCounter() {
		return goalCounter;
	}

	public ITile[][] getBoard() {
		assert board != null;
		return board;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	/**
	 * Adds a {@link Box} in the game, it checks first if it is not null and if the given coordinates are in the range of the boards.
	 * It adds the given box in a list of Boxes and than it calls {@link Box#joinGame(Game, int, int)} of new added box with 
	 * the given coordinates. Per default the box is set in an {@link Empty}. It is used only by the {@link Parser}, when it parses a 
	 * {@link Game} from a given .sok level.
	 * 
	 * @param box to add on the list of boxes
	 * @param x coordinate in which the box is in
	 * @param y coordinate in which the box is in
	 */
	
	public void addBox(Box box, int x, int y, ITile tile) {
		assert box != null;
		assert isInRangeX(x) && isInRangeY(y);

		this.setTile(x,y, tile);
		boxList.add(box);
		box.joinGame(this, x, y);
		assert invariant();
	}
	
	/**
	 * Looks for a {@link ITile} on the board from the given coordinates and {@link Indication} and returns it.
	 * It is used from the {@link ITile} if a {@link IMovable} has to move from it.
	 * This method calculates the coordinate of the landing {@link ITile} by adding the x coordinate of the {@link Indication}
	 * to the given x coordinate of the box (the same is done for the y coordinate). As always it is checked if the resulting 
	 * new coordinates are in range of the board and if the return is not null.
     *
	 * @param x coordinate of the {@link ITile} in which the {@link IMovable} is on
	 * @param y coordinate of the {@link ITile} in which the {@link IMovable} is on
	 * @param ind {@link Indication} in which the {@link IMovable} wants to move
	 * @return {@link ITile} in which the {@link IMovable} wanted to move
	 */
	
	public ITile findTile(int x, int y, Indication ind) {
		assert ind != null;
		assert this.getTile(x+ind.XAXIS, y+ind.YAXIS) != null;
		assert isInRangeX(x+ind.XAXIS) && isInRangeY(y+ind.YAXIS);
		return this.getTile(x+ind.XAXIS, y+ind.YAXIS);
	}
	
	/**
	 * Increases the parameter BoxOnGoalCounter. If it matches goalCounter, the game will be over.
	 * It is used only by {@link Goal} each time a {@link Box} enters on it.
	 */
	
	public void increaseBoxOnGoalCounter() {
		boxOnGoalCounter++;
		assert invariant();
	}
	
	/**
	 * Decreases the parameter BoxOnGoalCounter, it is used only by {@link Goal} each times a {@link Box} leaves a {@link Goal}.
	 */
	
	public void decreaseBoxOnGoalCounter() {
		boxOnGoalCounter--;
		assert invariant();
	}

	public int getBoxOnGoalCounter() {
		return boxOnGoalCounter;
	}
	
	/**
	 * Moves the player by the given direction and let the player land on the destination. It will be used by 
	 * {@link Game#play()}, when we make the game interactive, now it is used only by the tests.
	 * 
	 * @param ind direction in which player moves
	 */
	
	public void movePlayer(Indication ind) {
		assert ind != null;
		player.move(ind);
		assert invariant();
	}
	
	/**
	 * Uses {@link Renderer#print(Game)} to print the board of the game, it is used in tests and in {@link Game#play()}.
	 */
	
	public void printMe() {
		renderer.print(this);
	}
	
	/**
	 * Checks only if the given input is in range of the board's width, it is used only from the asserts.
	 * 
	 * @param x coordinate tested
	 * @return true if the given parameters is in range, false otherwise
	 */
	
	public boolean isInRangeX(int x) {
		return -1<x && x<width;
	}
	
	/**
	 * Checks only if the given integer is in range of the board's height, it is used only from asserts.
	 * 
	 * @param x coordinate tested
	 * @return true if the given parameters is in range, false otherwise
	 */
	
	public boolean isInRangeY(int x) {
		return -1<x && x<height;
	}
	
	/**
	 * This method is used to get from game files the board dimension of the game and the whole game as string to be used when
	 * called functions undo and redo.
	 * It is called by {@link History#add(Game)}
	 * @return the board as string
	 */
	
	public String getSaveInformation() {
		
		String string = String.format("%d %d\n%s",
									  getHeight(),
									  getWidth(),
						              renderer.render(this));
		return string;
	}
	/**
	 * Makes the game over by calling its {@link Game#makeGameOver()} and set the boolean resetted as true.
	 * It is only called by {@link SokobanPlay#loadGame(Game)}
	 */
	public void close() {
		this.makeGameOver();
		resetted = true;
	}
	
	/**
	 * Returns the path of the .sok file (if it is based one one), the number of players and boxes.
	 * It gives also the width and height of the board, with the current state of the game
	 */
	
	@Override
	public String toString() {
		return "Sokoban game " + "\n with " + (player!=null? "one":"no") + " players and " + 
	            boxList.size() + "boxes."+ "\n Its board's width is "+ width + "and its height " + 
				height + ".\n The current state of the game is the following:\n" + renderer.render(this);
	}
	/**
	 * Actualize the {@link History} by coping the parameter as the new history. It also set the boolean variable
	 * saveable as false. It is called only by {@link History#undo(Scanner scanner)} or {@link History#redo(Scanner scanner)}
	 * @param history2 which will override the old one
	 */
	public void actualizeHistory(History history2) {
		history=history2;
		saveable = false;
	}
}
