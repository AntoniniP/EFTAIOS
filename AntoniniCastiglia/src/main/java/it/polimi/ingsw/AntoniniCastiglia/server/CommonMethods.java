package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.players.*;

public class CommonMethods {

	private CommonMethods() {
	}

	public static Player toPlayer(int playerID, PlayerList playerList) {
		
		return playerList.get(playerID);

	}

}
