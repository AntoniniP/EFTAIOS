package it.polimi.ingsw.AntoniniCastiglia.maps;

/**
 * ALIEN SECTOR: This is the starting Sector for all Alien players. After the game begins, no player
 * can move through or end his move in this Sector.
 * 
 * @author Laura Castiglia
 *
 */
public class AlienBase extends Sector {

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
