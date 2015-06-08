package it.polimi.ingsw.AntoniniCastiglia.client.UI;


/**
 * CLI implementation of <code>UserInterface</code>.
 * 
 * @author Paolo Antonini
 *
 */
public class CLI implements UserInterface {

	@Override
	public void connected(int playerID) {
		System.out.println("You are now connected to the game as player number " +playerID + ".\n" 
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
		System.out.println("It turns out that you are a" + nature + ", and your name is " + name + ".");
		System.out.println("On this unfortunate ship you are the " + role + ".");
	}

	@Override
	public void printMap(String map) {
		System.out.println(map);
	}

	@Override
	public void printCards(boolean canUseCards, String... cards) {
		if (canUseCards) {
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
	public void chooseCards() {
		System.out.println("Please, choose the card (or cards) you want to use.");
	}

	@Override
	public void askMove(String adjacents) {
		System.out.println("You can move in the following sectors: ");
		System.out.println(adjacents.replace(';', ' ') + "\n");
		System.out.print("Please, choose where you want to go: ");
	}

	@Override
	public void genericError() {
		System.out.println("There has been an error while trying to connect the client.");
	}

	

	@Override
	public void youCanAttack(String nature) {
		System.out
				.println("It seems that you can attack the sector you are in! Now think carefully "
						+ "if you want to attack or not.");
		if ("H".equals(nature)) {
			System.out.println("(Please note that you are a Human. As such, you can perform "
					+ "an attack thanks to your Attack item card. If you go on, your Attack card "
					+ "will be discarded)");
		}
		System.out.println("Now choose:" + "\n" + "A - Attack" + "\n" + "B - Be a good guy");

	}

	@Override
	public void drawDangerousSectorCard(String drawnCard) {
		// TODO settore sicuro
		System.out.println("Since you were good, and you didn't attack, here is a reward (well, kind of...).");
		System.out.println(drawnCard);

		
	}

	@Override
	public void moveResult(String result) {
		System.out.println(result);		
	}

}
