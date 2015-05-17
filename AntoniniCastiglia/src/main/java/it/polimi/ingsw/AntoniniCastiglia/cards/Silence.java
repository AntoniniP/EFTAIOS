package it.polimi.ingsw.AntoniniCastiglia.cards;

public class Silence implements DangerousSectorCard {
	
	public Silence() { //Constructor
		}

	@Override
	public String toString() {
		return "Silence!";
	}
	@Override
	public void action() {
		//TODO Notify Silence to all players
		//Save records on a list, for instance
	}
	
}
