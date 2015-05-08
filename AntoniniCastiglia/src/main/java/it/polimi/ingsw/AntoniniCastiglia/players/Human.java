package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

public class Human extends Player {

	boolean beenEaten = false;

	public Human(String name, int id) {
		super(name, id);
		moves = 1;
	//	myBase = new Sector(Table.humanBase.getX(), Table.humanBase.getY());
		mySector = myBase;
	}

}
