package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

/**
 * Every instance of this class is a Human player, with all parameters set.
 * 
 * @author Paolo Antonini
 *
 */
public class Alien extends Player {


	/**
	 * Constructor for Alien player. Sets all fundamental parameters. Some are
	 * given to the constructor of the superclass, some are set here.
	 * 
	 * @param name
	 * @param role
	 * @param id
	 */
	public Alien(String name, String role, int id) {
		super(name, role, "A", id);
		maxMoves = 2;
		myBase = Table.getAlienBase();
		currentSector = myBase; path.add(myBase); // TODO use a move action!
	}

}
