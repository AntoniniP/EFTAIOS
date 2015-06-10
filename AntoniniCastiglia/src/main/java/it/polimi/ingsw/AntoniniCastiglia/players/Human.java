package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

/**
 * Every instance of this class is a Human player, with all parameters set.
 * 
 * @author Paolo Antonini
 *
 */
public class Human extends Player {
	private boolean shield;
	
	/**
	 * Constructor for Human player. Sets all fundamental parameters. Some are
	 * given to the constructor of the superclass, some are set here.
	 * 
	 * @param name
	 * @param role
	 * @param id
	 */
	public Human(String name, String role, int id) {
		super(name, role, "H", id);
		maxMoves = 1;
		myBase = Table.getHumanBase();
		currentSector = myBase; path.add(myBase); // TODO use a move action!
	}
	
	public boolean hasShield(){
		return shield;
	}
	
	public void setShield(boolean newShield){
		this.shield=newShield;
	}
}
