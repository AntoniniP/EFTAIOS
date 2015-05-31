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
	public void connecting();

	/**
	 * Lets the player know which are his name and nature (Human/Alien)
	 * 
	 * @param name
	 */
	public void youAre(String name);

	/**
	 * Shows the map, received as a string.
	 * 
	 * @param map
	 */
	public void printMap(String map);

	/**
	 * Prints the cards owned by a player.
	 * 
	 * @param card
	 */
	public void printAllCards(String... card);

	/**
	 * Shows all possible actions to the player.
	 * 
	 * @param possibleActions
	 */
	public void chooseAction(List<Character> possibleActions);

	/**
	 * Prints a simple message, asking the player to choose which card(s) he wants to use.
	 */
	public void chooseCards();
	
	/**
	 * Prints a simple message, asking the player to choose where to move.
	 * @param player TODO
	 */
	public void askMove(String adjacents, String player);

}
