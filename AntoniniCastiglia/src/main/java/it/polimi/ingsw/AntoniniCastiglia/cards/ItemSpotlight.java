package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * SPOTLIGHT: When you play this card, name any Sector. Any players (including you) that are in the
 * named Sector or any of the six adjacent Sectors must immediately announce their exact location
 * Coordinates. This card affects both Humans and Aliens.
 * 
 * @author Paolo Antonini
 *
 */
public class ItemSpotlight extends ItemCard {

	public ItemSpotlight() {
		super();
		name = CardNames.SPOTLIGHT;
	}

	@Override
	public void action(Player p) {
		// TODO Auto-generated method stub

	}

}
