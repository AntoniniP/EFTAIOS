package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.*;
import java.util.ArrayList;


public interface GameEngine extends Remote {
	
	  public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException;
	  public String getMap() throws RemoteException;
	  public String move(String player, String sector) throws RemoteException;
	  public String attack(Player p) throws RemoteException;
	  public String getCards() throws RemoteException;
	  public String useCard() throws RemoteException;
	  public boolean isStarted() throws RemoteException;
}
