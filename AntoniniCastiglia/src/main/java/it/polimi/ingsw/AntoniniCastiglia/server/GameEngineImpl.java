package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine {

	private Table table;
	private Deck deck;
	private PlayerList playerList;

	public GameEngineImpl() { // Constructor
	}

	public void createMap(){
		 // to put in another class GAME together with cards & stuff
		table = new Table();
	}
	
	public void createPlayers(int numPlayers){
		playerList= new PlayerList(numPlayers);
	}
	
	@Override
	public String getPlayerString(int playerID){
		return (playerList.get(playerID)).toString();
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
	public String move(int  playerID, String sector) throws RemoteException {
		Player p= CommonMethods.toPlayer(playerID, playerList);
		//Sector s = //convert string to coordinates, access table[][]
	    //p.setCurrentSector(s);
		return "You are now in Sector"+ p.getCurrentSector();
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
	public String getCards(int playerID) throws RemoteException {//better to be called drawCard
		Player p = CommonMethods.toPlayer(playerID, playerList);
		String cards = p.getPlayerCards();
		return cards;
	}

	
	
	
}