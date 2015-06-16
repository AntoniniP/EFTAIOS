package it.polimi.ingsw.AntoniniCastiglia.server;

import java.util.TimerTask;

/**
 * This class, extension of the class <code>TimeTask<code>, deals with the
 * timeout of timers used to handle Server/Client connections, player's Turn.
 *
 * @author Laura Castiglia
 *
 */
public class MyTimer extends TimerTask {

	private TimerInterface caller;

	/**
	 * Deals with the timeout of timers used to handle Server/Client connections.
	 *
	 * @param caller
	 */
	public MyTimer(TimerInterface caller) {
		this.caller = caller;
	}

	@Override
	public void run() {
		caller.timeout();
	}

}
