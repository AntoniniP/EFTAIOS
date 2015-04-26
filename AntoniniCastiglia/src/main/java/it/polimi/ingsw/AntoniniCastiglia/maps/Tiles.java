package it.polimi.ingsw.AntoniniCastiglia.maps;

public class Tiles {
	private int x; // Column
	private int y; // Row
	private String letter;
	private String number;
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVW"
			.toCharArray();

	public Tiles(int x, int y) {
		this.x = x;
		this.y = y;
		convert(); // converting as soon as I create
	}

	public Tiles(String name) {
		inverseConvertion(name);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private void convert() { // converting numeric coordinate into a letter
		letter = ((Character) ALPHABET[x]).toString(); // Casting char to String
		number = ((Integer) (y + 1)).toString();
		if (y < 9)
			number = "0" + number;
	}

	private void inverseConvertion(String s) {
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
