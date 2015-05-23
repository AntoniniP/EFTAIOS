package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

public class Silence implements DangerousSectorCard {
	
	public Silence() { //Constructor
		}

	@Override
	public String toString() {
		return "Silence!";
	}
	@Override
	public void action(Sector s) {
		//TODO Notify Silence to all players
		//Save records on a list, for instance
	}
	
}
