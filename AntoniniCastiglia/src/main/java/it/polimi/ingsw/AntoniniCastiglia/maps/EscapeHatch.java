package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;

public class EscapeHatch extends Sector {
	
	public boolean useable;

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
	}

	@Override
	public String toString() {
		return " E ";
	}

	@Override
	public void action() {
		// TODO EscapeHatchDeck.drawCard();
		this.setReachable(false);
		// TODO nextTurn();		
	}


	//Only Human players can end their move on an Escape Hatch Sector.
	
}
