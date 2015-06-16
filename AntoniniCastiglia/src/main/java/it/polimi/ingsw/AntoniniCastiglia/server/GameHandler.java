package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.cards.CardsConstants;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import it.polimi.ingsw.AntoniniCastiglia.maps.EscapeHatch;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;
import java.util.List;
import java.util.Timer;

/**
 * Every instantiation of this class handles a game.
 *
 * @author Laura Castiglia
 *
 */
public class GameHandler implements Runnable, TimerInterface {

	private Table table;
	private Deck dangerousSectorDeck;
	private Deck itemCardDeck;
	private Deck escapeHatchDeck;
	private PlayerList playerList;
	private int numPlayers = 0;
	private int rounds;
	private Timer timer;
	private List<String> winningH;

	private int playerTurn;
	private boolean started = false;
	private boolean suspended = false;
	private boolean oneAlien = false; // at least one alien in game
	private boolean oneHuman = false;
	private boolean aliensWin = false;
	private boolean humanWins = false;
	private boolean ended = false;

	private boolean mustMove = true;
	private boolean hasMoved = false;
	private boolean hasAttacked = false;
	private boolean mustDrawDSCard = false;
	private boolean hasDrawnDSCard = false;
	private boolean mustHandleICard = false;
	private boolean hasHandledICard = false;
	private boolean mustDeclare = false;
	private boolean hasDeclared = false;
	private boolean mustDrawEHCard = false;
	private boolean usedAdrenalineCard = false;
	private boolean usedAttackCard = false;
	private DangerousSectorCard dsc = null;
	private ItemCard ic = null;
	private EscapeHatchCard ehc = null;

	private int playerID;

	@Override
	public void run() {
		while (!started && !suspended) {
			/*
			 * try { this.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
			 */
			CommonMethods.sleep(50);
		}
		if (started) {
			playerTurn = 0;
			rounds = 0;
			this.turn();
		}
		if (suspended) {
			this.endGame();
			return;
		}
		this.endGame();
	}

	/**
	 * Concretely creates a game (that is, the map, the playerList and the decks).
	 */
	void gameTools() { // receives all the data about players, cards, map, etc!
		this.createMap(); // THE MAP MUST BE CREATED *BEFORE* THE PLAYERS!
		this.createPlayers(numPlayers);
		this.createDecks();
		this.instatiateTimer();

	}

	/**
	 * Starts the timer for a turn.
	 */
	private void turn() {
		while (rounds <= ServerConstants.ROUNDS && !ended) {
			timer.schedule(new MyTimer(this), 2 * 60 * 1000);
			while (playerList.get(playerTurn).isActive()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.win();
		}
	}

	/**
	 * Getter for the Item cards of a player.
	 *
	 * @param playerID
	 * @return the cards, in form of a string
	 */
	String getPlayerCards(int playerID) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		return p.getPlayerCards();

	}

	/**
	 * Checks whether is the turn of the calling player.
	 *
	 * @param playerID
	 * @return whether is the turn of the calling player
	 */
	boolean isMyTurn(int playerID) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		boolean flag = true;
		if (p instanceof Human) {
			flag = !((Human) p).isEscaped();
		}
		return playerID == playerTurn && !p.isDead() && !p.isSuspended() && flag;
	}

	/**
	 * Ends the game, returns the ending situation in form of a string.
	 *
	 * @return the ending situation
	 */
	private String endGame() {
		String s;
		this.win();
		if (aliensWin) {
			s = "The game is over: aliens win!";
		}
		if (humanWins && ended) {
			s = this.arrayToString();
		} else {
			s = "The game is over: both Humans and Aliens win!";
		}
		ended = true;
		return s;
	}

	/**
	 * Checks who won the game.
	 */
	private void win() {
		Player p = CommonMethods.toPlayer(playerTurn, playerList);
		this.checkActivePlayers();
		if (!oneHuman || (rounds == ServerConstants.ROUNDS - 1 && oneHuman)) {
			aliensWin = true; // case 1:Aliens win
			ended = true;
		}

		if (p instanceof Human) { // case 2: every human escaped is a winner!
			if (((Human) p).isEscaped()) {
				humanWins = true;
				p.suspend(); // the player end its game
				if (rounds == ServerConstants.ROUNDS - 1) {
					// if a Human escapes, he's a winner, but the game is still on
					ended = true;
				}
			}
		}
		if (rounds == ServerConstants.ROUNDS - 1) {
			ended = true;
			System.out.println("Ended since boh 2");
		}
	}

	/**
	 * Resets some variables, to let another player play his turn.
	 */
	private void resetTurn() {
		mustMove = true;
		hasMoved = false;
		hasAttacked = false;
		mustDrawDSCard = false;
		hasDrawnDSCard = false;
		mustHandleICard = false;
		hasHandledICard = false;
		mustDeclare = false;
		hasDeclared = false;
		mustDrawEHCard = false;
		dsc = null;
		ic = null;
		ehc = null;

	}

	/**
	 * Switches the turn, and checks whether there are active players or not.
	 */
	public synchronized void switchTurn() {
		timer.cancel();
		this.resetTurn();
		if (!this.checkActivePlayers()) {
			ended = true;
			return;
		}
		this.resetItemAdrenaline();
		this.resetItemAttack();
		CommonMethods.toPlayer(playerTurn, playerList).resetJournal();
		playerList.get(playerTurn).setActive(false);
		playerTurn++;
		if (playerTurn > numPlayers - 1) {
			playerTurn = 0;
			rounds++;
		}
		if (playerList.get(playerTurn).isSuspended()) {
			this.switchTurn();
		}
		playerList.get(playerTurn).setActive(true);
		this.notify();
	}

	/**
	 * Checks if the game is still valid, according to the number of players still in. Updates
	 * oneAlien and onePlayer, which state whether there are at least one player of each type.
	 *
	 * @return if there are active players
	 */
	private boolean checkActivePlayers() {
		oneAlien = false;
		oneHuman = false;
		for (int i = 0; i < playerList.size(); i++) {
			if (!playerList.get(i).isSuspended()) {
				if (playerList.get(i) instanceof Alien) {
					oneAlien = true;
				} else {
					oneHuman = true;
				}
			}
		}
		return oneAlien && oneHuman;
	}

	/**
	 * Getter for the playerList
	 *
	 * @return playerList
	 *
	 */
	public PlayerList getPlayerList() {
		return playerList;
	}

	/**
	 * Getter for the table.
	 *
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Getter for the Item card deck.
	 *
	 * @return the Item card deck
	 */
	public Deck getItemCardDeck() {
		return itemCardDeck;
	}

	/**
	 * Getter for the Dangerous Sector card deck.
	 *
	 * @return the Item card deck
	 */
	public Deck getDangerousSectorDeck() {
		return dangerousSectorDeck;
	}

	/**
	 * Getter for the Escape Hatch card deck.
	 *
	 * @return the Escape Hatch card deck
	 */
	public Deck getEscapeHatchDeck() {
		return escapeHatchDeck;
	}

	/**
	 * Getter for the ended variable.
	 *
	 * @return the ended variable
	 */
	public boolean isEnded() {
		return ended;
	}

	@Override
	public synchronized void timeout() {
		playerList.get(playerTurn).suspend();
		this.switchTurn();
	}

	/**
	 * Sets the game to started.
	 */
	public void setStarted() {
		started = true;
	}

	/**
	 *
	 * Setter for the number of players.
	 *
	 * @param n the number of players
	 */
	public void setNumPlayer(int n) {
		numPlayers = n;
	}

	/**
	 * Sets the game to suspended.
	 */
	public void setSuspended() {
		suspended = true;
	}

	/**
	 * Getter for started variable.
	 *
	 * @return whether the game is started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Returns the array of winning humans, in form of a string.
	 *
	 * @return the string of winning players
	 */
	private String arrayToString() {
		String str = "";
		for (String s : winningH) {
			str = str + s + "\t";
		}
		return str;
	}

	/**
	 * Creates the playerList, of number numPlayers.
	 *
	 * @param numPlayers number of players to create
	 */
	private void createPlayers(int numPlayers) {
		playerList = new PlayerList(numPlayers);
	}

	/**
	 * Creates a map for the game.
	 */
	private void createMap() {
		table = new Table();
	}

	/**
	 * Creates the decks for the game.
	 */
	private void createDecks() {
		dangerousSectorDeck = new DangerousSectorDeck();
		itemCardDeck = new ItemCardDeck();
		escapeHatchDeck = new EscapeHatchDeck();
	}

	/**
	 * Instantiates the timer for a turn.
	 */
	private void instatiateTimer() {
		timer = new Timer();

	}

	/**
	 * Moves the player to a selected sector, if it's valid.
	 *
	 * @param playerID
	 * @param sector
	 * @return the result of the move
	 */
	String move(int playerID, String sector) {
		this.playerID = playerID;
		if (mustMove) {
			Player p = CommonMethods.toPlayer(playerID, playerList);
			Sector s = CommonMethods.toSector(sector, table);
			String toReturn = p.move(table, s) + ";";
			mustDrawDSCard = p.getCurrentSector().getMustDrawDSCard();
			mustDrawEHCard = p.getCurrentSector().getMustDrawEHCard();
			if (toReturn.startsWith("OK")) {
				hasMoved = true;
				this.updateAllJournals("Player " + playerID + " moves.");
			}
			return toReturn;
		}
		return "KO";
	}

	/**
	 * Attacks in the current sector of the calling player, if he can.
	 *
	 * @param playerID
	 * @return the result of the attack
	 */
	String attack(int playerID) {
		this.playerID = playerID;

		Player p = CommonMethods.toPlayer(playerID, playerList);
		if (p.getCanAttack() && !hasAttacked && !hasDrawnDSCard) {

			String toReturn = p.attack(playerList);

			if (toReturn.startsWith("OK")) {
				hasAttacked = true;
				this.updateAllJournals("Player " + playerID + " has successfully attacked sector "
						+ p.getCurrentSector() + ".");

			}
			return toReturn;
		}
		return "KO";
	}

	/**
	 * Returns the possible actions for a player.
	 *
	 * @param playerID
	 * @return the possible actions for a player, in form of a string, separated by an underscore
	 */
	public String chooseAction(int playerID) {
		this.playerID = playerID;

		String toReturn = "";

		if (mustMove && !hasMoved) {
			toReturn = toReturn.concat(Constants.MOVE + "_");
		}
		if (!hasAttacked && mustDrawDSCard && !hasDrawnDSCard) {
			toReturn = toReturn.concat(Constants.DRAW_DS_CARD + "_");
		}
		if (playerList.get(playerID).getCanAttack() && !hasAttacked && !hasDrawnDSCard) {
			toReturn = toReturn.concat(Constants.ATTACK + "_");
		}
		if (playerList.get(playerID).canUseCards()) {
			toReturn = toReturn.concat(Constants.USE_CARDS + "_");
		}
		if (mustDrawEHCard) {
			toReturn = toReturn.concat(Constants.DRAW_EH_CARD + "_");
		}
		if (hasMoved && (hasAttacked || (!mustDrawDSCard || hasDrawnDSCard))) {
			toReturn = toReturn.concat(Constants.QUIT);
		}
		return toReturn;

	}

	/**
	 * Draws a dangerous sector card, and an item card if needed.
	 *
	 * @param playerID
	 * @return the card(s), separated by a semicolon
	 */
	String getDangerousSectorCard(int playerID) {
		this.playerID = playerID;

		if (!hasAttacked && mustDrawDSCard && !hasDrawnDSCard) {

			dsc = (DangerousSectorCard) dangerousSectorDeck.drawCard();
			dangerousSectorDeck.discardCard(dsc);
			hasDrawnDSCard = true;
			mustDeclare = true;

			String toReturn = dsc.toString();

			if (dsc.getWithObject()) {
				mustHandleICard = true;
				ic = (ItemCard) itemCardDeck.drawCard();
				toReturn = toReturn.concat(";" + ic.toString());
			}
			this.updateAllJournals("Player " + playerID + " draws a Dangerous Sector card.");

			return toReturn;
		}

		return "KO";

	}

	/**
	 * Adds the item card to player's carnet, if possibile.
	 *
	 * @param playerID
	 * @return the result of the operation
	 */
	public String getItemCard(int playerID) {
		this.playerID = playerID;
		if (mustHandleICard && !hasHandledICard) {
			Player p = CommonMethods.toPlayer(playerID, playerList);
			// NOTE: getDangerousSectorCard() method already drew the card
			if (p.howManyCards() < 3) {
				p.addItemCard(ic);
				hasHandledICard = true;
				this.updateAllJournals("Player " + playerID + " draws an Item card.");
				return "OK" + "_" + ic.toString();
			} else {
				return "KO" + "_" + ic.toString();
			}
		}
		return "KO";
	}

	/**
	 * Handles the case of too many item cards. Receives the index of the card to switch with.
	 *
	 * @param playerID
	 * @param cardIndex
	 * @return the result of the operation
	 */
	public String handleItemCard(int playerID, int cardIndex) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		if (mustHandleICard && !hasHandledICard && p.howManyCards() == 3) {
			if (cardIndex < 0 || cardIndex > 3) {
				return "KO";
			}
			ItemCard card = p.switchCard(ic, cardIndex);
			itemCardDeck.discardCard(card);
			hasHandledICard = true;
			return "OK";

		}
		return "KO";
	}

	/**
	 * Draws an Escape hatch card and lets the player escape (if possible).
	 *
	 * @param playerID
	 * @return the result of the operation
	 */
	public String escape(int playerID) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		Sector s = p.getCurrentSector();
		if (s instanceof EscapeHatch && mustDrawEHCard) {
			ehc = (EscapeHatchCard) escapeHatchDeck.drawCard();
			if (!ehc.isUseable()) {
				((EscapeHatch) s).setUsable(false);
			}
			String useable = "";
			if (!((EscapeHatch) s).isUsable()) {
				useable = "NOT ";
			}
			this.updateAllJournals("Player " + playerID + " reaches escape hatch in "
					+ p.getCurrentSector() + ". It's " + useable + "useable!");

			if (((EscapeHatch) s).isUsable()) {
				((Human) p).setEscaped(true);
				((EscapeHatch) s).setUsable(false);
				return "OK" + "_" + "You escaped! You win!";
			} else {
				((EscapeHatch) s).setUsable(false);
				return "KO" + "_" + "The escape hatch is not useable! You must find another one.";
			}
		}
		return "KO";
	}

	/**
	 * Lets the player declare noise or silence, in the sector.
	 *
	 * @param playerID
	 * @param sector
	 * @return the result of the operation
	 */
	public String declareNoise(int playerID, String sector) {
		this.playerID = playerID;

		if (mustDeclare) {
			Player p = CommonMethods.toPlayer(playerID, playerList);
			Sector s = CommonMethods.toSector(sector, table);
			String toReturn = "";
			if (CardsConstants.NOISE.equals(dsc.toString().split("_")[1])) {
				if (dsc.getYourSector()) {
					if (s.equals(p.getCurrentSector())) {
						toReturn = "OK"; // Noise in your sector
					} else {
						return "KO";
					}
				} else {
					toReturn = "OK"; // Noise in any sector
				}
				this.updateAllJournals("Player " + playerID + " declares: \"Noise in sector " + s
						+ "\".");
			} else {
				toReturn = "OK"; // Silence in any sector
				this.updateAllJournals("Player " + playerID
						+ " declares: \"Silence in all sectors\".");

			}
			mustDeclare = false;
			hasDeclared = true;
			return toReturn;
		}
		return "KO";

	}

	/**
	 * Updates the journals of all players with string s.
	 *
	 * @param s the string to add
	 */
	private void updateAllJournals(String s) {
		for (int i = 0; i < playerList.size(); i++) {
			CommonMethods.toPlayer(i, playerList).updateJournal("Round " + rounds + " - " + s);
		}
	}

	/**
	 * Returns the adjacent sectors to the calling player.
	 *
	 * @param playerID
	 * @return the adjacent sectors in form of a string
	 */
	public String getAdjacentSectors(int playerID) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		List<Sector> adjacentSectors = table.adjacent(p.getCurrentSector(), p.getMoves());
		String toReturn = "";
		for (Sector sector : adjacentSectors) {
			toReturn = toReturn + sector.toString() + ";";
		}
		return toReturn;
	}

	/**
	 * Performs the adrenaline card action.
	 *
	 * @return the result of the operation
	 */
	public String itemAdrenalineAction() {
		this.updateAllJournals("Player " + playerID + " uses: Adrenaline card.");

		Player p = CommonMethods.toPlayer(playerID, playerList);
		p.setMoves(2);
		usedAdrenalineCard = true;

		return "OK";
	}

	/**
	 * Resets, if needed, a variable set by the itemAdrenalineAction().
	 */
	private void resetItemAdrenaline() {
		if (usedAdrenalineCard) {
			CommonMethods.toPlayer(playerID, playerList).setMoves(1);
			usedAdrenalineCard = false;
		}
	}

	/**
	 * Performs the attack card action.
	 *
	 * @return the result of the operation
	 */
	public String itemAttackAction() {
		this.updateAllJournals("Player " + playerID + " uses: Attack card.");
		Player p = CommonMethods.toPlayer(playerID, playerList);
		p.setCanAttack(true);
		usedAttackCard = true;
		return "OK";
	}

	/**
	 * Resets, if needed, a variable set by the itemAttackAction().
	 */
	private void resetItemAttack() {
		if (usedAttackCard) {
			CommonMethods.toPlayer(playerID, playerList).setCanAttack(false);
			usedAttackCard = false;
		}
	}

	/**
	 * Performs the defense card action.
	 *
	 * @return the result of the operation
	 */
	public String itemDefenseAction() {
		Player p = CommonMethods.toPlayer(playerID, playerList);
		((Human) p).setShield(true);
		return "OK";

	}

	/**
	 * Performs the sedatives card action.
	 *
	 * @return the result of the operation
	 */
	public String itemSedativesAction() {
		this.updateAllJournals("Player " + playerID + " uses: Sedatives card.");
		hasDrawnDSCard = true;
		return "OK";

	}

	/**
	 * Performs the spotlight card action.
	 *
	 * @return the result of the operation
	 */
	public String itemSpotlightAction() {
		this.updateAllJournals("Player " + playerID + " uses: Spotlight card.");
		Player p = CommonMethods.toPlayer(playerID, playerList);
		List<Sector> adjacentSector = table.adjacent(p.getCurrentSector(), 1);
		String toReturn = "";
		for (int i = 0; i < playerList.size(); i++) {
			Player p1 = playerList.get(i);
			Sector s1 = p1.getCurrentSector();
			if ((s1).equals(p.getCurrentSector()) || adjacentSector.contains(s1)) {
				String s = "Player " + playerID + " declares: \"I'm in sector " + s1 + "\".";
				this.updateAllJournals(s);
				toReturn = toReturn.concat(s);
			}
		}
		return "OK" + "_" + toReturn;
	}

	/**
	 * Performs the teleport card action.
	 *
	 * @return the result of the operation
	 */
	public String itemTeleportAction() {
		this.updateAllJournals("Player " + playerID + " uses: Teleport card.");
		Player p = CommonMethods.toPlayer(playerID, playerList);
		p.setCurrentSector(p.getMyBase());
		return "OK";
	}

	/**
	 * Lets the player use a card in position posCard.
	 *
	 * @param playerID
	 * @param posCard
	 * @return the result of the operation
	 */
	public String useCard(int playerID, int posCard) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		ItemCard c = p.removeCard(posCard - 1);

		itemCardDeck.discardCard(c);
		return c.action(this);

	}

	/**
	 * Returns the journal of a player.
	 *
	 * @param playerID
	 * @return the journal of a player, in form of a string
	 */
	public String getJournal(int playerID) {
		this.playerID = playerID;
		return CommonMethods.toPlayer(playerID, playerList).getJournal();
	}

}
