package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import java.util.List;

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
	 */
	public void connected();

	/**
	 * Lets the player know which are his name and nature (Human/Alien)
	 * 
	 * @param name
	 */
	public void whoYouAreComplete(String[] player);

	/**
	 * Shows the map, received as a string.
	 * 
	 * @param map
	 */
	public void printMap(String map);

	/**
	 * Prints the cards passed as a parameter (they can be 0, 1, n, or in form of a string array.
	 * @param canUseCards TODO
	 * @param card
	 */
	public void printCards(boolean canUseCards, String... card);

	/**
	 * Shows all possible actions to the player.
	 * @param possibleActions
	 * @deprecated (not used)
	 */
	public void chooseAction(List<Character> possibleActions);

	/**
	 * Prints a simple message, asking the player to choose which card(s) he wants to use.
	 */
	public void chooseCards();

	/**
	 * Prints a simple message, asking the player to choose where to move.
	 */
	public void askMove(String adjacents);

	/**
	 * Prints a simple message, to let the player know that an error occurred.
	 */
	void genericError();

	/**
	 * Asks the player to wait for a game to begin.
	 */
	public void pleaseWait();
	
	/**
	 * Prints a simple message, informing the player that it's his turn.
	 */
	public void yourTurn();

	public void youCanAttack(String nature);
	
	public void drawDangerousSectorCard(String drawnCard);

}
