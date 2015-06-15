package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.DangerousSector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.MapConstants;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.cards.*;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the remote interface <code>GameEngine<code>.
 *
 * @author Laura Castiglia
 *
 */
public class GameEngineImpl implements GameEngine {

	private Map<Integer, GameHandler> gameHandlerList;

	public GameEngineImpl() { // Constructor
		gameHandlerList = new HashMap<Integer, GameHandler>();
	}

	public void addGame(int gameID, GameHandler game) {
		gameHandlerList.put(gameID, game);
	}

	@Override
	public boolean isStarted(int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).isStarted();
	}

	@Override
	public String getPlayer(int playerID, int gameID) throws RemoteException {
		return (gameHandlerList.get(gameID).getPlayerList().get(playerID)).toString();
	}

	@Override
	public String getMap(int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).getTable().drawMap();
	}

	@Override
	public String attack(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).attack(playerID);
	}

	@Override
	public String getItemCard(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).getItemCard(playerID);
	}

	@Override
	public String handleItemCard(int playerID, int gameID, int cardIndex) throws RemoteException {
		return gameHandlerList.get(gameID).handleItemCard(playerID, cardIndex);
	}

	@Override
	public String useCard(int playerID, int gameID, int posCard) throws RemoteException {
		return gameHandlerList.get(gameID).useCard(playerID, posCard);
	}

	@Override
	public String possibleActions(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).chooseAction(playerID);
	}

	@Override
	public String getPlayerCards(int playerID, int gameID) throws RemoteException { // ITEM CARDS
		return gameHandlerList.get(gameID).getPlayerCards(playerID);
	}

	@Override
	public String getAdjacentSectors(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).getAdjacentSectors(playerID);
	}

	@Override
	public boolean isEnded(int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).isEnded();
	}

	@Override
	public String drawCard(int gameID, int playerID, String deck) throws RemoteException {
		if ("DS".equals(deck)) {
			return gameHandlerList.get(gameID).getDangerousSectorCard(playerID);
		} else {
			System.exit(-1);
		}

		return null;
	}

	@Override
	public void endTurn(int playerID, int gameID) throws RemoteException {
		gameHandlerList.get(gameID).switchTurn();
	}

	@Override
	public boolean isMyTurn(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).isMyTurn(playerID);
	}

	@Override
	public String getJournal(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).getJournal(playerID);
	}

	@Override
	public boolean canAttack(int gameID, int playerID) throws RemoteException {
		return gameHandlerList.get(gameID).getPlayerList().get(playerID).getCanAttack();
	}

	@Override
	public String move(int playerID, int gameID, String sector) throws RemoteException {
		return gameHandlerList.get(gameID).move(playerID, sector);
	}

	@Override
	public boolean isDead(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).getPlayerList().get(playerID).isDead();
	}

	@Override
	public String declareNoise(int gameID, int playerID, String sector) throws RemoteException {
		return gameHandlerList.get(gameID).declareNoise(playerID, sector);

	}

	@Override
	public String escape(int playerID, int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).escape(playerID);
	}

}