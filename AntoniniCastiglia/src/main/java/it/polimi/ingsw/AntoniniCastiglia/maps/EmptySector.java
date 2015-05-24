package it.polimi.ingsw.AntoniniCastiglia.maps;

public class EmptySector extends Sector {

	public EmptySector(int x, int y) {
		super(x, y);
		this.setReachable(false);
	}

	public EmptySector(String name) {
		super(name);
		this.setReachable(false);
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public void action() {
		;
	}

}
