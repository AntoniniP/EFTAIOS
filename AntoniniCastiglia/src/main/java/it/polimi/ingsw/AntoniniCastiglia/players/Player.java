package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

//TODO (here+Players) visibility, createAll, why static??, movementRecord+currentPlace, CLIENT!

public class Player {

	// Attributes
	private String name;
	private int id;
	int moves;
	Sector mySector;
	Sector myBase;

	// Constructor
	protected Player(String name, int id) {
		this.name = name;
		this.id = id;
	}

	// Methods
	@Override
	public String toString() {
		return name + " " + id + " " + moves + "\n";
	}

	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * @param moves
	 *            the moves to set
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

}