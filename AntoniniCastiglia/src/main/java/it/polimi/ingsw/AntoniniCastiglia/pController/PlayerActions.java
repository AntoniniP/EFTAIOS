package it.polimi.ingsw.AntoniniCastiglia.pController;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.players.InvalidNumberException;

import java.util.Scanner;

public class PlayerActions {

	/**
	 * Gets a number from stdin, checks it and, if it's valid according to
	 * EFTAIOS rules about the number of players, returns it.
	 * 
	 * @return n
	 */
	public static int howManyPlayers() {

		System.out.print("How many players? ");

		while (true) {

			Scanner scanner = new Scanner(System.in);

			int n = 0;

			try {

				n = scanner.nextInt();

				if (n >= Constants.MINPLAYERS && n <= Constants.MAXPLAYERS) {
					scanner.close();
					return n;
				} else {
					throw new InvalidNumberException();
				}
			} catch (InvalidNumberException e) {
				System.out.println(e);
				n = 0;
			} catch (java.util.InputMismatchException e2) {
				System.out.println(e2);
				n = 0;
			}

		}

	}

}
