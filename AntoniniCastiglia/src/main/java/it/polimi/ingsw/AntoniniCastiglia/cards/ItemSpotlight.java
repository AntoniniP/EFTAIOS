package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.server.GameHandler;

/**
 * SPOTLIGHT: When you play this card, name any Sector. Any players (including you) that are in the
 * named Sector or any of the six adjacent Sectors must immediately announce their exact location
 * Coordinates. This card affects both Humans and Aliens.
 *
 * @author Paolo Antonini
 *
 */
public class ItemSpotlight extends ItemCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public ItemSpotlight() {
		super();
		name = CardsConstants.SPOTLIGHT;
	}

	@Override
	public String action(GameHandler gh) {
		return gh.itemSpotlightAction();
	}

}
