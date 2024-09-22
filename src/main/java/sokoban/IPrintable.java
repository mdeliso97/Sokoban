package sokoban;

public interface IPrintable {
	
	/**
	 * Returns the characters representing the situation of the {@link Tile} or {@link Movable} on the board, if
	 * there isn't {@link Movable} it will return the label of the {@link Tile} or {@link Movable}. 
	 * 
	 * @return label of the tile if it is not occupied, otherwise the label of the movable on it
	 */
	
	char toChar();
}
