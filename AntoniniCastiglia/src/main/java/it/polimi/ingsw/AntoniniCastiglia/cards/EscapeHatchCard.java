package it.polimi.ingsw.AntoniniCastiglia.cards;


/**
 * Every instance of this class is an Escape Hatch card. It contains a variable which indicates the
 * state of the Escape hatch.
 * 
 * @author Paolo Antonini
 *
 */
public class EscapeHatchCard extends Card {

	private boolean useable;

	/**
	 * Public constructor, through which you can set the state of the Escape hatch.
	 * 
	 * @param useable the state of the Escape hatch
	 */
	public EscapeHatchCard(boolean useable) {
		type = CardNames.ESCAPE_HATCH_CARD;
		// TODO name parameter is left undefined?!
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
