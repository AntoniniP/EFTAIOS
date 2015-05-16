package it.polimi.ingsw.AntoniniCastiglia.maps;

public class EscapeHatch extends Sector {

	public EscapeHatch(int x, int y) {
		super(x, y);
	}

	public EscapeHatch(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return " E ";
	}

	//Only Human players can end their move on an Escape Hatch Sector.
	
}
