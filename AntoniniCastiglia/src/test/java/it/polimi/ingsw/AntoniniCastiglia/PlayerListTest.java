package it.polimi.ingsw.AntoniniCastiglia;

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

	@Before
	public void setUp() {
		p = new PlayerList(8);
	}

	@Test
	public void testPlayerList() { //null since singleton
		assertNotNull(p);
	}
	
	@Test
	public void testSize() { //null since singleton
		assertEquals(p.size(),8);
	}
	

}
