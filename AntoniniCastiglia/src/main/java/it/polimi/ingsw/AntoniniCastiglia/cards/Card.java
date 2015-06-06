package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Public interface for cards. Completely empty now.
 * 
 * @author Paolo Antonini
 *
 */
public abstract class Card {

	protected String type;
	protected String name;

	public String getCard() {
		return type + "_" + name;
	}

}
