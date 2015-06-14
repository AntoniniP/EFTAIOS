package it.polimi.ingsw.AntoniniCastiglia.players;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.cards.DangerousSectorCard;
import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.client.ClientConstants;
import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;

/**
 * Every instance of this class is a Player. The class is extended by Alien and Human, which
 * concretely set all parameters. Here we have basic getters and setters for some variables, and a
 * toString method.
 * 
 * @author Paolo Antonini
 *
 */
public abstract class Player {

	private String name;
	private String role;
	private String nature;
	protected int maxMoves;
	protected Sector myBase;
	protected Sector currentSector;
	private List<Sector> path = new ArrayList<Sector>();
	private ItemCard[] items = new ItemCard[3];
	private boolean dead;
	private boolean suspended;
	private boolean active;
	protected boolean canAttack;
	private boolean hasMoved=false;
	private boolean  hasAttacked=false;
	private boolean mustDrawDSCard=false;
	private boolean hasDrawnDSCard=false;
	private boolean mustDrawEHCard=false;
	private boolean mustDeclareNoise=false;
	private boolean mustDrawICard=false;
	
	
	private DangerousSectorCard dsc;
	private ItemCard ic;
	
	
	private boolean canUseCards(){
		for (ItemCard card:items) {
			if (card!=null){
				return true;
			}
		}
		return false;
	}
	
	
	public void resetPlayer(){
		hasMoved=false;
		hasAttacked=false;
		mustDrawDSCard=false;
		hasDrawnDSCard=false;
		mustDrawEHCard=false;
	}
	
	/**
	 * Constructor for the class. It sets some parameters, such as the name, role, nature and ID of
	 * the player. It will always be called by the constructors of concrete classes Alien and Human.
	 * 
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param nature player's nature (alien/human)
	 * @param id integer value to identify the player
	 */
	protected Player(String name, String role, String nature) {
		this.name = name;
		this.role = role;
		this.nature = nature;
	}

	@Override
	public String toString() {
		return name + "_" + role + "_" + nature + "_" + maxMoves + "_" + currentSector + "_"
				+ canAttack;
	}

	/**
	 * Standard getter for <code>maxMoves</code>.
	 * 
	 * @return maxMoves the maximum number of sectors a player can reach
	 */
	public int getMoves() {
		return maxMoves;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * Standard setter for <code>maxMoves</code>.
	 * 
	 * @param moves the new value for <code>maxMoves</code>
	 */
	public void setMoves(int moves) {
		this.maxMoves = moves;
	}

	/**
	 * Standard getter for <code>myBase</code>.
	 * 
	 * @return the base of the player
	 */
	public Sector getMyBase() {
		return myBase;
	}

	/**
	 * Standard getter for <code>currentSector</code> variable.
	 * 
	 * @return the current sector a player is in
	 */
	public Sector getCurrentSector() {
		return currentSector;
	}

	/**
	 * Getter for the cards a player owns. It's returned as a string, separated by <code>;</code>
	 * character.
	 * 
	 * @return the list a player owns, in form of a string
	 */
	public String getPlayerCards() {
		String toReturn = "";
		for (int i = 0; i < items.length; i++) {
			toReturn = toReturn + items[i] + ";";
		}
		return toReturn;
	}

	public ItemCard removeCard(int i) {
		ItemCard c = items[i];
		items[i] = null;
		for (int j = i; j < items.length - 1 && items[j + 1] != null; j++)
			items[j] = items[j + 1]; // re-ordering the not null-cards
		return c;
	}

	public ItemCard switchCard(ItemCard c, int i) {
		ItemCard toRemove = this.removeCard(i);
		addItemCard(c);
		return toRemove;
	}

	public boolean addItemCard(ItemCard c) { // adding item card to the player's deck; if there
												// isn't any space left, return false to server
		for (int i = 0; i < items.length; i++)
			if (items[i] == null) {
				items[i] = c;
				return true;
			}
		return false;
	}

	public void suspend() {
		suspended = true;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String move(Table t, Sector newCurrentSector) {
		if ((t.adjacent(currentSector, maxMoves)).contains(newCurrentSector)) {
			this.currentSector = newCurrentSector;
			path.add(currentSector);
			
			hasMoved=true;
			mustDrawDSCard= this.currentSector.getMustDrawDSCard();
			mustDrawEHCard = this.currentSector.getMustDrawEHCard();
			return "OK" + "_" + this.currentSector + "_" + this.currentSector.getType() 
					//+ "_" + this.currentSector.getMustDrawDSCard()
					//+ "_" + this.currentSector.getMustDrawEHCard()
					;
		}
		return "KO";
	}

	public void setMustDrawDSCard(boolean mustDrawDSCard){
		this.mustDrawDSCard=mustDrawDSCard;
	}
	public void setHasDrawnDSCard(boolean hasDrawnDSCard){
		this.hasDrawnDSCard=hasDrawnDSCard;
	}
	public void setMustDrawICard(boolean mustDrawICard){
		this.mustDrawICard=mustDrawICard;
	}
	public void setMustDrawEHCard(boolean mustDrawEHCard){
		this.mustDrawEHCard=mustDrawEHCard;
	}
	
	public String attack(PlayerList playerList){
		
		if (canAttack) {
			int humanKilled=0;
			int alienKilled=0;
			for (int i = 0; i < playerList.size(); i++) {
				Player p1 = playerList.get(i);
				if (!(this.equals(p1)) && !p1.isSuspended()) {
					if ((this.currentSector).equals(p1.getCurrentSector())) {
						if (p1 instanceof Human) {
							if (!((Human) p1).hasShield()) {
								humanKilled++;
							} else {
								// TODO remove DefenseCard!!!
							}
						} else {
							alienKilled++;
						}
						p1.setDead(true);
					}
				}
			}
			if (this instanceof Alien && (humanKilled>0)) {
				maxMoves=3;
			}
			hasAttacked=true;
			return "OK"+"_"+ humanKilled +"_"+ alienKilled;
		}
		return "KO";
	}
	
	public boolean getCanAttack(){
		return canAttack;
	}
	
	/**
	 * @return the path
	 */
	public List<Sector> getPath() {
		return path;
	}

	public String chooseAction() {
		
		String toReturn = "";

		if (!hasMoved) {
			toReturn=toReturn.concat(Constants.MOVE +"_");
		}
		if (mustDrawDSCard && !hasAttacked) {
			toReturn=toReturn.concat(Constants.DRAW_DS_CARD+"_" );
		}
		if (mustDeclareNoise){
			toReturn=toReturn.concat(Constants.DECLARE_NOISE+"_");
		}
		if (mustDrawICard){
			toReturn = toReturn.concat(Constants.DRAW_I_CARD+"_");
		}
		if (canAttack && !hasAttacked && !hasDrawnDSCard) {
			toReturn=toReturn.concat(Constants.ATTACK +"_");
		}
		if (canUseCards()) {
			toReturn=toReturn.concat(Constants.USE_CARDS+"_" );
		}
		if (mustDrawEHCard){
			toReturn=toReturn.concat(Constants.DRAW_EH_CARD+"_");
		}
		if (hasMoved && true ) {
			toReturn=toReturn.concat(Constants.QUIT);
		}

		// TODO COMPLETARE!!!!

		return toReturn;

	}


	public void setMustDeclareNoise(boolean mustDeclareNoise) {
		this.mustDeclareNoise=mustDeclareNoise;
		
	}
}
