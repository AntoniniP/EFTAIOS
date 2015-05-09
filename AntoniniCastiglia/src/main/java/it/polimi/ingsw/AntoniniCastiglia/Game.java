package it.polimi.ingsw.AntoniniCastiglia;

import java.io.IOException;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Players;

public class Game {

	private Game() throws IOException {
		Table t = new Table();
		Players p = new Players();
		t.drawMap();
		System.out.println("Main again.");

		Sector s1 = new Sector("W12");
		Sector s2 = new Sector(5, 3);// f4
		System.out.println(s1 + " adjacent to " + t.adjacent(s1));
		System.out.println(s2 + " adjacent to " + t.adjacent(s2));

	}

	public static void main(String[] args) throws IOException {
		Game g = new Game();

	}

}