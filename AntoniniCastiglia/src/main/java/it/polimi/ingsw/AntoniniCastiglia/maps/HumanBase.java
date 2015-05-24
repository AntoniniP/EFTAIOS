package it.polimi.ingsw.AntoniniCastiglia.maps;

public class HumanBase extends Sector { // TODO SINGLETON!!

	public HumanBase(int x, int y) {
		super(x, y);
		this.setReachable(false);
	}

	public HumanBase(String name) {
		super(name);
		this.setReachable(false);
	}

	@Override
	public String toString() {
		return " H ";
	}

	@Override
	public void action() {
		// TODO nextTurn();		
	}
	
}
