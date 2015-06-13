package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Silence card (type of Dangerous Sector card).
 * 
 * @author Laura Castiglia
 *
 */
public class DangerousSectorSilence extends DangerousSectorCard {

	/**
	 * Public constructor for the class. It sets the <code>type</code> parameter by calling the
	 * superclass constructor, and then its <code>name</code>.
	 */
	public DangerousSectorSilence() {
		super();
		name = CardsConstants.SILENCE;
	}
/*
	@Override
	public String toString() {
		return "Silence in all sectors.";
	}
*/
	@Override
	public String action(Sector s) {
		return "Silence in all sectors.";
	}

}
