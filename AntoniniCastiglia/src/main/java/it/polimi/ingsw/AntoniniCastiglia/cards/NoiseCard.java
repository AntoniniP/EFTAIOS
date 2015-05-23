package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * @author Laura Castiglia
 *
 */
public class NoiseCard implements DangerousSectorCard {

	private boolean yourSector;
	private boolean withObject;

	/**
	 * Constructor for <code>NoiseCard</code>. You are able to set whether the
	 * noise is in your sector or in any other sector (<code>yourSector</code>
	 * parameter), and also whether you have to draw an <code>ItemCard</code> or
	 * not (<code>withObject</code> parameter).
	 * 
	 * @param yourSector
	 * @param withObject
	 */
	public NoiseCard(boolean yourSector, boolean withObject) {
		this.yourSector = yourSector;
		this.withObject = withObject;
	}

	@Override
	public String toString() {
		String message;
		if (yourSector == true)
			message = "Noise in your sector!";
		else
			message = "Noise in any sector, please choose!";
		if (withObject == true)
			message = message + " Pick an Item Card!";
		return message;
	}

	@Override
	public void action(Sector s) {

		// TODO Notify noise to all players
		// Save records on a list, for instance (one list for each player)
	}


}
