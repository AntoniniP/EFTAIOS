package it.polimi.ingsw.AntoniniCastiglia.maps;

/**
 * ESCAPE HATCH SECTOR: Humans must reach one of these Sectors to win. Only Human players can end
 * their move on an Escape Hatch Sector.
 * 
 * @author Laura Castiglia
 *
 */
public class EscapeHatch extends Sector {

	@SuppressWarnings("unused")
	private boolean useable;

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

	@Override
	public String toString() {
		return " E ";
	}

	// Only Human players can end their move on an Escape Hatch Sector.

}
