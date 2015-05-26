package it.polimi.ingsw.AntoniniCastiglia.maps;

/**
 * Every instance of this class (it will be one, hopefully) is an Alien Base
 * sector.
 * 
 * @author Paolo Antonini
 *
 */
public class AlienBase extends Sector { // TODO SINGLETON?

	/**
	 * Standard constructor.
	 * 
	 * @param x
	 * @param y
	 * @see Sector#Sector(String coord)
	 */
	public AlienBase(int x, int y) {
		super(x, y);
		this.setReachable(false);
	}

	/**
	 * Alternative constructor.
	 * 
	 * @param name
	 * @see Sector#Sector(String coord)
	 */
	public AlienBase(String name) {
		super(name);
		this.setReachable(false);
	}

	@Override
	public String toString() {
		return " A ";
	}

	@Override
	public void action() {
		// TODO nextTurn();
	}

}
