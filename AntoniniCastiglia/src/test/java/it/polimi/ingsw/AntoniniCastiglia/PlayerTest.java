package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.AntoniniCastiglia.maps.DangerousSector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.SecureSector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests about the player.
 *
 * @author Paolo Antonini
 *
 */
public class PlayerTest {

	Player human;
	Player alien;
	PlayerList playerList;
	Table t;

	/**
	 * Setup for the tests.
	 */
	@Before
	public void setUp() {
		t = new Table();
		human = new Human("Dorian", "Astrophotographer");
		alien = new Alien("Lucius", "Sociologist");
		playerList = new PlayerList(8);
	}

	/**
	 * I test the dimension of the path of a human before and after a move to two different sectors
	 * (one valid, one not).
	 */
	@Test
	public void testHuman() {
		// Test before the move.
		assertEquals(0, human.getPath().size());

		// Move to a non-valid sector.
		Sector s1 = new SecureSector("A02");
		String result = human.move(t, s1);
		assertTrue(result.startsWith("KO"));
		assertEquals(0, human.getPath().size());

		// Move to a valid sector.
		Sector s2 = new DangerousSector("K09");
		result = human.move(t, s2);
		assertTrue(result.startsWith("OK_"));
		assertEquals(1, human.getPath().size());

	}

	/**
	 * I test the dimension of the path of an alien before and after a move to two different sectors
	 * (one valid, one not).
	 */
	@Test
	public void testAlien() {
		// Test before the move.
		assertEquals(0, human.getPath().size());

		// Move to a non-valid sector.
		Sector s1 = new SecureSector("A02");
		String result = human.move(t, s1);
		assertTrue(result.startsWith("KO"));
		assertEquals(0, human.getPath().size());

		// Move to a valid sector.
		Sector s2 = new DangerousSector("J06");
		result = human.move(t, s2);
		assertTrue(result.startsWith("OK_"));
		assertEquals(1, human.getPath().size());
	}

	/**
	 * A newly created human doesn't have any card, so he can't use them.
	 */
	@Test
	public void testCanUseCards1() {
		assertFalse(human.canUseCards());
	}

	/**
	 * A newly created alien doesn't have any card, so he can't use them.
	 */
	@Test
	public void testCanUseCards2() {
		assertFalse(alien.canUseCards());
	}

	/**
	 * A newly created player doesn't have any card, so getPlayerCards() returns three "null;".
	 */
	@Test
	public void testGetPlayerCards() {
		assertEquals("null;null;null;", human.getPlayerCards());
	}

	/**
	 * A newly created player can't attack.
	 */
	@Test
	public void testAttack1() {
		assertEquals("KO", human.attack(playerList));
	}

	/**
	 * A newly created alien can attack. He attacks the sector he is in now, which is his base. He
	 * kills all the aliens in it. He can't move 3 sectors (he would, if he killed a human).
	 */
	@Test
	public void testAttack2() {
		assertEquals("OK_0_4", alien.attack(playerList));
		assertEquals(2, alien.getMoves());
	}
}
