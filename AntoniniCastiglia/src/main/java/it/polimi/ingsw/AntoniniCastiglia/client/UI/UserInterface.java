package it.polimi.ingsw.AntoniniCastiglia.client.UI;

/**
 * This interface provides a list of methods, useful for the factory method pattern implementation
 * of <code>UserInterface</code>.
 *
 * @author Paolo Antonini
 *
 */
public interface UserInterface {

	/**
	 * Prints a simple message containing playerID and gameID.
	 *
	 * @param gameID
	 * @param playerID
	 */
	void connected(int gameID, int playerID);

	/**
	 * Lets the player know which are his name, role and nature (Human/Alien)
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
	 * Prints the cards passed as a parameter (they can be 0, 1, n, or in form of a string array).
	 *
	 * @param canUseCards
	 * @param card
	 */
	void printItemCards(boolean canUseCards, String... card);

	/**
	 * Lets the player choose which card he wants to use.
	 *
	 * @param cards
	 * @return the index of the chosen card
	 */
	int selectItemCard(String[] cards);

	/**
	 * Presents the Dangerous Sector card, drawn by the player.
	 *
	 * @param drawnCard
	 */
	void drawDangerousSectorCard(String drawnCard);

	/**
	 * Lets the player move to a new sector
	 *
	 * @param playerID
	 * @param adjacentSectors
	 * @return the sector where to move
	 */
	String move(int playerID, String adjacentSectors);

	/**
	 * Prints the the sector where the player is currently.
	 *
	 * @param currentSector
	 * @param sectorType
	 */
	void whereYouAre(String currentSector, String sectorType);

	/**
	 * Prints the possible actions.
	 *
	 * @param possibleActions
	 * @return the chosen action
	 */
	String chooseAction(String possibleActions);

	/**
	 * Shows the result of the attack.
	 *
	 * @param attackResult
	 */
	void attackResult(String attackResult);

	/**
	 * Lets the player decide where he wants to declare a noise (if it's possible).
	 *
	 * @param noise
	 * @param yourSector
	 * @param currentSector
	 * @return the sector of player's choice
	 */
	String declareNoise(boolean noise, boolean yourSector, String currentSector);

	/**
	 * Lets the player cope with the situation when he has too many Item cards.
	 *
	 * @param itemCard
	 * @param cards
	 * @return the index of the card he wants to discard
	 */
	int handleItemCard(String itemCard, String... cards);

	/**
	 * Prints the journal.
	 *
	 * @param journal
	 */
	void printJournal(String journal);

	/**
	 * Prints the result of the use of the Item card.
	 *
	 * @param useResult
	 */
	void useResult(String useResult);

	/**
	 * Prints the result of the escape.
	 *
	 * @param escapeResult
	 */
	void escapeResult(String escapeResult);
}
