package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine {
	private Table table;
	
	public GameEngineImpl(Table table){ //Constructor; I have a map to work with
		this.table=table;
	}

	@Override
	public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException {
		return table.adjacent(p.getCurrenSector(), p.getHops());
	}

	@Override
	public String connect() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMap() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String move(Player p, Sector s) throws RemoteException {
		p.setCurrentSector(s);
		return "You are now in Sector"; /* p.getCurrentSector +"*/
	}

	@Override
	public String attack(Player p) throws RemoteException {
		return "Attack in "; /*+ p.getCurrentSector+".";*/
	}

	@Override
	public String getCards() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String useCard() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player updatePlayer(Player p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
