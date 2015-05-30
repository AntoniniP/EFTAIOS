package it.polimi.ingsw.AntoniniCastiglia.maps;

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
	}

	@Override
	public void action() {
		// TODO nextTurn();
	}

}