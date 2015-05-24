package it.polimi.ingsw.AntoniniCastiglia.maps;

public class SecureSector extends Sector {

	public SecureSector(int x, int y) {
		super(x, y);
		this.setReachable(true);
	}

	public SecureSector(String name) {
		super(name);
		this.setReachable(true);
	}

	@Override
	public void action() {
		// TODO nextTurn();
	}

}
