package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * ADRENALINE: This card allows you to move two Sectors this turn.
 * 
 * @author Paolo Antonini
 *
 */
public class ItemAdrenaline extends ItemCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public ItemAdrenaline() {
		super();
		name = CardsConstants.ADRENALINE;
	}

	@Override
	public void action(Player p) {
		// TODO needs a reset after use!
		if (p instanceof Human) {
			p.setMoves(2);
		}
	}

}
