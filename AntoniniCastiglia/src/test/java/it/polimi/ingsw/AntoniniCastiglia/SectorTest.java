/**
 * 
 */
package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
import it.polimi.ingsw.AntoniniCastiglia.maps.*;

import org.junit.Before;
import org.junit.Test;

/**
 * I test both equals and convert() (indirectly)
 * 
 * @author Paolo
 *
 */
public class SectorTest {
	Sector s1;
	Sector s2;
	Sector s3;
	Sector s4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		s1 = new DangerousSector("L09");
		s2 = new SecureSector("L09");
		s3 = new DangerousSector(11, 8);
		s4 = new AlienBase(3,3);
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
	public void testIsReachable1(){
		assertTrue(s1.isReachable());
	}
	
	@Test
	public void testIsReachable2(){
		assertFalse(s4.isReachable());
	}
	
	@Test
	public void testToString1(){
		assertEquals(s4.toString(), " A ");
	}

	@Test
	public void testToString2(){
		assertEquals(s3.toString(), "L09");
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
