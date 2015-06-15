package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import java.rmi.RemoteException;

/**
 * This interface provides a list of methods, useful for the factory method pattern implementation
 * of <code>NetworkInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public interface NetworkInterface {

	/**
	 * Connects to the server, and returns the name of the player who invoked the method.
	 * 
	 * @return the name of the player
	 */
	String connect() throws RemoteException;

	/**
	 * Gets the map from the server.
	 * 
	 * @param playerID TODO
	 * 
	 * @return the map, in form of a string, separated by <code>;</code>
	 * @throws RemoteException
	 */
	String getMap(int playerID, int gameID) throws RemoteException;

	/**
	 * Moves the player to the selected sector.
	 * 
	 * @param sector
	 * @return
	 * @throws RemoteException
	 */
	String move(int playerID, int gameID, String sector) throws RemoteException;

	boolean isEnded(int gameID) throws RemoteException;

	String getCards(int playerID, int gameID) throws RemoteException;

	/**
	 * When invoked, presents to the player his cards and lets him use them.
	 * 
	 * @param cards item cards of the players
	 * @param player TODO
	 */
	void useCard(int playerID, int gameID, int posCard) throws RemoteException;

	/**
	 * Asks the server for the adjacent sectors list.
	 * 
	 * @param playerID
	 * 
	 * @return the list of adjacent sectors, in form of a string
	 * @throws RemoteException
	 */
	String getAdjacentSectors(int playerID, int gameID) throws RemoteException;

	boolean isStarted(int gameID) throws RemoteException;

	String getPlayer(int playerID, int gameID) throws RemoteException;

	String attack(int playerID, int gameID) throws RemoteException;

	String drawCard(int gameID, int playerID, String deck) throws RemoteException;

	boolean isMyTurn(int playerID, int gameID) throws RemoteException;

	void endTurn(int playerID, int gameID) throws RemoteException;
	
	boolean canAttack(int gameID, int playerID) throws RemoteException;

	boolean isDead(int playerID, int gameID)throws RemoteException;
	
	 String useDangerousSectorCard(int playerID, int gameID) throws RemoteException;

	String possibleActions(int playerID, int gameID) throws RemoteException;

	String declareNoise(int gameID, int playerID, String sector) throws RemoteException;
	
	
	String getItemCard(int playerID, int gameID) throws RemoteException;

	String handleItemCard(int playerID, int gameID, int cardIndex) throws RemoteException;


	String getJournal (int playerID, int gameID) throws RemoteException;



}
