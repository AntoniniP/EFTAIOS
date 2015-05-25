package it.polimi.ingsw.AntoniniCastiglia.pastActions;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class TableActions extends Action {

	public TableActions(Player p, String message) {
		super(p, message);
	}
	
	//A move has been made
	public String toString() { //remote
		return "The player" + getP() +"has moved";
	}

	public static void move(Player p, Sector s) { //remote
		p.setCurrentSector(s);
	}

}
