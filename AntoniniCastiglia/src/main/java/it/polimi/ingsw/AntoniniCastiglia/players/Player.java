package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

//TODO (here+Players) visibility, why static??, moveRecord, CLIENT!

public class Player {

	// Attributes
	private String name;
	private int id;
	int maxMoves;
	//int objects=Constants.OBJECTNUMBER;
	Sector myBase;
	Sector currentSector;
    ArrayList<Sector> path = new ArrayList<Sector>();
    
	// Constructor
	protected Player(String name, int id) {
		this.name = name;
		this.id = id;
		//Deck objects = new Deck(objects);
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
		path.add(currentSector);
	}


	
	
	

}