package it.polimi.ingsw.AntoniniCastiglia;

//TODO visibility, createAll, why static??, 

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

	private String name;
	private int id;
	static ArrayList<Player> players;

	public Player(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String toString() {
		return name + " " + id + "\n";
	}

	public static int howManyPlayers() {
		int n;
		System.out.print("How many players? ");

		while (true) {
			n = new Scanner(System.in).nextInt();

			try {
				if (n >= Constants.MINPLAYERS & n <= Constants.MAXPLAYERS) {
					return n;

				} else

				{
					throw new InvalidNumberException();

				}

			} catch (InvalidNumberException e) {
				System.out.println(e);
			}
		}

		// return n;

	}

	public static void createAll() {

		int n = howManyPlayers();

		System.out.println("Your game will have " + n + " players");

		players = new ArrayList<Player>();
		int alienNumber = n / 2 + (n % 2);
		int humanNumber = n / 2;

		System.out.println("Aliens: " + alienNumber);
		System.out.println("Humans: " + humanNumber);

		for (int i = 1; i <= n; i++) {
			Random rnd = new Random();
			boolean r;
			r = rnd.nextBoolean();
			System.out.println(r);

			if (r == true & alienNumber > 0) {
				Player p = new Player("A" + alienNumber, i);
				players.add(p);
				alienNumber--;

			} else if (r == false & humanNumber > 0) {
				Player p = new Player("H" + humanNumber, i);
				players.add(p);
				humanNumber--;
			} else if (alienNumber == 0) {
				Player p = new Player("H" + humanNumber, i);
				players.add(p);
				humanNumber--;
			} else {
				Player p = new Player("A" + alienNumber, i);
				players.add(p);
				alienNumber--;
			}

		}

		System.out.println(players);

	}

}