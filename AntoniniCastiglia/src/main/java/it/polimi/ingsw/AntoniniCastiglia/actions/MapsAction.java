package it.polimi.ingsw.AntoniniCastiglia.actions;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class MapsAction {
	
	public MapsAction() {
	}

	public void nextMove(Player p, String name) {
		Sector newCurrentS = new Sector(name);
		p.setCurrentSector(newCurrentS);
	}

}
