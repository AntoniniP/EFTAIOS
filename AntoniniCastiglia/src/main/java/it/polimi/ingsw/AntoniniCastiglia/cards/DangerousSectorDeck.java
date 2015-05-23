package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

public class DangerousSectorDeck extends Deck{
	
	public DangerousSectorDeck (){ //Constructor; creating the decks
		for(int i=0;i<Constants.WITHOBJECT;i++)
			deck.add(new NoiseCard(true, true)); //yourSector + Object
		for(int i=0;i<Constants.WITHOBJECT;i++)
			deck.add(new NoiseCard(false, true)); //anySector + Object
		for(int i=0;i<Constants.NOISEnoOBJECT;i++)
			deck.add(new NoiseCard(true, false)); //yourSector
		for(int i=0;i<Constants.NOISEnoOBJECT;i++)
			deck.add(new NoiseCard(false, false)); //anySector
		for(int i=0;i<Constants.SILENCE;i++)
			deck.add(new Silence());
	}
		
}
	
