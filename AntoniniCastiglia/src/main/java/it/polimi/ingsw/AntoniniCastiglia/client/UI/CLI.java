package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import java.util.List;
import it.polimi.ingsw.AntoniniCastiglia.client.MyConstants;

/**
 * CLI implementation of <code>UserInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public class CLI implements UserInterface {

	@Override
	public void connected() {
		System.out.println("You are now connected to the game. "
				+ "Please wait for the game to begin. "
				+ "Soon you will know what character you are going to play with.");
	}

	@Override
	public void whoYouAreComplete(String[] player) {
		String name = player[0];
		String role = player[1];
		String id = player[3];
		String nature;
		if ("A".equals(player[2])) {
			nature = new String("n alien");
		} else {
			nature = new String(" human");
		}
		System.out.println("You are player number " + id + ".");
		System.out.println("You are a" + nature + ", and your name is " + name + ".");
		System.out.println("On this unfortunate ship you are the " + role + ".");
	}

	@Override
	public void printMap(String map) {
		System.out.println(map);
	}

	@Override
	public void printCards(boolean canUseCards, String... cards) {
		System.out.println(canUseCards);
		if (canUseCards) {
			for (int i = 0; i < cards.length; i++) {
				if (!"null".equals(cards[i])) {
					System.out.println((i + 1) + ". " + (cards[i].split("_"))[1] + " card");
				}
			}
		} else {
			System.out.println("You don't have any Item card right now.");
		}
	}

	@Override
	@Deprecated
	public void chooseAction(List<Character> possibleActions) {
		System.out.println("Please, choose your next action:\n");
		for (int i = 0; i < possibleActions.size(); i++) {
			switch (possibleActions.get(i)) {
				case MyConstants.USE_CARD: {
					System.out.println(MyConstants.USE_CARD + " - Use a card");
					break;
				}
				case MyConstants.MOVE: {
					System.out.println(MyConstants.MOVE + " - Move");
					break;
				}
				case MyConstants.QUIT: {
					System.out.println(MyConstants.QUIT + " - Quit the game");
					break;
				}
				case MyConstants.ATTACK: {
					System.out.println(MyConstants.ATTACK + " - Attack your current sector");
					break;
				}
			}
		}
	}

	@Override
	public void chooseCards() {
		System.out.println("Please, choose the card (or cards) you want to use.");
	}

	@Override
	public void askMove(String adjacents, String player) {
		System.out.println("You can move in the following sectors: ");
		System.out.println(adjacents.replace(';', ' ') + "\n");
		System.out.print("Please, choose where you want to go: ");
	}

	@Override
	public void genericError() {
		System.out.println("There has been an error while trying to connect the client.");
	}

	@Override
	public void pleaseWait() {
		System.out.println("Please, wait for a game to begin."
				+ "Soon you will be provided with a character, and you will see the map.");
	}

	@Override
	public void yourTurn() {
		System.out.println("Now it's your turn!");
	}

}
