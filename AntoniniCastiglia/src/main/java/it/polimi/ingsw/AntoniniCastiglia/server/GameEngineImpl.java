package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine {

	private Table table;
	private CommonMethods commonM;
	private Deck deck;

	public GameEngineImpl(Table table) { // Constructor; I have a map to work with
		this.table = table; // to put in another class GAME together with cards & stuff
	}

	@Override
	public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException {
		return table.adjacent(p.getCurrentSector(), p.getHops());
	}

	@Override
	public String getMap() throws RemoteException {
		return table.drawMap();
	}

	@Override
	public String move(String player, String sector) throws RemoteException {
		/*Player p= commonM.getPlayer(name);
	    p.setCurrentSector(s);
		return "You are now in Sector"+ p.getCurrentSector();*/
		return "ABC";
	}

	@Override
	public String attack(Player p) throws RemoteException {
		p.setAttack(true);
		/*for(int i=0; i<playerList.lenght-1;i++){
		 * if(!playerList[i].getAttack && !playerList[i].getShield && playerList[i].getCurrentSector==p.currentSector)
		 * 		if(playerList[i].getId!=p.id)
		 * 			playerList[i].getBeenEaten; ----> need to implement the kill thing!
		 * if(p instanceof Alien)
		 * 		p.maxMoves=3;
		 * */

		return "Attack in " + p.getCurrentSector();
	}



	@Override
	public String useCard() throws RemoteException {//TODO toString method for cards OR item array in players
		//If I have to receive a string and convert it into a card, why do I have to return a string?
		//TODO the player has to choose which card he wants to use; is this the Client's business?
		return null;

	}
	
	@Override
	public boolean isStarted() throws RemoteException {
		/*return server.startGame*/
		return true;
	}

	@Override //method that should return the deck
	public String getCards() throws RemoteException {//better to be called drawCard
		String deckString;
		deckString=deck.toString();
		return deckString;
	}
	
	
}