package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.server.GameHandler;

/**
 * ATTACK: This card allows you to attack, using the same rules as the Aliens. Note: the Human
 * character can still move only one Sector.
 *
 * @author Paolo Antonini
 *
 */
public class ItemAttack extends ItemCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public ItemAttack() {
		super();
		name = CardsConstants.ATTACK;
	}

	@Override
	public String action(GameHandler gh) {
		return gh.itemAttackAction();
	}

}
