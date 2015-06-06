package it.polimi.ingsw.AntoniniCastiglia;

/**
 * Public class to contain useful constants.
 * 
 * @author Paolo Antonini
 * @author Laura Castiglia
 *
 */
public final class Constants {

	// Players
	public static final int MINPLAYERS = 2;
	public static final int MAXPLAYERS = 4;

	// Map
	public static final int HEIGHT = 14;
	public static final int WIDTH = 23;

	// Cards
	public static final int NOISE_YOURSECTOR_WITHOBJECT = 4;
	public static final int NOISE_YOURSECTOR_WITHOUTOBJECT = 6;
	public static final int NOISE_ANYSECTOR_WITHOBJECT = 4;
	public static final int NOISE_ANYSECTOR_WITHOUTOBJECT = 6;
	public static final int SILENCE = 5;
	public static final int ATTACKCARD = 2;
	public static final int TELEPORTCARD = 2;
	public static final int SEDATIVESCARD = 3;
	public static final int SPOTLIGHTCARD = 2;
	public static final int DEFENSECARD = 1;
	public static final int ADRENALINECARD = 2;
	
	// Escape hatches
	public static final int GREEN_ESCAPE_HATCH = 3;
	public static final int RED_ESCAPE_HATCH = 3;

	// ANSI escapes for colors
	public static final String ANSI_RESET = "\u001b[0m";
	public static final String ANSI_CLS = "\u001b[2J";
	public static final String ANSI_BOLD = "\u001b[1m"; 
	public static final String ANSI_BLACK = "\u001b[30m";
	public static final String ANSI_RED = "\u001b[91m";
	public static final String ANSI_GREEN = "\u001b[32m";
	public static final String ANSI_YELLOW = "\u001b[33m";
	public static final String ANSI_BLUE = "\u001b[34m";
	public static final String ANSI_PURPLE = "\u001b[35m";
	public static final String ANSI_CYAN = "\u001b[36m";
	public static final String ANSI_GRAY= "\u001b[37m";

	/**
	 * Private constructor to hide the public one.
	 */
	private Constants() {
	}

}
