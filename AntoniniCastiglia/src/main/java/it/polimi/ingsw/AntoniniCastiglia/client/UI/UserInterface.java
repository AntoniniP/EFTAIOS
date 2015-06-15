package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import java.rmi.RemoteException;

/**
 * This interface provides a list of methods, useful for the factory method pattern implementation
 * of <code>UserInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public interface UserInterface {

	/**
	 * Prints a simple message.
	 * @param gameID TODO
	 * @param playerID TODO
	 */
	void connected(int gameID, int playerID);

	/**
	 * Lets the player know which are his name and nature (Human/Alien)
	 * 
	 * @param name
	 */
	void whoYouAreComplete(String[] player);

	/**
	 * Shows the map, received as a string.
	 * 
	 * @param map
	 */
	void printMap(String map);

	/**
	 * Prints the cards passed as a parameter (they can be 0, 1, n, or in form of a string array.
	 * 
	 * @param canUseCards TODO
	 * @param card
	 */
	void printItemCards(boolean canUseCards, String... card);

	/**
	 * Prints a simple message, informing the player that it's his turn.
	 */
	void yourTurn();

	int selectItemCard(String[] cards);

	//String wantToAttack(String nature);

	void drawDangerousSectorCard(String drawnCard);

	String move(int playerID, String adjacentSectors);

	void whereYouAre(String currentSector, String sectorType);

	String chooseAction(String possibleActions);

	void attackResult(String attackResult);

	String declareNoise(boolean noise, boolean yourSector, String currentSector);

	int handleItemCard(String itemCard, String... cards);

	void clearScreen();
	
	void printJournal (String journal);
}
