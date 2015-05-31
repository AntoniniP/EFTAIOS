package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;

import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;

import org.junit.Before;
import org.junit.Test;

// TODO test drawcard, reshuffle, discardcard, all deck types

public class DeckTest {
	Deck d;

	@Before
	public void setUp() throws Exception {
		d = new EscapeHatchDeck();
	}

	@Test
	public void testDrawCard() {
		assertTrue(true);
	}
	
	@Test
	public void testReshuffleDeck() {
		assertTrue(true);
	}
	
	@Test
	public void testDiscardCard() {
		assertTrue(true);
	}
	
}
