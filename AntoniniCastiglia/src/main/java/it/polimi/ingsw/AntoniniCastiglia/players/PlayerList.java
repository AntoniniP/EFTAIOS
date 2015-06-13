package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;

/**
 * This class provides a well formed ArrayList of players.
 * 
 * @author Paolo Antonini
 *
 */
public class PlayerList {

	ArrayList<Player> players;

	/**
	 * Constructor for the class. It provides an ArrayList of length <code>n</code>, without
	 * repetitions, and with a proper number of Aliens and Humans, to comply with EFTAIOS rules.
	 * 
	 * @param n the number of players
	 */
	@SuppressWarnings("unused")
	public PlayerList(int n) {

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
				j = (int) (Math.random() * 8); // it's +8 since cast to (int) truncates it
			} while (used[j]);

			used[j] = true;

			Character c = Character.values()[j]; // Gets from Character enumeration

			if (c.nature == 'H') {
				if (humanNumber > 0) {
					p = new Human(c.name, c.role);
					humanNumber--;
				} else {
					p = new Alien(c.name, c.role);
					alienNumber--;
				}
			} else {
				if (alienNumber > 0) {
					p = new Alien(c.name, c.role);
					alienNumber--;
				} else {
					p = new Human(c.name, c.role);
					humanNumber--;
				}
			}

			players.add(p);

		}
	}
/************************ REMOVE *********************************/
	@Override
	@Deprecated
	public String toString() {
		String playerList = "";
		for (int i = 0; i < players.size(); i++) {
			playerList = playerList + players.get(i) + "\n";
		}
		return playerList;
	}
/*****************************************************************/
	/**
	 * Returns the size of the list of players.
	 * 
	 * @return the size of the list of players
	 */
	public int size() {
		return players.size();
	}

	/**
	 * Returns the <code>i</code>-th player of the list.
	 * 
	 * @param i index of the desired player
	 * @return the desired player
	 */
	public Player get(int i) {
		return players.get(i);
	}
}
