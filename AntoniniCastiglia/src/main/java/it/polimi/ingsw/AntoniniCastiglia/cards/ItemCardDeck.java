package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

/**
 * Deck for Dangerous Sector cards.
 * 
 * @author Laura Castiglia
 *
 */
public class ItemCardDeck extends Deck {

	/**
	 * Constructor for the deck of item cards. The deck is created and shuffled.
	 */
	public ItemCardDeck() {

		for (int i = 0; i < CardsConstants.ADRENALINECARD; i++) {
			deck.add(new ItemAdrenaline());
		}
		for (int i = 0; i < CardsConstants.ATTACKCARD; i++) {
			deck.add(new ItemAttack());
		}
		for (int i = 0; i < CardsConstants.DEFENSECARD; i++) {
			deck.add(new ItemDefense());
		}
		for (int i = 0; i < CardsConstants.SEDATIVESCARD; i++) {
			deck.add(new ItemSedatives());
		}
		for (int i = 0; i < CardsConstants.SPOTLIGHTCARD; i++) {
			deck.add(new ItemSpotlight());
		}
		for (int i = 0; i < CardsConstants.TELEPORTCARD; i++) {
			deck.add(new ItemTeleport());
		}
		shuffleDeck();
	}

}
