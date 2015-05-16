package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

public class Player {

	// Attributes
	private String name;
	private String role;
	private int id;
	int maxMoves;
	Sector myBase;
	Sector currentSector;
    ArrayList<Sector> path = new ArrayList<Sector>();
    
	// Constructor
	protected Player(String name, String role, int id) {
		this.name = name;
		this.role = role;
		this.id = id;
		//Deck objects = new Deck(objects);
	}

	// Methods
	@Override
	public String toString() {
		return name + " " +role +" "+ id + " " + maxMoves + "\n";
	}

	/**
	 * @return the moves
	 */
	public int getMoves() {
		return maxMoves;
	}

	/**
	 * @param moves
	 *            the moves to set
	 */
	public void setMoves(int moves) {
		this.maxMoves = moves;
	}

	/**
	 * @return the myBase
	 */
	public Sector getMyBase() {
		return myBase;
	}

	/**
	 * @param currentSector the currentSector to set
	 */
	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
		path.add(currentSector);
	}


	
	
	

}