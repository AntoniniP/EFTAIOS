package it.polimi.ingsw.AntoniniCastiglia.maps;

/**
 * This abstract class provides sector instances, and methods to convert coordinates formats
 * (number/number for table's sake, single string as probable input by users, letter/number like in
 * the map).
 *
 * @author Laura Castiglia
 *
 */
public abstract class Sector {

	private int x; // column (literal part of coordinates, counter j)
	private int y; // row (numeric part of coordinates, counter i)
	private String letter;
	private String number;
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();
	private boolean reachable;
	private boolean mustDrawDSCard; // Dangerous Sector
	private boolean mustDrawEHCard; // Escape Hatch
	private String type;

	/**
	 * Standard constructor: receives the coordinates in numeric (integer) format only, and calls
	 * method convert() to give a proper value to variables letter and number.
	 *
	 * @param x column: literal part of coordinates, counter j
	 * @param y row: numeric part of coordinates, counter i
	 */
	public Sector(int x, int y) {
		this.x = x;
		this.y = y;
		this.convert();
	}

	/**
	 * Alternative constructor (overloading): receives a string containing the coordinates and calls
	 * inverseConversion() method to give a proper value to variables x, y, letter, number.
	 *
	 * @param name three-character string containing the alphanumeric coordinates of a sector
	 */
	public Sector(String coord) {
		this.inverseConvertion(coord);

	}

	/**
	 * The method gives a proper value to letter and number strings, given the numeric coordinates
	 * values (stored in x and y integers).
	 */
	private void convert() {
		letter = ((Character) ALPHABET[x]).toString(); // casting char to String
		number = ((Integer) (y + 1)).toString(); // casting int to String
		if (y < 9) {
			number = "0" + number; // number must be a two-character string
		}
	}

	/**
	 * The method receives the coordinates as a single alphanumeric string and gives proper values
	 * to x, y, letter and number variables.
	 *
	 * @param s coordinates in alphanumeric format
	 */
	private void inverseConvertion(String coord) {
		letter = coord.substring(0, 1);
		number = coord.substring(1, 3);

		char c = letter.charAt(0); // converting letter to char
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				x = i;
				break;
			}
		}
		y = Integer.parseInt(number) - 1; // converting string number to integer
	}

	/**
	 * Getter for mustDrawDSCard.
	 *
	 * @return whether a player must draw a Dangerous Sector card
	 */
	public boolean getMustDrawDSCard() {
		return mustDrawDSCard;
	}

	/**
	 * Setter for mustDrawDSCard.
	 *
	 * @param mustDrawDSCard sets whether a player must draw a Dangerous Sector card
	 */
	void setMustDrawDSCard(boolean mustDrawDSCard) {
		this.mustDrawDSCard = mustDrawDSCard;
	}

	/**
	 * Getter for mustDrawEHCard.
	 *
	 * @return whether a player must draw an Escape Hatch card
	 */
	public boolean getMustDrawEHCard() {
		return mustDrawEHCard;
	}

	/**
	 * Setter for mustDrawEHCard.
	 *
	 * @param mustDrawEHCard sets whether a player must draw an Escape Hatch card
	 */
	void setMustDrawEHCard(boolean mustDrawEHCard) {
		this.mustDrawEHCard = mustDrawEHCard;
	}

	/**
	 *
	 * Getter for type variable.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for type variable.
	 *
	 * @param type the type to set
	 */
	void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for reachable boolean.
	 *
	 * @return the reachable
	 */
	public boolean isReachable() {
		return reachable;
	}

	/**
	 * Setter for reachable boolean.
	 *
	 * @param reachable the reachable to set
	 */
	public void setReachable(boolean reachable) {
		this.reachable = reachable;
	}

	/**
	 * Standard getter for variable x.
	 *
	 * @return variable x (column reference).
	 */
	public int getX() {
		return x;
	}

	/**
	 * Standard getter for variable y.
	 *
	 * @return variable y (row reference).
	 */
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return letter + number;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Sector)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Sector s = (Sector) obj;
		return ((x == s.x) && (y == s.y));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Override to comply with the contract between equals(Object) and hashCode().
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return ((y * MapConstants.WIDTH) + (x * MapConstants.HEIGHT));
	}

}
