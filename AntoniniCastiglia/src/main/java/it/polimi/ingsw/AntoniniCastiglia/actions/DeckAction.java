package it.polimi.ingsw.AntoniniCastiglia.actions;

import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.cards.*;
import it.polimi.ingsw.AntoniniCastiglia.*;

public class DeckAction { 
	
	public DeckAction(){  // Constructor
	}

	
	
	public void shuffleDeck(){ //shuffles cards when all of them have already been picked
		
		for(int i=0;i<10;i++){
			int m= (int)(Math.random()*Constants.ITEMCARDS);
			
		}
		
		for(int i=0;i<10;i++){
			int n= (int)(Math.random()*Constants.DANGERSECTCARDS);
		}
		
	}
		
}
