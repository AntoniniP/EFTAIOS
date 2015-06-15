package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * The class provides a proper Escape Hatch card deck.
 *
 * @author Paolo Antonini
 *
 */
public class EscapeHatchDeck extends Deck {

	/**
	 * Public constructor for the class. Creates the deck according to EFTAIOS rules.
	 */
	public EscapeHatchDeck() {
		for (int i = 0; i < CardsConstants.GREEN_ESCAPE_HATCH; i++) {
			deck.add(new EscapeHatchCard(true));
		}
		for (int i = 0; i < CardsConstants.RED_ESCAPE_HATCH; i++) {
			deck.add(new EscapeHatchCard(false));
		}
		this.shuffleDeck();
	}

}
