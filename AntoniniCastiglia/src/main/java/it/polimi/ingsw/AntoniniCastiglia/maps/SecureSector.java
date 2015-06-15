package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

/**
 * SECURE SECTOR: If you end your move in a Secure Sector, your turn is over.
 *
 * @author Laura Castiglia
 *
 */
public class SecureSector extends Sector {

	/**
	 * Standard constructor.
	 *
	 * @param x
	 * @param y
	 * @see Sector#Sector(String coord)
	 */
	public SecureSector(int x, int y) {
		super(x, y);
		this.setReachable(true);
		this.setMustDrawDSCard(false);
		this.setMustDrawEHCard(false);
		this.setType(MapConstants.SECURE);

	}

	/**
	 * Alternative constructor.
	 *
	 * @param name
	 * @see Sector#Sector(String coord)
	 */
	public SecureSector(String name) {
		super(name);
		this.setReachable(true);
		this.setMustDrawDSCard(false);
		this.setMustDrawEHCard(false);
		this.setType(MapConstants.SECURE);

	}

	@Override
	public String toString() {
		return "[" + Constants.ANSI_GRAY + super.toString() + Constants.ANSI_RESET + "]";
	}

}
