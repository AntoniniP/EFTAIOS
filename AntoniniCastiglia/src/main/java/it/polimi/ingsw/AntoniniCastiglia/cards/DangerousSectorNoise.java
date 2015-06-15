package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Every instance of this class is a Noise card (type of Dangerous Sector card). It can be either in
 * your sector or in any sector, and it can also either request that you draw an Item card or not.
 *
 * @author Laura Castiglia
 *
 */
public class DangerousSectorNoise extends DangerousSectorCard {

	/**
	 * Constructor for the class. You are able to set whether the noise is in your sector or in any
	 * other sector (<code>yourSector</code> parameter), and also whether you have to draw an Item
	 * card or not (<code>withObject</code> parameter).
	 *
	 * @param yourSector
	 * @param withObject
	 */
	public DangerousSectorNoise(boolean yourSector, boolean withObject) {
		super();
		name = CardsConstants.NOISE;
		this.yourSector = yourSector;
		this.withObject = withObject;
	}

}
