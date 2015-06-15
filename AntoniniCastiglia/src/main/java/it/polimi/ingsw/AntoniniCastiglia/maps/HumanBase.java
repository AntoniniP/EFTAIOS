package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

/**
 * HUMAN SECTOR: This is the starting Sector for all Human players. After the game begins, no player
 * can move through or end his move in this Sector.
 * 
 * @author Laura Castiglia
 *
 */
public class HumanBase extends Sector {

	/**
	 * Standard constructor.
	 * 
	 * @param x
	 * @param y
	 * @see Sector#Sector(String coord)
	 */
	public HumanBase(int x, int y) {
		super(x, y);
		this.setReachable(false);
		this.setMustDrawDSCard(false);
		this.setMustDrawEHCard(false);
		this.setType(MapConstants.HUMANBASE);

	}

	/**
	 * Alternative constructor.
	 * 
	 * @param name
	 * @see Sector#Sector(String coord)
	 */
	public HumanBase(String name) {
		super(name);
		this.setReachable(false);
		this.setMustDrawDSCard(false);
		this.setMustDrawEHCard(false);
		this.setType(MapConstants.HUMANBASE);

	}

	@Override
	public String toString() {
		return "[" +Constants.ANSI_BOLD + Constants.ANSI_RED_BRIGHT + " H " + Constants.ANSI_RESET+"]";
	}

}
