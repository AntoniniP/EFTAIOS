package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.Human;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
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

	private static Map<Integer, GameHandler> gameHandlerList;

	public GameEngineImpl() { // Constructor
		gameHandlerList = new HashMap<Integer, GameHandler>();
	}

	public static GameHandler getGameHandler(int gameID) {
		return gameHandlerList.get(gameID);
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
		int humanKilled=0;
		int alienKilled=0;
		
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		if (p.getCanAttack()) {
			for (int i = 0; i < gameHandlerList.get(gameID).getPlayerList().size(); i++) {
				Player p1 = gameHandlerList.get(gameID).getPlayerList().get(i);
				if (!(p.equals(p1)) && !p1.isSuspended()) {
					if ((p.getCurrentSector()).equals(p1.getCurrentSector())) {
						if (p1 instanceof Human) {
							if (!((Human) p1).hasShield()) {
								humanKilled++;
							}
						} else {
							alienKilled++;
						}
						p1.setDead(true);
					}
				}
			}
			if (p instanceof Alien && (humanKilled>0)) {
				p.setMoves(3);
			}
			return "OK"+"_"+ humanKilled +"_"+ alienKilled;
		}
		return "KO";
	}

	@Override
	public void useCard(int playerID, int gameID, int posCard) throws RemoteException {
		Player p = gameHandlerList.get(gameID).getPlayerList().get(playerID);
		ItemCard c = p.removeCard(posCard);
		c.action(p);
	}

	@Override
	public String getCards(int playerID, int gameID) throws RemoteException { // ITEM CARDS
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		return p.getPlayerCards();
	}

	@Override
	public String getAdjacentSectors(int playerID, int gameID) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		List<Sector> adjacentSectors = gameHandlerList.get(gameID).getTable()
				.adjacent(p.getCurrentSector(), p.getMoves());
		String toReturn = "";
		for (Sector sector : adjacentSectors) {
			toReturn = toReturn + sector.toString() + ";";
		}
		return toReturn;
	}

	@Override
	public boolean isEnded(int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).isEnded();
	}

	@Override
	public String drawCard(int gameID, String deck) throws RemoteException {
		if ("DS".equals(deck)) {
			return (gameHandlerList.get(gameID).getDangerousSectorDeck().drawCard()).getCard();
		}
		if ("IC".equals(deck)) {
			return (gameHandlerList.get(gameID).getItemCardDeck().drawCard()).getCard();
		}
		if ("EH".equals(deck)) {
			return (gameHandlerList.get(gameID).getEscapeHatchDeck().drawCard()).getCard();
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
	public void notifyWin(int gameID, int playerID) throws RemoteException {
		if (!gameHandlerList.get(gameID).isMyTurn(playerID)) {
			gameHandlerList.get(gameID).escapedNotify(); // Problem: the other players are in
			// waiting!!!!
		}

	}

	@Override
	public boolean canAttack(int gameID, int playerID) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		return p.getCanAttack();
	}

	@Override
	public String move(int playerID, int gameID, String sector) throws RemoteException {

		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		Table t = gameHandlerList.get(gameID).getTable();
		Sector s = CommonMethods.toSector(sector, t);

		return p.move(t, s);
	}

	@Override
	public boolean isDead(int playerID, int gameID) throws RemoteException{
		return gameHandlerList.get(gameID).getPlayerList().get(playerID).isDead();
	}

	
}