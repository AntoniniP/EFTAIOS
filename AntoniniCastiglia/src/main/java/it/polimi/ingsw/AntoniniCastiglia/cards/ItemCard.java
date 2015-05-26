package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

/**
 * Public interface for an Item card. It provides a method to perform the
 * actions expected from the card.
 * 
 * @author Paolo Antonini
 *
 */
public interface ItemCard extends Card {

	public abstract void action(Player p);

}
