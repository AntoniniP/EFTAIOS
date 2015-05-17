package it.polimi.ingsw.AntoniniCastiglia.maps;

// TODO SINGLETON? An updateTable() method is needed.
// TODO adjacent() is horrible!
// TODO validChar() is bad too (maybe enumeration for sector types?)
// TODO assuming (for now) that "table.txt" is well-formed
// TODO drawMap is VIEW
// TODO improve drawMap in order to avoid repeated code


import it.polimi.ingsw.AntoniniCastiglia.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the map for the game (it's called Table in order to avoid
 * override problems with Java predefined classes).
 * 
 * @author Laura Castiglia
 *
 */
public class Table {

	private Sector[][] table = new Sector[Constants.HEIGHT][Constants.WIDTH];

	private static final char EMPTYSECTOR = '0';
	private static final char DANGEROUSSECTOR = '1';
	private static final char HUMANBASE = '2';
	private static final char ALIENBASE = '3';
	private static final char SAFESECTOR = '4';
	private static final char ESCAPEHATCH = '5';

	// keeps a list of each type of sector, for convenience
	private static Sector alienBase;
	private static Sector humanBase;
	private ArrayList<Sector> dangerSectors = new ArrayList<Sector>();
	private ArrayList<Sector> emptySectors = new ArrayList<Sector>();
	private ArrayList<Sector> escapeHatches = new ArrayList<Sector>();
	private ArrayList<Sector> safeSectors = new ArrayList<Sector>();

	/**
	 * Constructor for Table class. Reads a text file containing a map (every
	 * sector is denoted by a number, to denote its type), and the matrix of
	 * Sector called table.
	 * 
	 * @throws IOException
	 */
	public Table() throws IOException {
		FileInputStream f = new FileInputStream("resources/table.txt");

		for (int i = 0; i < Constants.HEIGHT; i++)
			for (int j = 0; j < Constants.WIDTH; j++) {

				switch (validChar(f)) {

				case EMPTYSECTOR:
					table[i][j] = new EmptySector(j, i);
					emptySectors.add(table[i][j]);
					break;

				case DANGEROUSSECTOR:
					table[i][j] = new DangerousSector(j, i);
					dangerSectors.add(table[i][j]);
					break;

				case HUMANBASE:
					table[i][j] = new HumanBase(j, i);
					humanBase = table[i][j];
					break;

				case ALIENBASE:
					table[i][j] = new AlienBase(j, i);
					alienBase = table[i][j];
					break;

				case SAFESECTOR:
					table[i][j] = new SafeSector(j, i);
					safeSectors.add(table[i][j]);
					break;

				case ESCAPEHATCH:
					table[i][j] = new EscapeHatch(j, i);
					escapeHatches.add(table[i][j]);
					break;

				default:
				}

			}
		f.close();
	}

	/**
	 * The method receives the reference to the map file, and returns only valid
	 * characters (that is, the chars corresponding to the various types of
	 * sectors).
	 * 
	 * @param f
	 *            reference to the map file
	 * @return valid character
	 * @throws IOException
	 */
	private char validChar(FileInputStream f) throws IOException {
		char c = 0;
		boolean eof = false;

		try {
			do {
				c = (char) f.read();
			} while (!eof && c != ALIENBASE && c != DANGEROUSSECTOR
					&& c != EMPTYSECTOR && c != ESCAPEHATCH && c != HUMANBASE
					&& c != SAFESECTOR);
		} catch (EOFException e) {
			eof = true;
			System.out.println(e);
		}
		return c;
	}

	/**
	 * The method draws the map on the console.
	 */
	public void drawMap() {

		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySector || (j % 2 == 1)) {
					System.out.print("     ");
				} else {
					System.out.print("[" + table[i][j] + "]");
				}
			}
			System.out.println();
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySector || (j % 2 == 0)) {
					System.out.print("     ");
				} else {
					System.out.print("[" + table[i][j] + "]");
				}

			}
			System.out.println();

		}
	}

	/**
	 * Standard getter for Alien base sector.
	 * 
	 * @return alien base sector
	 */
	public static Sector getAlienBase() {
		return alienBase;
	}

	/**
	 * Standard getter for human base sector.
	 * 
	 * @return human base sector
	 */
	public static Sector getHumanBase() {
		return humanBase;
	}

	/**
	 * The method, after receiving a sector and the maximum distance, produces
	 * an ArrayList containing all reachable sectors within the given distance.
	 * The calling sector is EXCLUDED.
	 * 
	 * @param s
	 * @param hops
	 * @return ArrayList of sectors
	 */
	public ArrayList<Sector> adjacent(Sector s, int hops) {
		ArrayList<Sector> sectorList = new ArrayList<Sector>();
		sectorList.add(s);

		int x = s.getX();
		int y = s.getY();

		while (hops > 0) {
			List<Sector> tmp = new ArrayList<Sector>();

			for (Sector s1 : sectorList) {

				int x1 = s1.getX();
				int y1 = s1.getY();

				for (int i = y1 - 1; i <= y1 + 1; i++) {
					for (int j = x1 - 1; j <= x1 + 1; j++) {
						if ((i >= 0 && i < Constants.HEIGHT)
								&& (j >= 0 && j < Constants.WIDTH)) {
							if (!(table[i][j] instanceof EmptySector)
									&& !(j == x1 && i == y1)) {
								if ((x1 % 2 == 1)
										&& (!(j == x1 - 1 && i == y1 - 1) && !(j == x1 + 1 && i == y1 - 1))) {
									tmp.add(table[i][j]);
								} else if ((x1 % 2 == 0)
										&& (!(j == x1 - 1 && i == y1 + 1) && !(j == x1 + 1 && i == y1 + 1))) {
									tmp.add(table[i][j]);
								}
							}
						}
					}
				}

			}

			for (Sector s1 : tmp) {

				if (!sectorList.contains(s1) && !s.isEqual(s1)) {
					sectorList.add(s1);
				}
			}

			sectorList.remove(s);

			hops--;

		}

		return sectorList;

	}
}
