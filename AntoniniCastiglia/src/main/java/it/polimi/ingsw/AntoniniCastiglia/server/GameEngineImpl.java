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
		return gameHandlerList.get(gameID).attack(playerID);
	}

	@Override
	public String useCard(int playerID, int gameID, int posCard) throws RemoteException {
		Player p = gameHandlerList.get(gameID).getPlayerList().get(playerID);
		ItemCard c = p.removeCard(posCard);
		if (c instanceof ItemSpotlight){
			List<Sector> adjacentSector = gameHandlerList.get(gameID).getTable()
					.adjacent(p.getCurrentSector(), p.getMoves()); //get adjacent Sectors
			for (int i=0; i<gameHandlerList.get(gameID).getPlayerList().size(); i++){ //taking all the players in the game
				Player p1 = gameHandlerList.get(gameID).getPlayerList().get(i);
				if ((p.getCurrentSector()).equals(p1.getCurrentSector()) || adjacentSector.contains(p1.getCurrentSector())){
					String s = "player" + gameHandlerList.get(gameID).getPlayerList().get(i) + "is in sector" + p1.getCurrentSector();
					return s;
				}
					
			}
			
		}
		return null;
	}
	
	
	@Override
	public String possibleActions(int playerID, int gameID) throws RemoteException {
		String s = gameHandlerList.get(gameID).getPlayerList().get(playerID).chooseAction();		
		return s;
	}

	@Override
	public String getCards(int playerID, int gameID) throws RemoteException { // ITEM CARDS
		
		return gameHandlerList.get(gameID).getPlayerCards(playerID);
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
	public String drawCard(int gameID, int playerID, String deck) throws RemoteException {
		if ("DS".equals(deck)) {
			gameHandlerList.get(gameID).getPlayerList().get(playerID).setMustDrawDSCard(false);
			gameHandlerList.get(gameID).getPlayerList().get(playerID).setHasDrawnDSCard(true);
			gameHandlerList.get(gameID).getPlayerList().get(playerID).setMustDeclareNoise(true);
			return ((DangerousSectorCard)gameHandlerList.get(gameID).getDangerousSectorDeck().drawCard()).getCard();
		}
		if ("IC".equals(deck)) {
			gameHandlerList.get(gameID).getPlayerList().get(playerID).setMustDrawICard(false);
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
		return gameHandlerList.get(gameID).move(playerID, sector);
	}

	@Override
	public boolean isDead(int playerID, int gameID) throws RemoteException{
		return gameHandlerList.get(gameID).getPlayerList().get(playerID).isDead();
	}

	@Override
	@Deprecated
	public String useDangerousSectorCard(int playerID, int gameID) throws RemoteException {
		Player p = gameHandlerList.get(gameID).getPlayerList().get(playerID);
		String[] s= drawCard(gameID,playerID,"DS").split("_");
		String type = s[2];
		Boolean yourSector = Boolean.parseBoolean(s[3]);
		Boolean withObject = Boolean.parseBoolean(s[4]);
		if(withObject){
			drawCard(gameID,playerID,"IC");
			if(type.equals(CardsConstants.SILENCE))
				return "Silence in al sectors";
			if(type.equals(CardsConstants.NOISE)){
				if(yourSector)
					return "Noise in sector" + p.getCurrentSector();
				else
					return "Noise in sector"; //the player chooses the sector to make noise i	
			
			}
		}
		return null;
	}

	@Override
	public String declareNoise(String noise) throws RemoteException{
		// TODO Auto-generated method stub
		return "OK";
		
	}
	
}