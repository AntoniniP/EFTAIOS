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


import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

// TODO REMEMBER it's (expected, actual)
// TODO execution order NOT guaranteed

@RunWith(value = Parameterized.class)
public class DeckTest {

	Deck deck;
	Card c;
	int originalSize;

	@Parameters
	public static Collection<Deck> data() {
		return Arrays.asList(new EscapeHatchDeck(), new DangerousSectorDeck(), new ItemCardDeck());
	}

	public DeckTest(Deck concreteDeck) {
		this.deck = concreteDeck;
		originalSize = deck.deckSize();
	}

	@Test
	public void testDrawCard() {
		c = deck.drawCard();
		assertEquals(originalSize -1 , deck.deckSize() );
	
		deck.discardCard(c);
		assertEquals(1, deck.discardedDeckSize() );
	
		int tmpSize = deck.deckSize();
		for (int i = 0; i < tmpSize; i++) {
			deck.discardCard(deck.drawCard());
		}
		assertEquals(0, deck.deckSize() );
		assertEquals(originalSize , deck.discardedDeckSize() ); // It says originalSize==0!! WAT?

	

		deck.drawCard();
		assertEquals(deck.deckSize() , originalSize - 1);

	

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
