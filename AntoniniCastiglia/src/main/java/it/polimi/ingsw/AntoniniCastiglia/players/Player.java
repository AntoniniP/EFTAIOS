package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Player. The class is extended by Alien and Human, which
 * concretely set all parameters. Here we have basic getters and setters for some variables, and a
 * toString method.
 * 
 * @author Paolo Antonini
 *
 */
public abstract class Player {

	private String name;
	private String role;
	private int id;
	private String nature;
	protected int maxMoves;
	protected Sector myBase;
	protected Sector currentSector;
	protected List<Sector> path = new ArrayList<Sector>();
	private Card[] items = new ItemCard[3];
	private boolean shield = false;
	private boolean dead = false;

	/**
	 * Constructor for the class. It sets some parameters, such as the name, role, nature and ID of
	 * the player. It will always be called by the constructors of concrete classes Alien and Human.
	 * 
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param nature player's nature (alien/human)
	 * @param id integer value to identify the player
	 */
	protected Player(String name, String role, String nature, int id) {
		this.name = name;
		this.role = role;
		this.id = id;
		this.nature = nature;
	}

	@Override
	public String toString() {
		return name + "_" + role + "_" + nature + "_" + id + "_" + maxMoves;
	}

	/**
	 * Standard getter for <code>maxMoves</code>.
	 * 
	 * @return maxMoves the maximum number of sectors a player can reach
	 */
	public int getMoves() {
		return maxMoves;
	}

	/**
	 * Standard setter for <code>maxMoves</code>.
	 * 
	 * @param moves the new value for <code>maxMoves</code>
	 */
	public void setMoves(int moves) {
		this.maxMoves = moves;
	}

	/**
	 * Standard getter for <code>shield</code> variable (it states whether the player is protected
	 * from attacks thanks to a Defense Card).
	 * 
	 * @return whether the player is protected from an attack
	 */
	public boolean hasShield() {
		return shield;
	}

	/**
	 * Standard setter for <code>shield</code> variable.
	 * 
	 * @param newShield the new value for the <code>shield</code> variable
	 */
	public void setShield(boolean newShield) {
		this.shield = newShield;
	}

	/**
	 * Standard getter for <code>dead</code> variable.
	 * 
	 * @return whether the player is dead or not
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Standard setter for <code>dead</code> variable.
	 * 
	 * @param dead the new value for <code>dead</code> variable
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * Standard getter for <code>myBase</code>.
	 * 
	 * @return the base of the player
	 */
	public Sector getMyBase() {
		return myBase;
	}

	/**
	 * Standard getter for <code>currentSector</code> variable.
	 * 
	 * @return the current sector a player is in
	 */
	public Sector getCurrentSector() {
		return currentSector;
	}

	/**
	 * Setter for <code>currentSector</code>.
	 * 
	 * @param the new value for <code>currentSector</code> variable
	 */
	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
		path.add(currentSector);
	}

	/**
	 * Getter for the cards a player owns. It's returned as a string, separated by <code>;</code>
	 * character.
	 * 
	 * @return the list a player owns, in form of a string
	 */
	public String getPlayerCards() {
		String toReturn = "";
		for (int i = 0; i < items.length; i++) {
			toReturn = toReturn + items[i] + ";";
		}
		return toReturn;
	}

}