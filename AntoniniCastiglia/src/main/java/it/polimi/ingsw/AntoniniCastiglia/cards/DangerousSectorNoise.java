package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Noise card (type of Dangerous Sector card). It can be either in
 * your sector or in any sector, and it can also either request that you draw a card or not.
 * 
 * @author Laura Castiglia
 *
 */
public class DangerousSectorNoise extends DangerousSectorCard {

	private boolean yourSector;
	private boolean withObject;

	private DangerousSectorNoise() {
		super();
		name = CardNames.NOISE;
	}

	/**
	 * Constructor for <code>NoiseCard</code>. You are able to set whether the noise is in your
	 * sector or in any other sector (<code>yourSector</code> parameter), and also whether you have
	 * to draw an <code>ItemCard</code> or not (<code>withObject</code> parameter).
	 * 
	 * @param yourSector
	 * @param withObject
	 */
	public DangerousSectorNoise(boolean yourSector, boolean withObject) {
		this();
		this.yourSector = yourSector;
		this.withObject = withObject;
	}

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

	@Override
	public void action(Sector s) {

		// TODO Notify noise to all players
		// Save records on a list, for instance (one list for each player)
	}

}
