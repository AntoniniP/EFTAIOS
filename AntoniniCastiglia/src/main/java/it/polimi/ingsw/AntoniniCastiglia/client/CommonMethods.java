package it.polimi.ingsw.AntoniniCastiglia.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	// TODO check + JavaDoc
	public static int[] validCard(String choice, int len) {
		int count = 0;
		int[] cardsToUse = new int[3];

		for (int i = 0; i < choice.length(); i++) {
			String ch = choice.substring(i, i + 1);

			if ("0".compareTo(ch)>0 || "1".compareTo(ch)>0 || "2".compareTo(ch)>0 || "3".compareTo(ch)>0) {
				int n = Integer.parseInt(ch);
				if (n >= 0 && n <= len) {
					cardsToUse[count] = n;
					count++;
				}
			}
		}
		return cardsToUse;
	}

	public static boolean validSector(String adjacents, String sector) {
		return adjacents.contains(sector);

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

	/*
	private static String readWriteLine(String format, Object... args) {
		if (System.console() != null) {
			return System.console().readLine(format, args);
		}

		System.out.print(String.format(format, args));

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
	*/
}
