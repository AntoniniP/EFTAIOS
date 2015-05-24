package it.polimi.ingsw.AntoniniCastiglia.action;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.*;
import java.util.ArrayList;

//Starting to think about RMI stuff

public interface GameEngine extends Remote {
	
	public void move(Player p, Sector s) throws RemoteException;
	public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException;
	public Player updatePlayer(Player p) throws RemoteException; 
	
}
