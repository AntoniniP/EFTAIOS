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
				+ "Please wait for the game to begin."
				+ "Soon you will know what character you are going to play with.");
	}

	@Override
	public void youAre(String name) {
		System.out.println("You are " + name);
	}

	@Override
	public void printMap(String map) {
		System.out.println(map);
	}

	@Override
	public void printCards(String... cards) {
		if (cards.length == 0) {
			System.out.println("You don't have any card!");
		} else {
			for (int i = 0; i < cards.length; i++) {
				System.out.println((i + 1) + ". " + cards[i]);
			}
		}
	}

	@Override
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

	// Main to test
	public static void main(String[] args) {
		CLI ui = new CLI();
		String[] s = new String[3];
		ui.printCards(s);
		s[0] = null;
		ui.printCards(s);
		s[0] = "1.abc";
		s[1] = "2.def";
		s[2] = "3.ghi";
		ui.printCards(s);
	}
}
