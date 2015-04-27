package it.polimi.ingsw.AntoniniCastiglia;

import java.io.IOException;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class Game {

	public static void main(String[] args) throws IOException {
		Player.createAll();
		Table t = new Table();
		System.out.println(t);
		System.out.println("Main again.");
	}
}