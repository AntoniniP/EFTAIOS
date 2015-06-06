package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Every instance of this class is an Escape Hatch card. The <code>usable</code> variable (which is
 * set through the class constructor) states the state of the Escape hatch.
 * 
 * @author Paolo Antonini
 *
 */
public class EscapeHatchCard extends Card {

	boolean useable;

	private EscapeHatchCard() {
		type = CardNames.ESCAPE_HATCH_CARD;
	}

	public EscapeHatchCard(boolean useable) {
		this();
		this.useable = useable;
	}

	/**
	 * Standard getter for <code>usable</code> variable.
	 * 
	 * @return the state of the Escape Hatch
	 */
	public boolean isUseable() {
		return useable;
	}

}
