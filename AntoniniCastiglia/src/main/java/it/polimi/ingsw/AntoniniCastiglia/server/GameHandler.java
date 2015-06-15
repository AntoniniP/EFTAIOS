package it.polimi.ingsw.AntoniniCastiglia.server;

import java.util.List;
import java.util.Timer;
import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.CardsConstants;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorNoise;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemSpotlight;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;

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
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (started) {
			// playerTurn=(int)(Math.random()*(numPlayers+1));
			playerTurn = 0;
			rounds = 0;
			turn();
		}
		if (suspended) {
			endGame();
			return;
		}
		endGame();
	}

	public void gameTools() { // receives all the data about players, cards, map, etc!
		createMap(); // THE MAP MUST BE CREATED *BEFORE* THE PLAYERS!
		createPlayers(numPlayers);
		createDecks();
		instatiateTimer();

	}

	public void turn() {
		while (rounds <= ServerConstants.ROUNDS && !ended) {
			timer.schedule(new MyTimer(this), 2 * 60 * 1000);
			while (playerList.get(playerTurn).isActive()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			win();// check if someone has won

		}

	}

	public String getPlayerCards(int playerID) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		return p.getPlayerCards();

	}

	public boolean isMyTurn(int playerID) {
		this.playerID = playerID;
		return playerID == playerTurn;
	}

	public String endGame() { // client will receive the output according to the ending way
		String s;
		win();
		if (aliensWin)
			s = "The game is over: aliens win!";
		if (humanWins && ended) {
			s = arrayToString();
		} else {
			s = "The game is over: both Humans and Aliens win!";
		}
		this.ended = true;
		return s;
	}

	public void win() { // method to check if someone has won the game
		Player p = CommonMethods.toPlayer(playerTurn, playerList);

		if (!oneHuman || (rounds == ServerConstants.ROUNDS - 1 && oneHuman)) {
			aliensWin = true; // case 1:Aliens win
			ended = true;
		}

		if (p instanceof Human) { // case 2: every human escaped is a winner!
			if (((Human) p).isEscaped()) {
				humanWins = true;
				p.suspend(); // the player end its game
				escapedNotify();
				if (rounds == ServerConstants.ROUNDS - 1) {
					// if a Human escapes, he's a winner, but the game is still on
					ended = true;
				}
			}
		}
		if (rounds == ServerConstants.ROUNDS - 1) {
			ended = true;
		}
	}

	public void escapedNotify() { // live notification of winning by escape
		String s = "player" + playerList.get(playerTurn) + "has escaped: he wins!";
		winningH.add(s);
		System.out.println(s);
	}

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
		resetItemAdrenaline();
		resetItemAttack();
	}

	public synchronized void switchTurn() {
		timer.cancel();

		resetTurn();
		

		if (!checkActivePlayers()) {
			ended = true;
			return;
		}
		playerList.get(playerTurn).setActive(false);
		CommonMethods.toPlayer(playerTurn, playerList).resetJournal();
		playerTurn++;
		if (playerTurn > numPlayers - 1) {
			playerTurn = 0;
			rounds++;
		}
		if (playerList.get(playerTurn).isSuspended()) {
			switchTurn();
		}
		playerList.get(playerTurn).setActive(true);
		this.notify();
	}

	// check if the game is still valid, according to the number of players still in
	private boolean checkActivePlayers() {
		for (int i = 0; i < playerList.size(); i++) {
			if (!playerList.get(i).isSuspended()) {
				if (playerList.get(i) instanceof Alien)
					oneAlien = true;
				else
					oneHuman = humanLeft();
			}
		}
		return oneAlien && oneHuman;
	}

	private boolean humanLeft() { // checks if there are human left
		for (int i = 0; i < playerList.size() && !oneHuman; i++) {
			if (!playerList.get(i).isSuspended())
				if (playerList.get(i) instanceof Human)
					oneHuman = true;
		}
		return oneHuman;
	}

	public PlayerList getPlayerList() {
		return playerList;
	}

	public Table getTable() {
		return table;
	}

	public Deck getItemCardDeck() {
		return itemCardDeck;
	}

	public Deck getDangerousSectorDeck() {
		return dangerousSectorDeck;
	}

	public Deck getEscapeHatchDeck() {
		return escapeHatchDeck;
	}

	public boolean isEnded() {
		return ended;
	}

	@Override
	public synchronized void timeout() {
		playerList.get(playerTurn).suspend();
		switchTurn();
	}

	public void setStarted() {
		started = true;
	}

	public void setNumPlayer(int n) {
		numPlayers = n;
	}

	public void setSuspended() {
		suspended = true;
	}

	public boolean isStarted() {
		return this.started;
	}

	private String arrayToString() {
		// method useful for converting winninH arrayList into String (see endGame)
		String str = "";
		for (String s : winningH)
			str = str + s + "\t";
		return str;
	}

	private void createPlayers(int numPlayers) {
		playerList = new PlayerList(numPlayers);
	}

	private void createMap() {
		table = new Table();
	}

	private void createDecks() {
		dangerousSectorDeck = new DangerousSectorDeck();
		itemCardDeck = new ItemCardDeck();
		escapeHatchDeck = new EscapeHatchDeck();
	}

	private void instatiateTimer() {
		timer = new Timer();

	}

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
				this.updateAllJournals("Player "+playerID+ " moves.");

			}
			return toReturn;
		}
		return "KO";
	}

	String attack(int playerID) {
		this.playerID = playerID;

		Player p = CommonMethods.toPlayer(playerID, playerList);
		if (p.getCanAttack() && !hasAttacked && !hasDrawnDSCard) {

			String toReturn = p.attack(playerList);

			if (toReturn.startsWith("OK")) {
				hasAttacked = true;
				this.updateAllJournals("Player "+playerID+ " has successfully attacked sector " + p.getCurrentSector()+".");

			}
			return toReturn;
		}
		return "KO";
	}

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

		/************************************************************************/

		if (mustDrawEHCard) {
			toReturn = toReturn.concat(Constants.DRAW_EH_CARD + "_");
		}
		if (hasMoved && (hasAttacked || (!mustDrawDSCard || hasDrawnDSCard))) {
			toReturn = toReturn.concat(Constants.QUIT);
		}
		return toReturn;

	}

	String getDangerousSectorCard(int playerID) {
		this.playerID = playerID;

		if (!hasAttacked && mustDrawDSCard && !hasDrawnDSCard) {

			dsc = (DangerousSectorCard) dangerousSectorDeck.drawCard();
			dangerousSectorDeck.discardCard(dsc);
			this.hasDrawnDSCard = true;
			this.mustDeclare = true;

			String toReturn = dsc.toString();

			if (dsc.getWithObject()) {
				this.mustHandleICard = true;
				ic = (ItemCard) itemCardDeck.drawCard();
				toReturn = toReturn.concat(";" + ic.toString());
			}
			this.updateAllJournals("Player "+playerID+ " draws a Dangerous Sector card.");

			return toReturn;
		}

		return "KO";

	}

	public String getItemCard(int playerID) {
		this.playerID = playerID;

		if (this.mustHandleICard && !this.hasHandledICard) {
			Player p = CommonMethods.toPlayer(playerID, playerList);
			// NOTE: getDangerousSectorCard() method already drew the card
			if (p.howManyCards() < 3) {
				p.addItemCard(ic);
				hasHandledICard = true;
				this.updateAllJournals("Player "+playerID+ " draws an item card.");

				return "OK" + "_" + ic.toString();
			} else {
				return "KO" + "_" + ic.toString();
			}
		}
		return "KO";
	}

	public String handleItemCard(int playerID, int cardIndex) {
		this.playerID = playerID;

		Player p = CommonMethods.toPlayer(playerID, playerList);

		if (this.mustHandleICard && !this.hasHandledICard && p.howManyCards() == 3) {
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

	public String getEscapeHatchCard(int playerID) {
		this.playerID = playerID;
		this.updateAllJournals("Player "+playerID+ " draws: Escape Hatch card.");

		// TODO Auto-generated method stub
		return null;
	}

	public String declareNoise(int playerID, String sector) {
		this.playerID = playerID;

		if (this.mustDeclare) {
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
					this.updateAllJournals("Player "+playerID+ " declares: \"Noise in sector "+s+"\".");
				} else {
					toReturn = "OK"; // Noise in any sector
				}
			} else {
				toReturn = "OK"; // Silence in any sector
				this.updateAllJournals("Player "+playerID+ " declares: \"Silence in all sectors\".");

			}
			mustDeclare = false;
			hasDeclared = true;
			return toReturn;
		}
		return "KO";

	}
	
	private void updateAllJournals(String s){
		for (int i = 0; i< playerList.size(); i++){
			CommonMethods.toPlayer(i, playerList).updateJournal("Round "+ this.rounds+ " - " +s);
		}
	}

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

	public void itemAdrenalineAction() {
		this.updateAllJournals("Player "+playerID+ " uses: Adrenaline card.");

		Player p = CommonMethods.toPlayer(playerID, playerList);
		p.setMoves(2);
		this.usedAdrenalineCard = true;
	}

	private void resetItemAdrenaline() {
		if (usedAdrenalineCard) {
			CommonMethods.toPlayer(playerID, playerList).setMoves(1);
			usedAdrenalineCard = false;
		}
	}

	public void itemAttackAction() {
		this.updateAllJournals("Player "+playerID+ " uses: Attack card.");
		Player p = CommonMethods.toPlayer(playerID, playerList);
		p.setCanAttack(true);
		this.usedAttackCard = true;
	}

	private void resetItemAttack() {
		if (usedAttackCard) {
			CommonMethods.toPlayer(playerID, playerList).setCanAttack(false);
			usedAttackCard = false;
		}
	}

	public void itemDefenseAction() {
		Player p = CommonMethods.toPlayer(playerID, playerList);
		((Human) p).setShield(true);
	}

	public void itemSedativesAction() {
		this.updateAllJournals("Player "+playerID+ " uses: Sedatives card.");
		this.hasDrawnDSCard = true;
	}

	public void itemSpotlightAction() {
		this.updateAllJournals("Player "+playerID+ " uses: Spotlight card.");

		// TODO not so easy!
		Player p = CommonMethods.toPlayer(playerID, playerList);
		List<Sector> adjacentSector = table.adjacent(p.getCurrentSector(), 1);
		for (int i = 0; i < playerList.size(); i++) {
			Player p1 = playerList.get(i);
			Sector s1 = p1.getCurrentSector();
			if ((s1).equals(p.getCurrentSector()) || adjacentSector.contains(s1)) {
				this.updateAllJournals("Player "+playerID+ " declares: \"I'm in sector "+s1+"\".");
			}
		}
	}

	public void itemTeleportAction() {
		this.updateAllJournals("Player "+playerID+ " uses: Teleport card.");
		Player p = CommonMethods.toPlayer(playerID, playerList);
		p.setCurrentSector(p.getMyBase());

	}

	public String useCard(int playerID, int posCard) {
		this.playerID = playerID;
		Player p = CommonMethods.toPlayer(playerID, playerList);
		ItemCard c = p.removeCard(posCard-1);
		
		itemCardDeck.discardCard(c);
		c.action(this);
		return null;

	}

	public String getJournal(int playerID) {
		this.playerID=playerID;
		return  CommonMethods.toPlayer(playerID, playerList).getJournal();
	}
}
