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
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
			return null;
		}

		try {
			return remoteRMI.connect();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getMap(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.getMap(gameID);
	}

	@Override
	public String move(int playerID, int gameID, String sector) throws RemoteException {
		return remoteEFTAIOS.move(playerID, gameID, sector);
	}

	@Override
	public String getCards(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.getPlayerCards(playerID, gameID);
	}

	@Override
	public String useCard(int playerID, int gameID, int posCard) throws RemoteException {
		return remoteEFTAIOS.useCard(playerID, gameID, posCard);
	}

	@Override
	public String getAdjacentSectors(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.getAdjacentSectors(playerID, gameID);
	}

	@Override
	public String attack(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.attack(playerID, gameID);
	}

	@Override
	public boolean isEnded(int gameID) throws RemoteException {
		return remoteEFTAIOS.isEnded(gameID);
	}

	@Override
	public boolean isStarted(int gameID) throws RemoteException {
		return remoteEFTAIOS.isStarted(gameID);
	}

	@Override
	public String getPlayer(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.getPlayer(playerID, gameID);
	}

	@Override
	public String drawCard(int gameID, int playerID, String deck) throws RemoteException {
		return remoteEFTAIOS.drawCard(gameID, playerID, deck);
	}

	@Override
	public boolean isMyTurn(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.isMyTurn(playerID, gameID);
	}

	@Override
	public void endTurn(int playerID, int gameID) throws RemoteException {
		remoteEFTAIOS.endTurn(playerID, gameID);
	}

	@Override
	public boolean canAttack(int gameID, int playerID) throws RemoteException {
		return remoteEFTAIOS.canAttack(gameID, playerID);
	}

	@Override
	public boolean isDead(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.isDead(playerID, gameID);
	}

	@Override
	public String possibleActions(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.possibleActions(playerID, gameID);
	}

	@Override
	public String declareNoise(int gameID, int playerID, String sector) throws RemoteException {
		return remoteEFTAIOS.declareNoise(gameID, playerID, sector);
	}

	@Override
	public String getItemCard(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.getItemCard(playerID, gameID);
	}

	@Override
	public String handleItemCard(int playerID, int gameID, int cardIndex) throws RemoteException {
		return remoteEFTAIOS.handleItemCard(playerID, gameID, cardIndex);
	}

	@Override
	public String getJournal(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.getJournal(playerID, gameID);
	}

	@Override
	public String escape(int playerID, int gameID) throws RemoteException {
		return remoteEFTAIOS.escape(playerID, gameID);
	}

}
