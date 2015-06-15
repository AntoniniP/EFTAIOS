package it.polimi.ingsw.AntoniniCastiglia.players;

/**
 * This enumeration contains all characters of the game. Every character has a name, a role and a
 * nature (human/alien).
 *
 * @author Paolo Antonini
 *
 */
public enum Character {
	CAPTAIN("Ennio Maria Dominoni", "Captain", 'H'), 
	PILOT("Julia Niguloti", "Pilot", 'H'), 
	PSYCHOLOGIST("Silvano Porpora", "Psychologist", 'H'), 
	SOLDIER("Tuccio Brendon", "Soldier", 'H'), 
	FIRST("Piero Ceccarella", "First Alien", 'A'), 
	SECOND("Vittorio Martana", "Second Alien", 'A'), 
	THIRD("Maria Galbani", "Third Alien", 'A'), 
	FOURTH("Paolo Landon", "Fourth Alien", 'A');

	String name;
	String role;
	char nature;

	/**
	 * Basic constructor for the enumeration.
	 *
	 * @param name the name in the game
	 * @param role the role in the crew, as stated in the game rules
	 * @param c character to identify the player's nature (alien/human)
	 */
	Character(String name, String role, char c) {
		this.name = name;
		this.role = role;
		nature = c;
	}

}
