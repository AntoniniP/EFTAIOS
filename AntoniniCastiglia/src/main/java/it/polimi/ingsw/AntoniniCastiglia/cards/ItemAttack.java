package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * ATTACK: This card allows you to attack, using the same rules as the Aliens. Note: the Human
 * character can still move only one Sector.
 * 
 * @author Paolo Antonini
 *
 */
public class ItemAttack extends ItemCard {

	public ItemAttack() {
		super();
		name = CardNames.ATTACK;
	}

	@Override
	public void action(Player p) {
		// TODO set boolean canAttack in player p to true
	}

}
