package it.polimi.ingsw.AntoniniCastiglia.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class provides all necessary variables and methods to handle a generic deck. An instance of
 * the class has both a "main" deck, and also a deck where to store discarded cards, if any.
 * 
 * @author Laura Castiglia
 *
 */
public class Deck {

	protected List<Card> deck = new ArrayList<Card>();
	protected List<Card> discardedDeck = new ArrayList<Card>();

	/**
	 * Shuffles the deck on which it's called.
	 */
	protected void shuffleDeck() {
		Collections.shuffle(deck);
	}

	/**
	 * Puts back in the main deck all the discarded cards, shuffles it and resets the discarded
	 * cards deck.
	 */
	private void reshuffleDeck() {
		deck.addAll(discardedDeck);
		shuffleDeck();
		discardedDeck = new ArrayList<Card>();
	}

	/**
	 * Draws a card from the deck and returns it, after removing it from the deck itself.
	 * 
	 * @return the drawn card
	 */
	public Card drawCard() {
		if (deck.isEmpty()) {
			if (discardedDeck.isEmpty()) {
				return null; // Client will need to deal with "null"
			} else {
				reshuffleDeck();
			}
		}
		Card drawnCard = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return drawnCard;
	}

	/**
	 * Discards a card, received as a parameter, by adding it to the discarded cards deck.
	 * 
	 * @param c the card to discard
	 */
	public void discardCard(Card c) {
		discardedDeck.add(c);
	}

	/**
	 * Returns the size of the main deck.
	 * 
	 * @return the size of the main deck
	 */
	public int deckSize() {
		return deck.size();
	}

	/**
	 * Returns the size of the discarded cards deck.
	 * 
	 * @return the size of the discarded cards deck
	 */
	public int discardedDeckSize() {
		return discardedDeck.size();
	}

	@Override
	@Deprecated
	public String toString() {
		String toReturn = new String("");
		for (int i = 0; i < deck.size(); i++) {
			toReturn = toReturn + deck.get(i) + "\n";
		}
		return toReturn;
	}
}