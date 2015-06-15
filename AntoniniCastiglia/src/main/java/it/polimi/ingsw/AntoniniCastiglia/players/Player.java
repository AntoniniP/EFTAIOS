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
	
	
	
	
	
	
	public  boolean canUseCards(){
		for (ItemCard card:items) {
			if (card!=null){
				return true;
			}
		}
		return false;
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
			return "OK" + "_" + this.currentSector + "_" + this.currentSector.getType();
		}
		return "KO";
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

	


	
}
