package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {
	Deck d;

	@Before
	public void setUp() throws Exception {
		d= new EscapeHatchDeck();
	}

	@Test
	public void test() {
		assertTrue(true);
	}

}
