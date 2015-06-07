package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.*;

public class CommonMethods {

	private CommonMethods() {
	}

	public static Player toPlayer(int playerID, PlayerList playerList) {

		return playerList.get(playerID);

	}

	private static int getXcoord(String sector) {

		char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();

		String letter = sector.substring(0, 1);

		char c = letter.charAt(0); // converting letter to char
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				return i;
			}
		}
		return -1;
	}

	private static int getYcoord(String sector) {
		String number = sector.substring(1, 3);
		return Integer.parseInt(number) - 1; // converting string number to integer
	}

	public static Sector toSector(String sector, Table table) {
		int x = CommonMethods.getXcoord(sector);
		int y = CommonMethods.getYcoord(sector);
		
		return table.getSector(y, x);
	}
}
