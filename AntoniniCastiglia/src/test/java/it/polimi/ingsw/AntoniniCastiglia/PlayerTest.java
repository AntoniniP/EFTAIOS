package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
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
	PlayerList playerList;
	Table t;
	
	@Before 
	public void setUp(){
		t=new Table();
		
		human=new Human("Dorian", "Astrophotographer");
		alien = new Alien("Lucius","Sociologist");
		
		playerList = new PlayerList(8);
		
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
	
	@Test 
	public void testCanUseCards1(){
		assertFalse(human.canUseCards());
	}
	
	@Test 
	public void testCanUseCards2(){
		assertFalse(alien.canUseCards());
	}
	
	@Test 
	public void testGetPlayerCards(){
		assertEquals("null;null;null;", human.getPlayerCards());
	}
	
	@Test 
	public void testAttack1(){
		assertEquals("KO", human.attack(playerList));
	}
	
	@Test
	public void testAttack2(){
		assertEquals("OK_0_4",alien.attack(playerList));
		assertEquals(2,alien.getMoves());
	}
}
