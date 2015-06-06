package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine {

	private Table table;
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
		/*Player p= CommonMethods.getPlayer(name);
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
	public Card useCard(String s) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public String getCards(Player p) throws RemoteException {//better to be called drawCard
		String string;
		string=p.getPlayerCards();
		return string;
	}

	@Override
	public boolean isStarted() throws RemoteException {
		// return server.startGame
		return false;
	}
}