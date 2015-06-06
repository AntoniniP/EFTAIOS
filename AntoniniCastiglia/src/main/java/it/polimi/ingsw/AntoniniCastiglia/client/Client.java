package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterfaceFactory;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterfaceFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

/**
 * Every instance of this class is the client for a player.
 * 
 * @author Paolo Antonini
 *
 */
public class Client {

	private String[] player;
	private int playerID;
	private String[] cards;
	private UserInterface ui;

	/**
	 * This is the <code>main</code> method of the class. It simply creates a network interface,
	 * which is passed to the constructor of the class. The whole game is started in the
	 * constructor.
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			NetworkInterface ni = NetworkInterfaceFactory.getInterface(chooseNetwork());
			System.out.println();
			Client application = new Client(ni);
		} catch (Exception e) {
			//System.out.println(e);
			e.printStackTrace();
		}

	}

	/**
	 * Private constructor for the class. Receives the network interface as parameter.
	 * 
	 * @param ni network interface
	 * @throws IOException
	 */
	private Client(NetworkInterface ni) throws RemoteException {
		ui = UserInterfaceFactory.getInterface(chooseUI());

		/***********************************************************************************/
		ui.connected(); // no!!! dopo ni.connect()!!!
		// assegnazione giocatore quando inizia la partita
		player = (new String(ni.connect())).split("_");
		playerID = Integer.parseInt(player[3]);
		ui.whoYouAreComplete(player);
		// System.out.println("Waiting for at least another player to begin.");
		/***********************************************************************************/

		ui.pleaseWait();
		while (!ni.isStarted()) {
			// Waiting for a game to begin
		}

		boolean endGame = false;
		while (!endGame) {
			if (!ni.isEnded()) {

				// print map
				ui.printMap(ni.getMap(playerID).replace(";", "\n"));

				// get cards
				cards = ni.getCards(playerID).split(";");
				
				if (canUseCards()) {

				}
				endGame=true;
			} else {
				endGame = true;
				System.out.println("THE WINNER IS " + ni.getWinner());
			}
		}
	}

	/**
	 * Asks the player to choose his preferred connection method.
	 * 
	 * @return an integer parameter
	 */
	private static int chooseNetwork() {
		String choice;
		while (true) {
			System.out.println("Choose your preferred network interface:");
			System.out.println("1 - RMI");
			System.out.println("2 - Socket (not implemented yet)");
			choice = readLine();
			if ("1".equals(choice) || "2".equals(choice)) {
				return Integer.parseInt(choice);
			}
			System.out.println("Please, choose between 1 or 2.");
		} 
	}

	/**
	 * Asks the player to choose his preferred graphical interface.
	 * 
	 * @return an integer parameter
	 */
	private static int chooseUI() {
		String choice;
		while (true) {
			System.out.println("\n" + "Now choose your preferred user interface:");
			System.out.println("1 - CLI");
			System.out.println("2 - GUI (not implemented yet)");
			choice = readLine();
			if ("1".equals(choice) || "2".equals(choice)) {
				return Integer.parseInt(choice);
			}
			System.out.println("Please, choose between 1 or 2.");
		} 
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

	// Use card, then move
	private void phase1(NetworkInterface ni) throws RemoteException {
		boolean canUseCards = canUseCards();

		if (canUseCards) {
			ui.chooseCards();
			// TODO usecards;
		}

		String adjacents = new String(ni.getAdjacents());
		String chosenSector = null;
		do {
			ui.askMove(adjacents, player[0]);
			chosenSector = readLine();
		} while (!CommonMethods.validSector(adjacents, chosenSector));
		ni.move(chosenSector, player[0]);

	}

	// Use card (attack) if HUMAN, then attack
	private void phase2() {
		if (canAttack()) {
			/*
			 * prompt human: You can attack! TODO attack boolean hasattacked
			 */

		}
	}

	// Use card, then end turn
	private void phase3(UserInterface ui, NetworkInterface ni, String... cards) {
		// if(!hasAttacked)
		{
			// ni.drawDangerousSectorCard();
		}
		boolean canUseCards = canUseCards();

		if (canUseCards) {
			ui.chooseCards();
			// TODO usecards;
		}
	}

	private boolean canUseCards() {
		for (String card : cards) {
			if (card != null) {
				return true;
			}
		}
		return false;
	}

	private boolean canAttack() {
		// if player is alien
		if (player[2].contains("_A_")) {
			return true;
		}
		// if player if human but has Attack card
		for (String card : cards) {
			if (card != null) {
				return true;
			}
		}
		// if player if human but has Attack card
		return false;
	}

}