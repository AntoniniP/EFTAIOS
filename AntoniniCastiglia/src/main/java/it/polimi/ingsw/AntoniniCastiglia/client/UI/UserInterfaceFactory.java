package it.polimi.ingsw.AntoniniCastiglia.client.UI;

public class UserInterfaceFactory {

	private UserInterfaceFactory() {

	}

	public static UserInterface getInterface(int param) {
		if (param == 1)
			return new CLI();
		else //tutti i casi possibili!
			return null;
	}
}
