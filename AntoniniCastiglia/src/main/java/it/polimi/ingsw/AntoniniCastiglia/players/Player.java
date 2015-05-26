package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Player. The class is extended by Alien and
 * Human, which concretely set all parameters. Here we have basic getters and
 * setters for some variables, and a toString method (@Override).
 * 
 * @author Paolo Antonini
 *
 */
public class Player {

	private String name;
	private String role;
	private int id;
	protected int maxMoves;
	protected Sector myBase;
	protected Sector currentSector;
	protected ArrayList<Sector> path = new ArrayList<Sector>();
	private Card[] items = new ItemCard[3];
	
	boolean shield = false;
	boolean attack = false;

	protected Player(String name, String role, int id) {
		this.name = name;
		this.role = role;
		this.id = id;
	}

	@Override
	public String toString() {
		return name + ", " + role + ", " + id + ", " + maxMoves;
	}

	/**
	 * Standard getter for maxMoves.
	 * 
	 * @return maxMoves
	 */
	public int getMoves() {
		return maxMoves;
	}

	/**
	 * Standard setter for maxMoves.
	 * 
	 * @param moves
	 */
	public void setMoves(int moves) {
		this.maxMoves = moves;
	}

	/**
	 * Standard getter for myBase.
	 * 
	 * @return the myBase
	 */
	public Sector getMyBase() {
		return myBase;
	}

	/**
	 * Setter for currentSector.
	 * 
	 * @param currentSector
	 */
	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
		path.add(currentSector);
	}

	public Sector getCurrenSector() {
		// TODO Auto-generated method stub
		return currentSector;
	}

	public int getHops() {
		// TODO Auto-generated method stub
		return maxMoves;
	}

}