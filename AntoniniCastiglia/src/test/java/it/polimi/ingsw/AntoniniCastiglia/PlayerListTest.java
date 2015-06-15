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
	public void testSize() {
		assertEquals(p.size(),8);
	}
	
	@Test
	public void testGet() { 
		assertNotNull(p.get(0));
	}
	

}
