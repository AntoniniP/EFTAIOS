package it.polimi.ingsw.AntoniniCastiglia.maps;

public class EmptySector extends Sector {

	public EmptySector(int x, int y) {
		super(x, y);
	}

	public EmptySector(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "";
	}

}
