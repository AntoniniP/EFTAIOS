package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;

/**
 * Public interface for Dangerous Sector cards. A method <code>action</code>
 * must be implemented.
 * 
 * @author Paolo Antonini
 *
 */
public interface DangerousSectorCard extends Card {

	public void action(Sector s);
}
