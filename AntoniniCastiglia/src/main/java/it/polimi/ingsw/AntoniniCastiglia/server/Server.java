package it.polimi.ingsw.AntoniniCastiglia.server;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private boolean suspended ;
	private GameHandler gameHandler;
	private int gameID = 0;

	
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
		System.out.println("Welcome to EFTAIOS server.");

		while (true) {
			numPlayers = 0;
			waitConn();
			gameID++;
		}

	}

	private void waitConn() {

		firstConnection();

		while (!outOfTime && (numPlayers < ServerConstants.MAXPLAYERS)) {
			CommonMethods.doMagic(500);
		}
		timer.cancel(); // in case the timer is out AND the number of players is right

		if (outOfTime && numPlayers < ServerConstants.MINPLAYERS) {
			synchronized (gameHandler) {
				suspendGame();
				gameHandler.notify();
			}
			return;
		}
		startGame();

	}

	private void firstConnection() {
		firstConnected = false;
		System.out.println("Waiting for the first connection to start a new game.");
	
		gameHandler = new GameHandler();
		ExecutorService newGame = Executors.newSingleThreadExecutor();
		newGame.submit(gameHandler);
		((GameEngineImpl) game).addGame(gameID, gameHandler);
		
		
		
		
		/*TODO***************************ANDREA GUARDA QUI******************************TODO*/
		/* devo aggiungere il gameHandler alla lista molto presto, per evitare NullPointerException.
		 * facendo così, quando sincronizzo in  startGame(), sto sincronizzando sul
		 * gameHandler corretto, già aggiunto alla lista. 
		 * ma non sono per niente sicuro che vada bene.
		 */
		//gameHandler=GameEngineImpl.getGameHandler(gameID);
		/************************************************************************************/


		
		while (!isFirstConn()) {
			CommonMethods.doMagic(500);
		}
		
		startTimer();
	}

	
	
	
	private void startTimer() {
		timer = new Timer();
		outOfTime = false;
		timer.schedule(new MyTimer(this), 5 * 60 * 1000);
	}

	private void startGame() {
		synchronized (gameHandler) {
			gameHandler.setNumPlayer(numPlayers);
			gameHandler.gameTools();
			gameHandler.setStarted();
			gameHandler.notify(); 
		}
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
		numPlayers++;
	}

	public void suspendGame() {
		synchronized (gameHandler) {
			gameHandler.setSuspended();
			gameHandler.notify();
		}
	}

	public int getNumPlayer() {
		return numPlayers;
	}

	public int getGameID() {
		return gameID;
	}
}
