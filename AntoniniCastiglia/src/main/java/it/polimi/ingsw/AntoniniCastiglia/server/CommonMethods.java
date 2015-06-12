package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.*;

/**
 * This class contains methods useful in the <code>server<code>'s package.
 * 
 * @author Paolo Antonini
 *
 */
public class CommonMethods {

	/**
	 * Constructor
	 */
	private CommonMethods() {
	}
	
	/**
	 * This method returns the identifier of the player.
	 * @param playerID
	 * @param playerList
	 * @return player's identifier, searching in the player's list
	 */
	public static Player toPlayer(int playerID, PlayerList playerList) {

		return playerList.get(playerID);

	}

	/**
	 * Convertion of the x coordinate, from a letter (char) to a number (int).
	 * @param sector
	 * @return the x coordinate converted into Integer.
	 */
	private static int getXcoord(String sector) {

		char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();

		String letter = sector.substring(0, 1);

		char c = letter.charAt(0);
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Convertion of the y coordinate, from a String to a number(int).
	 * The string is split, as it is made of two consecutive numbers.
	 * @param sector
	 * @return the y coordinate converted into integer
	 */
	private static int getYcoord(String sector) {
		String number = sector.substring(1, 3);
		return Integer.parseInt(number) - 1;
	}
	/**
	 * Association of a sector to the table.
	 * @param sector
	 * @param table
	 * @return coordinate x and coordinate y's related sector in the table. 
	 */
	public static Sector toSector(String sector, Table table) {
		int x = CommonMethods.getXcoord(sector);
		int y = CommonMethods.getYcoord(sector);
		
		return table.getSector(y, x);
	}
}
