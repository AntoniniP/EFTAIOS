package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Server {

	private static final int port = 1099;

	private Registry registry;

	private GameEngine game;
	private RMIInterface rmiInterface;
	private Timer timer;

	private boolean outOfTime;
	private boolean firstConnected;
	private int numPlayer;
	private boolean started = false;

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

		waitConn();
	}

	private void waitConn() {
		firstConnection();
		System.out.println("\n" + "Waiting for other connections..." + "\n");
		while (!outOfTime && (getNumPlayer() < Constants.MAXPLAYERS)) {
			// TODO Some magic happens here (without the SLEEP instruction, nothing works).
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("Out of the loop");
		timer.cancel(); // in case the timer is out AND the number of players is right
		if (outOfTime && numPlayer < Constants.MINPLAYERS) {
			System.out.println("Sorry.The game won't start.");
		} else {
			startGame();
		}
	}

	private void firstConnection() {
		firstConnected = false;
		numPlayer = 0;
		System.out.println("Waiting for first connection");
		while (!isFirstConn()) {
			// TODO Some magic happens here (without the SLEEP instruction, nothing works).
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		startTimer();
		outOfTime = false;
	}

	private void startGame() {
		// Game game = new Game //Creation of the class Game, extending thread, maybe?
		((GameEngineImpl) game).createMap();
		((GameEngineImpl) game).createPlayers(numPlayer);
		((GameEngineImpl) game).createDecks();
		started = true;
	}

	private void startTimer() {
		timer = new Timer();
		timer.schedule(new MyTimer(this), 5 * 60 * 1000);
	}

	protected boolean isStarted() {
		return started;
	}

	protected void firstConn() {
		firstConnected = true;
	}

	protected boolean isFirstConn() {
		return firstConnected;
	}

	public synchronized void timeout() {
		outOfTime = true;
	}

	protected void incrementNumPlayer() {
		numPlayer++;
	}

	protected int getNumPlayer() {
		return numPlayer; // numPlayer=playerID
	}

}
