package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.server.GameHandler;

/**
 * TELEPORT: This card allows you to move directly to the Human Sector from any part of the ship.
 * This is in addition to your normal movement which can happen before or after you use the item.
 *
 * @author Paolo Antonini
 *
 */
public class ItemTeleport extends ItemCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public ItemTeleport() {
		super();
		name = CardsConstants.TELEPORT;
	}

	@Override
	public String action(GameHandler gh) {
		return gh.itemTeleportAction();
	}

}
