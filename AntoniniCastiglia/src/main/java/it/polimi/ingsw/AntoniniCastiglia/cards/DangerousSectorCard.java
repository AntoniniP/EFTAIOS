package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Public interface for Dangerous Sector cards. A method <code>action</code> must be implemented.
 * 
 * @author Paolo Antonini
 *
 */
public abstract class DangerousSectorCard extends Card {

	protected DangerousSectorCard() {
		type = CardNames.DANGEROUS_SECTOR_CARD;
	}

	public abstract void action(Sector s);
}
