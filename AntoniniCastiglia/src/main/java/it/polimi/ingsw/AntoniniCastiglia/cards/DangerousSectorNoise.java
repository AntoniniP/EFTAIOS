package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Noise card (type of Dangerous Sector card). It can be either in
 * your sector or in any sector, and it can also either request that you draw an Item card or not.
 * 
 * @author Laura Castiglia
 *
 */
public class DangerousSectorNoise extends DangerousSectorCard {

	private boolean yourSector;
	private boolean withObject;

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
	
	public boolean isWithObject() {
		return withObject;
	}

	public boolean isYourSector() {
		return yourSector;
	}

	/*
	@Override
	public String toString() {
		String message;
		if (yourSector)
			message = "Noise in your sector!";
		else
			message = "Noise in any sector, please choose!";
		if (withObject)
			message = message + " Pick an Item Card!";
		return message;
	}
*/
	@Override
	public String action(Sector s) {
		return "Noise in "+ s + "sector.";
	}

	@Override
	public String getCard(){
		return super.getCard()+"_"+yourSector+"_"+withObject;
	}
	
}
