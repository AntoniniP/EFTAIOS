package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

/**
 * Every instance of this class is a Human player, with all parameters set.
 *
 * @author Paolo Antonini
 *
 */
public class Human extends Player {

	private boolean shield = false;
	private boolean escaped = false;

	/**
	 * Constructor for Human player. Sets all fundamental parameters. Some are given to the
	 * constructor of the superclass, some are set here.
	 *
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param c character to identify the player's nature (alien/human)
	 */
	public Human(String name, String role) {
		super(name, role, "H");
		maxMoves = 1;
		myBase = Table.getHumanBase();
		currentSector = myBase;
		canAttack = false;
	}

	/**
	 * Getter for shield variable.
	 * 
	 * @return whether the player has a Defense card
	 */
	public boolean hasShield() {
		return shield;
	}

	/**
	 * Setter for shield variable.
	 * 
	 * @param newShield
	 */
	public void setShield(boolean newShield) {
		shield = newShield;
	}

	/**
	 * Getter for escaped variable.
	 * 
	 * @return whether the player escaped from the ship
	 */
	public boolean isEscaped() {
		return escaped;
	}

	/**
	 * Setter for escaped variable.
	 * 
	 * @param escaped
	 */
	public void setEscaped(boolean escaped) {
		this.escaped = escaped;
	}

}
