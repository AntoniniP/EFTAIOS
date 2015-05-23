package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

public class EscapeHatchDeck extends Deck {
	
	public EscapeHatchDeck(){
		for (int i =0 ; i < Constants.GREEN_ESCAPE_HATCH; i++){
			deck.add(new EscapeHatchCard(true));
		}
		for (int i =0 ; i < Constants.RED_ESCAPE_HATCH; i++){
			deck.add(new EscapeHatchCard(false));
		}
		shuffleDeck();
	}
	
	
/*	
	public static void main(String[] args) {
		Deck d = new EscapeHatchDeck();
		for (int i = 0; i < (Constants.GREEN_ESCAPE_HATCH + Constants.RED_ESCAPE_HATCH); i++) {
			Card c = d.drawCard();
			System.out.println(i + 1 + " " + ((EscapeHatchCard)c).isUseable());
		}
	}
*/
}
