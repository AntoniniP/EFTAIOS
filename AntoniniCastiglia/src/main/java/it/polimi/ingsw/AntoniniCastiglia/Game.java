package it.polimi.ingsw.AntoniniCastiglia;

import java.io.IOException;
import java.util.List;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.pController.PlayerActions;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;

public class Game {

	private Game() throws IOException {
		Table t = new Table();
		//PlayerList p = PlayerList.getPlayerList(PlayerActions.howManyPlayers());
		PlayerList p = new PlayerList(PlayerActions.howManyPlayers());
		System.out.println(p);
		
		// t.drawMap();

		Sector s = new Sector(5, 3);// f4
		System.out.println(s + " adjacent to " + t.adjacent(s, 2));

	}

	public static void main(String[] args) throws IOException {
		Game g = new Game();

	}

}