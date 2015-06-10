package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import java.rmi.RemoteException;

/**
 * CLI implementation of <code>UserInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public class CLI implements UserInterface {

	@Override
	public void connected(int playerID) {
		System.out.println("You are now connected to the game as player number " + playerID + ".\n"
				+ "Please wait for a game to begin. "
				+ "Soon you will be provided with a character, and you will see the map.");
	}

	@Override
	public void whoYouAreComplete(String[] player) {
		String name = player[0];
		String role = player[1];
		String nature;
		if ("A".equals(player[2])) {
			nature = new String("n alien");
		} else {
			nature = new String(" human");
		}
		System.out.println("It turns out that you are a" + nature + ", and your name is " + name
				+ ".");
		System.out.println("On this unfortunate ship you are the " + role + ".");
	}

	@Override
	public void printMap(String map) {
		System.out.println(map);
	}

	@Override
	// TODO make suitable to DangerousSectorCards too!
	public void printCards(boolean canUseCards, String... cards) {
		if (canUseCards) {
			// TODO add string for noCard
			for (int i = 0; i < cards.length; i++) {
				if (!("null".equals(cards[i]))) {
					System.out.println((i + 1) + ". " + (cards[i].split("_"))[1] + " card");
				}
			}
		} else {
			System.out.println("You don't have any Item card right now. I feel it.");
		}
	}

	@Override
	public void yourTurn() {
		System.out.println("Now it's your turn!");
	}

	@Override
	public String selectCard(String[] cards) {
		System.out.println("You can take advantage of the card(s) you own, which are:");
		this.printCards(true, cards);
		int cardIndex;
		do {
			String choice = CommonMethods.readLine();
			try {
				cardIndex = Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				cardIndex = -1;
			}
		} while ((cardIndex >= 0 && cardIndex <= cards.length) && "null".equals(cards[cardIndex]));

		if (cardIndex == 0) {
			return "noCard";
		}
		return cards[cardIndex];
	}

	public String move(int playerID, String adjacentSectors) {
		System.out.println("You can move to the following sectors: " + "\n"
				+ adjacentSectors.replace(';', ' '));
		String chosenSector = null;
		do {
			System.out.println("Please, choose where you want to go: ");
			chosenSector = (CommonMethods.readLine()).toUpperCase();
		} while (!CommonMethods.validSector(adjacentSectors, chosenSector));

		return chosenSector;
	}

	@Override
	public String wantToAttack(String nature) {
		System.out.println("It seems that you can attack the sector you are in!");
		if ("H".equals(nature)) {
			System.out.println("(Note that you are a Human. As such, you can perform "
					+ "an attack thanks to your Attack item card.)");
		}
		System.out.println("Now choose carefully:" + "\n" + "A - Attack" + "\n"
				+ "B - Be a good guy");
		String chosenAction = null;
		do {
			chosenAction = CommonMethods.readLine();
			chosenAction.toUpperCase(); // TODO doesn't work!!
		} while (!("A".equals(chosenAction) || "B".equals(chosenAction)));
		return chosenAction;
	}

	@Override
	public void genericError() {
		System.out.println("There has been an error while trying to connect the client.");
	}

	@Override
	public void drawDangerousSectorCard(String drawnCard) {
		// TODO settore sicuro
		System.out.println("Since you were good, here is a reward (well, kind of...).");
		System.out.println(drawnCard);

	}

	@Override
	public void moveResult(String result) {
		System.out.println(result);
	}

}
