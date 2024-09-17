package sokoban;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import indications.ParserIndication;
import movable.Box;
import movable.Player;
import tile.Goal;
/**
 * Stores an ArrayList of String, which represent a {@link Game} after a move done by the player.
 * It is also possible to restore the previous or the following version of the {@link Game} by using the {@link History#tryParser(String, Scanner)}
 * But it should be done only if there are previous version or following version of the current {@link Game}.
 * Before calling {@link History#tryParser(String, Scanner)}, it should be called {@link History#add(Game)} first, because
 * the index is initially setted as -1.
 * There are some getters, but they are just for testing.
 * 
 * It is implemented the "Memento Pattern".
 */
public class History {
	
	private ArrayList<String> history = new ArrayList<String>();
	private int index = -1;
	
	/**
	 * Loads the next {@link Game} stored in {@link History} in {@link SokobanPlay} by calling {@link SokobanPlay#loadGame(Game)}.
	 *  It can be called only maximally the same amount of times as {@link History#undo(Scanner)}. If it is called
	 *  more than {@link History#undo(Scanner)} it will print a message that tells the human to give something
	 *  else as an input.
	 *  This method checks first if the index stored is equal the size of the array in which are stored all the stage
	 *  of the {@link Game} -1. Than it increase the index, get the element in the index-th place in the array.
	 *  Than it Prints how many times it is still possible to call this method. After that it creates a new {@link Game}
	 *  by calling {@link Parser#parserContent(String, Scanner)}. Finally it loads the new game by calling {@link SokobanPlay#loadGame(Game)}
	 * This method is only called by {@link History#tryParser(String, Scanner)}
	 * 
	 * @param scanner used in the {@link Game}, it is needed because in our implementation of the game, the scanner created in {@link SokobanPlay} is passed in each game
	 * @throws FileNotFoundException if the .sok file is not found
	 * @throws WrongNumberOfBoxOrGoalException if the number of {@link Box} and {@link Goal} is different
	 * @throws WrongNumberOfPlayersException if the number of {@link Player} is different then 1
	 * @throws NumberFormatException if the string does not contain a parsable integer.
	 */
	
	private void redo(Scanner scanner) throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
		assert scanner != null;
		assert history != null;
		
		if (index == history.size() - 1) {
			System.out.println("You can't redo. Please move, or undo your move.");
			return;
		}
		
		index++;
		String content = history.get(index);
		int difference = history.size()-index - 1;
		System.out.println("You can redo " + difference + " times.");
		Game newgame = new Parser().parserContent(content, scanner);
		newgame.actualizeHistory(this);
		SokobanPlay.loadGame(newgame);
	}
	
	/**
	 * Loads the restored {@link Game} in {@link SokobanPlay} by calling {@link SokobanPlay#loadGame(Game)}.
	 * It is loaded the  {@link Game} version before the last input.
	 *  It can be called only maximally the same amount of times as the size of the array. If it is called
	 *  more than that it will print a message that tells the human to give something else as an input.
	 *  This method checks first if the index stored is equal to zero. Than it decrease the index, get the element
	 *  in the index-th place in the array. It is checked if the index is equal to zero, because then it is decreased
	 *  and an array can not be ask to give the -1th element of the list.
	 *  Than it Prints how many times it is still possible to call this method. After that, it creates a new {@link Game}
	 *  by calling {@link Parser#parserContent(String, Scanner)}. Finally it loads the new game by calling {@link SokobanPlay#loadGame(Game)}
	 * 
	 * This method is only called by {@link History#tryParser(String, Scanner)}
	 * 
	 * @param scanner used in the {@link Game}, it is needed because in our implementation of the game, the scanner created in {@link SokobanPlay} 
	 * is passed in each game.
	 * @throws FileNotFoundException if the .sok file is not found
	 * @throws WrongNumberOfBoxOrGoalException if the number of {@link Box} and {@link Goal} is different
	 * @throws WrongNumberOfPlayersException if the number of {@link Player} is different then 1
	 * @throws NumberFormatException if the string does not contain a parsable integer.
	 */
	
     private void undo(Scanner scanner) throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
    	assert scanner != null;
    	assert history != null;
    	
		if (index == 0) {
			System.out.println("You are trying to undo the undoable. Please move.");
			return;
		}
		index--;
		System.out.println("You can undo "+ index + " times.");
		String content = history.get(index);
		Game newgame = new Parser().parserContent(content, scanner);
		newgame.actualizeHistory(this);
		SokobanPlay.loadGame(newgame);
	}
     
     /**
      * Adds the {@link Game#getSaveInformation()} String of the {@link Game} on the ArrayList of {@link History}.
      * Before that it increases the index. After that it checks if the index is not equal to the size of the ArrayList.
      * If that is the case it creates a new ArrayList, and it loads in it all the {@link Game#getSaveInformation()} String
      * that are stored before the index-Position. Than this new ArrayList is stored as the new one.
      * At the end it is finally added the new {@link Game#getSaveInformation()} String, which stores the new version of the game  
      * 
      * This Method is called only by the {@link Game#play()}
      *
      * @param game which is going to be stored in {@link History} as a String
      */
     
     public void add(Game game) {
    	 assert game != null;
    	 assert history != null;
    	 index++;
    	 
    	 if (index != history.size()) {
    		 
    		 ArrayList<String> list = new ArrayList<String>();
    		 
    		 for (int i = 0; i < index; i++) list.add(history.get(i));
    		 
    		 history = list;
    	 }
    	 
    	 history.add(game.getSaveInformation());
     }
     
     /**
      * Try to parse the given String. If it is equal to "undo" it will call the {@link History#undo(Scanner)} Method,
      * if it is equal to "redo" it will call the {@link History#redo(Scanner)} Method. If the input is null it will just return.
      * It is only called in {@link Game#play()}, after that it is called {@link ParserIndication#parse(String)}
      * Before calling this method is should be called {@link History#tryParser(String, Scanner)} first, because
      * the index starts from -1, and only add would increase it to 0.
      * 
      * @param input of the human given during the {@link Game#play()}
      * @param scanner of the actual game, it is needed because in our implementation it is created only one and it is passed between each game
      * @throws FileNotFoundException if the .sok file is not found
      * @throws WrongNumberOfBoxOrGoalException if the number of {@link Box} and {@link Goal} is different
      * @throws WrongNumberOfPlayersException if the number of {@link Player} is different then 1
      * @throws NumberFormatException if the string does not contain a parsable integer.
      */
     
     public void tryParser(String input, Scanner scanner) throws FileNotFoundException, IllegalTileException, WrongNumberOfBoxOrGoalException, WrongNumberOfPlayersException {
    	 assert scanner != null;
    	 assert history != null;
    	 
    	 if (input == null) return;
    	 
    	 switch (input.toLowerCase()) {
    	 
    	 	case "undo": {
    		 	undo(scanner);
    		 	break;
    	 	}
    		 
    	 	case "redo": {
    		 	redo(scanner);
    		 	break;
    	 	}
    	 }
     }

     public int getIndex() {
    	 return index;
     }
     
     /**
      * Returns the current {@link Game#getSaveInformation()} String of the {@link Game}. BUT if there are none,
      * that means that the array is empty, it returns "Empty", because it is better than a null
      * @return the current {@link Game#getSaveInformation()} String of the {@link Game}, "Empty" if there are none.
      */
     
     public String getCurrentSaveInformation() {
    	 if (history.size()==0 || index < 0) return "Empty";
    	 return history.get(index);
     }
}
