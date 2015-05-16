package it.polimi.ingsw.AntoniniCastiglia.maps;

public class AlienBase extends Sector { // TODO SINGLETON?

	public AlienBase(int x, int y) {
		super(x, y);
	}

	public AlienBase(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return " A ";
	}
	
	//After the game begins, no player can move through or end his move in this Sector.

	
}
