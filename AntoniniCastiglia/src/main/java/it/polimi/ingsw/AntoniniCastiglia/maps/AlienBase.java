package it.polimi.ingsw.AntoniniCastiglia.maps;

public class AlienBase extends Sector { // TODO SINGLETON?

	public AlienBase(int x, int y) {
		super(x, y);
	}

	public AlienBase(String name) {
		super(name);
	}

	@Override
	public String toString(){
		return " A ";
	}
}
