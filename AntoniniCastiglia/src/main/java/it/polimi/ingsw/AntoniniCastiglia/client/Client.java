package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterfaceFactory;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterfaceFactory;
import java.io.IOException;
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
	private String nature;
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
			// System.out.println(e);
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
		playerID = ni.connect();
		//ui.connected(); 
		System.out.println("playerID: " + playerID);
		/***********************************************************************************/

		ui.pleaseWait();
		while (!ni.isStarted()) {
			// Waiting for a game to begin
		}
		player = (new String(ni.getPlayer(playerID))).split("_");
		nature = player[2];
		ui.whoYouAreComplete(player);
		
		if (playerID-1 != Integer.parseInt(player[3])) {
			System.out.println("Ops! Something went wrong.");
		}


		boolean endGame = false;
		while (!endGame) {
			if (!ni.isEnded()) {
				boolean hasAttacked = false;
				// print map
				ui.printMap(ni.getMap(playerID).replace(";", "\n"));

				// get cards
				cards = ni.getCards(playerID).split(";");
				ui.printCards(canUseCards(), cards);

				ui.yourTurn();
				
				phase1(ni);
				
				if (canAttack()) {
					phase2();
					hasAttacked = true;
				}
				
				phase3(ni, hasAttacked);

				endGame = true;
			} else {
				endGame = true;
				System.out.println("THE WINNER IS " + ni.getWinner());
			}
		}
	}

	// Use card, then move
	private void phase1(NetworkInterface ni) throws RemoteException {

		if (canUseCards()) {
			ui.chooseCards();
			useCards(ni);
			ni.getCards(playerID);
		}

		String adjacents = new String(ni.getAdjacents());
		String chosenSector = null;
		do {
			ui.askMove(adjacents, player[0]);
			chosenSector = CommonMethods.readLine();
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
	private void phase3(NetworkInterface ni, boolean hasAttacked) {
		if (!hasAttacked) {
			// ni.drawDangerousSectorCard();
		}
		boolean canUseCards = canUseCards();

		if (canUseCards) {
			ui.chooseCards();
			// TODO usecards;
		}
	}

	private void useCards(NetworkInterface ni) throws RemoteException{
		String choice = CommonMethods.readLine();
	
		int[] validChoices = new int[3];
	
		validChoices = CommonMethods.validCard(choice, cards.length);
		for (int i = 0; i < validChoices.length; i++) {
			ni.useCard(cards[i], playerID);
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
		if ("A".equals(nature)) {
			return true;
		}
		// if player is human but has Attack card
		for (String card : cards) {
			if (card != null) {
				return true;
			}
		}
		// if player if human but has Attack card
		return false;
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
			choice = CommonMethods.readLine();
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
			choice = CommonMethods.readLine();
			if ("1".equals(choice) || "2".equals(choice)) {
				return Integer.parseInt(choice);
			}
			System.out.println("Please, choose between 1 or 2.");
		}
	}

}