package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Abstract class for cards. It contains two parameters to identify a card (<code>type</code> and
 * <code>name</code>), and a method to return them.
 * 
 * @author Laura Castiglia
 *
 */
public abstract class Card {

	protected String type;
	protected String name;

	/**
	 * Getter for <code>type</code> and <code>name</code> of the card.
	 * 
	 * @return
	 */
	public String toString() {
		return type + "_" + name;
	}

}
