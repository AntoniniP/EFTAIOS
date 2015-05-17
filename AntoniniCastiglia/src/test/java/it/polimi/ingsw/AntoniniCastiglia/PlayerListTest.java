package it.polimi.ingsw.AntoniniCastiglia;

import it.polimi.ingsw.AntoniniCastiglia.pController.PlayerActions;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Test class for
 * {@link it.polimi.ingsw.AntoniniCastiglia.players.PlayerList#PlayerList(int)}.
 * 
 * @author Paolo
 */
public class PlayerListTest {

	PlayerList p;

	@Test
	public void testApp() {
		assertTrue(true);
	}

	@Before
	public void setUp() {
		PlayerList p = PlayerList.getPlayerList(8);
	}

	@Test
	public void testPlayerList() { //null since singleton
		assertNull(p);
	}

}
