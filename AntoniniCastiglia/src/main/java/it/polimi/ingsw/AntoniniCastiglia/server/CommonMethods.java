package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.players.*;

public class CommonMethods {

	private CommonMethods() {
	}

	public static Player getPlayer(String name) {
		/*
		for (Player p : playerList) {
			if (p.toString().contains(name)) {
				return p;
			}
		}
		*/
		return new Human("a","a",0);

	}

}
