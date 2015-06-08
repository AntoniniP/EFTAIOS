package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.cards.Card;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.Deck;
import it.polimi.ingsw.AntoniniCastiglia.cards.EscapeHatchDeck;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCardDeck;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Alien;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import it.polimi.ingsw.AntoniniCastiglia.players.PlayerList;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GameEngineImpl implements GameEngine {

	private Table table;
	private Deck dangerousSectorDeck;
	private Deck itemCardDeck;
	private Deck escapeHatchDeck;
	private PlayerList playerList;

	public GameEngineImpl() { // Constructor
	}

	public void createMap() {
		// to put in another class GAME together with cards & stuff
		table = new Table();
	}

	public void createPlayers(int numPlayers) {
		playerList = new PlayerList(numPlayers);
	}

	public void createDecks() {
		dangerousSectorDeck = new DangerousSectorDeck();
		itemCardDeck = new ItemCardDeck();
		escapeHatchDeck = new EscapeHatchDeck();
	}

	@Override
	public String getPlayerString(int playerID) {
		return (playerList.get(playerID)).toString();
	}

	@Override
	public String getMap() throws RemoteException {
		return table.drawMap();
	}

	@Override
	public String move(int playerID, String sector) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, playerList);

		Sector s = CommonMethods.toSector(sector, table);
		p.setCurrentSector(s);
		return "You are now in Sector" + p.getCurrentSector();
	}

	@Override
	public String attack(int playerID) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, playerList);
		for (int i = 0; i < playerList.size(); i++) {
			Player p1 = playerList.get(i);

			// TODO equals!
			if (!(p == p1)) {
				if ((p.getCurrentSector()).equals(p1.getCurrentSector())) {
					if (!(p1.hasShield())) {
						p1.isDead();
					}
				}
			}
		}
		if (p instanceof Alien) {
			p.setMoves(3);
		}
		return "Attack in " + p.getCurrentSector();
	}

	@Override
	public Card useCard(String card, int playerID ) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCards(int playerID) throws RemoteException {// better to be called drawCard
		Player p = CommonMethods.toPlayer(playerID, playerList);
		String cards = p.getPlayerCards();
		return cards;
	}

	@Override
	public String getAdjacents(int playerID) throws RemoteException {
		Player p = CommonMethods.toPlayer(playerID, playerList);
		List<Sector> adjacents = table.adjacent(p.getCurrentSector(), p.getHops());
		String toReturn = "";
		for (Sector sector : adjacents) {
			toReturn = toReturn + sector.toString() + ";";
		}
		return toReturn;
	}

	@Override
	public boolean isEnded() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String drawCard(String deck) throws RemoteException {
		if ("DS".equals(deck)) {
			return (dangerousSectorDeck.drawCard()).getCard();
		}
		if ("IC".equals(deck)) {
			return (itemCardDeck.drawCard()).getCard();
		}
		if ("EH".equals(deck)) {
			return (escapeHatchDeck.drawCard()).getCard();
		}
		return null;
	}

}