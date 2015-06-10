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

		for (int i = 0; i < Constants.ADRENALINECARD; i++) {
			deck.add(new ItemAdrenaline());
		}
		for (int i = 0; i < Constants.ATTACKCARD; i++) {
			deck.add(new ItemAttack());
		}
		for (int i = 0; i < Constants.DEFENSECARD; i++) {
			deck.add(new ItemDefense());
		}
		for (int i = 0; i < Constants.SEDATIVESCARD; i++) {
			deck.add(new ItemSedatives());
		}
		for (int i = 0; i < Constants.SPOTLIGHTCARD; i++) {
			deck.add(new ItemSpotlight());
		}
		for (int i = 0; i < Constants.TELEPORTCARD; i++) {
			deck.add(new ItemTeleport());
		}
		shuffleDeck();
	}

}
