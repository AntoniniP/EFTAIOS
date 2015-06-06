package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
import it.polimi.ingsw.AntoniniCastiglia.maps.*;
import it.polimi.ingsw.AntoniniCastiglia.players.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player p;

	@Before
	public void setUp() throws Exception {
		p = new Human("John", "Captain", 0);
	}


	@Test
	public void testSetCurrentSector() {
		Sector s1 = new SecureSector ("A02");
		p.setCurrentSector(s1);
		//TODO test che sia stato aggiunto a path[]!
		assertEquals(s1, p.getCurrentSector());
	}
	
}
