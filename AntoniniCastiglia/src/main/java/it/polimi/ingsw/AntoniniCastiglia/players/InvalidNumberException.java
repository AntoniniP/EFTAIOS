package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

public class InvalidNumberException extends Exception {

	@Override
	public String toString() {
		return "Error! There must be between " + Constants.MINPLAYERS + " and "
				+ Constants.MAXPLAYERS + " players in the game. Retry!";
	}

}
