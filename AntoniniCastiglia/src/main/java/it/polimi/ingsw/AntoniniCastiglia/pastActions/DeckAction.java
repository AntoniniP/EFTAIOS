package it.polimi.ingsw.AntoniniCastiglia.pastActions;

import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.players.*;
import it.polimi.ingsw.AntoniniCastiglia.cards.*;
import it.polimi.ingsw.AntoniniCastiglia.*;

public class DeckAction extends Action { 
	
	public DeckAction(Player p, String message){ 
		super(p, message);
	}
	//To be checked!
	public String toString() {
		return "The player" + getP() +"has drawn a card";
	}
}	
