package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Silence card (type of Dangerous Sector card).
 * 
 * @author Laura Castiglia
 *
 */
public class DangerousSectorSilence extends DangerousSectorCard {

	public DangerousSectorSilence() {
		super();
		name = CardNames.SILENCE;
	}

	@Override
	public String toString() {
		return "Silence in all sectors.";
	}

	@Override
	public void action(Sector s) {
		// TODO Notify Silence to all players
		// Save records on a list, for instance
	}

}
