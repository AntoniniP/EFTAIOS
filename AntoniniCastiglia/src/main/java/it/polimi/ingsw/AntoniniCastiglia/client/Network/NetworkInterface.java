package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import java.io.IOException;
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
	String connect();

	boolean close() throws IOException;

	/**
	 * Gets the map from the server.
	 * 
	 * @return the map, in form of a string, separated by <code>;</code>
	 * @throws RemoteException
	 */
	String getMap() throws RemoteException;

	/**
	 * Moves the player to the selected sector.
	 * 
	 * @param sector
	 * @return
	 * @throws RemoteException
	 */
	String move(String sector, String player) throws RemoteException;

	boolean isEnded() throws RemoteException;

	String getWinner() throws RemoteException;

	String getCards(String player) throws RemoteException;

	/**
	 * When invoked, presents to the player his cards and lets him use them.
	 * 
	 * @param cards item cards of the players
	 * @param ui user interface (to communicate with the player
	 * @param player TODO
	 */
	void useCards(String[] cards, UserInterface ui, String player);

	/**
	 * Asks the server for the adjacent sectors list.
	 * 
	 * @return the list of adjacent sectors, in form of a string
	 */
	String getAdjacents();

	boolean isStarted();
}
