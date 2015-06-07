package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.RemoteException;

public class RMIInterfaceImpl implements RMIInterface {

	private Server server;

	public RMIInterfaceImpl(Server server) {
		this.server = server;
	}

	@Override
	public int connect() throws RemoteException {

		synchronized (server) {
			if (!server.isFirstConn()) {
				server.firstConn();
			}
			server.incrementNumPlayer();
		}

		System.out.println("New player connected. " + server.getNumPlayer());

		return server.getNumPlayer() - 1;
	}

	@Override
	public boolean isStarted() throws RemoteException {
		return server.isStarted();
	}

}
