package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.server.GameHandler;

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
		type = CardsConstants.ITEM_CARD;
	}

	public abstract String action(GameHandler gh);

}
