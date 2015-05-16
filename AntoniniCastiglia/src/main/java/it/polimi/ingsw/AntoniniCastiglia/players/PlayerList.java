package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerList {
	ArrayList<Player> players;

	// Gets from stdin, checks and returns the number of players.
	private int howManyPlayers() {
		Scanner scanner = new Scanner(System.in);
		int n = 0;

		while (true) {
			System.out.print("How many players? ");

			try {

				n = scanner.nextInt();

				if (n >= Constants.MINPLAYERS && n <= Constants.MAXPLAYERS) {
					scanner.close();
					return n;
				} else {
					throw new InvalidNumberException();
				}
			} catch (InvalidNumberException e) {
				System.out.println(e + "\n");
			}
			// catch (java.util.InputMismatchException e) { n=0;}

		}

	}

	// Constructor.
	// Creates an arrayList of Player, of length n, according to EFTAIOS rules.
	public PlayerList() {

		int n = howManyPlayers();

		players = new ArrayList<Player>(n);
		boolean[] used = new boolean[8];

		for (boolean i : used) {
			i = false;
		}

		int alienNumber = n / 2 + (n % 2);
		int humanNumber = n / 2;

		System.out.println("Your game will have " + n + " players.");
		System.out.println("- Aliens: " + alienNumber);
		System.out.println("- Humans: " + humanNumber);

		for (int i = 0; i < n; i++) {

			Player p;
			int j;

			// Generate a non-used integer 0<j<7 (cast op. truncates it)
			do {
				j = (int) (Math.random() * 8);
			} while (used[j]);

			used[j] = true;
			Character c = Character.values()[j];

			if (c.nature == 'H') {
				if (humanNumber > 0) {
					p = new Human(c.name, c.role, i);
					humanNumber--;
				} else {
					p = new Alien(c.name, c.role, i);
					alienNumber--;
				}

			} else {
				if (alienNumber > 0) {
					p = new Alien(c.name, c.role, i);
					alienNumber--;
				} else {
					p = new Human(c.name, c.role, i);
					humanNumber--;
				}
			}

			players.add(p);

		}
		
		// TODO write test: aliens 0, humans 0
		
		System.out.println(players);

	}
}
