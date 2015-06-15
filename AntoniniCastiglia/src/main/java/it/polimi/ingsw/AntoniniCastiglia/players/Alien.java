package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

/**
 * Every instance of this class is an Alien player, with all parameters set.
 * 
 * @author Paolo Antonini
 *
 */
public class Alien extends Player {

	/**
	 * Constructor for Alien player. Sets all fundamental parameters. Some are given to the
	 * constructor of the superclass, some are set here.
	 * 
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param c character to identify the player's nature (alien/human)
	 */
	public Alien(String name, String role) {
		super(name, role, "A");
		maxMoves = 2;
		myBase = Table.getAlienBase();
		currentSector = myBase;
		canAttack=true;
	}
	
	@Override
	public boolean canUseCards(){
		return false;
	}

}
