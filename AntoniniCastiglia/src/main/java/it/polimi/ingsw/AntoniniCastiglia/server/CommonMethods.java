package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;
import java.util.concurrent.TimeUnit;

/**
 * This class contains methods useful in the <code>server<code> package.
 *
 * @author Paolo Antonini
 *
 */
public class CommonMethods {

	/**
	 * Private constructor to hide the implicit one.
	 */
	private CommonMethods() {
	}

	/**
	 * This method returns the identifier of the player.
	 * 
	 * @param playerID
	 * @param playerList
	 * @return player's identifier, searching in the player's list
	 */
	public static Player toPlayer(int playerID, PlayerList playerList) {
		return playerList.get(playerID);
	}

	/**
	 * Conversion of the x coordinate, from a letter (char) to a number (int).
	 * 
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
	 * Conversion of the y coordinate, from a String to a number(int).
	 * 
	 * @param sector
	 * @return the y coordinate converted into integer
	 */
	private static int getYcoord(String sector) {
		String number = sector.substring(1, 3);
		return Integer.parseInt(number) - 1;
	}

	/**
	 * Association of a sector to the table.
	 * 
	 * @param sector
	 * @param table
	 * @return a reference to the table sectors of coordinates x,y
	 */
	public static Sector toSector(String sector, Table table) {
		String mySector = new String(sector);
		if (sector.length() > 3) {
			int index = sector.indexOf(Constants.ANSI_RESET);
			mySector = sector.substring(index - 3, index);
		}
		int x = CommonMethods.getXcoord(mySector);
		int y = CommonMethods.getYcoord(mySector);
		return table.getSector(y, x);
	}

	/**
	 * Magical method which allows some things to work flawlessly. Simply, it makes the thread sleep
	 * for a while.
	 *
	 * @param time milliseconds to sleep
	 */
	public static void sleep(int time) {
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			// nothing to do; the sleep is fundamental to let everything work
		}

	}
}
