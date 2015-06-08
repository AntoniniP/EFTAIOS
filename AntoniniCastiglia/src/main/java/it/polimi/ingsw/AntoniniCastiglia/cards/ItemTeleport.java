package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * TELEPORT: This card allows you to move directly to the Human Sector from any part of the ship.
 * This is in addition to your normal movement which can happen before or after you use the item.
 * 
 * @author Paolo Antonini
 *
 */
public class ItemTeleport extends ItemCard {

	public ItemTeleport() {
		super();
		name = CardNames.TELEPORT;
	}

	@Override
	public void action(Player p) {
		p.setCurrentSector(p.getMyBase());
	}

}
