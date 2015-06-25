package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.cards.CardsConstants;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterfaceFactory;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterfaceFactory;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

/**
 * Every instance of this class is the client for a player.
 *
 * @author Paolo Antonini
 *
 */
public class Client {

	private NetworkInterface ni;
	private UserInterface ui;

	private int playerID;
	private int gameID;

	private String currentSector;
	private String sectorType;

	private String[] cards; // item cards

	private boolean canUseCards; // item cards

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
			NetworkInterface tmpNi = NetworkInterfaceFactory.getInterface(chooseNetwork());
			System.out.println();
			Client application = new Client(tmpNi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Private constructor for the class. Receives the network interface, which was created in the
	 * main, as a parameter. The whole game is conducted here.
	 *
	 * @param ni instantiation of the network interface
	 * @throws RemoteException
	 */
	private Client(NetworkInterface ni) throws RemoteException {
		this.ni = ni;
		ui = UserInterfaceFactory.getInterface(chooseUI());

		String[] IDs = (ni.connect()).split("_");
		gameID = Integer.parseInt(IDs[0]);
		playerID = Integer.parseInt(IDs[1]);

		ui.connected(gameID, playerID);

		// Waiting for a game to begin
		while (!ni.isStarted(gameID)) {
			CommonMethods.sleep(500);
		}

		String[] player = (ni.getPlayer(playerID, gameID)).split("_");
		currentSector = player[4];
		sectorType = "Base";

		ui.whoYouAreComplete(player);

		// print map
		ui.printMap(ni.getMap(playerID, gameID));

		// print your current position
		ui.whereYouAre(currentSector, sectorType);


		boolean endGame = false;
		while (!endGame) {

			while (!ni.isMyTurn(playerID, gameID)) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
				}
			}

			if (!ni.isEnded(gameID) && !ni.isDead(playerID, gameID)) {

				// print map
				ui.printMap(ni.getMap(playerID, gameID));

				// print your current position
				ui.whereYouAre(currentSector, sectorType);

				// get cards
				cards = ni.getCards(playerID, gameID).split(";");
				canUseCards = this.canUseCards();
				ui.printItemCards(canUseCards, cards);

				// print a record of what happened
				ui.printJournal(ni.getJournal(playerID, gameID));

				this.play();

			} else {

				if (ni.isDead(playerID, gameID)) {
					System.out.println("You are now dead!");
					ni.endTurn(playerID, gameID);
				} else {
					System.out.println("The game is ended!");
				}
				endGame = true;
			}
		}
	}

	/**
	 * Shows the possible actions to the player (which are provided by the server) and calls the
	 * proper methods.
	 *
	 * @throws RemoteException
	 */
	private void play() throws RemoteException {
		String chosenAction = null;
		String possibleActions;

		do {
			possibleActions = ni.possibleActions(playerID, gameID);
			chosenAction = ui.chooseAction(possibleActions);

			switch (chosenAction) {

				case Constants.MOVE: {
					this.clientMove();
					break;
				}

				case Constants.ATTACK: {
					this.clientAttack();
					break;
				}

				case Constants.DRAW_DS_CARD: {
					this.clientDrawDSCard();
					break;
				}

				case Constants.USE_CARDS: { // ITEM CARDS
					this.clientUseCards();
					break;
				}

				case Constants.DRAW_EH_CARD: {
					this.escape();
					break;
				}

				case Constants.QUIT: {
					ni.endTurn(playerID, gameID);
					break;
				}

				default: {
					break;
				}
			}
		} while (!(Constants.QUIT.equals(chosenAction)));
	}

	/**
	 * Calls the proper methods in UI and NI package to let the player move to a new sector.
	 *
	 * @throws RemoteException
	 */
	private void clientMove() throws RemoteException {
		String args;
		do {
			String adjacentSectors = ni.getAdjacentSectors(playerID, gameID);
			String chosenSector = ui.move(playerID, adjacentSectors);
			args = ni.move(playerID, gameID, chosenSector);
		} while (args.startsWith("KO"));

		String[] moveResult = (args.split(";")[0]).split("_");
		currentSector = moveResult[1];
		sectorType = moveResult[2];

		ui.whereYouAre(currentSector, sectorType);

	}

	/**
	 * Calls the proper methods in UI and NI package to let the player attack the sector where he is
	 * now.
	 *
	 * @throws RemoteException
	 */
	private void clientAttack() throws RemoteException {
		String attackResult = "";
		attackResult = ni.attack(playerID, gameID);
		ui.attackResult(attackResult);
	}

	/**
	 * Calls the proper methods in UI and NI package to let the player draw a Dangerous Sector card,
	 * and handle the Item card he may be requested to draw.
	 *
	 * @throws RemoteException
	 */
	private void clientDrawDSCard() throws RemoteException {
		String args = ni.drawCard(gameID, playerID, "DS");
		String[] dsCard = (args.split(";")[0]).split("_");
		ui.drawDangerousSectorCard(args.split(";")[0]);
		boolean noise = CardsConstants.NOISE.equals(dsCard[1]);
		boolean yourSector = Boolean.parseBoolean(dsCard[2]);
		boolean withObject = Boolean.parseBoolean(dsCard[3]);
		this.declareNoise(noise, yourSector, currentSector);
		if (withObject) {
			this.handleICard(args);
		}
	}

	/**
	 * Calls the proper methods in UI and NI package to let the player declare a noise or silence.
	 *
	 * @throws RemoteException
	 */
	private void declareNoise(boolean noise, boolean yourSector, String currentSector)
			throws RemoteException {
		String declareResult;
		do {
			String sector = ui.declareNoise(noise, yourSector, currentSector);
			declareResult = ni.declareNoise(gameID, playerID, sector);
		} while (declareResult.startsWith("KO"));
	}

	/**
	 * Calls the proper methods in UI and NI package to let the player handle a new Item card (that
	 * is, to add it to his carnet, or switch with another one, if his carnet is full).
	 *
	 * @throws RemoteException
	 */
	private void handleICard(String args) throws RemoteException {
		String itemCard = args.split(";")[1];
		String getResult = ni.getItemCard(playerID, gameID);
		if (getResult.startsWith("KO")) {
			String handleResult;
			do {
				int cardIndex = ui.handleItemCard(itemCard, cards);
				handleResult = ni.handleItemCard(playerID, gameID, cardIndex);
			} while (!(handleResult.startsWith("OK")));
		}
		ni.getCards(playerID, gameID);
	}

	/**
	 * Checks whether the player can use cards, and returns a boolean.
	 *
	 * @return the result of the check
	 */
	private boolean canUseCards() {
		for (String card : cards) {
			if (!("null".equals(card))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Calls the proper methods in UI and NI package to let the player use an Item card.
	 *
	 * @throws RemoteException
	 */
	private void clientUseCards() throws RemoteException {
		cards = ni.getCards(playerID, gameID).split(";");
		int cardToUse = ui.selectItemCard(cards);
		String useResult = "";
		do {
			if (cardToUse != 0) {
				useResult = ni.useCard(playerID, gameID, cardToUse);
			}
		} while ("KO".equals(useResult));

		ui.useResult(useResult);

	}

	/**
	 * Calls the proper methods in UI and NI package to let the player escape from the ship.
	 *
	 * @throws RemoteException
	 */
	private void escape() throws RemoteException {
		String escapeResult = ni.escape(playerID, gameID);
		ui.escapeResult(escapeResult);
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
			System.out.println("Now choose your user interface. In know you're interested!");
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