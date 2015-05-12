package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class AdrenalineCard implements ObjectCard {

	@Override
	// TODO needs a reset after use!
	public void action(Player p) { 
		if (p instanceof Human) {
			p.setMoves(p.getMoves() + 1);
		}
	}

}
