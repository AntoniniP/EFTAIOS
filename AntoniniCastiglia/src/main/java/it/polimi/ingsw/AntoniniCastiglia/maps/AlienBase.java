package it.polimi.ingsw.AntoniniCastiglia.maps;

public class AlienBase extends Sector { // TODO SINGLETON?

	public AlienBase(int x, int y) {
		super(x, y);
		this.setReachable(false);
	}

	public AlienBase(String name) {
		super(name);
		this.setReachable(false);
	}

	@Override
	public String toString() {
		return " A ";
	}

	@Override
	public void action() {
		// TODO nextTurn();		
	}
	
}
