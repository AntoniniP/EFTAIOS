package it.polimi.ingsw.AntoniniCastiglia.cards;


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


	
}
