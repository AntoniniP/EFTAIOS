package it.polimi.ingsw.AntoniniCastiglia.maps;

public class EmptySector extends Sector {

	/**
	 * Standard constructor.
	 * 
	 * @param x
	 * @param y
	 * @see Sector#Sector(String coord)
	 */
	public EmptySector(int x, int y) {
		super(x, y);
		this.setReachable(false);
	}

	/**
	 * Alternative constructor.
	 * 
	 * @param name
	 * @see Sector#Sector(String coord)
	 */
	public EmptySector(String name) {
		super(name);
		this.setReachable(false);
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void action() {
		
	}

}
