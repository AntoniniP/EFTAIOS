package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Class containing all necessary constants (names and numbers, mainly) for cards and decks.
 *
 * @author Laura Castiglia
 *
 */
public final class CardsConstants {

	// Dangerous Sector cards
	public static final String DANGEROUS_SECTOR_CARD = "Dangerous Sector";
	public static final String NOISE = "Noise";
	static final int NUM_NOISE_YOURSECTOR_WITHOBJECT = 4;
	static final int NUM_NOISE_YOURSECTOR_WITHOUTOBJECT = 6;
	static final int NUM_NOISE_ANYSECTOR_WITHOBJECT = 4;
	static final int NUM_NOISE_ANYSECTOR_WITHOUTOBJECT = 6;
	public static final String SILENCE = "Silence";
	static final int NUM_SILENCE = 5;

	// Item cards
	public static final String ITEM_CARD = "Item";
	public static final String ATTACK = "Attack";
	static final int NUM_ATTACK = 2;
	public static final String TELEPORT = "Teleport";
	static final int NUM_TELEPORT = 2;
	public static final String SEDATIVES = "Sedatives";
	static final int NUM_SEDATIVES = 3;
	public static final String SPOTLIGHT = "Spotlight";
	static final int NUM_SPOTLIGHT = 2;
	public static final String DEFENSE = "Defense";
	static final int NUM_DEFENSE = 1;
	public static final String ADRENALINE = "Adrenaline";
	static final int NUM_ADRENALINE = 2;

	// Escape Hatch card
	public static final String ESCAPE_HATCH_CARD = "Escape Hatch";
	static final int GREEN_ESCAPE_HATCH = 3;
	static final int RED_ESCAPE_HATCH = 3;

	/**
	 * Private constructor to hide the implicit one.
	 */
	private CardsConstants() {
	}

}
