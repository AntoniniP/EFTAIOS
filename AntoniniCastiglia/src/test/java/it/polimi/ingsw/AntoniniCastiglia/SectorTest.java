/**
 * 
 */
package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import it.polimi.ingsw.AntoniniCastiglia.maps.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * I test both equals and convert() (indirectly)
 * 
 * @author Paolo
 *
 */
@RunWith(value = Parameterized.class)
public class SectorTest {
	Sector s1;
	Sector s2;
	Sector s3;
	Sector s4;

	@Parameters
	public static Collection<Sector> data() {
		int letter=11;
		int number = 8;
		
		return Arrays.asList(new AlienBase(letter, number), new DangerousSector(letter, number),
				new EmptySector(letter, number), new EscapeHatch(letter, number), new SecureSector(letter, number));
	}
	
	public SectorTest(Sector concreteSector){
		this.s1 = concreteSector;
		this.s2 = concreteSector;
		this.s3 = concreteSector;
		this.s4 = concreteSector;
	}
	
	
	
	
	@Test
	public void testEqualsReflexive() {
		assertTrue(s1.equals(s1));
	}
	
	@Test
	public void testEqualsSymmetric() {
		assertTrue(s1.equals(s2) && s2.equals(s1));
	}
	
	@Test
	public void testEqualsTransitive() {
		assertTrue(s1.equals(s2) && s2.equals(s3) && s1.equals(s3));
	}
	
	@Test
	public void testEqualsConsistent() {
		assertFalse(s1.equals(null));
	}
	
	@Test
	public void testGetX(){
		assertEquals(s1.getX(),11);
	}
	
	@Test
	public void testGetY(){
		assertEquals(s1.getY(),8);
	}
	
	
	
	@Test
	public void testHashCode(){
		assertEquals(s1.hashCode(), 338);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSector(){
	Sector s5 = new SecureSector(26,5);
	// TODO assert something?!
	}
}
