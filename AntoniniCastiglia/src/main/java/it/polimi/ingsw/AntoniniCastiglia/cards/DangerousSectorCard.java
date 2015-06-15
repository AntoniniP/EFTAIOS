package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Abstract class for Dangerous Sector cards. <code>toString</code> method is overridden. Some
 * useful getters are provided too.
 *
 * @author Laura Castiglia
 *
 */
public abstract class DangerousSectorCard extends Card {

	protected boolean yourSector = false;
	protected boolean withObject = false;

	/**
	 * Constructor for the class. The <code>type</code> parameter is set.
	 */
	protected DangerousSectorCard() {
		type = CardsConstants.DANGEROUS_SECTOR_CARD;
	}

	@Override
	public String toString() {
		return super.toString() + "_" + yourSector + "_" + withObject;
	}

	/**
	 * Returns whether the noise must be in the sector of the player who draws the card.
	 * 
	 * @return <code>yourSector</code> variable
	 */
	public boolean getYourSector() {
		return yourSector;
	}

	/**
	 * Returns whether the player must draw an Item card or not.
	 * 
	 * @return <code>withObject</code> variable
	 */
	public boolean getWithObject() {
		return withObject;
	}

}
