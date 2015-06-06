package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import it.polimi.ingsw.AntoniniCastiglia.client.Client;
import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.server.GameEngine;
import it.polimi.ingsw.AntoniniCastiglia.server.GameEngineImpl;

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
	public String getMap(int playerID) throws RemoteException {
		return remoteEFTAIOS.getMap();
	}

	@Override
	public String move(String player, String sector) throws RemoteException {
		return remoteEFTAIOS.move(player, sector);
	}

	@Override
	public boolean isEnded() throws RemoteException {
		// return eftaios.isEnded();
		return false;
	}

	@Override
	public String getWinner() throws RemoteException {
		// return eftaios.getWinner();
		return "abc";
	}

	@Override
	public String getCards(int playerID) throws RemoteException {
		remoteEFTAIOS.getCards();
		return "abc"; // TODO!!
	}

	@Override
	public boolean close() throws RemoteException {
		return true;
	}

	@Override
	public void useCards(String[] cards, String player) throws RemoteException {
		String choice;
		int[] validChoices = new int[3];

		choice = Client.readLine();
		validChoices = CommonMethods.validCard(choice, cards.length);
		for (int i = 0; i < validChoices.length; i++) {
			// remoteEFTAIOS.useCard(cards[i]);
		}
	}

	@Override
	public String connect() throws RemoteException {
		String gameInt = "GameEngine";
		String rmiInt = "RMIinterface";
		Registry registry;

		try {
			registry = LocateRegistry.getRegistry(1099);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

		try {
			remoteEFTAIOS = (it.polimi.ingsw.AntoniniCastiglia.server.GameEngine) (registry
					.lookup(gameInt));
			remoteRMI = (it.polimi.ingsw.AntoniniCastiglia.server.RMIInterface) (registry
					.lookup(rmiInt));
		} catch (AccessException e) {
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		} catch (NotBoundException e) {
			e.printStackTrace();
			return null;
		}

		try {
			return remoteRMI.connect();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAdjacents() throws RemoteException {
		// return eftaios.getAdjacents();
		return null;
	}

	@Override
	public boolean isStarted() throws RemoteException {
		return remoteEFTAIOS.isStarted();
	}

	
}
