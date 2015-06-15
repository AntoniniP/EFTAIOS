package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameEngine extends Remote {

	String move(int playerID, int gameID, String sector) throws RemoteException;

	String attack(int playerID, int gameID) throws RemoteException;

	String useCard(int playerID, int gameID, int posCard) throws RemoteException;

	String getAdjacentSectors(int playerID, int gameID) throws RemoteException;

	boolean isEnded(int gameID) throws RemoteException;

	String drawCard(int gameID, int playerID, String deck) throws RemoteException;

	String getPlayer(int playerID, int gameID) throws RemoteException;

	String getMap(int gameID) throws RemoteException;

	String getCards(int playerID, int gameID) throws RemoteException;

	boolean isStarted(int gameID) throws RemoteException;

	boolean isMyTurn(int playerID, int gameID) throws RemoteException;

	void endTurn(int playerID, int gameID) throws RemoteException;

	void notifyWin(int gameID, int playerID) throws RemoteException;

	boolean canAttack(int gameID, int playerID) throws RemoteException;

	boolean isDead(int playerID, int gameID)throws RemoteException;
	
	 
	 String possibleActions(int playerID, int gameID) throws RemoteException;

	String declareNoise(int gameID, int playerID, String sector) throws RemoteException;


}
