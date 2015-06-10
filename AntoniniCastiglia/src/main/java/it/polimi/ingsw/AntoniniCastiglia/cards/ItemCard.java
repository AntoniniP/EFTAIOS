package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * Abstract class to define an Item card. It provides a method to perform the actions expected from
 * the card.
 * 
 * @author Paolo Antonini
 *
 */
public abstract class ItemCard extends Card {

	/**
	 * Constructor for the class. The <code>type</code> parameter is set.
	 */
	protected ItemCard() {
		type = CardNames.ITEM_CARD;
	}

	// TODO write JavaDoc, or delete it!
	public abstract void action(Player p);

}
