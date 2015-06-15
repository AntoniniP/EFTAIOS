package it.polimi.ingsw.AntoniniCastiglia.client.Network;

/**
 * Factory method pattern implementation for the choice of the network interface (RMI/socket).
 *
 * @author Paolo Antonini
 *
 */
public class NetworkInterfaceFactory {

	/**
	 * Private contructor for the class.
	 */
	private NetworkInterfaceFactory() {

	}

	/**
	 * Returns the instantiation of the requested network interface.
	 *
	 * @param param enables the choice
	 * @return the requested network interface
	 */
	public static NetworkInterface getInterface(int param) {
		if (param == 1) {
			return new RMIInterface();
		} else {
			System.out.println("Funnily enough, I'm sure I told you that Socket network interface"
					+ " has NOT been implemented yet." + "\n"
					+ " Since I love you, I'll let you play with RMI connection, instead.");
			return new RMIInterface();
		}
	}
}
