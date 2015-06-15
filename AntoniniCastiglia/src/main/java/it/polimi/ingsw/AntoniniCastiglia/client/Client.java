package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
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
				ui.whereYouAre(currentSector, sectorType);

				// get cards
				cards = ni.getCards(playerID, gameID).split(";");
				canUseCards = this.canUseCards();
				ui.printItemCards(canUseCards, cards);

				ui.yourTurn();

				this.play();
				
			} else {
				if (ni.isDead(playerID, gameID)) {
					System.out.println("You are now dead!");
				} else {
					System.out.println("The game is ended!");
				}
				endGame = true;
			}
		}
	}

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
				
				case Constants.QUIT: { 
					ni.endTurn(playerID, gameID);				
					break;
				}
				
				default: {
					break;
				}
			}
		} while (!(ClientConstants.QUIT.equals(chosenAction)));
	}

	
	
	
	
	
	

	private void clientDrawDSCard() throws RemoteException   {
		String args = ni.drawCard(gameID, playerID, "DS");
		String[] dsCard = (args.split(";")[0]).split("_");
		ui.drawDangerousSectorCard(args.split(";")[0]);
		boolean noise = CardsConstants.NOISE.equals(dsCard[1]);
		boolean yourSector = Boolean.parseBoolean(dsCard[2]);
		boolean withObject = Boolean.parseBoolean(dsCard[3]);
		declareNoise(noise, yourSector, currentSector);
		if (withObject) {
			handleICard(args);
		}
	}
	
	private void declareNoise(boolean noise, boolean yourSector, String currentSector) throws RemoteException{
		String declareResult;
		do{
			String sector = ui.declareNoise(noise, yourSector, currentSector);
			declareResult=ni.declareNoise(gameID, playerID, sector);
		} while (declareResult.startsWith("KO"));
	}
	
	
	
	
	private void handleICard(String args) {
		
		String[] iCard = (args.split(";")[1]).split("_");
		System.out.println("I drew an Item card!");
		//ni.handleICard();
		// TODO Auto-generated method stub
		return;
	}
	
	
	
	

	

	
	
	
	
	
	
	
	@Deprecated
	private void clientUseCards() {
		int cardToUse = ui.selectItemCard(cards);
		if (!("noCard".equals(cardToUse))) {
			// ni.useCard(cardToUse, playerID);
		}
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
	
	
	
	private void clientAttack() throws RemoteException {
		String attackResult = "";
		attackResult = ni.attack(playerID, gameID);
		ui.attackResult(attackResult);
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