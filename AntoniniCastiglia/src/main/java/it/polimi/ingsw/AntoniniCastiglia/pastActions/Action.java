package it.polimi.ingsw.AntoniniCastiglia.pastActions;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class Action {
	
	private Player p;
	private String message;
	
	//The idea is of creating a message that looks quite like this: 
	//Player Tizio Caio (we can also use the number instead of the name) did this Action.
	
	public Action(Player p, String message) {
		this.setP(p);
		this.message = message;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
	
	
}
