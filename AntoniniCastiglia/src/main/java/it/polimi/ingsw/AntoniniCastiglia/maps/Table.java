package it.polimi.ingsw.AntoniniCastiglia.maps;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

import java.io.*;

public class Table {
	private Sectors[][] table = new Sectors[Constants.HEIGHT][Constants.WIDTH];
	FileInputStream f = new FileInputStream("table.txt");
	char c;
	boolean eof=false;

	public Table() throws IOException { 
		c = (char) f.read();
		
		for (int i = 0; i < Constants.HEIGHT && !eof; i++)
			for (int j = 0; j < Constants.WIDTH && !eof; j++) {
				if (c == '0')
					table[i][j] = new EmptySectors(i + 1, j + 1);
				else if (c == '1')
					table[i][j] = new DangerSectors(i + 1, j + 1);
				else if (c == '2')
					table[i][j] = new HumanBase(i + 1, j + 1);
				else if (c == '3')
					table[i][j] = new AlienBase(i + 1, j + 1);
				else if (c == '4')
					table[i][j] = new SafeSectors(i + 1, j + 1);
				else if (c == '5')
					table[i][j] = new EscapeHatches(i + 1, j + 1);
				else ;
				c = (char) f.read();
			}
		f.close();

	}

	public void drawMap() {
		for (int i = 0; i < Constants.HEIGHT; i++) {
			for (int j = 0; j < Constants.WIDTH; j += 2) {
				if (table[j][i] instanceof EmptySectors)
					System.out.print("      ");
				else
					System.out.print("(" + table[j][i] + ")" + " ");
			}
			System.out.println();
			System.out.print("   ");
			for (int j = 1; j < Constants.WIDTH; j += 2) {
				if (table[j][i] instanceof EmptySectors)
					System.out.print("      ");
				else
					System.out.print("(" + table[j][i] + ")" + " ");
			}
			System.out.println();
		}
	}
}
