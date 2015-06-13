package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;
import it.polimi.ingsw.AntoniniCastiglia.maps.*;
import it.polimi.ingsw.AntoniniCastiglia.players.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class PlayerTest {
	Player human;
	Player alien;
	Table t;
	
	@Before 
	public void setUp(){
		t=new Table();
		
		human=new Human("Dorian", "Astrophotographer");
		alien = new Alien("Lucius","Sociologist");
		
	}

	
	@Test
	public void testHuman() {
		assertTrue(human.getPath().size()==0);
		Sector s1 = new SecureSector ("A02");
		String result= human.move(t, s1);
		assertTrue(result.startsWith("KO"));
		Sector s2 = new DangerousSector("K09");
		result= human.move(t, s2);
		assertTrue(result.startsWith("OK_"));
		assertTrue(human.getPath().size()==1);
	}
	
	@Test
	public void testAlien() {
		assertTrue(alien.getPath().size()==0);
		Sector s1 = new SecureSector ("A02");
		String result= alien.move(t, s1);
		assertTrue(result.startsWith("KO"));
		Sector s2 = new DangerousSector("J06");
		result= alien.move(t, s2);
		assertTrue(result.startsWith("OK_"));
		assertTrue(alien.getPath().size()==1);
	}
}
