package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.cards.CardsConstants;
import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;

/**
 * CLI implementation of <code>UserInterface</code>.
 *
 * @author Paolo Antonini
 *
 */
public class CLI implements UserInterface {

	@Override
	public void connected(int gameID, int playerID) {
		System.out.println("You are now connected to the game " + gameID + " as player number "
				+ playerID + ".\n" + "Please wait for a game to begin. "
				+ "Soon you will be provided with a character, and you will see the map.");
	}

	@Override
	public void whoYouAreComplete(String[] args) {
		String name = args[0];
		String role = args[1];
		String nature;
		if ("A".equals(args[2])) {
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
	public void printItemCards(boolean canUseCards, String... cards) {
		if (canUseCards) {
			// TODO System.out.println("0. Don't use any card");
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
	public void printJournal(String journal) {
		System.out.println(journal);
	}

	@Override
	public int selectItemCard(String[] cards) {
		System.out.println("You can take advantage of the card(s) you own, which are:");
		this.printItemCards(true, cards);
		int cardIndex;
		do {
			String choice = CommonMethods.readLine();
			try {
				cardIndex = Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				cardIndex = -1;
			}
		} while (!(cardIndex >= 0 && cardIndex <= cards.length));

		return cardIndex;
	}

	@Override
	public String move(int playerID, String adjacentSectors) {
		System.out.println("You can move to the following sectors: " + "\n"
				+ adjacentSectors.replace(';', ' '));
		String chosenSector = null;
		do {
			System.out.print("Please, choose a valid sector: ");
			chosenSector = (CommonMethods.readLine()).toUpperCase();
		} while (!CommonMethods.validSector(chosenSector));

		return chosenSector;
	}

	@Override
	public void drawDangerousSectorCard(String drawnCard) {
		String[] card = drawnCard.split("_");
		String name = card[1];
		boolean yourSector = Boolean.parseBoolean(card[2]);
		boolean withObject = Boolean.parseBoolean(card[3]);

		System.out.print("Well, here is your Dangerous Sector card: " + name + " ");
		if (name.equals(CardsConstants.NOISE)) {
			if (yourSector) {
				System.out.print("in your sector. ");
			} else {
				System.out.print("in a sector of your choice. ");
			}
			System.out.println("\n" + "Soon you will be prompted to declare it. ");
			if (withObject) {
				System.out.println("You are lucky, though! You can draw an Item Card.");
			}
		}
	}

	@Override
	public void whereYouAre(String currentSector, String sectorType) {
		System.out.println("You are now in " + currentSector + " sector, which is a " + sectorType
				+ " sector.");
	}

	@Override
	public String chooseAction(String possibleActions) {
		String chosenAction = null;
		do {

			System.out.println("Now choose your next action: ");

			if (possibleActions.contains(Constants.MOVE)) {
				System.out.println(Constants.MOVE + " - Move to a new sector");
			}
			if (possibleActions.contains(Constants.ATTACK)) {
				System.out.println(Constants.ATTACK + " - Attack the sector you are in now");
			}
			if (possibleActions.contains(Constants.USE_CARDS)) {
				System.out.println(Constants.USE_CARDS + " - Use your cards");
			}
			if (possibleActions.contains(Constants.DRAW_DS_CARD)) {
				System.out.println(Constants.DRAW_DS_CARD + " - Draw a Dangerous Sector card");
			}
			if (possibleActions.contains(Constants.DRAW_EH_CARD)) {
				System.out.println(Constants.DRAW_EH_CARD + " - Escape from this wrecked ship");
			}
			if (possibleActions.contains(Constants.QUIT)) {
				System.out.println(Constants.QUIT + " - End your turn");
			}
			chosenAction = (CommonMethods.readLine()).toUpperCase();

		} while (!(possibleActions.contains(chosenAction)));
		return chosenAction;
	}

	@Override
	public void attackResult(String attackResult) {
		if (attackResult.startsWith("KO")) {
			System.out.println("It appears that you can't attack "
					+ "(and you can certainly call for a bug if you see this message).");
		} else {

			String[] args = attackResult.split("_");
			int humanKilled = Integer.parseInt(args[1]);
			int alienKilled = Integer.parseInt(args[2]);
			System.out.println("You attacked your sector and you killed " + humanKilled
					+ " humans and " + alienKilled + " aliens. Good for you!");

		}
	}

	@Override
	public String declareNoise(boolean noise, boolean yourSector, String currentSector) {
		if (noise) {
			if (yourSector) {
				System.out.println("You must declare a noise in your sector. "
						+ "Hit RETURN to confirm: " + currentSector);
				CommonMethods.readLine();
				return currentSector;
			}
			System.out.println("You must declare a noise in a sector of your choice. ");
			String chosenSector = null;
			do {
				System.out.print("Please, choose a valid sector: ");
				chosenSector = (CommonMethods.readLine()).toUpperCase();
			} while (!CommonMethods.validSector(chosenSector));
			return chosenSector;
		} else {
			System.out.println("Silence in all sectors");
			return currentSector;
		}

	}

	@Override
	public int handleItemCard(String itemCard, String... cards) {
		System.out.println("You're too lucky, bro! You can have only three Item cards. " + "\n"
				+ "Please, choose which one you want to discard.");
		this.printItemCards(true, cards);
		int cardIndex;

		String choice = CommonMethods.readLine();
		try {
			cardIndex = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			cardIndex = -1;
		}
		return cardIndex;
	}

	@Override
	public void useResult(String useResult) {
		try {
			System.out.println("Here is the result:" + "\n" + useResult.split("_")[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
	}

	@Override
	public void escapeResult(String escapeResult) {
		System.out.println(escapeResult.split("_")[1]);
	}

}
