package it.polimi.ingsw.AntoniniCastiglia.maps;

public class Sector {
	private int x; // Column
	private int y; // Row
	private String letter;
	private String number;
//	private String coordinates;
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();

	public Sector(int x, int y) {
		this.x = x;
		this.y = y;
		convert(); // converting as soon as I create
	}

	public Sector(String name) {
		inverseConvertion(name);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private void convert() { // converting numeric coordinate into a letter
		letter = ((Character) ALPHABET[y]).toString(); // Casting char to String
		number = ((Integer) (x + 1)).toString();
		if (x < 9)
			number = "0" + number;
	}

	private void inverseConvertion(String s) { //TODO check!
		letter = s.substring(0, 1);
		number = s.substring(1, 3);
		char c = letter.charAt(0);// Converting x to char
		for (int i = 0; i < ALPHABET.length; i++)
			// for each i belonging to ALPHABET
			if (ALPHABET[i] == c) {
				x = i;
				break;
			}
		y = Integer.parseInt(number) - 1;

	}

	@Override
	// re-writing a superclass method (superclass=Object; common to all objects)
	// READ OVERRIDE & OVERLOAD
	public String toString() {
		return letter + number;
	}
}
