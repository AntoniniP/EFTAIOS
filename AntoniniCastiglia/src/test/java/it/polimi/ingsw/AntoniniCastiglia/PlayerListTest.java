package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for PlayerList class.
 *
 * @author Paolo Antonini
 */
public class PlayerListTest {

	PlayerList p;

	/**
	 * Setup for the test.
	 */
	@Before
	public void setUp() {
		p = new PlayerList(8);
	}

	/**
	 * The size of the playerList must be 8, since it was created of dimension 8.
	 */
	@Test
	public void testSize() {
		assertEquals(p.size(), 8);
	}

	/**
	 * If I get the player in 0, I don't get null.
	 */
	@Test
	public void testGet() {
		assertNotNull(p.get(0));
	}

}
