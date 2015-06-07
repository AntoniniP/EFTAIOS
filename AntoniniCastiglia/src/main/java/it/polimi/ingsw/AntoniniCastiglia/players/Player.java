package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Every instance of this class is a Player. The class is extended by Alien and Human, which
 * concretely set all parameters. Here we have basic getters and setters for some variables, and a
 * toString method (@Override).
 * 
 * @author Paolo Antonini
 *
 */
public class Player {

	private String name;
	private String role;
	private int id;
	private String nature;
	protected int maxMoves;
	protected Sector myBase;
	protected Sector currentSector;
	protected ArrayList<Sector> path = new ArrayList<Sector>();
	private Card[] items = new ItemCard[3];
	private boolean shield = false;
	private boolean dead=false;


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
	 * Standard getter for maxMoves.
	 * 
	 * @return maxMoves
	 */
	public int getMoves() {
		return maxMoves;
	}

	public boolean hasShield(){
		return shield;
	}
	
	public void setShield(boolean newShield){
		this.shield=newShield;
	}
	
	
	public boolean isDead() {
		return dead;
	}

	
	public void setDead(boolean dead) {
		this.dead = dead;
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

	public Sector getCurrentSector() {
		return currentSector;
	}

	public int getHops() {
		return maxMoves;
	}

	

	public String getPlayerCards() {
		String toReturn="";
		for(int i=0;i<items.length;i++){
			toReturn=toReturn + items[i]+";";
		}
		return toReturn;
	}

}