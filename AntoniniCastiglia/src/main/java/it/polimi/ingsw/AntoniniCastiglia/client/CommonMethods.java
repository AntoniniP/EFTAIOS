package it.polimi.ingsw.AntoniniCastiglia.client;

public class CommonMethods {

	private CommonMethods() {
	}

	public static int[] validCard(String choice, int len) {
		int count = 0;
		int[] cardsToUse = new int[3];

		for (int i = 0; i < choice.length(); i++) {
			String ch = choice.substring(i, i + 1);

			int flag = "1".compareTo(ch) + "2".compareTo(ch) + "3".compareTo(ch);

			if (flag != 0) {
				int n = Integer.parseInt(ch);
				if (n >= 1 && n <= len) {
					cardsToUse[count] = n;
					count++;
				}
			}
		}
		return cardsToUse;
	}

	public static boolean validSector(String adjacents, String sector) {
		return adjacents.contains(sector);

	}

	/*
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
			e.printStackTrace();
		}

		return read;
	}
	*/
}
