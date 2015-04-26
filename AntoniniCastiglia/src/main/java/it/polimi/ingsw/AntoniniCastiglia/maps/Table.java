package it.polimi.ingsw.AntoniniCastiglia.maps;

import java.io.*;

public class Table {
	private Tiles[][] table = new Tiles[10][10];

	// FileReader fileIn = new FileReader("Table.txt"); // Error: unhandled
	// exception type
	// FileNotFound - ERGO:
	// Dunno what it means
	// int c = fileIn.read();// Same error as previous line

	// Trasposta of what I see in computation
	public Table() {
		// Exemple of filling
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) { // Creating a temporary numeric
											// system to distinguish the tiles
				/*
				 * if (c == 0) table[j][i] = new EmptyTiles(i, j);// Swap the
				 * indexes in // order to print the // table correctly if (c ==
				 * 1) table[j][i] = new DangerTiles(i, j); if (c == 2)
				 * table[j][i] = new HumanBase(i, j); if (c == 3) table[j][i] =
				 * new AlienBase(i, j); if (c == 4) table[j][i] = new
				 * SafeTiles(i, j); if (c == 5) table[j][i] = new ShallopTile(i,
				 * j);
				 */
			}
		// fileIn.close();*/
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				toReturn = toReturn + table[i][j] + " ";
			toReturn = toReturn + "\n";
		}
		return toReturn;
	}
}
