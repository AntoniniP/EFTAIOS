package it.polimi.ingsw.AntoniniCastiglia.players;

import it.polimi.ingsw.AntoniniCastiglia.cards.ItemCard;
import it.polimi.ingsw.AntoniniCastiglia.maps.Sector;
import it.polimi.ingsw.AntoniniCastiglia.maps.Table;
import java.util.ArrayList;
import java.util.List;

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

	private String journal = "";

	private List<Sector> path = new ArrayList<Sector>();
	private ItemCard[] items = new ItemCard[3];
	private boolean dead;
	private boolean suspended;
	private boolean active;
	protected boolean canAttack;

	/**
	 * Constructor for the class. It sets some parameters, such as the name, role and nature of the
	 * player. It will always be called by the constructors of concrete classes Alien and Human.
	 *
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param nature player's nature (alien/human)
	 */
	protected Player(String name, String role, String nature) {
		this.name = name;
		this.role = role;
		this.nature = nature;
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
	 * Setter for <code>currentSector</code> variable.
	 * 
	 * @param currentSector the currentSector to set
	 */
	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
	}

	/**
	 * Getter for the journal.
	 * 
	 * @return the journal
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * Updates the journal with a new string.
	 * 
	 * @param s the string to add
	 */
	public void updateJournal(String s) {
		journal = journal.concat(s + "\n");
	}

	/**
	 * Resets the journal to an empty one.
	 */
	public void resetJournal() {
		journal = "";
	}

	/**
	 * Returns whether the player can attack.
	 * 
	 * @return whether the player can attack
	 */
	public boolean getCanAttack() {
		return canAttack;
	}

	/**
	 * Setter for canAttack variable
	 * 
	 * @param canAttack
	 */
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	/**
	 * Checks whether the player can use cards.
	 * 
	 * @return the result of the check
	 */
	public boolean canUseCards() {
		for (ItemCard card : items) {
			if (card != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns how many cards a player has.
	 * 
	 * @return the result of the count
	 */
	public int howManyCards() {
		int toReturn = 0;
		for (ItemCard card : items) {
			if (card != null) {
				toReturn++;
			}
		}
		return toReturn;
	}

	/**
	 * Standard getter for <code>maxMoves</code>.
	 *
	 * @return maxMoves the maximum number of sectors a player can reach
	 */
	public int getMoves() {
		return maxMoves;
	}

	/**
	 * Standard setter for <code>maxMoves</code>.
	 *
	 * @param moves the new value for <code>maxMoves</code>
	 */
	public void setMoves(int moves) {
		maxMoves = moves;
	}

	/**
	 * Getter for dead variable.
	 * 
	 * @return whether the player is dead
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Setter for dead variable.
	 * 
	 * @param dead
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * Getter for suspended variable.
	 * 
	 * @return whether the player is suspended
	 */
	public boolean isSuspended() {
		return suspended;
	}

	/**
	 * Suspends the player, by setting it's suspended variable to true.
	 */
	public void suspend() {
		suspended = true;
	}

	/**
	 * Getter for active variable.
	 * 
	 * @return whether the player is active or not
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Setter for active variable.
	 * 
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * Getter for the cards a player owns. It's returned as a string, separated by <code>;</code>
	 * character.
	 *
	 * @return the list a player owns, in form of a string
	 */
	public String getPlayerCards() {
		String toReturn = "";
		for (int i = 0; i < items.length; i++) {
			try {
				toReturn = toReturn.concat(items[i].toString() + ";");
			} catch (NullPointerException e) {
				toReturn = toReturn.concat("null" + ";");
			}
		}
		return toReturn;
	}

	/**
	 * Removes a card from the player's carnet.
	 * 
	 * @param cardIndex index of the card to remove
	 * @return the card
	 */
	public ItemCard removeCard(int cardIndex) {
		ItemCard c = items[cardIndex];
		items[cardIndex] = null;
		return c;
	}

	/**
	 * Switches the card received as a parameter with the one at index cardIndex. If cardIndex is 0,
	 * then the discarded card is the one received as a parameter.
	 * 
	 * @param card
	 * @param cardIndex
	 * @return the discarded card
	 */
	public ItemCard switchCard(ItemCard card, int cardIndex) {
		if (cardIndex == 0) {
			return card;
		} else {
			ItemCard toRemove = this.removeCard(cardIndex - 1);
			this.addItemCard(card);
			return toRemove;
		}
	}

	/**
	 * Adds item card to the player's deck; if there isn't any space left, returns false to server.
	 * 
	 * @param c card to add
	 * @return the result of the operation
	 */
	public boolean addItemCard(ItemCard c) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = c;
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter for the path variable.
	 * 
	 * @return the path
	 */
	public List<Sector> getPath() {
		return path;
	}

	/**
	 * Moves a player to a new sector, if it's valid. Returns "KO" otherwise.
	 * 
	 * @param t the table we are working on
	 * @param newCurrentSector
	 * @return the result of the operation
	 */
	public String move(Table t, Sector newCurrentSector) {
		if ((t.adjacent(currentSector, maxMoves)).contains(newCurrentSector)) {
			currentSector = newCurrentSector;
			path.add(currentSector);
			return "OK" + "_" + currentSector + "_" + currentSector.getType();
		}
		return "KO";
	}

	/**
	 * Lets the player attack the sector where he is now. If he can't attack, returns "KO".
	 * Otherwise returns the result of the operation.
	 * 
	 * @param playerList
	 * @return
	 */
	public String attack(PlayerList playerList) {
		if (canAttack) {
			int humanKilled = 0;
			int alienKilled = 0;
			for (int i = 0; i < playerList.size(); i++) {
				Player p1 = playerList.get(i);
				if (!(this.equals(p1)) && !p1.isSuspended()) {
					if ((currentSector).equals(p1.getCurrentSector())) {
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
			if (this instanceof Alien && (humanKilled > 0)) {
				maxMoves = 3;
			}
			return "OK" + "_" + humanKilled + "_" + alienKilled;
		}
		return "KO";
	}

	@Override
	public String toString() {
		return name + "_" + role + "_" + nature + "_" + maxMoves + "_" + currentSector + "_"
				+ canAttack;
	}

}
