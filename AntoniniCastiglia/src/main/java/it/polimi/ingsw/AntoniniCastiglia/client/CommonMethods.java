package it.polimi.ingsw.AntoniniCastiglia.client;

public class CommonMethods {

	private CommonMethods() {
	}

	public static int[] validCard(String choice, int len) {
		int count = 0;
		int[] cardsToUse = new int[3];

		for (int i = 0; i < choice.length(); i++) {
			String ch = choice.substring(i, i + 1);

			int flag = ch.compareTo("1") + ch.compareTo("2") + ch.compareTo("3");

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
		return true;

	}
}
