package it.polimi.ingsw.AntoniniCastiglia.players;

//TODO visibility, createAll, why static??, movementRecord+currentPlace,

import it.polimi.ingsw.AntoniniCastiglia.Constants;

import java.util.Scanner;

public class Player {

	// Attributes
	private String name;
	private int id;

	// static ArrayList<Player> players;

	// Constructor
	protected Player(String name, int id) {
		this.name = name;
		this.id = id;
	}

	// Methods

	/*
	 * @Override public String toString() { return name + " " + id + "\n"; }
	 */

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

	// Creates a proper array of Player, of length n, according to EFTAIOS rules.
	public static void createAll() {
		int n = howManyPlayers();
		Player[] players = new Player[n];

		// players = new ArrayList<Player>();

		int alienNumber = n / 2 + (n % 2);
		int humanNumber = n / 2;

		System.out.println("Your game will have " + n + " players");
		System.out.println("Aliens: " + alienNumber);
		System.out.println("Humans: " + humanNumber);

		for (int i = 0; i < n; i++) {
			
			float j = (float) Math.random(); //Generates a double between 0 and 1.
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
			
			// players.add(p);
			players[i] = p;

		}

		// System.out.println(players);

		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].name + " " + players[i].id);
		}

	}

}