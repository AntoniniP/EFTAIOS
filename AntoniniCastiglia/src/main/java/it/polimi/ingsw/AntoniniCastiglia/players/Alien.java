package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

public class Alien extends Player {

	boolean hasEaten = false;

	public Alien(String name, int id) {
		super(name, id);
		maxMoves = 2;
		myBase = Table.getAlienBase();
		currentSector = myBase;
	}

}
