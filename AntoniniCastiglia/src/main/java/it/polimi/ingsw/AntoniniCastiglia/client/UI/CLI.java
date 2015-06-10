package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.client.Constants;

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

	@Override
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
			chosenAction = (CommonMethods.readLine()).toUpperCase();
		} while (!("A".equals(chosenAction) || "B".equals(chosenAction)));
		return chosenAction;
	}

	@Override
	public void drawDangerousSectorCard(String drawnCard) {
		System.out.println("Well, here is your card: " + (drawnCard.split("_"))[1]);
	}

	@Override
	public void whereYouAre(String currentSector) {
		System.out.println("You are now in " + (currentSector.split("_"))[0]
				+ " sector, which is a XXXXXXXXX sector.");
	}

	@Override
	public String chooseAction(boolean hasMoved, boolean canAttack, boolean hasAttacked,
			boolean canUseCards, boolean mustDraw, boolean hasDrawn) {
		String chosenAction = null;
		do {

			System.out.println("Now choose your next action:");

			if (!hasMoved) {
				System.out.println(Constants.MOVE + " - Move to a new sector");
			}
			if (canAttack && !hasAttacked && !hasDrawn) {
				System.out.print(Constants.ATTACK + " - Attack the sector you are in now");
				if (mustDraw && !hasAttacked) {
					System.out.println(" (please note that you won't be able"
							+ " to draw a Dangerous Sector card during this turn)");
				} else {
					System.out.println();
				}
			}
			if (canUseCards) {
				System.out.println(Constants.USE_CARD + " - Use your cards");
			}
			if (mustDraw && !hasAttacked) {
				System.out.print(Constants.DRAW_CARD + " - Draw a Dangerous Sector card");
				if (canAttack && !hasAttacked && !hasDrawn) {
					System.out.println(" (please note that you won't be able"
							+ " to attack during this turn)");
				} else {
					System.out.println();
				}
			}
			System.out.println(Constants.QUIT + " - End your turn");

			chosenAction = (CommonMethods.readLine()).toUpperCase();

		} while (!((!hasMoved && Constants.MOVE.equals(chosenAction))
				|| (!hasAttacked && canAttack && Constants.ATTACK.equals(chosenAction))
				|| (canUseCards && Constants.USE_CARD.equals(chosenAction))
				|| (Constants.QUIT.equals(chosenAction)) || (mustDraw && !hasAttacked && Constants.DRAW_CARD
				.equals(chosenAction))));
		return chosenAction;
	}

}
