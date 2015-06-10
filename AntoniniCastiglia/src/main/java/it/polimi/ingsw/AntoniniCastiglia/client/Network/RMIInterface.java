package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import it.polimi.ingsw.AntoniniCastiglia.server.GameEngine;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI implementation of <code>NetworkInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public class RMIInterface implements NetworkInterface {

	private GameEngine remoteEFTAIOS;
	private it.polimi.ingsw.AntoniniCastiglia.server.RMIInterface remoteRMI;

	@Override
	public int connect() throws RemoteException {
		String gameInt = "GameEngine";
		String rmiInt = "RMIinterface";
		Registry registry;
	
		try {
			registry = LocateRegistry.getRegistry(1099);
		} catch (RemoteException e) {
			e.printStackTrace();
			return -1;
		}
	
		try {
			remoteEFTAIOS = (it.polimi.ingsw.AntoniniCastiglia.server.GameEngine) (registry
					.lookup(gameInt));
			remoteRMI = (it.polimi.ingsw.AntoniniCastiglia.server.RMIInterface) (registry
					.lookup(rmiInt));
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			return -1;
		}
	
		try {
			return remoteRMI.connect();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String getMap(int playerID) throws RemoteException {
		return remoteEFTAIOS.getMap();
	}

	@Override
	public String move(int playerID, String sector) throws RemoteException {
		return remoteEFTAIOS.move(playerID, sector);
	}

	@Override
	public String getCards(int playerID) throws RemoteException {
		return remoteEFTAIOS.getCards(playerID);
	}

	@Override
	public void useCard(String card, int playerID) throws RemoteException {
		remoteEFTAIOS.useCard(card, playerID);
	}

	@Override
	public String getAdjacentSectors(int playerID) throws RemoteException {
		return remoteEFTAIOS.getAdjacents(playerID);
	}

	@Override
	public void attack(int playerID) throws RemoteException {
		remoteEFTAIOS.attack(playerID);
	}

	@Override
	public boolean isEnded() throws RemoteException {
		return remoteEFTAIOS.isEnded();
	}

	@Override
	public String getWinner() throws RemoteException {
		// return eftaios.getWinner();
		return "abc";
	}

	@Override
	public boolean isStarted() throws RemoteException {
		return remoteRMI.isStarted();
	}

	@Override
	public String getPlayer(int playerID) throws RemoteException {
		return remoteEFTAIOS.getPlayerString(playerID);
	}

	@Override
	public String drawDangerousSectorCard() throws RemoteException {
		return remoteEFTAIOS.drawCard("DS");
	}

	@Override
	public String drawEscapeHatchCard() throws RemoteException {
		return remoteEFTAIOS.drawCard("EH");
	}

	@Override
	public String drawItemCard() throws RemoteException {
		return remoteEFTAIOS.drawCard("IC");
	}
	
	@Override
	public String whereYouAre(int playerID) throws RemoteException {
		//return remoteEFTAIOS.whereyouAre(playerID);
		return "ABC";
	}
	

}
