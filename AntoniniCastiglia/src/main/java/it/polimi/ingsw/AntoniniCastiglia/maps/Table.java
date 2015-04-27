package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

import java.io.*;

public class Table {
	private Sectors[][] table = new Sectors[Constants.HEIGHT][Constants.WIDTH];
	FileInputStream f = new FileInputStream("table.txt");//File still empty; I'll fill it tomorrow I swear!
	boolean eof = false;
	int c;
	
	
	// Trasposta of what I see in computation
	public Table() throws IOException { // No errors signaled now, Probably the Try/catch thing is missing
		// Exemple of filling
		for (int i = 0; i < Constants.HEIGHT && eof; i++)
			for (int j = 0; j < Constants.WIDTH && eof; j++) {
				c = f.read();
				if (c == -1)
					eof = true;
				else {
					if (c == 0)
						table[j][i] = new EmptySectors(i, j);
					if (c == 1)
						table[j][i] = new DangerSectors(i, j);
					if (c == 2)
						table[j][i] = new HumanBase(i, j);
					if (c == 3)
						table[j][i] = new AlienBase(i, j);
					if (c == 4)
						table[j][i] = new SafeSectors(i, j);
					if (c == 5)
						table[j][i] = new EscapeHatches(i, j);
				}
			}
		f.close();
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
