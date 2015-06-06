package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server {

	private static final int port = 1099;

	private Registry registry;
	
	private GameEngine game;
	private RMIInterface rmiInterface;
	private Timer timer;

	private boolean outOfTime;
	private boolean firstConn;
	private int numPlayer;
	private boolean started = false;

	public boolean isStarted() {
		return started;
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	private void start() {
		game = new GameEngineImpl();
		rmiInterface = new RMIInterfaceImpl(this);
		try {
			registry = LocateRegistry.createRegistry(port);
			GameEngine gameStub = (GameEngine) UnicastRemoteObject.exportObject(game, 0);
			RMIInterface rmiStub = (RMIInterface) UnicastRemoteObject.exportObject(rmiInterface, 0);
			registry.bind("GameEngine", gameStub);
			registry.bind("RMIinterface", rmiStub);
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Registry bound");
		//firstConn=false;
		//waitFirstConn();
		//startTimer();
		//outOfTime=false;
		numPlayer=0;

		waitConn();
	}

	private void waitConn() {

		System.out.println("Waiting for connections...");
		while (!outOfTime && getNumPlayer() < Constants.MAXPLAYERS) {
			// Waiting for others to connect
			if (getNumPlayer() == 9)
				System.out.println(getNumPlayer());

		}

		System.out.println("Out of the loop");
		startGame();
		return;
		// timer.cancel(); // in case the timer is out AND the number of players is right
		/*
		 * if (outOfTime && numPlayer < Constants.MINPLAYERS) {
		 * System.out.println("Sorry.The game won't start."); } else { startGame(); }
		 */
	}

	private void startGame() {
		// Game game = new Game ---->Creation of the class Game, extending thread, maybe?
		
		((GameEngineImpl)game).createMap();
		((GameEngineImpl)game).createPlayers(numPlayer);
		started = true;

	}

	private void startTimer() {
		timer = new Timer();
		timer.schedule(new MyTimer(this), 5 * 60 * 1000);
	}
/*
	private void waitFirstConn() {
		System.out.println("Waiting for first connection");
		while (!firstConn) {}
	}

	public void firstConn() {
		firstConn = true;
	}

	public boolean isFirstConn() {
		return firstConn;
	}
*/
	public synchronized void timeout() {
		outOfTime = true;
	}

	public void incrementNumPlayer() {
		numPlayer++;
	}

	public int getNumPlayer() {
		return numPlayer; // numPlayer=playerID
	}
	
	
}
