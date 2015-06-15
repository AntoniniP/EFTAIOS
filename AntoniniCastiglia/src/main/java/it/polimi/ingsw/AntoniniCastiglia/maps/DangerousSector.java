package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

/**
 * DANGEROUS SECTOR: If you end your move in a Dangerous Sector, you must draw a Dangerous Sector
 * Card and follow its instructions. Then your turn is over.
 *
 * @author Laura Castiglia
 *
 */
public class DangerousSector extends Sector {

	/**
	 * Standard constructor.
	 *
	 * @param x
	 * @param y
	 * @see Sector#Sector(String coord)
	 */
	public DangerousSector(int x, int y) {
		super(x, y);
		this.setReachable(true);
		this.setMustDrawDSCard(true);
		this.setMustDrawEHCard(false);
		this.setType(MapConstants.DANGEROUS);

	}

	/**
	 * Alternative constructor.
	 *
	 * @param name
	 * @see Sector#Sector(String coord)
	 */
	public DangerousSector(String name) {
		super(name);
		this.setReachable(true);
		this.setMustDrawDSCard(true);
		this.setMustDrawEHCard(false);
		this.setType(MapConstants.DANGEROUS);

	}

	@Override
	public String toString() {
		return "[" + Constants.ANSI_DARK_GREY + super.toString() + Constants.ANSI_RESET + "]";
	}

}
