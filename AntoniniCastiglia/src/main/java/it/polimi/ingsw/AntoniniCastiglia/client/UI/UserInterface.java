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
	 * Prints a simple message.
	 * @param playerID TODO
	 */
	public void connected(int playerID);

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
	 * Prints a simple message, informing the player that it's his turn.
	 */
	public void yourTurn();

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

	public void youCanAttack(String nature);
	
	public void drawDangerousSectorCard(String drawnCard);
	
	public void moveResult(String result);

}
