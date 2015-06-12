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
	private boolean escaped;
	
	/**
	 * Constructor for Human player. Sets all fundamental parameters. Some are given to the
	 * constructor of the superclass, some are set here.
	 * 
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param c character to identify the player's nature (alien/human)
	 */
	public Human(String name, String role, int id) {
		super(name, role, "H", id);
		maxMoves = 1;
		myBase = Table.getHumanBase();
		currentSector = myBase;
	}
	
	public boolean hasShield(){
		return shield;
	}
	
	public void setShield(boolean newShield){
		this.shield=newShield;
	}
	public void setEscaped(boolean escaped){
		this.escaped = escaped;
	}
	public boolean isEscaped(){
		return escaped;
	}
		
}
