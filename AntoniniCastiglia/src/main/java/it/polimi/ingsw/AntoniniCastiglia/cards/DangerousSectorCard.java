package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Abstract class for Dangerous Sector cards. A method <code>action</code> is provided.
 * 
 * @author Laura Castiglia
 *
 */
public abstract class DangerousSectorCard extends Card {

	/**
	 * Constructor for the class. The <code>type</code> parameter is set.
	 */
	protected DangerousSectorCard() {
		type = CardNames.DANGEROUS_SECTOR_CARD;
	}

	// TODO write JavaDoc, or delete it!
	public abstract void action(Sector s);
}
