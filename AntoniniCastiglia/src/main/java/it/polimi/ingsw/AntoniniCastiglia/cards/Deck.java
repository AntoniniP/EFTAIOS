package it.polimi.ingsw.AntoniniCastiglia.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides all necessary variables and methods to handle a generic
 * deck.
 * 
 * @author Laura Castiglia
 *
 */
public class Deck {

	protected List<Card> deck = new ArrayList<Card>();
	protected List<Card> discardedDeck = new ArrayList<Card>();

	/**
	 * Implementation of Knuth's "Algorithm P" to shuffle a deck.
	 */
	public void shuffleDeck() {
		for (int i = deck.size() - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			Card tmp = deck.get(i);
			deck.set(i, deck.get(j));
			deck.set(j, tmp);
		}
	}

	/**
	 * The method puts back in the main deck all the discarded cards, shuffles
	 * it and resets the discarded cards deck.
	 */
	private void reshuffleDeck() {
		deck.addAll(discardedDeck);
		shuffleDeck();
		discardedDeck = new ArrayList<Card>();
	}

	// TODO check!
	// Not a remote method!!!!!!
	public Card drawCard() {

		if (deck.isEmpty() && discardedDeck.isEmpty())
			return null; // Client will need to deal with "null"

		if (deck.isEmpty() && !discardedDeck.isEmpty())
			reshuffleDeck();

		Card drawnCard = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return drawnCard;
	}

	public void discardCard(Card c) {
		deck.remove(c);
		discardedDeck.add(c);
	}
	
	public int deckSize() {
		return deck.size();
	}
	
	public int discardedDeckSize() {
		return discardedDeck.size();
	}

	@Override
	public String toString() {
		String toReturn = new String("");
		for (int i = 0; i < deck.size(); i++) {
			toReturn = toReturn + deck.get(i) + "\n";
		}
		return toReturn;
	}
}