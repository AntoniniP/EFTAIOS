package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class for the server package. Provides some useful methods to wait the connections.
 *
 * @author Laura Castiglia
 *
 */
public class Server implements TimerInterface {

	private static final int port = 1099;

	private Registry registry;

	private GameEngine game;
	private RMIInterface rmiInterface;
	private Timer timer;

	private boolean outOfTime;
	private boolean firstConnected;
	private int numPlayers;
	private boolean started;
	private boolean suspended;
	private GameHandler gameHandler;
	private int gameID = 0;

	/**
	 * Main method of the class. Creates an instantiation of the class and calls the start() method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	/**
	 * Binds the remote methods on the registry and waits for connections by calling the waitConn()
	 * method.
	 */
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
		System.out.println("Welcome to EFTAIOS server.");

		while (true) {
			numPlayers = 0;
			this.waitConn();
			gameID++;
		}

	}

	/**
	 * Waits for the first connection, then checks the number of players.
	 */
	private void waitConn() {

		this.firstConnection();

		while (!outOfTime && (numPlayers < ServerConstants.MAXPLAYERS)) {
			CommonMethods.sleep(500);
		}
		timer.cancel(); // in case the timer is out AND the number of players is right

		if (outOfTime && numPlayers < ServerConstants.MINPLAYERS) {
			synchronized (gameHandler) {
				this.suspendGame();
				gameHandler.notify();
			}
			return;
		}
		this.startGame();

	}

	/**
	 * Waits for the first connection, then starts a new GameHandler thread.
	 */
	private void firstConnection() {
		firstConnected = false;
		System.out.println("Waiting for the first connection to start a new game.");

		gameHandler = new GameHandler();
		ExecutorService newGame = Executors.newSingleThreadExecutor();
		newGame.submit(gameHandler);
		((GameEngineImpl) game).addGame(gameID, gameHandler);
		while (!this.isFirstConn()) {
			CommonMethods.sleep(500);
		}

		this.startTimer();
	}

	/**
	 * Starts the timer to check whether a sufficient number of players is connected to the game.
	 */
	private void startTimer() {
		timer = new Timer();
		outOfTime = false;
		timer.schedule(new MyTimer(this), 5 * 60 * 1000);
	}

	/**
	 * Concretely starts the game, by setting the number of players, creating the needed things and
	 * setting the instantiation of the class as started. Eventually notifies the gameHandler to
	 * start.
	 */
	private void startGame() {
		synchronized (gameHandler) {
			gameHandler.setNumPlayer(numPlayers);
			gameHandler.gameTools();
			gameHandler.setStarted();
			gameHandler.notify();
		}
	}

	/**
	 * Sets the firstConnected variable to true.
	 */
	protected void firstConn() {
		firstConnected = true;
	}

	/**
	 * Returns whether the first player is connected to a new game.
	 *
	 * @return
	 */
	protected boolean isFirstConn() {
		return firstConnected;
	}

	@Override
	public synchronized void timeout() {
		outOfTime = true;
	}

	/**
	 * Increments the number of players.
	 */
	protected void incrementNumPlayer() {
		numPlayers++;
	}

	/**
	 * Suspends the game.
	 */
	public void suspendGame() {
		synchronized (gameHandler) {
			gameHandler.setSuspended();
			gameHandler.notify();
		}
	}

	/**
	 * Returns the number of players.
	 *
	 * @return the number of players
	 */
	public int getNumPlayer() {
		return numPlayers;
	}

	/**
	 * Returns the gameID.
	 *
	 * @return the gameID
	 */
	public int getGameID() {
		return gameID;
	}
}
