package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;

public class DangerousSector extends Sector {

	public DangerousSector(int x, int y) {
		super(x, y);
		this.setReachable(true);
	}

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
