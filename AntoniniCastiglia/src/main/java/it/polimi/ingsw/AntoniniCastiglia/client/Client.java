package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterfaceFactory;
import it.polimi.ingsw.AntoniniCastiglia.cards.CardsConstants;
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

	private NetworkInterface ni;
	private UserInterface ui;

	private int playerID;
	private int gameID;

	private String playerName;
	private String playerRole;
	private String playerNature;
	private int playerMaxMoves;
	private String currentSector;
	private boolean canAttack;
	private String sectorType;

	private String[] cards; // item cards

	private boolean hasMoved;
	private boolean hasAttacked;
	private boolean mustDrawDSCard; // dangerous sector card
	private boolean hasDrawnDSCard; // dangerous sector card
	private boolean mustDrawEHCard;
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
	 * @throws IOException
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
			CommonMethods.doMagic(500);
		}

		String[] player = (ni.getPlayer(playerID, gameID)).split("_");
		playerName = player[0];
		playerRole = player[1];
		playerNature = player[2];
		playerMaxMoves = Integer.parseInt(player[3]);
		currentSector = player[4];
		sectorType = "Base";

		ui.whoYouAreComplete(player);

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
				ui.whereYouAre(currentSector, sectorType, mustDrawDSCard, mustDrawEHCard);

				// get cards
				cards = ni.getCards(playerID, gameID).split(";");
				canUseCards = this.canUseCards();
				ui.printCards(canUseCards, cards);

				ui.yourTurn();

				this.play();

				ni.endTurn(playerID, gameID);
				
				
				System.out.println("TURN ENDED CORRECTLY");
				

			} else {
				if (ni.isDead(playerID, gameID)) {
					System.out.println("You are now dead!");
				}

				endGame = true;

				// System.out.println("THE WINNER IS " + ni.getWinner());
			}
		}
	}

	private void play() throws RemoteException {
		String chosenAction = null;
		hasMoved = false;
		hasAttacked = false;
		mustDrawDSCard = false;
		hasDrawnDSCard = false;
		mustDrawEHCard = false;
		canAttack = ni.canAttack(gameID, playerID);
		do {
			chosenAction = ui.chooseAction
					(hasMoved, canAttack, hasAttacked, canUseCards, mustDrawDSCard, hasDrawnDSCard);

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
				
				
				
				
				
				
				
				
				case Constants.USE_CARD: { // ITEM CARDS
					this.clientUseCards();
					break;
				}
				
				
				
				case Constants.QUIT: {
					// TODO endTurn()
					break;
				}
				default: {
					// Will never execute this
					break;
				}
			}
		} while (!("Q".equals(chosenAction)));
	}

	/**
	 * The method checks whether the player can use cards, and returns a boolean.
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
	 * Calls the proper methods in UI and NI package to let the player move to a new sector.
	 * 
	 * @throws RemoteException
	 */
	private void clientMove() throws RemoteException {
		String[] moveResult;
		do {
	
			String adjacentSectors = ni.getAdjacentSectors(playerID, gameID);
			String chosenSector = ui.move(playerID, adjacentSectors);
			moveResult = (ni.move(playerID, gameID, chosenSector)).split("_");
	
		} while ("KO".equals(moveResult[0]));
	
		currentSector = moveResult[1];
		sectorType = moveResult[2];
		mustDrawDSCard = Boolean.parseBoolean(moveResult[3]);
		mustDrawEHCard = Boolean.parseBoolean(moveResult[4]);
	
		ui.whereYouAre(currentSector, sectorType, mustDrawDSCard, mustDrawEHCard);
	
		hasMoved = true;
	
	}

	private void clientDrawDSCard() throws RemoteException {
		String[] dsCard = (ni.drawCard(gameID, "DS")).split("_");

		String type = dsCard[1];
		String toUI = new String (type);
		if (CardsConstants.NOISE.equals(type)) {
			boolean yourSector = Boolean.parseBoolean(dsCard[2]);
			boolean withObject = Boolean.parseBoolean(dsCard[3]);

		}

		String where = ui.drawDangerousSectorCard(toUI);
		boolean validNoise;
		/*do{
			validNoise= ni.noise(where);
		} while (!validNoise);*/
		hasDrawnDSCard = true;
		mustDrawDSCard = false;

	}
	
	private void clientAttack() throws RemoteException {
		String attackResult = null;
		if (canAttack) {
			attackResult = ni.attack(playerID, gameID);
		}
		if ("OK".equals((attackResult.split("_"))[0])){
			hasAttacked=true;
		}
		ui.attackResult(attackResult);
	}

	private void clientUseCards() {
		String cardToUse = ui.selectCard(cards);
		if (!("noCard".equals(cardToUse))) {
			// ni.useCard(cardToUse, playerID);
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