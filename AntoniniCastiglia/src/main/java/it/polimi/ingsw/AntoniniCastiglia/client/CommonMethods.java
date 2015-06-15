package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.maps.MapConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * This class contains some methods that may be useful in the whole <code>client</code> package.
 * 
 * @author Paolo Antonini
 *
 */
public class CommonMethods {

	/**
	 * Private constructor, to hide the implicit one.
	 */
	private CommonMethods() {
	}

	/**
	 * Checks whether a <code>sector</code>, chosen by the player, is a valid destination (i.e. it
	 * is contained in the <code>adjacentSectors</code> list).
	 * 
	 * @param adjacentSectors string containing all the adjacent sectors
	 * @param chosenSector sector chosen by the player
	 * @return true/false
	 */
	public static boolean validSector(String chosenSector) {
		String number;
		try {
			number = chosenSector.substring(1, 3);
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}
		int n;
		try {
			n = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return n <= MapConstants.HEIGHT;

	}

	/**
	 * Simplifies the acquisition of a string.
	 * 
	 * @return the acquired string
	 */
	public static String readLine() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String read = null;
		try {
			read = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return read;
	}

	public static void doMagic(int time) {
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			// nothing to do; the sleep is fundamental to let everything work
		}

	}

}
