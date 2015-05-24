package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * ADRENALINE: This card allows you to move two Sectors this turn.
 * 
 * @author Paolo Antonini
 *
 */
public class AdrenalineCard implements ItemCard {

	@Override
	// TODO needs a reset after use!
	public void action(Player p) {
		if (p instanceof Human) {
			p.setMoves(p.getMoves() + 1);
		}
	}

}
