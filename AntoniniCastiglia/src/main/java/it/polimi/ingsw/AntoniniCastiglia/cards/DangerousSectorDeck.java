package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

/**
 * Deck for Dangerous Sector cards. 
 * 
 * @author Laura Castiglia
 *
 */
public class DangerousSectorDeck extends Deck {

	/**
	 * Constructor for the deck of dangerous sector cards. The deck is created
	 * and shuffled.
	 */
	public DangerousSectorDeck() {

		// noise in your sector, with object
		for (int i = 0; i < Constants.NOISE_YOURSECTOR_WITHOBJECT; i++) {
			deck.add(new DangerousSectorNoise(true, true));
		}

		// noise in any sector, with object
		for (int i = 0; i < Constants.NOISE_ANYSECTOR_WITHOBJECT; i++) {
			deck.add(new DangerousSectorNoise(false, true));
		}

		// noise in your sector, without object
		for (int i = 0; i < Constants.NOISE_YOURSECTOR_WITHOUTOBJECT; i++) {
			deck.add(new DangerousSectorNoise(true, false));
		}

		// noise in any sector, without object
		for (int i = 0; i < Constants.NOISE_ANYSECTOR_WITHOUTOBJECT; i++) {
			deck.add(new DangerousSectorNoise(false, false));
		}

		// silence
		for (int i = 0; i < Constants.SILENCE; i++) {
			deck.add(new DangerousSectorSilence());
		}

		shuffleDeck();
	}
	/*
	 * public static void main(String[] args) { Deck d = new
	 * DangerousSectorDeck(); for (int i = 0; i <
	 * (Constants.NOISE_ANYSECTOR_WITHOBJECT +
	 * Constants.NOISE_ANYSECTOR_WITHOUTOBJECT +
	 * Constants.NOISE_YOURSECTOR_WITHOBJECT +
	 * Constants.NOISE_YOURSECTOR_WITHOUTOBJECT + Constants.SILENCE); i++) {
	 * System.out.println(i + 1 + " " + d.drawCard()); } }
	 */
}
