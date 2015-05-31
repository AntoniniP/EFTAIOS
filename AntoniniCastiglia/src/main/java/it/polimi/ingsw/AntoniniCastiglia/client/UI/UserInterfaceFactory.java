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
		if (param == 1)
			return new CLI();
		else
			return null;
	}
}
