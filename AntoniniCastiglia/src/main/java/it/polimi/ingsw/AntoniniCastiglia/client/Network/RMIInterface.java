package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import it.polimi.ingsw.AntoniniCastiglia.client.Client;
import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.server.GameEngine;
import it.polimi.ingsw.AntoniniCastiglia.server.GameEngineImpl;

/**
 * RMI implementation of <code>NetworkInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public class RMIInterface implements NetworkInterface {

	private GameEngine eftaios;
	private it.polimi.ingsw.AntoniniCastiglia.server.RMIInterface remoteRMI;

	@Override
	public String getMap() throws RemoteException {
		return eftaios.getMap();
	}

	@Override
	public String move(String player, String sector) throws RemoteException  {
		return eftaios.move(player, sector);
	}

	@Override
	public boolean isEnded() throws RemoteException {
		//return eftaios.isEnded();
		return false;
	}

	@Override
	public String getWinner() throws RemoteException {
		//return eftaios.getWinner();
		return "abc";
	}

	@Override
	public String getCards(String player) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean close() {
		return true;
	}

	@Override
	public void useCards(String[] cards, UserInterface ui, String player) {
		String choice;
		int[] validChoices = new int[3];

		ui.chooseCards();
		ui.printAllCards(cards);

		choice = Client.readLine();
		validChoices = CommonMethods.validCard(choice, cards.length);
		for (int i = 0; i < validChoices.length; i++) {
		//	eftaios.useCard(cards[i]);
		}
	}

	@Override
	public String connect() {
		String name1 = "GameEngine";
		String name2 = "RMIinterface";
		Registry registry;

		try {
			System.out.println("Locate registry");
			registry = LocateRegistry.getRegistry(1099);
		} catch (RemoteException e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			System.out.println("Lookup name BEFORE");

			eftaios = (GameEngine) (registry.lookup(name1));
			remoteRMI = (it.polimi.ingsw.AntoniniCastiglia.server.RMIInterface) (registry.lookup(name2));
			
			System.out.println("Lookup name AFTER");

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
			System.out.println("connect BEFORE");

			return remoteRMI.connect();

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		System.out.println("connection failed");

		return null;
	}

	@Override
	public String getAdjacents() {
	//	return eftaios.getAdjacents();
		return null;
	}
	
	@Override
	public boolean isStarted() {
		try {
			return eftaios.isStarted();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
