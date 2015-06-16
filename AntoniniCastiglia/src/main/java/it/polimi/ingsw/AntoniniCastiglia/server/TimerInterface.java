package it.polimi.ingsw.AntoniniCastiglia.server;

/**
 * This interface provides the method that handles the timer timeout where and when required.
 *
 * @author Laura Castiglia
 *
 */

public interface TimerInterface {

	/**
	 * Provides a timeout method for the timer. It's implemented both in Server and in GameHandler
	 * classes.
	 */
	public void timeout();
}
