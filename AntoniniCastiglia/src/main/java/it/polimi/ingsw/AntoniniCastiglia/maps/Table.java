package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

import java.io.*;
import java.util.ArrayList;

public class Table {
	private Sector[][] table = new Sector[Constants.HEIGHT][Constants.WIDTH];

	// keeps a list of each type of sector, for convenience
	// maybe as coordinates??
	
	// TODO SOLO PUNTATORI!!!!
	
	public static Sector alienBase;
	public static Sector humanBase;
	private ArrayList<Sector> dangerSectors = new ArrayList<Sector>();
	private ArrayList<Sector> emptySectors = new ArrayList<Sector>();
	private ArrayList<Sector> escapeHatches = new ArrayList<Sector>();
	private ArrayList<Sector> safeSectors = new ArrayList<Sector>();

	public Table() throws IOException {
		char c;
		boolean eof = false;
		FileInputStream f = new FileInputStream("resources/table.txt");

		for (int i = 0; i < Constants.HEIGHT && !eof; i++)
			for (int j = 0; j < Constants.WIDTH && !eof; j++) {

				// TODO assuming (for now) that "table.txt" is well-formed

				do {
					c = (char) f.read();
				} while (c != '0' && c != '1' && c != '2' && c != '3'
						&& c != '4' && c != '5');

				if (c == '0') {
					table[i][j] = new EmptySector(i, j);
					emptySectors.add(table[i][j]);

				} else if (c == '1') {
					table[i][j] = new DangerSector(i, j);
					dangerSectors.add(table[i][j]);

				} else if (c == '2') {
					table[i][j] = new HumanBase(i, j);
					humanBase = new HumanBase(i, j);

				} else if (c == '3') {
					table[i][j] = new AlienBase(i, j);
					alienBase = new AlienBase(i, j);

				} else if (c == '4') {
					table[i][j] = new SafeSector(i, j);
					safeSectors.add(table[i][j]);

				} else if (c == '5') {
					table[i][j] = new EscapeHatch(i, j);
					escapeHatches.add(table[i][j]);

				} else {
					; // TODO exception? NOPE: never reached thanks to do_while
				}

			}

		f.close();

	}

	public void drawMap() {

		// TODO improve in order to avoid repeated code
		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySector || (j % 2 == 1)

				) {
					System.out.print("     ");
				} else {
					System.out.print("[" + table[i][j] + "]" + "");
				}

			}
			System.out.println();
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySector || (j % 2 == 0)) {
					System.out.print("     ");
				} else {
					System.out.print("[" + table[i][j] + "]" + "");
				}

			}
			System.out.println();

		}
	}
}
