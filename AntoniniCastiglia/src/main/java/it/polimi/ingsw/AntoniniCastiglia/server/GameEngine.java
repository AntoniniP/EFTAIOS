package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameEngine extends Remote {

	public String move(int playerID, int gameID, String sector) throws RemoteException;

	public String attack(int playerID, int gameID) throws RemoteException;

	public void useCard(int playerID, int gameID, int posCard) throws RemoteException;

	public String getAdjacentSectors(int playerID, int gameID) throws RemoteException;

	public boolean isEnded(int gameID) throws RemoteException;

	public String drawCard(int gameID, String deck) throws RemoteException;

	public String getPlayer(int playerID, int gameID) throws RemoteException;

	public String getMap(int gameID) throws RemoteException;

	public String getCards(int playerID, int gameID) throws RemoteException;

	public boolean isStarted(int gameID) throws RemoteException;

	public boolean isMyTurn(int playerID, int gameID) throws RemoteException;

	public void endTurn(int playerID, int gameID) throws RemoteException;

	public void notifyWin(int gameID, int playerID) throws RemoteException;

}
