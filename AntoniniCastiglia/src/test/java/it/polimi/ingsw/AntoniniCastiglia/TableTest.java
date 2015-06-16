package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.polimi.ingsw.AntoniniCastiglia.maps.DangerousSector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.SecureSector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for table class.
 *
 * @author Paolo Antonini
 *
 */
public class TableTest {

	Table t;
	ArrayList<Sector> expected;
	Sector boundary;
	Sector center;
	Sector outside;

	/**
	 * Setup for the tests.
	 */
	@Before
	public void setUp() throws IOException {
		t = new Table();
		boundary = new DangerousSector("A02");
		center = new DangerousSector(11, 8);
		outside = new SecureSector("Z17");
	}

	/**
	 * Test for adjacency on the boarder of the map.
	 */
	@Test
	public void testAdjacentBoundary() {
		ArrayList<Sector> produced = new ArrayList<Sector>();
		expected = new ArrayList<Sector>();
		boolean check = true;

		expected.add(new DangerousSector("B01"));
		expected.add(new SecureSector("B02"));
		expected.add(new SecureSector("A03"));

		produced = t.adjacent(boundary, 1);

		for (int i = 0; i < produced.size(); i++) {
			if (!(expected.contains(produced.get(i)))) {
				check = false;
			}
			expected.remove(produced.get(i));
		}

		if (!expected.isEmpty()) {
			fail();
		}
		assertTrue(check);
	}

	/**
	 * Tests for adjacency in the centre of a map, at distance 2.
	 */
	@Test
	public void testAdjacentCenter() {
		ArrayList<Sector> produced = new ArrayList<Sector>();
		expected = new ArrayList<Sector>();
		boolean check = true;

		expected.add(new SecureSector("J08"));
		expected.add(new DangerousSector("J09"));
		expected.add(new SecureSector("J10"));
		expected.add(new SecureSector("K08"));
		expected.add(new DangerousSector("K09"));
		expected.add(new SecureSector("K10"));
		expected.add(new DangerousSector("K11"));
		expected.add(new SecureSector("L10"));
		expected.add(new DangerousSector("L11"));
		expected.add(new SecureSector("M08"));
		expected.add(new DangerousSector("M09"));
		expected.add(new SecureSector("M10"));
		expected.add(new DangerousSector("M11"));
		expected.add(new SecureSector("N08"));
		expected.add(new DangerousSector("N09"));
		expected.add(new SecureSector("N10"));

		produced = t.adjacent(center, 2);

		for (int i = 0; i < produced.size(); i++) {
			if (!(expected.contains(produced.get(i)))) {
				check = false;
			}
			expected.remove(produced.get(i));
		}

		if (!expected.isEmpty()) {
			fail();
		}
		assertTrue(check);
	}

	/**
	 * If I create a sector outside the map, the adjacent list is empty.
	 */
	@Test
	public void testAdjacentAlone() {
		ArrayList<Sector> produced = new ArrayList<Sector>();

		produced = t.adjacent(outside, 1);

		assertTrue(produced.isEmpty());
	}
}
