/**
 * 
 */
package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;
import it.polimi.ingsw.AntoniniCastiglia.maps.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Paolo
 *
 */
public class SectorTest {
	Sector s1;
	Sector s2;
	Sector s3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		s1 = new DangerousSector("L09");
		s2 = new SecureSector("L09");
		s3 = new DangerousSector(11, 8);
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

}
