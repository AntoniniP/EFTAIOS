package it.polimi.ingsw.AntoniniCastiglia.client;

//TODO to test??

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
}