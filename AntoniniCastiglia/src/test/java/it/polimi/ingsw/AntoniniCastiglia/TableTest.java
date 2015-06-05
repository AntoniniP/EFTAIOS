package it.polimi.ingsw.AntoniniCastiglia;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.maps.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

// TODO test: top(1), bottom(1), left(1), right(1), centre(2)!

public class TableTest {
	Table t;
	ArrayList<Sector> expected;
	
	@Before
	public void setUp() throws IOException{
		t = new Table();
	}

	@Test
	public void testAdjacent1() {
		ArrayList<Sector> produced = new ArrayList<Sector>();
		expected = new ArrayList<Sector>();
		boolean check = true;
		
		expected.add(new DangerousSector("B01"));
		expected.add(new SecureSector("B02"));
		expected.add(new SecureSector("A03"));
		
		produced = t.adjacent(new DangerousSector("A02"), 1);

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
	
	@Test
	public void testAdjacent2() {
		ArrayList<Sector> produced = new ArrayList<Sector>();
		expected = new ArrayList<Sector>();
		boolean check = true;
		
		expected.add(new SecureSector("J08"));//
		expected.add(new DangerousSector("J09"));//
		expected.add(new SecureSector("J10"));
		expected.add(new SecureSector("K08"));//
		expected.add(new DangerousSector("K09"));//
		expected.add(new SecureSector("K10"));//
		expected.add(new DangerousSector("K11"));//
		expected.add(new SecureSector("L10"));//
		expected.add(new DangerousSector("L11"));//
		expected.add(new SecureSector("M08"));//
		expected.add(new DangerousSector("M09"));//
		expected.add(new SecureSector("M10"));//
		expected.add(new DangerousSector("M11"));//
		expected.add(new SecureSector("N08"));
		expected.add(new DangerousSector("N09"));//
		expected.add(new SecureSector("N10"));//
		
		produced = t.adjacent(new DangerousSector(11,8), 2);

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

}
