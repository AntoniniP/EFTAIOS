package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.*;

public interface RMIInterface extends Remote { // Methods dealing with connection's stuff

	public int connect() throws RemoteException;

}
