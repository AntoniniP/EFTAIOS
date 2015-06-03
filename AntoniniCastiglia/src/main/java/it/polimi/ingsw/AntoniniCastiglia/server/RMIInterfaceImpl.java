package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import java.rmi.RemoteException;

public class RMIInterfaceImpl implements RMIInterface {

	private Server server;

	public RMIInterfaceImpl(Server server) {
		this.server = server;
	}

	@Override
	public String connect() throws RemoteException {

		System.out.println("Now in server");

		String playerStr;
		Player player = new Human("abc", "abc", 0);
		synchronized (server) {
			// TODO Create players; discuss about method in PlayerList class
			server.addPlayer(player);
			playerStr = player.toString();
			server.incrementNumPlayer();
			if (!server.getFirstConn())
				server.firstConn();
		}
		return playerStr;

	}
}
