package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

//TODO (here+Players) visibility, why static??, moveRecord, CLIENT!

public class Player {

	// Attributes
	private String name;
	private int id;
	int maxMoves;
	Sector myBase;
	Sector currentSector;

	// Constructor
	protected Player(String name, int id) {
		this.name = name;
		this.id = id;
	}

	// Methods
	@Override
	public String toString() {
		return name + " " + id + " " + maxMoves + "\n";
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
	}
	
	

}