package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import it.polimi.ingsw.AntoniniCastiglia.client.Client;
import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.misc.RemoteEFTAIOS;

/**
 * RMI implementation of <code>NetworkInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public class RMIInterface implements NetworkInterface {

	private RemoteEFTAIOS eftaios;

	@Override
	public String getMap() throws RemoteException {
		return eftaios.getMap();
	}

	@Override
	public String move(String sector, String player) throws RemoteException  {
		return eftaios.move(sector, player);
	}

	@Override
	public boolean isEnded() throws RemoteException {
		return eftaios.isEnded();
	}

	@Override
	public String getWinner() throws RemoteException {
		return eftaios.getWinner();
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
			eftaios.useCard(cards[i]);
		}
	}

	@Override
	public String connect() {
		String name = "RemoteEFTAIOS";
		Registry registry;

		try {
			registry = LocateRegistry.getRegistry(2026);
		} catch (RemoteException e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			eftaios = (RemoteEFTAIOS) registry.lookup(name);
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
			return eftaios.connect();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getAdjacents() {
		return eftaios.getAdjacents();
	}
	
	

}
