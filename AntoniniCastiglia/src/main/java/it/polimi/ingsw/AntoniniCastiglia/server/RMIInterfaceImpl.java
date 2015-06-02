package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.RemoteException;

public class RMIInterfaceImpl implements RMIInterface {
	private Server server;
	
	public RMIInterfaceImpl(Server server){
		this.server=server;
	}
	@Override
	public String connect() throws RemoteException {
	    String playerStr;
        Player player=null; // cancel the NULL
        synchronized(server) {
            //TODO Create players; discuss about method in PlayerList class
            server.addPlayer(player);
            playerStr = player.toString();
            server.incrementNumPlayer();
            if(!server.getFirstConn())
                server.firstConn();
        }
        return playerStr;
    }
}