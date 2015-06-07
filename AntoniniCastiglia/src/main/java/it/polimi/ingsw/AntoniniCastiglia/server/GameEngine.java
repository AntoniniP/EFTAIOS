package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import java.rmi.*;
import java.util.ArrayList;

public interface GameEngine extends Remote {

	public String getMap() throws RemoteException;

	public String move(int playerID, String sector) throws RemoteException;

	public String attack(int playerID) throws RemoteException;

	public String getCards(int playerID) throws RemoteException;

	public Card useCard(String s, int playerID) throws RemoteException;

	public String getPlayerString(int playerID) throws RemoteException;

	public String getAdjacents(int playerID) throws RemoteException;

	public boolean isEnded() throws RemoteException;

	public String drawCard(String deck) throws RemoteException;
}
