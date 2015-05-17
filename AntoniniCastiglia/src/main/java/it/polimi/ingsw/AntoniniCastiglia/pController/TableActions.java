package it.polimi.ingsw.AntoniniCastiglia.pController;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class TableActions {

	private TableActions() {
	}

	public static void move(Player p, Sector s) {
		p.setCurrentSector(s);

	}

}
