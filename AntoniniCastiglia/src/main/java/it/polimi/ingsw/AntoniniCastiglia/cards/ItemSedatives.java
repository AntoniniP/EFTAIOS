package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.server.GameHandler;

/**
 * SEDATIVES: If you play this card you do not draw a Dangerous Sector Card this turn, even if you
 * move into a Dangerous Sector.
 *
 * @author Paolo Antonini
 *
 */
public class ItemSedatives extends ItemCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public ItemSedatives() {
		super();
		name = CardsConstants.SEDATIVES;
	}

	@Override
	public String action(GameHandler gh) {
		return gh.itemSedativesAction();
	}

}
