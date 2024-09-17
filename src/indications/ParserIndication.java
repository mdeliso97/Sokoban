package indications;

/**
 * Parses a String into a {@link Indication}. It is used only by {@link Game#play()} to parse
 * the input of the real world player into a {@link Indication}. 
 *
 */
public class ParserIndication {

	/**
	 * Parses a String into a {@link Indication}.
	 * The syntax is the following:
	 * "up" to return a {@link Up};
	 * "down" to return a {@link Down};
	 * "left" to return a {@link Left};
	 * "right" to return a {@link Right};
	 * Everything else will return a {@link NotMove}.
	 * Notice that the inputs can also be given in upper case.
	 * 
	 * @param input String given from the player
	 * @return the {@link Indication} according from the input given and the above syntax
	 */
	
	public static Indication parse(String input) {
		
		assert input != null;
		
		switch(input) {
		
		case "up": return new Up();
			
		case "down": return new Down();
			
		case "left": return new Left();
			
		case "right": return new Right();
			
		default: return new NotMove();
			
		}
	}
	
	@Override
	public String toString() {
		return "I am the ParserIndication: I parse a String into an Indication";
	}
}
