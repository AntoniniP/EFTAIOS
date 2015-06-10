package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

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
		return Constants.ANSI_BOLD + Constants.ANSI_RED_BRIGHT + " H " + Constants.ANSI_RESET;
	}

	@Override
	public void action() {
		// TODO nextTurn();
	}

}
