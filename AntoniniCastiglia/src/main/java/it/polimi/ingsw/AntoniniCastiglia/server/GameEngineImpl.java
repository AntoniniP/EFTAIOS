package it.polimi.ingsw.AntoniniCastiglia.server;

import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import it.polimi.ingsw.AntoniniCastiglia.players.Player;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine {
	private Table table;
	
	 public GameEngineImpl(Table table){ //Constructor; I have a map to work with
	        this.table=table; //to put in another class GAME together with cards & stuff
	    }
	 
	    @Override
	    public ArrayList<Sector> adjacentSectors(Player p) throws RemoteException {
	        return table.adjacent(p.getCurrentSector(), p.getHops());
	    }
	 
	    @Override
	    public String getMap() throws RemoteException {
	        return table.drawMap();
	    }
	 
	    @Override
	    public String move(String player, String sector) throws RemoteException {
	       // p.setCurrentSector(s);
	        //return "You are now in Sector"+ p.getCurrentSector();
	    	return "ABC";
	    }
	 
	    @Override
	    public String attack(Player p) throws RemoteException {
	        p.setAttack(true);
	         
	        return "Attack in " + p.getCurrentSector();
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
}