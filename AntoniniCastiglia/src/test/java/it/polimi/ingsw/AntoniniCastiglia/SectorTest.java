/**
 *
 */
package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.AntoniniCastiglia.maps.AlienBase;
import it.polimi.ingsw.AntoniniCastiglia.maps.DangerousSector;
import it.polimi.ingsw.AntoniniCastiglia.maps.EmptySector;
import it.polimi.ingsw.AntoniniCastiglia.maps.EscapeHatch;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.SecureSector;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Parametric test for Sector class.
 *
 * @author Paolo Antonini
 *
 */
@RunWith(value = Parameterized.class)
public class SectorTest {

	Sector s1;
	Sector s2;
	Sector s3;
	Sector s4;

	/**
	 * Provides parameters for the parametric test.
	 *
	 * @return a collection of sectors
	 */
	@Parameters
	public static Collection<Sector> data() {
		int letter = 11;
		int number = 8;

		return Arrays.asList(new AlienBase(letter, number), new DangerousSector(letter, number),
				new EmptySector(letter, number), new EscapeHatch(letter, number), new SecureSector(
						letter, number));
	}

	/**
	 * Constructor for the class. Assigns the parameter <code>concreteSector</code> to the variables
	 * of the class.
	 *
	 * @param concreteDeck newly created deck in data() method
	 */
	public SectorTest(Sector concreteSector) {
		s1 = concreteSector;
		s2 = concreteSector;
		s3 = concreteSector;
		s4 = concreteSector;
	}

	/**
	 * equals() must be reflexive.
	 */
	@Test
	public void testEqualsReflexive() {
		assertTrue(s1.equals(s1));
	}

	/**
	 * equals() must be symmetric.
	 */
	@Test
	public void testEqualsSymmetric() {
		assertTrue(s1.equals(s2) && s2.equals(s1));
	}

	/**
	 * equals() must be transitive.
	 */
	@Test
	public void testEqualsTransitive() {
		assertTrue(s1.equals(s2) && s2.equals(s3) && s1.equals(s3));
	}

	/**
	 * equals() must be consistent.
	 */
	@Test
	public void testEqualsConsistent() {
		assertFalse(s1.equals(null));
	}

	/**
	 * Test for getX() method.
	 */
	@Test
	public void testGetX() {
		assertEquals(s1.getX(), 11);
	}

	/**
	 * Test for getY() method.
	 */
	@Test
	public void testGetY() {
		assertEquals(s1.getY(), 8);
	}

	/**
	 * Test hashCode method().
	 */
	@Test
	public void testHashCode() {
		assertEquals(s1.hashCode(), 338);
	}

	/**
	 * I expect ArrayIndexOutOfBoundsException since I'm trying to create a sector out of the bounds
	 * of the map.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testSector() {
		Sector s5 = new SecureSector(26, 5);
		// TODO assert something?!
	}
}
