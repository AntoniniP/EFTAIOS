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
	 * Connects to the server, and returns the gameID and the playerID of the player who invoked the
	 * method.
	 *
	 * @return the gameID and the playerID, separated by an underscore (<code>_</code>)
	 */
	String connect() throws RemoteException;

	/**
	 * Gets the map from the server.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the map, in form of a string
	 * @throws RemoteException
	 */
	String getMap(int playerID, int gameID) throws RemoteException;

	/**
	 * Moves the player to the selected sector.
	 *
	 * @param sector
	 * @return the result of the action
	 * @throws RemoteException
	 */
	String move(int playerID, int gameID, String sector) throws RemoteException;

	/**
	 * Checks whether the game is ended or not.
	 *
	 * @param gameID
	 * @return the result of the check
	 * @throws RemoteException
	 */
	boolean isEnded(int gameID) throws RemoteException;

	/**
	 * Returns a string containing all the Item cards of a player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return a string containing the cards, separated by a semicolon (<code>;</code>)
	 * @throws RemoteException
	 */
	String getCards(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets a player use the Item card in <code>posCard</code> in his carnet.
	 *
	 * @param playerID
	 * @param gameID
	 * @param posCard
	 * @return the result of the use of the card
	 */
	String useCard(int playerID, int gameID, int posCard) throws RemoteException;

	/**
	 * Asks the server for the adjacent sectors list.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the list of adjacent sectors, in form of a string
	 * @throws RemoteException
	 */
	String getAdjacentSectors(int playerID, int gameID) throws RemoteException;

	/**
	 * Checks whether a game is started.
	 *
	 * @param gameID
	 * @return the result of the check
	 * @throws RemoteException
	 */
	boolean isStarted(int gameID) throws RemoteException;

	/**
	 * Returns name, role and other pieces of information about the player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return some information about the player, separated by an underscore
	 * @throws RemoteException
	 */
	String getPlayer(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets the player attack the sector where he is in now.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the attack, with the number of humans and aliens who were killed
	 * @throws RemoteException
	 */
	String attack(int playerID, int gameID) throws RemoteException;

	/**
	 * Asks for a card.
	 *
	 * @param gameID
	 * @param playerID
	 * @param deck states from which deck the card must be drawn
	 * @return the card
	 * @throws RemoteException
	 */
	String drawCard(int gameID, int playerID, String deck) throws RemoteException;

	/**
	 * Checks whether it's the turn of the player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the check
	 * @throws RemoteException
	 */
	boolean isMyTurn(int playerID, int gameID) throws RemoteException;

	/**
	 * Ends the turn of the player.
	 *
	 * @param playerID
	 * @param gameID
	 * @throws RemoteException
	 */
	void endTurn(int playerID, int gameID) throws RemoteException;

	/**
	 * Checks whether the player can attack or not.
	 *
	 * @param gameID
	 * @param playerID
	 * @return the result of the check
	 * @throws RemoteException
	 */
	boolean canAttack(int gameID, int playerID) throws RemoteException;

	/**
	 * Checks whether the player is dead or not.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the check
	 * @throws RemoteException
	 */
	boolean isDead(int playerID, int gameID) throws RemoteException;

	/**
	 * Asks for the possible actions a player can perform.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the list of possible actions, separated by an underscore
	 * @throws RemoteException
	 */
	String possibleActions(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets a player declare the noise/silence in a sector.
	 *
	 * @param gameID
	 * @param playerID
	 * @param sector
	 * @return the result of the declaration
	 * @throws RemoteException
	 */
	String declareNoise(int gameID, int playerID, String sector) throws RemoteException;

	/**
	 * Asks the server for an Item card.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the requested card
	 * @throws RemoteException
	 */
	String getItemCard(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets a player cope with the situation when his Item card carnet is full.
	 *
	 * @param playerID
	 * @param gameID
	 * @param cardIndex the index of the card he wants to discard
	 * @return the result of the operation
	 * @throws RemoteException
	 */
	String handleItemCard(int playerID, int gameID, int cardIndex) throws RemoteException;

	/**
	 * Asks the server for the record of events happened after the end of the turn of a player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the journal
	 * @throws RemoteException
	 */
	String getJournal(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets the player escape fron the ship.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the attempt
	 * @throws RemoteException
	 */
	String escape(int playerID, int gameID) throws RemoteException;

}
