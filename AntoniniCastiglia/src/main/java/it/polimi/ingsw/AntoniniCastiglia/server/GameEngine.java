package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <code>GameEngine<code> remote interface.
 * 
 * @author Laura Castiglia
 *
 */
public interface GameEngine extends Remote {

	/**
	 * Moves the player to a selected sector, if it's valid.
	 *
	 * @param playerID
	 * @param gameID
	 * @param sector
	 * @return the result of the move
	 * @throws RemoteException
	 */
	String move(int playerID, int gameID, String sector) throws RemoteException;

	/**
	 * Attacks in the current sector of the calling player, if he can.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the attack
	 * @throws RemoteException
	 */
	String attack(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets the player use a card in position posCard.
	 *
	 * @param playerID
	 * @param gameID
	 * @param posCard
	 * @return the result of the operation
	 * @throws RemoteException
	 */
	String useCard(int playerID, int gameID, int posCard) throws RemoteException;

	/**
	 * Returns the adjacent sectors to the calling player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the adjacent sectors in form of a string
	 * @throws RemoteException
	 */
	String getAdjacentSectors(int playerID, int gameID) throws RemoteException;

	/**
	 * Getter for the ended variable.
	 *
	 * @param gameID
	 * @return the ended variable
	 * @throws RemoteException
	 */
	boolean isEnded(int gameID) throws RemoteException;

	/**
	 * Draws a card from the asked deck.
	 *
	 * @param gameID
	 * @param playerID
	 * @param deck
	 * @return the card, in form of a string
	 * @throws RemoteException
	 */
	String drawCard(int gameID, int playerID, String deck) throws RemoteException;

	/**
	 * Getter for the player number playerID.
	 *
	 * @param playerID
	 * @param gameID
	 * @return some information about the player
	 * @throws RemoteException
	 */
	String getPlayer(int playerID, int gameID) throws RemoteException;

	/**
	 * Getter for the table.
	 *
	 * @param gameID
	 *
	 * @return the table
	 * @throws RemoteException
	 */
	String getMap(int gameID) throws RemoteException;

	/**
	 * Getter for the Item cards of a player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the cards, in form of a string
	 * @throws RemoteException
	 */
	String getPlayerCards(int playerID, int gameID) throws RemoteException;

	/**
	 * Getter for started variable.
	 *
	 * @param gameID
	 * @return whether the game is started
	 * @throws RemoteException
	 */
	boolean isStarted(int gameID) throws RemoteException;

	/**
	 * Checks whether is the turn of the calling player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return whether is the turn of the calling player
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
	 * Returns whether the player can attack or not
	 *
	 * @param playerID
	 * @param gameID
	 * @return whether the player can attack or not
	 * @throws RemoteException
	 */
	boolean canAttack(int gameID, int playerID) throws RemoteException;

	/**
	 * Returns whether the player is dead or not
	 *
	 * @param playerID
	 * @param gameID
	 * @return whether the player is dead or not
	 * @throws RemoteException
	 */
	boolean isDead(int playerID, int gameID) throws RemoteException;

	/**
	 * Returns the possible actions for a player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the possible actions for a player, in form of a string, separated by an underscore
	 * @throws RemoteException
	 */
	String possibleActions(int playerID, int gameID) throws RemoteException;

	/**
	 * Lets the player declare noise or silence, in the sector.
	 *
	 * @param playerID
	 * @param gameID
	 * @param sector
	 * @return the result of the operation
	 * @throws RemoteException
	 */
	String declareNoise(int gameID, int playerID, String sector) throws RemoteException;

	/**
	 * Adds the item card to player's carnet, if possibile.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the operation
	 * @throws RemoteException
	 */
	String getItemCard(int playerID, int gameID) throws RemoteException;

	/**
	 * Handles the case of too many item cards. Receives the index of the card to switch with.
	 *
	 * @param playerID
	 * @param gameID
	 * @param cardIndex
	 * @return the result of the operation
	 * @throws RemoteException
	 */
	String handleItemCard(int playerID, int gameID, int cardIndex) throws RemoteException;

	/**
	 * Returns the journal of a player.
	 *
	 * @param playerID
	 * @param gameID
	 * @return the journal of a player, in form of a string
	 * @throws RemoteException
	 */
	String getJournal(int playerID, int gameID) throws RemoteException;

	/**
	 * Draws an Escape hatch card and lets the player escape (if possible).
	 *
	 * @param playerID
	 * @param gameID
	 * @return the result of the operation
	 * @throws RemoteException
	 */
	String escape(int playerID, int gameID) throws RemoteException;

}
