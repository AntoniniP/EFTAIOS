package it.polimi.ingsw.AntoniniCastiglia;

/**
 * Public class to contain some, but not all, useful constants.
 *
 * @author Paolo Antonini
 * @author Laura Castiglia
 *
 */
public final class Constants {

	// ANSI escapes for colours
	public static final String ANSI_RESET = "\u001b[0m";
	public static final String ANSI_CLS = "\u001b[2J";
	public static final String ANSI_BOLD = "\u001b[1m";
	public static final String ANSI_BLACK = "\u001b[30m";
	public static final String ANSI_DARK_GREY = "\u001b[90m";
	public static final String ANSI_RED = "\u001b[31m";
	public static final String ANSI_RED_BRIGHT = "\u001b[91m";
	public static final String ANSI_GREEN = "\u001b[32m";
	public static final String ANSI_GREEN_BRIGHT = "\u001b[92m";
	public static final String ANSI_BROWN = "\u001b[33m";
	public static final String ANSI_YELLOW = "\u001b[93m";
	public static final String ANSI_BLUE = "\u001b[34m";
	public static final String ANSI_BLUE_BRIGHT = "\u001b[94m";
	public static final String ANSI_MAGENTA = "\u001b[35m";
	public static final String ANSI_MAGENTA_BRIGHT = "\u001b[95m";
	public static final String ANSI_CYAN = "\u001b[36m";
	public static final String ANSI_CYAN_BRIGHT = "\u001b[96m";
	public static final String ANSI_GRAY = "\u001b[37m";
	public static final String ANSI_WHITE = "\u001b[97m";

	// Actions
	public static final String MOVE = "M";
	public static final String ATTACK = "A";
	public static final String USE_CARDS = "U";
	public static final String DRAW_DS_CARD = "D";
	public static final String QUIT = "Q";
	public static final String DECLARE_NOISE = "N";
	public static final String DRAW_I_CARD = "I";
	public static final String DRAW_EH_CARD = "E";

	/**
	 * Private constructor to hide the implicit one.
	 */
	private Constants() {
	}

}
