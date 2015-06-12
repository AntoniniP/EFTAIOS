package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
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

	private Map<Integer, GameHandler> gameHandlerList;

	public GameEngineImpl() { // Constructor
		this.gameHandlerList = new HashMap<Integer, GameHandler>();
	}

	public void addGame(int gameID, GameHandler game) {
		gameHandlerList.put(gameID, game);
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
	public String move(int playerID, int gameID, String sector) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		Sector s = CommonMethods.toSector(sector, gameHandlerList.get(gameID).getTable());
		p.setCurrentSector(s);
		return "You are now in Sector" + p.getCurrentSector();
	}

	@Override
	public String attack(int playerID, int gameID) throws RemoteException {
		boolean humanKilled = false;
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		for (int i = 0; i < gameHandlerList.get(gameID).getPlayerList().size(); i++) {
			Player p1 = gameHandlerList.get(gameID).getPlayerList().get(i);
			if (!(p.equals(p1)) && !p1.isSuspended()) {
				if ((p.getCurrentSector()).equals(p1.getCurrentSector())) {
					if (p1 instanceof Human) {
						if (!((Human) p1).hasShield()) {
							humanKilled = true;
							p1.isDead();
						} else
							;
					}
				}
			}
		}
		if (p instanceof Alien && humanKilled) {
			p.setMoves(3);
		}
		return "Attack in " + p.getCurrentSector();
	}

	@Override
	public void useCard(int playerID, int gameID, int posCard) throws RemoteException {
		Player p = gameHandlerList.get(gameID).getPlayerList().get(playerID);
		ItemCard c = p.removeCard(posCard);
		c.action(p);
	}

	@Override
	// Returns the item cards a player owns.
	public String getCards(int playerID, int gameID) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		String cards = p.getPlayerCards();
		return cards;
	}

	@Override
	public String getAdjacentSectors(int playerID, int gameID) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, gameHandlerList.get(gameID).getPlayerList());
		List<Sector> adjacents = gameHandlerList.get(gameID).getTable()
				.adjacent(p.getCurrentSector(), p.getMoves());
		String toReturn = "";
		for (Sector sector : adjacents) {
			toReturn = toReturn + sector.toString() + ";";
		}
		return toReturn;
	}

	@Override
	public boolean isEnded(int gameID) throws RemoteException {
		gameHandlerList.get(gameID).endGame(); //method with the output strings
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
	public boolean isStarted(int gameID) throws RemoteException {
		return gameHandlerList.get(gameID).isStarted();
	}

	@Override
	public boolean isMyTurn(int playerID, int gameID) throws RemoteException {
		gameHandlerList.get(gameID).isMyTurn(playerID);
		return false;
	}

	@Override
	public void notifyWin(int gameID, int playerID) throws RemoteException {
		if(!gameHandlerList.get(gameID).isMyTurn(playerID))//if it is not my turn
			gameHandlerList.get(gameID).escapedNotify(); //Problem: the other players are in waiting!!!!
		
	}
}