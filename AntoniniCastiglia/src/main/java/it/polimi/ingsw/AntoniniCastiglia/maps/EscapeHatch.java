package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;

public class EscapeHatch extends Sector {
	
	public boolean useable;

	public EscapeHatch(int x, int y) {
		super(x, y);
	}

	public EscapeHatch(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return " E ";
	}

	public void action(){
	//	Card c = EscapeHatchDeck.drawCard();
	}
	
	//Only Human players can end their move on an Escape Hatch Sector.
	
}
