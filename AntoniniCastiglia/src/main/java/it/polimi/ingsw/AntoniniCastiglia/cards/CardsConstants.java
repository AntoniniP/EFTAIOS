package it.polimi.ingsw.AntoniniCastiglia.cards;

/**
 * Class containing all necessary constants (names and numbers, mainly) for cards and decks.
 *
 * @author Paolo Antonini
 *
 */
public final class CardsConstants {

	static final int NUM_NOISE_YOURSECTOR_WITHOBJECT = 4;
	static final int NUM_NOISE_YOURSECTOR_WITHOUTOBJECT = 6;
	static final int NUM_NOISE_ANYSECTOR_WITHOBJECT = 4;
	static final int NUM_NOISE_ANYSECTOR_WITHOUTOBJECT = 6;
	static final int NUM_SILENCE = 5;
	static final int NUM_ATTACK = 2;
	static final int NUM_TELEPORT = 2;
	static final int NUM_SEDATIVES = 3;
	static final int NUM_SPOTLIGHT = 2;
	static final int NUM_DEFENSE = 1;
	static final int NUM_ADRENALINE = 2;

	static final int GREEN_ESCAPE_HATCH = 3;
	static final int RED_ESCAPE_HATCH = 3;

	// Types
	public static final String ITEM_CARD = "Item";
	public static final String DANGEROUS_SECTOR_CARD = "Dangerous Sector";
	public static final String ESCAPE_HATCH_CARD = "Escape Hatch";

	// Item cards
	public static final String ADRENALINE = "Adrenaline";
	public static final String ATTACK = "Attack";
	public static final String DEFENSE = "Defense";
	public static final String SEDATIVES = "Sedatives";
	public static final String SPOTLIGHT = "Spotlight";
	public static final String TELEPORT = "Teleport";

	// Dangerous sector cards
	public static final String NOISE = "Noise";
	public static final String SILENCE = "Silence";

	/**
	 * Private constructor to hide the implicit one.
	 */
	private CardsConstants() {
	}

}
