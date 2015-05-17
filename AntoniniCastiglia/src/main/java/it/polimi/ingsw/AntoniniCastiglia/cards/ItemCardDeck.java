package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

public class ItemCardDeck extends Deck {
	
	public ItemCardDeck (){
		for(int i=0; i<Constants.ITEMCARDFORTYPE;i++)
			deck.add(new AdrenalineCard());
		for(int i=0; i<Constants.ITEMCARDFORTYPE;i++)
			deck.add(new AttackCard());
		for(int i=0; i<Constants.ITEMCARDFORTYPE;i++)
			deck.add(new DefenseCard());
		for(int i=0; i<Constants.ITEMCARDFORTYPE;i++)
			deck.add(new SedativesCard());
		for(int i=0; i<Constants.ITEMCARDFORTYPE;i++)
			deck.add(new SpotlightCard());
		for(int i=0; i<Constants.ITEMCARDFORTYPE;i++)
			deck.add(new TeleportCard());
	}
}
