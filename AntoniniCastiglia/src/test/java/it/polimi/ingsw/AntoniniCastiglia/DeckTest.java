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
	 * the removal of all cards (see inline comments for further explanation.
	 */
	@Test
	public void testDrawCard() {
		c = deck.drawCard();
		assertEquals(originalSize - 1, deck.deckSize());

		deck.discardCard(c);
		assertEquals(1, deck.discardedDeckSize());

		int tmpSize = deck.deckSize();
		for (int i = 0; i < tmpSize; i++) {
			deck.discardCard(deck.drawCard());
		}
		assertEquals(0, deck.deckSize());
		assertEquals(originalSize, deck.discardedDeckSize()); // It says originalSize==0!! WAT?

		deck.drawCard();
		assertEquals(deck.deckSize(), originalSize - 1);

		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		deck.drawCard();
		assertTrue(deck.discardedDeckSize() == 0);

		oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			deck.drawCard();
		}
		assertTrue(deck.discardedDeckSize() == 0);

		oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		assertTrue(deck.drawCard() == null);

		oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}
		deck.drawCard();
		assertTrue(deck.deckSize() == 0);

		oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		deck.drawCard();
		assertTrue(deck.discardedDeckSize() == 0);

	}

}
