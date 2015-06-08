package it.polimi.ingsw.AntoniniCastiglia.server;

import java.util.TimerTask;

public class MyTimer extends TimerTask {
	 private Server server;
     
	    public MyTimer(Server server){
	        this.server=server;
	    }
	 
	    @Override
	    public void run() {
	        server.timeout();
	    }
	 
}
