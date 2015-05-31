package it.polimi.ingsw.AntoniniCastiglia.client;

import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.Network.NetworkInterfaceFactory;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterface;
import it.polimi.ingsw.AntoniniCastiglia.client.UI.UserInterfaceFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Every instance of this class is the client for a player.
 * 
 * @author Paolo Antonini
 *
 */
public class Client {

	/**
	 * This is the <code>main</code> method for the class.
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			NetworkInterface ni = NetworkInterfaceFactory.getInterface(chooseNetwork());
			Client application = new Client(ni);
		} catch (Exception e) {
			System.out.println();
			System.out.println("Exception");
			System.out.println(e);
		}
	}

	/**
	 * Private constructor for the class. Receives the network interface as parameter.
	 * 
	 * @param ni network interface
	 * @throws IOException
	 */
	private Client(NetworkInterface ni) throws IOException {

		UserInterface ui = UserInterfaceFactory.getInterface(chooseUI());

		ui.connecting();

		String player = ni.connect();
		ui.youAre(player);

		// System.out.println("Waiting for at least another player to begin.");

		boolean endGame = false;

		// String toPrint = "Maybe I'm useful! Probably I contain the report of the move.";

		while (!endGame) {

			if (!ni.isEnded()) {
				// print map
				ui.printMap(ni.getMap().replace(";", "\n"));
				// print cards
				String[] cards = ni.getCards(player).split(";");
				boolean canUseCards = (cards.length != 0);
				// print possible actions
				List<Character> possibleActions = new ArrayList<Character>(Arrays.asList(
						MyConstants.MOVE, MyConstants.QUIT));
				if (canUseCards) {
					possibleActions.add(MyConstants.USE_CARD);
				}
				ui.chooseAction(possibleActions);

				char choice = readLine().charAt(0);
				Character.toUpperCase(choice);
				switch (choice) {
					case MyConstants.QUIT: {
						endGame = true;
						break;
					}
					case MyConstants.USE_CARD: {
						// TODO unsafe: I may type U anyway!
						ni.useCards(cards, ui, player);
						break;
					}
					case MyConstants.MOVE: {
						String adjacents = new String(ni.getAdjacents());
						String chosenSector = null;
						do {
							ui.askMove(adjacents, player);
							chosenSector = readLine();
						} while (!CommonMethods.validSector(adjacents, chosenSector));

						// TODO ni.move() returns a string...
						ni.move(chosenSector, player);

						break;
					}
					default: {
						// TODO handle!
						System.out.println("Error!");
						break;
					}
				}

				// Analyse if win/lose/draw/nothing
				// endGame = analyze(toPrint);
			} else {
				// if ended check who won.
				endGame = false;
				System.out.println("THE WINNER IS " + ni.getWinner());
			}
		}
	}

	private static boolean analyze(String toPrint) {
		boolean finish = false;
		if (toPrint.equals("YOULOSE")) {
			finish = true;
			System.out.println("KONGRATS, " + toPrint);
		} else if (toPrint.equals("YOUWIN")) {
			finish = true;
			System.out.println(toPrint);
		} else if (toPrint.equals("YOUDRAW")) {
			finish = true;
			System.out.println("YAY " + toPrint);
		} else {
			System.out.println("MSG " + toPrint);
		}
		return finish;
	}

	/**
	 * Asks the player to choose his preferred connection method.
	 * 
	 * @return an integer parameter
	 */
	private static int chooseNetwork() {
		String choice;
		do {
			System.out.println("Choose your network interface:");
			System.out.println("1 - Socket (not implemented yet)");
			System.out.println("2 - RMI");
			choice = readWriteLine("\n");
			if (!"1".equals(choice) && !"2".equals(choice)) {
				System.out.println("You typed the wrong command!");
			}
		} while (!choice.equals("1") && !choice.equals("2"));
		return Integer.parseInt(choice);
	}

	/**
	 * Asks the player to choose his preferred graphical interface.
	 * 
	 * @return an integer parameter
	 */
	private static int chooseUI() {
		String choice;
		do {
			System.out.println("Choose your user interface:");
			System.out.println("1 - CLI");
			System.out.println("2 - GUI (not implemented yet)");
			choice = readWriteLine("\n");
			if (!"1".equals(choice) && !"2".equals(choice)) {
				System.out.println("You typed the wrong command!");
			}
		} while (!"1".equals(choice) && !"2".equals(choice));
		return Integer.parseInt(choice);
	}

	private static String readWriteLine(String format, Object... args) {
		if (System.console() != null) {
			return System.console().readLine(format, args);
		}

		System.out.print(String.format(format, args));

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String read = null;

		try {
			read = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return read;
	}

	/**
	 * Simplifies the acquisition of a string.
	 * 
	 * @return the acquired string
	 */
	public static String readLine() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String read = null;
		try {
			read = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return read;
	}

}