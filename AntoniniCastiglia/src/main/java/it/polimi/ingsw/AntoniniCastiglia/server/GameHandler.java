package it.polimi.ingsw.AntoniniCastiglia.server;

import java.util.List;
import java.util.Timer;

import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
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
	private int numPlayers =0;
	private int rounds;
	private Timer timer;
	private List<String> winningH;

	private int playerTurn;
	private boolean started = false;
	private boolean suspended = false;
	private boolean oneAlien=false; //at least one alien in game
	private boolean oneHuman = false;
	private boolean aliensWin=false;
	private boolean humanWins=false;
	private boolean ended=false;

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
			playerTurn=(int)(Math.random()*numPlayers);
			rounds=0;
			turn();
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
			win();//check if someone has won

		}

	}

	public boolean isMyTurn(int playerID) {
		return playerID == playerTurn;
	}

	public String endGame() { // client will receive the output according to the ending way
		String s;
		win();
		if(aliensWin)
			s = "The game is over: aliens win!";
		if(humanWins && ended){
			s = arrayToString();
		}
		else
			s = "The game is over: both Humans and Aliens win!";
		this.ended=true;
		return s;
	}
	
	public void win(){ //method to check if someone has won the game
		Player p=playerList.get(playerTurn);
		
		if(!oneHuman||(rounds==ServerConstants.ROUNDS-1 && oneHuman)){
			aliensWin=true; //case 1:Aliens win
			ended=true;
		}
		
		if(p instanceof Human){ //case 2: every human escaped is a winner!
			if(((Human)p).isEscaped()){
				humanWins=true;
				p.suspend(); //the player end its game
				escapedNotify();
				if(rounds==ServerConstants.ROUNDS-1) //if a Human escapes, he's a winner, but the game is still on
					ended=true; 
			}
		}
		if(rounds==ServerConstants.ROUNDS-1)
			ended=true;
	}

	public void escapedNotify(){ //live notification of winning by escape
		String s = "player" + playerList.get(playerTurn)+"has escaped: he wins!";
		winningH.add(s);
		System.out.println(s);
	}

	public synchronized void switchTurn() {
		timer.cancel(); 
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
		for (int i = 0; i < playerList.size() && !oneAlien; i++) {
			if (!playerList.get(i).isSuspended()) {
				if (playerList.get(i) instanceof Alien)
					oneAlien = true;
				else
					oneHuman=humanLeft();
			}
		}
		return oneAlien && oneHuman;
	}

	private boolean humanLeft() { //checks if there are human left
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
	
	private String arrayToString() { //method useful for converting winninH arrayList into String (see endGame)
		String str = "";
		for(String s:winningH)
			str = str + s +"\t";
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
}
