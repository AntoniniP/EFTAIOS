package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

import java.util.ArrayList;
import java.util.Scanner;

public class Players {
	static ArrayList<Player> players;

	
	
	
	// Gets from stdin, checks and returns the number of players.
	private static int howManyPlayers() {
		int n;

		System.out.print("How many players? ");

		while (true) {
			Scanner scanner = new Scanner(System.in);
			n = scanner.nextInt();
			try {
				if (n >= Constants.MINPLAYERS && n <= Constants.MAXPLAYERS) {
					scanner.close();
					return n;
				} else {
					throw new InvalidNumberException();
				}
			} catch (InvalidNumberException e) {
				System.out.println(e);
			} catch (java.util.InputMismatchException e) {
				// TODO type mismatch exception handling
			}
		}
	}

	// Creates an arrayList of Player, of length n, according to EFTAIOS rules.
	public static void createAll() {
		int n = howManyPlayers();

		players = new ArrayList<Player>();

		int alienNumber = n / 2 + (n % 2);
		int humanNumber = n / 2;

		System.out.println("Your game will have " + n + " players");
		System.out.println("Aliens: " + alienNumber);
		System.out.println("Humans: " + humanNumber);

		for (int i = 0; i < n; i++) {

			float j = (float) Math.random(); // Generates a double between 0 and
												// 1.
			Player p;

			if (j < 0.5 && alienNumber > 0) {
				p = new Alien("A" + alienNumber, i);
				alienNumber--;
			} else if (j >= 0.5 && humanNumber > 0) {
				p = new Human("H" + humanNumber, i);
				humanNumber--;
			} else if (alienNumber == 0) {
				p = new Human("H" + humanNumber, i);
				humanNumber--;
			} else {
				p = new Alien("A" + alienNumber, i);
				alienNumber--;
			}

			players.add(p);

		}

		System.out.println(players);



	}
}
