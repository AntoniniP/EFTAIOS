package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

public class DangerousSectorDeck extends Deck{
	
	public DangerousSectorDeck (){ //Constructor; creating the decks
		for(int i=0;i<Constants.WITHOBJECT;i++)
			deck.add(new Noise(true, true)); //yourSector + Object
		for(int i=0;i<Constants.WITHOBJECT;i++)
			deck.add(new Noise(false, true)); //anySector + Object
		for(int i=0;i<Constants.RUMORNOOBJECT;i++)
			deck.add(new Noise(true, false)); //yourSector
		for(int i=0;i<Constants.RUMORNOOBJECT;i++)
			deck.add(new Noise(false, false)); //anySector
		for(int i=0;i<Constants.SILENCE;i++)
			deck.add(new Silence());
	}
		
}
	
