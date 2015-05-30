package it.polimi.ingsw.AntoniniCastiglia.client.Network;

/**
 * Factory method pattern implementation for the choice of the network interface
 * (RMI/socket).
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
			return null;
		} else {
			return new RMIInterface();
		}
	}
}
