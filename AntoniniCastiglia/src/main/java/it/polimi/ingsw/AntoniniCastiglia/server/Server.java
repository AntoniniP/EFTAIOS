package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.Constants;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server implements TimerInterface {

	private static final int port = 1099;

	private Registry registry;

	private GameEngine game;
	private RMIInterface rmiInterface;
	private Timer timer;

	private boolean outOfTime;
	private boolean firstConnected;
	private int numPlayer;
	private boolean started = false;
	private boolean suspended = false;
	private GameHandler gameHandler;
	private int gameId = 0;

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

		while (true)
			waitConn();// multiple game thing

	}

	private void waitConn() {
		firstConnection();
		gameHandler = new GameHandler();
		ExecutorService newGame = Executors.newSingleThreadExecutor();
		newGame.submit(gameHandler);
		((GameEngineImpl) game).addGame(gameHandler);
		System.out.println("Waiting for other connections..." + "\n");
		while (!outOfTime && (numPlayer < Constants.MAXPLAYERS)) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// nothing to do; the sleep is fundamental to make the cycle work
			}
		}
		System.out.println("Out of the loop");
		timer.cancel(); // in case the timer is out AND the number of players is right
		if (outOfTime && numPlayer < Constants.MINPLAYERS) {
			suspendGame();
		} else {
			startGame();
		}
		gameHandler.notify();
	}

	private void firstConnection() {
		firstConnected = false;
		numPlayer = 0;
		System.out.println("Waiting for first connection");
		while (!isFirstConn()) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// nothing to do; the sleep is fundamental to make the cycle work
			}
		}
		startTimer();
		outOfTime = false;
	}

	public void startGame() {
		gameHandler.setStarted();
		gameHandler.setNumPlayer(numPlayer);
		gameHandler.gameTools();
	}

	private void startTimer() {
		timer = new Timer();
		timer.schedule(new MyTimer(this), 5 * 60 * 1000);
	}

	protected void firstConn() {
		firstConnected = true;
	}

	protected boolean isFirstConn() {
		return firstConnected;
	}

	@Override
	public synchronized void timeout() {
		outOfTime = true;
	}

	protected void incrementNumPlayer() {
		numPlayer++;
	}

	public void suspendGame() {
		gameHandler.setSuspended();
	}

	public int getNumPlayer() {
		return numPlayer;
	}
}
