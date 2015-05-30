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

public class Client {

	public static void main(String[] args) {
		try {
			// TODO delle due, l'una! @SEE AuctionClient!
			NetworkInterface ni = NetworkInterfaceFactory
					.getInterface(chooseNetwork());

			Client application = new Client(ni);
		} catch (Exception e) {
			System.out.println();
			System.out.println("Exception");
			System.out.println(e);
		}
	}

	private Client(NetworkInterface ni) throws IOException {

		UserInterface ui = UserInterfaceFactory.getInterface(chooseUI());

		ui.connecting();

		String player = ni.connect();
		ui.youAre(player);

		// System.out.println("Waiting for at least another player to begin.");

		boolean endGame = false;

		String toPrint = "Maybe I'm useful! Probably I contain the report of the move.";

		while (!endGame) {

			if (!ni.isEnded()) {

				ui.printMap(ni.getMap().replace(";", "\n"));

				String[] cards = ni.getCards().split(";");
				boolean canUseCards = (cards.length != 0);
				

				List<Character> possibleActions = new ArrayList<Character>(
						Arrays.asList(MyConstants.MOVE, MyConstants.QUIT));
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
					ni.useCards(cards, ui);
					break;
				}
				case MyConstants.MOVE: {
					// String read = readLine(Messages.ASK_MOVE);
					// TODO move
					// toPrint =
					// ni.move(Integer.parseInt(result[0]),Integer.parseInt(result[1]),player);
					System.out.println("Nice! You moved! Cheers!");
					break;
				}
				default: {
					// TODO handle!
					System.out.println("Error!");
					break;
				}
				}

				// Analyse if win/lose/draw/nothing
				endGame = analyze(toPrint);
			} else {
				// if ended check who won.
				endGame = true;
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

	private static int chooseNetwork() {
		String choice;
		do {
			System.out.println("Choose your network interface:");
			System.out.println("1 - Socket (not implemented yet)");
			System.out.println("2 - RMI");
			choice = readWriteLine("\n");
			if (!choice.equals("1") && !choice.equals("2")) {
				System.out.println("You typed the wrong command!");
			}
		} while (!choice.equals("1") && !choice.equals("2"));
		return Integer.parseInt(choice);
	}

	private static int chooseUI() {
		String choice;
		do {
			System.out.println("Choose your user interface:");
			System.out.println("1 - CLI");
			System.out.println("2 - GUI (not implemented yet)");
			choice = readWriteLine("\n");
			if (!choice.equals("1") && !choice.equals("2")) {
				System.out.println("You typed the wrong command!");
			}
		} while (!choice.equals("1") && !choice.equals("2"));
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

	public static String readLine()  {
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

}