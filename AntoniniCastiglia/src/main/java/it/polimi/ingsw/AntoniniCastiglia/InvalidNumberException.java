package it.polimi.ingsw.AntoniniCastiglia;

public class InvalidNumberException extends Exception {

//	private int detail;

	public String toString(){
		return ("Error! There must be between " + Constants.MINPLAYERS +" and " + Constants.MAXPLAYERS + " in the game.");
	}
	
	

}
