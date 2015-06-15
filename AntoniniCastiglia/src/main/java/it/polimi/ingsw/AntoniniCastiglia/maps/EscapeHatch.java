package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

/**
 * ESCAPE HATCH SECTOR: Humans must reach one of these Sectors to win. Only Human players can end
 * their move on an Escape Hatch Sector.
 *
 * @author Laura Castiglia
 *
 */
public class EscapeHatch extends Sector {

	private boolean usable = true;

	/**
	 * Standard constructor.
	 *
	 * @param x
	 * @param y
	 * @see Sector#Sector(String coord)
	 */
	public EscapeHatch(int x, int y) {
		super(x, y);
		this.setReachable(true);
		this.setMustDrawDSCard(false);
		this.setMustDrawEHCard(true);
		this.setType(MapConstants.ESCAPEHATCH);

	}

	/**
	 * Alternative constructor.
	 *
	 * @param name
	 * @see Sector#Sector(String coord)
	 */
	public EscapeHatch(String name) {
		super(name);
		this.setReachable(true);
		this.setMustDrawDSCard(false);
		this.setMustDrawEHCard(true);
		this.setType(MapConstants.ESCAPEHATCH);

	}

	/**
	 * Getter for the usable variable.
	 *
	 * @return whether the escape hatch is usable
	 */
	public boolean isUsable() {
		return usable;
	}

	/**
	 * Setter for the usable variable.
	 *
	 * @param usable sets wether the escape hatch is usable or not
	 */
	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	@Override
	public String toString() {
		return "[" + Constants.ANSI_BOLD + Constants.ANSI_CYAN + super.toString()
				+ Constants.ANSI_RESET + "]";
	}

}
