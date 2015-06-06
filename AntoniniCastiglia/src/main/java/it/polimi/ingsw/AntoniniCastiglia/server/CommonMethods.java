package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.players.*;

public class CommonMethods {

	private CommonMethods() {
	}

	public static Player getPlayer(String name) {
		/*private Server server;
		for (Player p : server.getPlayerList) {  //TODO understand why I'm not allowed to call the variable server as private
			if (p.toString().contains(name)) { //getPlayerlist is to be implemented
				return p;
			}
		}
		*/
		return new Human("a","a",0);

	}

}
