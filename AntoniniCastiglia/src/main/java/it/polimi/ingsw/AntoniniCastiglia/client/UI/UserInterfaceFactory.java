package it.polimi.ingsw.AntoniniCastiglia.client.UI;

/**
 * Factory method pattern implementation for the choice of the graphical interface (Command
 * Line/GUI).
 *
 * @author Paolo Antonini
 *
 */
public class UserInterfaceFactory {

	/**
	 * Private contructor for the class.
	 */
	private UserInterfaceFactory() {
	}

	/**
	 * Returns the instantiation of the requested graphical interface.
	 *
	 * @param param enables the choice
	 * @return the requested graphical interface
	 */
	public static UserInterface getInterface(int param) {
		if (param == 1) {
			return new CLI();
		} else {
			System.out
					.println("Funnily enough, I'm sure I told you that the Graphical User Interface (GUI)"
							+ " has NOT been implemented yet." + "\n"
							+ " Since I love you, I'll let you play with CLI interface, instead.");
			return new CLI();
		}
	}
}
