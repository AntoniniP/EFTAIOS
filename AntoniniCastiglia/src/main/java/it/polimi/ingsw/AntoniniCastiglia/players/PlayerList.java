package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;

/**
 * This class provides a well formed ArrayList of players.
 * 
 * @author Paolo Antonini
 *
 */
public class PlayerList {

	private static PlayerList instance;

	ArrayList<Player> players;

	/**
	 * Private constructor for the class (SINGLETON pattern implementation). it
	 * provides an ArrayList of length n, without repetitions, and with a proper
	 * number of Aliens and Humans, to comply with EFTAIOS rules.
	 */
	private PlayerList(int n) {

		int alienNumber = n / 2 + (n % 2);
		int humanNumber = n / 2;

		players = new ArrayList<Player>(n);

		boolean[] used = new boolean[8];
		for (boolean i : used) {
			i = false;
		}

		for (int i = 0; i < n; i++) {

			Player p;
			int j;

			// Generate a random, not used yet, integer 0<=j<=7.
			do {
				j = (int) (Math.random() * 8); // Cast to int truncates it
			} while (used[j]);

			used[j] = true;

			Character c = Character.values()[j]; // Gets from Character enum

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
	}



	/**
	 * Implementation of SINGLETON pattern. Receives the number of players (<code>n</code>),
	 * returns a single instance of <code>PlayerList</code>.
	 * 
	 * @param n number of players
	 * @return a <code>PlayerList</code> instance
	 */
	public static PlayerList getPlayerList(int n) {
		if (instance == null) {
			instance = new PlayerList(n);
		}
		return instance;
	}

}
