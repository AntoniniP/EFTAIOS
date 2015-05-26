package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;

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
	}

	@Override
	public void action() {
		// TODO DangerousSectorDeck.drawCard();
		// TODO nextTurn();
	}

}
