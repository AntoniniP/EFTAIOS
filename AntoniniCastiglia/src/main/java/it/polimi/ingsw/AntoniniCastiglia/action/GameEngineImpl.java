package it.polimi.ingsw.AntoniniCastiglia.action;

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
	public void move(Player p, Sector s) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Player updatePlayer(Player p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException {
		return table.adjacent(p.getCurrenSector(), p.getHops());
	}

}
