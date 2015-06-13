package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * DEFENSE: Play this card immediately when an Alien attacks you. You are not affected by the
 * attack.
 * 
 * @author Paolo Antonini
 *
 */
public class ItemDefense extends ItemCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public ItemDefense() {
		super();
		name = CardsConstants.DEFENSE;
	}

	@Override
	public void action(Player p) {
		// TODO maybe a boolean in player?
	}

}
