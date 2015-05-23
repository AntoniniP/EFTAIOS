package it.polimi.ingsw.AntoniniCastiglia.cards;

public class EscapeHatchCard implements Card {
	boolean useable;
	


	public EscapeHatchCard(boolean useable){
		this.useable=useable;
	}
	
	
	/**
	 * @return the useable
	 */
	public boolean isUseable() {
		return useable;
	}
	
}
