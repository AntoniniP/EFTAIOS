package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import java.rmi.*;
import java.util.ArrayList;

public interface GameEngine extends Remote {

	public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException;

	public String getMap() throws RemoteException;

	public String move(int playerID, String sector) throws RemoteException;

	public String attack(Player p) throws RemoteException;

	public String getCards(int playerID) throws RemoteException;

	public Card useCard(String s) throws RemoteException;


	public String getPlayerString(int playerID) throws RemoteException;
}
