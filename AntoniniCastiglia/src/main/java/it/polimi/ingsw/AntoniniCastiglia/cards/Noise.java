package it.polimi.ingsw.AntoniniCastiglia.cards;

public class Noise implements DangerousSectorCard {
	
	private boolean yourSector;
	private boolean withObject;
	
	public Noise(boolean yourSector, boolean withObject){
		this.yourSector=yourSector;
		this.withObject=withObject;
	}
	@Override
	public String toString() {
		String message;
		
		if(yourSector==true)
			message="Rumor in My Sector:......";
		else
			message="Rumor in Any Sector:......";
		if(withObject==true)
			message=message + "Pick an Item Card!";
		return message;
	}
	
	@Override
	public void action(/*Sector rumorOn*/) { //this method would be useful with an argument such as this one
		//TODO Notify noise to all players
		//Do rumor (all the players should take account of the rumor event)
		//Save records on a list, for instance (one list for each player)
	}
}
