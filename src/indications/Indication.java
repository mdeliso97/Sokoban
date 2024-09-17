package indications;

/**
 * Saves the inputs, which an object such like {@link movable.IMovable}interpret. Its main responsibility
 * is to store the x and y inputs. If asked it returns x and y.
 */

public abstract class Indication {

	public final int XAXIS, YAXIS;
	
	public Indication(int x, int y)
	{
		this.XAXIS = x;
		this.YAXIS = y;
	}
	
	@Override
	public String toString() {
		return "Indication. X and Y are free to choose";
	}
}
