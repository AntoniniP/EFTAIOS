package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server {

	private static final int port = 1099;

	private Registry registry;
	private Table table;
	private PlayerList playerList;
	private GameEngine game;
	private RMIInterface rmiInterface;
	private Timer timer;

	private boolean outOfTime;
	private boolean firstConn;
	private int numPlayer;
	//private boolean isStarted;
	/*public boolean getIsStarted() 
	 * return isStarted;*/
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	private void start() {
		table = new Table();
		playerList = new PlayerList(0);// cancel the 0
		game = new GameEngineImpl(table);
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

		waitFirstConn();
		startTimer();
		waitConn();
	}

	private void waitConn() {
		while (!outOfTime && numPlayer < 8) {
		}

		timer.cancel(); // in case the timer is out AND the number of players is right
		if (outOfTime && numPlayer < 2){
			System.out.println("Sorry.The game won't start.");
		}
			
		else
			startGame();

	}

	private void startGame() {
		/*Game game = new Game ---->Creation of the class Game, extending thread, maybe?
		 * isStarted=true;*/

	}

	private void startTimer() {
		timer = new Timer();
		timer.schedule(new MyTimer(this), 5 * 60 * 1000);
	}

	private void waitFirstConn() {
		System.out.println("Waiting first connection");
		while (!firstConn) {
		}

	}

	public void firstConn() {
		firstConn = true;
	}

	public boolean getFirstConn() {
		return firstConn;
	}

	public synchronized void timeout() {
		outOfTime = true;
	}

	public void incrementNumPlayer() {
		numPlayer++;

	}
	/*public int getNumPlayer()
	 * return numPlayer*/

	public void addPlayer(Player player) {
		playerList.add(player);
	}

}
