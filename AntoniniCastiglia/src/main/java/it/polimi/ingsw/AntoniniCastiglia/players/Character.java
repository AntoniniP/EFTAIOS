package it.polimi.ingsw.AntoniniCastiglia.players;

public enum Character {
	CAPTAIN("Ennio Maria Dominoni", "Captain", 'H'), 
	PILOT("Julia Niguloti","Pilot", 'H'), 
	PSYCHOLOGIST("Silvano Porpora", "Psychologist", 'H'), 
	SOLDIER("Tuccio Brendon", "Soldier", 'H'),
	FIRST("Piero Ceccarella", "First Alien", 'A'), 
	SECOND("Vittorio Martana", "Second Alien", 'A'), 
	THIRD("Maria Galbani", "Third Alien", 'A'), 
	FOURTH("Paolo Landon", "Fourth Alien", 'A');

	 String name;
	 String role;
	 char nature;

	Character(String name, String role, char c) {
		this.name = name;
		this.role = role;
		this.nature = c;
	}
	
}
