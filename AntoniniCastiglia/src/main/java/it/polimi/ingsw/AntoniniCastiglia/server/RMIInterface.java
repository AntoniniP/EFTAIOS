package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.*;

/**
 * this remote interface provides the method useful for its implementation, <code>RMIInterfaceImpl<code>.
 * 
 * @author Laura Castiglia
 *
 */
public interface RMIInterface extends Remote { // Methods dealing with connection's stuff

	
	/**
	 * deals with connections got from a client, associates the connected
	 * player to a game and keeps trace of the number of connected players.
	 * 
	 * @return the game's identifier (gameID) and the number of connected player (at that time)
	 * @throws RemoteException
	 */
	public String connect() throws RemoteException;

}
