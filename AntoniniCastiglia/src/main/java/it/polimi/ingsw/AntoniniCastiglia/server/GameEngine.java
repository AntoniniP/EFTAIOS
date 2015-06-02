package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.*;
import java.util.ArrayList;

//Starting to think about RMI stuff

public interface GameEngine extends Remote {
	
	  public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException;
	  public String getMap() throws RemoteException;
	  public String move(Player p, Sector s) throws RemoteException;
	  public String attack(Player p) throws RemoteException;
	  public String getCards() throws RemoteException;
	  public String useCard() throws RemoteException;
}
