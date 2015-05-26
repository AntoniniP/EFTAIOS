package it.polimi.ingsw.AntoniniCastiglia.maps;

public class HumanBase extends Sector { // TODO SINGLETON!!

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
	}

	@Override
	public String toString() {
		return " H ";
	}

	@Override
	public void action() {
		// TODO nextTurn();		
	}
	
}
