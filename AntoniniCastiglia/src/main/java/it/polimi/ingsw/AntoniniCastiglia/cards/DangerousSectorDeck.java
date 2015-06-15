package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Deck for Dangerous Sector cards.
 *
 * @author Laura Castiglia
 *
 */
public class DangerousSectorDeck extends Deck {

	/**
	 * Constructor for the deck of dangerous sector cards. The deck is created and shuffled.
	 */
	public DangerousSectorDeck() {

		// noise in your sector, with object
		for (int i = 0; i < CardsConstants.NUM_NOISE_YOURSECTOR_WITHOBJECT; i++) {
			deck.add(new DangerousSectorNoise(true, true));
		}

		// noise in any sector, with object
		for (int i = 0; i < CardsConstants.NUM_NOISE_ANYSECTOR_WITHOBJECT; i++) {
			deck.add(new DangerousSectorNoise(false, true));
		}

		// noise in your sector, without object
		for (int i = 0; i < CardsConstants.NUM_NOISE_YOURSECTOR_WITHOUTOBJECT; i++) {
			deck.add(new DangerousSectorNoise(true, false));
		}

		// noise in any sector, without object
		for (int i = 0; i < CardsConstants.NUM_NOISE_ANYSECTOR_WITHOUTOBJECT; i++) {
			deck.add(new DangerousSectorNoise(false, false));
		}

		// silence
		for (int i = 0; i < CardsConstants.NUM_SILENCE; i++) {
			deck.add(new DangerousSectorSilence());
		}

		this.shuffleDeck();
	}

}
