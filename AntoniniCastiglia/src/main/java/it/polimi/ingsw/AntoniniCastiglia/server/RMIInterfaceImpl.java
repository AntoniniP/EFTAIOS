package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.RemoteException;

public class RMIInterfaceImpl implements RMIInterface {

	private Server server;

	public RMIInterfaceImpl(Server server) {
		this.server = server;
	}

	@Override
	public int connect() throws RemoteException {

		System.out.print("Connecting new player... ");
		/*
		synchronized (server) {
			if (!server.isFirstConn()) {
				server.firstConn();
			}
			
		}
		*/
		server.incrementNumPlayer();
		System.out.println("New player connected. " + server.getNumPlayer());

		return server.getNumPlayer();
	}
	
	@Override
	public boolean isStarted()throws RemoteException{
		return server.isStarted();
	}

	/*
	 * public String playerList(n){ int n=getNumPlayer() re-write the method in playerList class
	 * using server.addPlayer(player) return playerList (created in PlayerList constructor).toString
	 */
}
