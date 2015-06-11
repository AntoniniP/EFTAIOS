package it.polimi.ingsw.AntoniniCastiglia.server;

import java.util.Timer;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;

public class GameHandler implements Runnable, TimerInterface {

	private Table table;
	private Deck dangerousSectorDeck;
	private Deck itemCardDeck;
	private Deck escapeHatchDeck;
	private PlayerList playerList;
	private int numPlayers;
	private int rounds;
	private Timer timer;

	private int playerTurn = 0;
	private boolean started;
	private boolean suspended;
	private boolean winner;
	private boolean ended;

	@Override
	public void run() {
		while (!started && !suspended) {
			try {
				System.out.println("A game is about to start");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (started) {
			turn();
		} else if (suspended) {
			// nothing to do
		}

		endGame();

	}

	public void gameTools() { // receives all the data about players, cards, map, etc!
		createMap();
		createPlayers(numPlayers);
		createDecks();
		instatiateTimer();

	}

	private void instatiateTimer() {
		timer = new Timer();

	}

	private void createMap() {
		table = new Table();
	}

	private void createPlayers(int numPlayers) {// TODO initialize Players setPlayerList
		playerList = new PlayerList(numPlayers);
	}

	private void createDecks() {
		dangerousSectorDeck = new DangerousSectorDeck();
		itemCardDeck = new ItemCardDeck();
		escapeHatchDeck = new EscapeHatchDeck();
	}

	public void turn() {
		while (rounds <= 39 && !ended) {
			timer.schedule(new MyTimer(this), 2 * 60 * 1000);
			while (playerList.get(playerTurn).isActive()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Player's Actions: create another class with all the actions & stuff
			// TODO create the method for the winner!
			if (!winner)// or human killed==n_human
				break;// goes out of the cycle if there's a winner

		}

	}

	public boolean isMyTurn(int playerID) {
		return playerID == playerTurn;
	}

	private void endGame() {
		ended = true;
	}

	public synchronized void switchTurn() {
		timer.cancel(); // avoid useless switches of turn
		if (!checkActivePlayers()) {
			ended = true;
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
		boolean oneAlien = false;
		boolean oneHuman = false;
		for (int i = 0; i < playerList.size() && !oneAlien && !oneHuman; i++) {
			if (!playerList.get(i).isSuspended()) {
				if (playerList.get(i) instanceof Alien)
					oneAlien = true;
				else
					oneHuman = true;
			}
		}
		return oneAlien && oneHuman;
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
		return started;
	}
}
