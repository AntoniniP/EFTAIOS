package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemAdrenaline;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class SISTEMADeckTest {

	Deck deck;
	Card c;
	int oldSize;

	@Parameters
	public static Collection<Deck> data() {
		return Arrays.asList(new EscapeHatchDeck(), new DangerousSectorDeck(), new ItemCardDeck());
	}

	public SISTEMADeckTest(Deck concreteDeck) {
		this.deck = concreteDeck;
	}

	@Test
	public void testDrawCard() {
		oldSize = deck.deckSize();
		deck.drawCard();
		int newSize = deck.deckSize();
		assertTrue(oldSize == newSize + 1);
		oldSize=newSize;
	}

	@Test
	public void testDiscardCard() {
		Card c = deck.drawCard();
		deck.discardCard(c);
		assertTrue(deck.discardedDeckSize() == 1);
		oldSize=deck.deckSize();
	}

	@Ignore
	@Test
	public void testReshuffleDeck1() {
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}
		assertTrue(deck.deckSize() == 0);
		oldSize=deck.deckSize();
	}

	@Ignore
	@Test
	public void testReshuffleDeck2() {
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}
		assertTrue(deck.discardedDeckSize() == oldSize);
		oldSize=deck.deckSize();
	}

	@Ignore
	@Test
	public void testReshuffleDeck3() {
		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		deck.drawCard();
		assertTrue(deck.deckSize() == oldSize - 1);

	}

	@Test
	public void testReshuffleDeck4() {
		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		deck.drawCard();
		assertTrue(deck.discardedDeckSize() == 0);

	}

	@Test
	public void testReshuffleDeck5() {
		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			deck.drawCard();
		}
		assertTrue(deck.discardedDeckSize() == 0);

	}

	@Test
	public void testReshuffleDeck6() {
		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		assertTrue(deck.drawCard() == null);
	}

	@Test
	public void testReshuffleDeck7() {
		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}
		deck.drawCard();
		assertTrue(deck.deckSize() == 0);

	}

	@Test
	public void testReshuffleDeck8() {
		int oldSize = deck.deckSize();
		for (int i = 0; i < oldSize; i++) {
			Card c = deck.drawCard();
			deck.discardCard(c);
		}

		deck.drawCard();
		assertTrue(deck.discardedDeckSize() == 0);

	}

}
