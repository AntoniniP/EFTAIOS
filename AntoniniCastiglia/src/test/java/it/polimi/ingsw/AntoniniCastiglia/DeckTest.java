package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Parametric test about decks. I create three different types of deck (EscapeHatch, DangerousSector
 * and ItemCard), and conduct tests about them.
 *
 * @author Paolo Antonini
 *
 */
@RunWith(value = Parameterized.class)
public class DeckTest {

	Deck deck;
	Card c;
	int originalSize;

	/**
	 * Provides parameters for the parametric test.
	 *
	 * @return a collection of decks
	 */
	@Parameters
	public static Collection<Deck> data() {
		return Arrays.asList(new EscapeHatchDeck(), new DangerousSectorDeck(), new ItemCardDeck());
	}

	/**
	 * Constructor for the class. Assigns the parameter <code>concreteDeck</code> and the original
	 * size to the variables of the class.
	 *
	 * @param concreteDeck newly created deck in data() method
	 */
	public DeckTest(Deck concreteDeck) {
		deck = concreteDeck;
		originalSize = deck.deckSize();
	}

	/**
	 * Huge and horrible method to test (almost) everything about a deck, that is: the size of both
	 * the deck and the discardedDeck before and after the removal of a card, and before and after
	 * the removal of all cards.
	 */
	@Test
	public void testDrawCard() {

		// I draw a card; deckSize must be smaller by one unit.
		c = deck.drawCard();
		assertEquals(originalSize - 1, deck.deckSize());

		// I discard the card; discardedDeckSize must be greater by one unit.
		deck.discardCard(c);
		assertEquals(1, deck.discardedDeckSize());

		// I discard all cards; deckSize must be 0; discardedDeckSize must equal the original size
		// of the deck.
		int tmpSize = deck.deckSize();
		for (int i = 0; i < tmpSize; i++) {
			deck.discardCard(deck.drawCard());
		}
		assertEquals(0, deck.deckSize());
		assertEquals(originalSize, deck.discardedDeckSize());

		// I draw another card; thanks to reshuffle deckSize must be smaller than the original size
		// by one unit; the discardedCardDeck must be 0.
		deck.drawCard();
		assertEquals(deck.deckSize(), originalSize - 1);
		assertTrue(deck.discardedDeckSize() == 0);

		// I draw all cards, without discarding them; both deckSize and discardedDeckSize must be 0;
		// if I draw another card, I get null.
		for (int i = 0; i < originalSize; i++) {
			deck.drawCard();
		}
		assertTrue(deck.deckSize() == 0);
		assertTrue(deck.discardedDeckSize() == 0);
		assertTrue(deck.drawCard() == null);

	}

}
