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
	private int gameID;
	private String nature;
	private String[] cards; // item cards
	private UserInterface ui;
	private String currentSector;
	private boolean hasMoved;
	private boolean hasAttacked;
	private boolean mustDraw; // dangerous sector card
	private boolean hasDrawn; // dangerous sector card
	private boolean canUseCards; // item cards
	
	NetworkInterface ni;
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
			NetworkInterface tmp = NetworkInterfaceFactory.getInterface(chooseNetwork());
			System.out.println();
			Client application = new Client(tmp);
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
		this.ni=ni;
		ui = UserInterfaceFactory.getInterface(chooseUI());

		String[] IDs = (ni.connect()).split("_");
		gameID=Integer.parseInt(IDs[0]);
		playerID=Integer.parseInt(IDs[1]);
		
		ui.connected(gameID, playerID);
		
		/**********************************************************
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		************************************************************/
		
		// Waiting for a game to begin
		while (!ni.isStarted(gameID)) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		// It's: [0] name; [1] role; [2] nature; [3] playerID; [4] maxMoves.
		player = (new String(ni.getPlayer(playerID, gameID))).split("_");
		nature = player[2];

		ui.whoYouAreComplete(player);

		boolean endGame = false;
		while (!endGame) {
			
			while (!ni.isMyTurn(playerID, gameID)){
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
				}
			}
			
			if (!ni.isEnded(gameID)) {
				hasMoved = false;
				hasAttacked = false;
				mustDraw = false;
				hasDrawn = false;

				// print map
				ui.printMap(ni.getMap(playerID, gameID));

				// print your current position
				currentSector = ni.whereYouAre(playerID);
				ui.whereYouAre(currentSector);

				// get cards
				cards = ni.getCards(playerID, gameID).split(";");
				canUseCards = this.canUseCards();
				ui.printCards(canUseCards, cards);

				ui.yourTurn();

				this.play();

				

			} else {
				endGame = true;
				//System.out.println("THE WINNER IS " + ni.getWinner());
			}
		}
	}

	private void play() throws RemoteException {
		String chosenAction = null;
		do {
			chosenAction = ui.chooseAction(hasMoved, canAttack(), hasAttacked, canUseCards,
					mustDraw, hasDrawn);
			switch (chosenAction) {
				case Constants.USE_CARD: { // ITEM CARDS
					this.clientUseCards();
					break;
				}
				case Constants.MOVE: {
					this.clientMove(); // updates mustDraw
					hasMoved = true;
					break;
				}
				case Constants.ATTACK: {
					ni.attack(playerID, gameID);
					hasAttacked = true;
					break;
				}
				case Constants.DRAW_CARD: { // DANGEROUS SECTOR CARDS
					ui.drawDangerousSectorCard(ni.drawCard(gameID, "DS"));
					hasDrawn = true;
					mustDraw = false;
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
	 * @param ni instantiation of the network interface
	 * @return the result of the check
	 * @throws RemoteException
	 */
	private boolean canUseCards() {
		for (String card : cards) {
			if (!("null".equals(card))) {
				return true;
			}
		}
		return false;
	}

	private void clientUseCards() throws RemoteException {
		String cardToUse = ui.selectCard(cards);
		if (!("noCard".equals(cardToUse))) {
			//ni.useCard(cardToUse, playerID);
		}
	}

	/**
	 * Calls the proper methods in UI and NI package to let the player move to a new sector.
	 * 
	 * @param ni instantiation of the network interface
	 * @throws RemoteException
	 */
	private void clientMove() throws RemoteException {
		String adjacentSectors = new String(ni.getAdjacentSectors(playerID, gameID));
		String chosenSector = ui.move(playerID, adjacentSectors);
		ni.move(playerID, gameID, chosenSector);
		currentSector = ni.whereYouAre(playerID); // update the global variable
		ui.whereYouAre(currentSector);
	}

	/**
	 * The method checks whether the player can attack, and returns a boolean.
	 * 
	 * @param ni instantiation of the network interface
	 * @return the result of the check
	 * @throws RemoteException
	 */
	private boolean canAttack() throws RemoteException {
		// if player is alien
		if ("A".equals(nature)) {
			return true;
		}
		// if player is human and has Attack card
		if (canUseCards) {
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