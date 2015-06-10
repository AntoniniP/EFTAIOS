package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the map for the game (it's called Table in order to avoid override problems
 * with Java predefined classes).
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
	private static final char SECURESECTOR = '4';
	private static final char ESCAPEHATCH = '5';

	// keeps a list of each type of sector, for convenience
	private static Sector alienBase;
	private static Sector humanBase;

	/**
	 * Constructor for Table class. Reads a text file containing a map (every sector is denoted by a
	 * number, to denote its type), and the matrix of Sector called table.
	 * 
	 * @throws IOException
	 */
	public Table() {
		FileInputStream f;
		try {
			f = new FileInputStream("resources/table.txt");

			for (int i = 0; i < Constants.HEIGHT; i++)
				for (int j = 0; j < Constants.WIDTH; j++) {

					switch (validChar(f)) {

						case EMPTYSECTOR:
							table[i][j] = new EmptySector(j, i);
							break;

						case DANGEROUSSECTOR:
							table[i][j] = new DangerousSector(j, i);
							break;

						case HUMANBASE:
							table[i][j] = new HumanBase(j, i);
							humanBase = table[i][j];
							break;

						case ALIENBASE:
							table[i][j] = new AlienBase(j, i);
							alienBase = table[i][j];
							break;

						case SECURESECTOR:
							table[i][j] = new SecureSector(j, i);
							break;

						case ESCAPEHATCH:
							table[i][j] = new EscapeHatch(j, i);
							break;

						default:
					}

				}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the reference to a sector of the map, given its coordinates.
	 * 
	 * @param y row
	 * @param x column
	 * @return the corresponding sector
	 */
	public Sector getSector(int y, int x) {
		return table[y][x];
	}

	/**
	 * Returns a String containing the whole map.
	 * 
	 * @return the map
	 */
	public String drawMap() {
		String string = "";

		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySector || (j % 2 == 1)) {
					string = string + "     ";
				} else {
					string = string + "[" + table[i][j] + "]";
				}
			}
			string = string + "\n";
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySector || (j % 2 == 0)) {
					string = string + "     ";
				} else {
					string = string + "[" + table[i][j] + "]";
				}

			}
			string = string + "\n";
		}
		return string;
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
	 * The method, after receiving a sector and the maximum distance, produces an ArrayList
	 * containing all reachable sectors within the given distance. The calling sector is EXCLUDED.
	 * 
	 * @param s the sector we are considering
	 * @param hops the maximum reachable distance
	 * @return ArrayList of sectors
	 */
	public ArrayList<Sector> adjacent(Sector s, int hops) {

		ArrayList<Sector> sectorList = new ArrayList<Sector>();

		sectorList.add(s);

		while (hops > 0) {

			List<Sector> tmp = new ArrayList<Sector>();

			for (Sector s1 : sectorList) {

				int x1 = s1.getX();
				int y1 = s1.getY();

				for (int i = y1 - 1; i <= y1 + 1; i++) {
					addSector(i, x1, tmp);
				}

				for (int j = x1 - 1; j <= x1 + 1; j++) {
					addSector(y1, j, tmp);
				}

				if (x1 % 2 == 1) {
					// Even columns
					addSector(y1 + 1, x1 - 1, tmp);
					addSector(y1 + 1, x1 + 1, tmp);

				} else {
					// Odd columns
					addSector(y1 - 1, x1 - 1, tmp);
					addSector(y1 - 1, x1 + 1, tmp);
				}
			}

			for (Sector s1 : tmp) {
				if (!sectorList.contains(s1) && s1.isReachable()) {
					sectorList.add(s1);
				}
			}

			sectorList.remove(s);
			hops--;
		}

		sectorList.trimToSize();
		return sectorList;
	}

	/**
	 * Adds a sector to the table (it's a method to support <code>adjacent</code> method.
	 * 
	 * @param y row
	 * @param x column
	 * @param tmp the list to which the sector must be added
	 */
	private void addSector(int y, int x, List<Sector> tmp) {
		try {
			tmp.add(table[y][x]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	/**
	 * Receives the reference to the map file, and returns only valid characters (that is, the chars
	 * corresponding to the various types of sectors).
	 * 
	 * @param f reference to the map file
	 * @return valid character
	 * @throws IOException
	 */
	private char validChar(FileInputStream f) throws IOException {
		char c = 0;
		boolean eof = false;

		try {
			do {
				c = (char) f.read();
			} while (!eof && c != ALIENBASE && c != DANGEROUSSECTOR && c != EMPTYSECTOR
					&& c != ESCAPEHATCH && c != HUMANBASE && c != SECURESECTOR);
		} catch (EOFException e) {
			eof = true;
			System.out.println(e);
		}
		return c;
	}

	/**
	 * Main to test some things.
	 * 
	 * @param args
	 * @deprecated to delete before final commit!
	 */
	@Deprecated
	public static void main(String[] args) {
		Table t = new Table();
		// Sector s = new DangerousSector(11, 8);
		// System.out.println(s + " " + t.adjacent(s, 2));
		System.out.println(t.drawMap().replace(';', '\n'));
	}

}
