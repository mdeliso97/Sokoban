package sokoban;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Welcomes the human, tells him which are the rules of the {@link Game}, ask him the 
 * path of the .sok file which he wants to play. Than it parse it with {@link Parser}. It starts
 * the game with {@link Game#play()}. When the game is over, it will run {@link SokobanPlay#playAgain()}
 * after which it will either redo what it did, ask again until it gets a valid answer or end loop and 
 * the main method. 
 * It use only {@link Parser} to parse the .sok file in a level, {@link Game} to play it
 * and {@link SokobanPlay#playAgain()} to ask to play again.
 * It allows to load a new {@link Game}, by calling the {@link SokobanPlay#loadGame(Game)}
 * 
 */

public class SokobanPlay {
	@SuppressWarnings("resource")
	static Scanner scanner = new Scanner(System.in);
	private static Game currentGame;
	private static boolean play = false;
	/**
	 * Tells the human in which game he is playing, what are the rules, ask him a path of a .sok file
	 * in order to parse it and play it. When the game is over, the human is asked if he
	 * wants to play again by calling {@link SokobanPlay#playAgain()}. If the human answers that he 
	 * wants to play again, it will ask again the path of a .sok file and restart a new game. Otherwise
	 * if the human answers that he did not wants to play again, he will be thanked and will be greeted
	 * 
	 * @param args
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	public static void main(String[] args) throws FileNotFoundException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException, IllegalTileException {
		Parser parse = new Parser();
		System.out.println("Welcome to Sokoban Game.");
		System.out.println(" To play this game, you control the movements of the player 'P', you"
						+ "\n can move it with 'left', 'right', 'down', 'up'. They do what they"
						+ "\n say to do. To win the game you have to move each box 'B' on top"
						+ "\n of each goal 'G'. When you do it they become like 'C'. You can" 
						+ "\n still move them, if you need to. You cannot go through walls '#'"); //prints some general information for the player
		
		play = true;
		
		while(play) {
			System.out.println("\nPlease enter the level path which you want to play, which must be valid!");
			
			String levelScanned = scanner.nextLine();
		    Game oldGame = parse.parser(levelScanned, scanner); // asks a path to play and it tries to parse it
			System.out.println("You have chosen wisely, now play!");
			
			loadGame(oldGame);
			
			play = playAgain(); // asks if the human wants to play again
		}
		System.out.print("Thank you for playing our game! Bye!");
	}

	/**
	 * Asks the human if he wants to play again the game. The human has to
	 * answer with an y (which means "yes, I want to play again") or a n (which means "no, thanks. I 
	 * don't want to play again"). Even though it was not a requirement, the human can use both lower 
	 * and upper case, but it must be a y or n (also for move inputs). It returns a boolean, which is 
	 * true if the human answered with "y", otherwise it is false if the human answered with "n".
	 * Because there is a while loop, the method will not stop until the human gives as
	 * an input "y", "Y", "n" or "N".
	 * 
	 * @return true if the player answers with a "y", false if he answered with a "n" ( both upper and lower case are allowed)
	 */
	
	private static boolean playAgain() {

		String playAgain;
		
		System.out.println("Do you want to play again?Y/n");
		playAgain = scanner.nextLine().toLowerCase(); // takes the input and it makes it lower case
		
		while(!playAgain.equals("y") && !playAgain.equals("n")) { //checks if the given input is y or n, if not it asks to give y or n
			System.out.println("Please enter y or n, not something else");
			playAgain = scanner.nextLine().toLowerCase();
		}
		
		return playAgain.equals("y");
	}
	
	/**
	 * This method is used when we want to undo or redo a move, is responsible to create a new game, close the previous one, and actualize
	 * the move (if undo it shows the board before we moved, if redo it shows the board which we undo before). So if currentGame in not null
	 * and the currentGame is not over, it is called the method close. After that it sets the currentGame as the new game (in which the human
	 *  plays). If the boolean play is true, than it plays the new game by calling its {@link Game#play()}
	 * This method is also used in the main of {@link SokobanPlay} to load the game that the player wants to play 
	 * @param newGame
	 * @throws FileNotFoundException if the human gives a path of a not existing .sok file
	 * @throws WrongNumberOfBoxOrGoalException if the real world player gives a path of a .sok file with not the same amount of goals and boxes
	 * @throws WrongNumberOfPlayersException if the real world player gives a path of a .sok file with more or less than one player
	 * @throws IllegalTileException if the human gives a path of a .sok file which does not respects the strict syntax of {@link Parser}
	 */
	
	public static void loadGame(Game newGame) throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		
		if (currentGame !=null)
			if(currentGame.notOver())currentGame.close();
		currentGame = newGame;
		if(play) {
			currentGame.play();
		}
	}
	
	public Game getCurrentGame() {
		return currentGame;
	}
}
