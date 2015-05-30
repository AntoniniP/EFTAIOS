package it.polimi.ingsw.AntoniniCastiglia.client.Network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.AntoniniCastiglia.client.Client;
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
	public String move(int i, int j, String player) throws RemoteException {
		return eftaios.move(i, j, player);
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
	public String getCards() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean close() {
		return true;
	}

	@Override
	public void useCards(String[] cards, UserInterface ui) {
		String choice;

		ui.chooseCards();
		ui.printAllCards(cards);

		choice = Client.readLine();

		for (int i = 0; i < choice.length(); i++) {
			String ch = choice.substring(i, i + 1);

			int flag = ch.compareTo("1") + ch.compareTo("2")
					+ ch.compareTo("3");

			if (flag != 0) {
				int n = Integer.parseInt(ch);
				if (n >= 1 && n <= cards.length) {
					eftaios.useCard(cards[n]);
				}
			}

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

}
