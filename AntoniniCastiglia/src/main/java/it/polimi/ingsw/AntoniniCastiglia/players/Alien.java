package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

public class Alien extends Player {

	boolean hasEaten = false;

	public Alien(String name, String role, int id) {
		super(name, role, id);
		maxMoves = 2;
		myBase = Table.getAlienBase();
		currentSector = myBase;
		path.add(myBase);
	}

}
