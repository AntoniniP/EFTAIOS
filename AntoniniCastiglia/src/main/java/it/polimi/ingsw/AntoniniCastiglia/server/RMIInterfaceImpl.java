package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.RemoteException;

/**
 * Implementation of the interface <code>RMIInterface<code>.
 *
 * @author Laura Castiglia
 *
 */
public class RMIInterfaceImpl implements RMIInterface {

	private Server server;

	public RMIInterfaceImpl(Server server) {
		this.server = server;
	}

	@Override
	public String connect() throws RemoteException {

		synchronized (server) {
			if (!server.isFirstConn()) {
				server.firstConn();
			}
			server.incrementNumPlayer();
		}

		System.out.println("New player connected. " + "gameID: " + server.getGameID()
				+ " playerID: " + (server.getNumPlayer()-1));

		return server.getGameID() + "_" + (server.getNumPlayer() - 1);
	}
}
