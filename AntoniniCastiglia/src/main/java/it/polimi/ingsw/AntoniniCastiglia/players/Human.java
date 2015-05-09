package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

public class Human extends Player {

	boolean beenEaten = false;

	public Human(String name, int id) {
		super(name, id);
		maxMoves = 1;
		myBase = Table.getHumanBase();
		currentSector = myBase;
	}

}
