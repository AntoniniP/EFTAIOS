package it.polimi.ingsw.AntoniniCastiglia;

import java.io.IOException;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.Players;

public class Game {

	public static void main(String[] args) throws IOException {
		
		Table t = new Table();
		System.out.println("Going to print table");
		t.drawMap();
		
		
		Players.createAll();
		
		
		System.out.println("Main again.");
	}
}