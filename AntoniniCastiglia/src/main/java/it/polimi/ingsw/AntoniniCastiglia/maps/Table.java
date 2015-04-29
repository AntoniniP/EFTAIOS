package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import java.io.*;

public class Table {
	private Sectors[][] table = new Sectors[Constants.HEIGHT][Constants.WIDTH];
	char c;
	boolean eof = false;

	public Table() throws IOException {
		FileInputStream f = new FileInputStream("table.txt");

		for (int i = 0; i < Constants.HEIGHT && !eof; i++)
			for (int j = 0; j < Constants.WIDTH && !eof; j++) {
				// assuming that "table.txt" is well-formed

				do {
					c = (char) f.read();
				} while (c != '0' && c != '1' && c != '2' && c != '3'
						&& c != '4' && c != '5');

				if (c == '0') {
					table[i][j] = new EmptySectors(i, j);
				} else if (c == '1') {
					table[i][j] = new DangerSectors(i, j);
				} else if (c == '2') {
					table[i][j] = new HumanBase(i, j);
				} else if (c == '3') {
					table[i][j] = new AlienBase(i, j);
				} else if (c == '4') {
					table[i][j] = new SafeSectors(i, j);
				} else if (c == '5') {
					table[i][j] = new EscapeHatches(i, j);
				} else {
					; // maybe an exception?
				}
				

			}
		f.close();

	}

	public void drawMap() {
		// original code
/*		for (int i = 0; i < Constants.HEIGHT; i++) {
			if (i % 2 == 1) {// indentation to comply with hexagonal sectors
				System.out.print("   ");
			}
			for (int j = 0; j < Constants.WIDTH; j++) {

				if (table[i][j] instanceof EmptySectors) {
					System.out.print("      ");
				} else {
					System.out.print("(" + table[i][j] + ")" + " ");
				}
			}
			System.out.println();
		}
*/
		// TODO to improve in order to avoid repeated code
		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySectors || (j % 2 == 1)

				) {
					System.out.print("     ");
				} else {
					System.out.print("[" + table[i][j] + "]" + "");
				}

			}
			System.out.println();
			for (int j = 0; j < Constants.WIDTH; j++) {
				if (table[i][j] instanceof EmptySectors || (j % 2 == 0)) {
					System.out.print("     ");
				} else {
					System.out.print("[" + table[i][j] + "]" + "");
				}

			}
			System.out.println();

		}
	}
}
