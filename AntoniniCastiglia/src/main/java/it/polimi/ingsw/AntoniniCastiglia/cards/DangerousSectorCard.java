package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Abstract class for Dangerous Sector cards. A method <code>action</code> is provided.
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

	public boolean getYourSector() {
		return yourSector;
	}

	public boolean getWithObject() {
		return withObject;
	}

}
