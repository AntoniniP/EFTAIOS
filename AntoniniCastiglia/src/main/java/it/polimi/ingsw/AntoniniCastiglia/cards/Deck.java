package it.polimi.ingsw.AntoniniCastiglia.cards;
 
import java.util.ArrayList;
import java.util.List;

public abstract class Deck {
	
	protected List<Card> deck = new ArrayList <Card> ();
	protected List<Card> discardedDeck = new ArrayList <Card>(); 
	
	public void shuffleDeck(){ //shuffles cards when all of them have already been picked
		int m, n;
		Card temp;
		for(int i=0;i<50;i++){ //50--> statistically high enough to have a great shuffle
			m= (int)(Math.random()*(deck.size()-2));
			n= (int)(Math.random()*(deck.size()-2));
			temp=deck.get(m);
			deck.set(m, deck.get(n));
			deck.set(n, temp);
		}
	}
	
	private void reshuffleDeck() {
		deck.addAll(discardedDeck);
		shuffleDeck();
		discardedDeck = new ArrayList<Card>();//"azzero" the discarded's deck
	}
	
	public Card drawCard(){ //Not a remote method!!!!!!
		if(deck.size()==0) 
			reshuffleDeck();
		if(deck.size()==0)
			//Client will need to deal with "null"
			return null;//The Deck may be empty after reshuffle; notify the end of the deck
		return deck.get(deck.size()-1);
	}
	
	public void discardCard(Card c){
		discardedDeck.add(c);
	}
	
}