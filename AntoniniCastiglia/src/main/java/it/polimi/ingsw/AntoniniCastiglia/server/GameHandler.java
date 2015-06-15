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
	private boolean hasHandledICard =false;
	private boolean mustDeclare = false;
	private boolean hasDeclared=false;
	private boolean mustDrawEHCard = false;
	private DangerousSectorCard dsc = null;
	private ItemCard ic =null;
	private EscapeHatchCard ehc = null;

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
		if(suspended) {
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
		Player p = CommonMethods.toPlayer(playerID, playerList);
		return p.getPlayerCards();

	}

	public boolean isMyTurn(int playerID) {
		return playerID == playerTurn;
	}

	public String endGame() { // client will receive the output according to the ending way
		String s;
		win();
		if (aliensWin)
			s = "The game is over: aliens win!";
		if (humanWins && ended) {
			s = arrayToString();
		} else
			s = "The game is over: both Humans and Aliens win!";
		this.ended = true;
		System.out.println("Ended game in endGame() method");

		return s;
	}

	public void win() { // method to check if someone has won the game
		Player p = CommonMethods.toPlayer(playerTurn, playerList);

		if (!oneHuman || (rounds == ServerConstants.ROUNDS - 1 && oneHuman)) {
			aliensWin = true; // case 1:Aliens win
			ended = true;
			System.out.println("Ended game in win() method (no humans, aliens win)");

		}

		if (p instanceof Human) { // case 2: every human escaped is a winner!
			if (((Human) p).isEscaped()) {
				humanWins = true;
				p.suspend(); // the player end its game
				escapedNotify();
				if (rounds == ServerConstants.ROUNDS - 1){
					// if a Human escapes, he's a winner, but the game is still on
					ended = true;
					System.out.println("Ended game in win() method (human escaped, NOT CLEAR)");

				}
				
			}
		}
		if (rounds == ServerConstants.ROUNDS - 1) {
			ended = true;
			System.out.println("Ended game in win() method (NOT CLEAR)");

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
	}
	
	
	
	

	public synchronized void switchTurn() {
		timer.cancel();

		resetTurn();

		if (!checkActivePlayers()) {
			ended = true;
			System.out.println("Ended game since no players are active");
			return;
		}
		playerList.get(playerTurn).setActive(false);
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
		for (int i = 0; i < playerList.size() ; i++) {
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
		if (mustMove) {
			Player p = CommonMethods.toPlayer(playerID, playerList);
			Sector s = CommonMethods.toSector(sector, table);

			String toReturn = p.move(table, s) + ";";

			hasMoved = true;
			mustDrawDSCard = p.getCurrentSector().getMustDrawDSCard();
			mustDrawEHCard = p.getCurrentSector().getMustDrawEHCard();

			return toReturn;
		}
		return "KO";
	}

	String attack(int playerID) {
		Player p = CommonMethods.toPlayer(playerID, playerList);
		if (p.getCanAttack() && !hasAttacked && !hasDrawnDSCard) {
			
			String toReturn = p.attack(playerList);
			
			if (toReturn.startsWith("OK")) {
				hasAttacked = true;
			}
			return toReturn;
		}
		return "KO";
	}

	public String chooseAction(int playerID) {

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
		if (!hasAttacked && mustDrawDSCard && !hasDrawnDSCard) {

			dsc = (DangerousSectorCard) dangerousSectorDeck.drawCard();
			dangerousSectorDeck.discardCard(dsc);
			this.hasDrawnDSCard = true;
			this.mustDeclare = true;

			String toReturn = dsc.getCard();
			
			if (dsc.getWithObject()) {
				this.mustHandleICard = true;
				ic = (ItemCard) itemCardDeck.drawCard();
				toReturn = toReturn.concat(";" + ic.getCard());
			}

			return toReturn;
		}
		
		return "KO";

	}

	public String getItemCard(int playerID) {
		if (this.mustHandleICard && !this.hasHandledICard){
			// TODO Auto-generated method stub
			hasHandledICard=true;
		}
		return null;
	}

	public String getEscapeHatchCard(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	public String declareNoise(int playerID, String sector) {
		if (this.mustDeclare){
			Player p = CommonMethods.toPlayer(playerID, playerList);
			Sector s = CommonMethods.toSector(sector, table);
			String toReturn = "";
			if (CardsConstants.NOISE.equals(dsc.getCard().split("_")[1])) {
				if (dsc.getYourSector()) {
					if (s.equals(p.getCurrentSector())){
						toReturn="OK"; //Noise in your sector
					} else {
						return "KO";
					}
				} else {
					toReturn="OK"; //Noise in any sector
				}
			} else {
				toReturn="OK"; //Silence in any sector
			}
			mustDeclare=false;
			hasDeclared=true;
			return toReturn;
		}
		return null;

	}
	
	public String getAdjacentSectors(int playerID){
		Player p = CommonMethods.toPlayer(playerID, playerList);
		List<Sector> adjacentSectors = table.adjacent(p.getCurrentSector(), p.getMoves());
		String toReturn = "";
		for (Sector sector : adjacentSectors) {
			toReturn = toReturn + sector.toString() + ";";
		}
		return toReturn;
		
	}
	
	

}
