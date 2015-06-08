package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterfaceFactory;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterfaceFactory;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

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
	private boolean hasAttacked;

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

		playerID = ni.connect();
		ui.connected(playerID);

		// Waiting for a game to begin
		while (!ni.isStarted()) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		// It's: [0] name; [1] role; [2] nature; [3] playerID; [4] maxMoves.
		player = (new String(ni.getPlayer(playerID))).split("_");
		nature = player[2];

		ui.whoYouAreComplete(player);

		boolean endGame = false;
		while (!endGame) {
			if (!ni.isEnded()) {
				// print map
				ui.printMap(ni.getMap(playerID));

				// get cards
				cards = ni.getCards(playerID).split(";");
				ui.printCards(canUseCards(ni), cards);

				ui.yourTurn();

				phase1(ni);
				phase2(ni);
				phase3(ni);

				endGame = true; // Remove me as soon as isEnded() is decently implemented

			} else {
				endGame = true;
				System.out.println("THE WINNER IS " + ni.getWinner());
			}
		}
	}

	// Use card, then move
	private void phase1(NetworkInterface ni) throws RemoteException {
		useCards(ni);

		String adjacentSectors = new String(ni.getAdjacentSectors(playerID));
		String chosenSector = null;
		do {
			ui.askMove(adjacentSectors);
			chosenSector = CommonMethods.readLine();
		} while (!CommonMethods.validSector(adjacentSectors, chosenSector));
		String toPrint = ni.move(playerID, chosenSector);
		ui.moveResult(toPrint);
	}

	// Use card (attack) if HUMAN, then attack
	private void phase2(NetworkInterface ni) throws RemoteException {
		hasAttacked = false;
		if (canAttack(ni)) {
			ui.youCanAttack(nature);
			String chosenAction = CommonMethods.readLine();
			if (chosenAction.equals("A")) {
				ni.attack(playerID);
				hasAttacked = true;
			}
		}
	}

	// Use card, then end turn
	private void phase3(NetworkInterface ni) throws RemoteException {
		if (!hasAttacked) {
			ui.drawDangerousSectorCard(ni.drawDangerousSectorCard());
		}

		if (canUseCards(ni)) {
			ui.chooseCards();
			useCards(ni);
		}

		// TODO endTurn
	}

	private void useCards(NetworkInterface ni) throws RemoteException {
		if (canUseCards(ni)) {
			ui.chooseCards();
			String choice = CommonMethods.readLine();
			int[] validChoices = new int[3];

			// TODO now assuming that the player is neither evil nor dumb.
			// if he chooses 0, he won't write anything else
			validChoices = CommonMethods.validCard(choice, cards.length);
			if (validChoices[0] == 0) {
				return;
			}
			for (int i = 0; i < validChoices.length; i++) {
				ni.useCard(cards[i], playerID);
			}
		}
	}

	private boolean canUseCards(NetworkInterface ni) throws RemoteException {
		cards = ni.getCards(playerID).split(";"); // Update the global variable cards
		for (String card : cards) {
			if (!("null".equals(card))) {
				return true;
			}
		}
		return false;
	}

	private boolean canAttack(NetworkInterface ni) throws RemoteException {
		// if player is alien
		if ("A".equals(nature)) {
			return true;
		}
		// if player is human and has Attack card
		if (canUseCards(ni)) {
			for (String card : cards) {
				// TODO there is a constant for the name of the card!
				if ("Attack".equals((card.split("_"))[1])) {
					return true;
				}
			}
		}
		// if player if human but has no Attack card
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
			System.out.println("Choose your preferred network interface. Here's what we offer:");
			System.out.println("1 - RMI");
			System.out.println("2 - Socket (not implemented yet)");
			choice = CommonMethods.readLine();
			if ("1".equals(choice) || "2".equals(choice)) {
				return Integer.parseInt(choice);
			}
			System.out.println("Please, choose between 1 or 2. Not difficult.");
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
			System.out
					.println("Now choose your preferred user interface. In know you're interested!");
			System.out.println("1 - CLI");
			System.out.println("2 - GUI (not implemented yet)");
			choice = CommonMethods.readLine();
			if ("1".equals(choice) || "2".equals(choice)) {
				return Integer.parseInt(choice);
			}
			System.out.println("Please, choose between 1 or 2. It's simple, actually.");
		}
	}

}