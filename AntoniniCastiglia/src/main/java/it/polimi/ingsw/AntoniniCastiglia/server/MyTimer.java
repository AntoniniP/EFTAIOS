package it.polimi.ingsw.AntoniniCastiglia.server;

import java.util.TimerTask;

public class MyTimer extends TimerTask {
	 private TimerInterface caller;
     
	    public MyTimer(TimerInterface caller){
	        this.caller=caller;
	    }
	 
	    @Override
	    public void run() {
	       caller.timeout();
	    }
	 
}
