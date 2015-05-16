package it.polimi.ingsw.AntoniniCastiglia.maps;

public class HumanBase extends Sector { // TODO SINGLETON!!

	public HumanBase(int x, int y) {
		super(x, y);
	}

	public HumanBase(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return " H ";
	}
	
	//After the game begins, no player can move through or end his move in this Sector.

	
}
