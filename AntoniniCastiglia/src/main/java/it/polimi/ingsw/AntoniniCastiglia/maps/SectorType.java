package it.polimi.ingsw.AntoniniCastiglia.maps;

// TODO Maybe better than constants in Table?

public enum SectorType {
	EMPTY_SECTOR("Empty", '0'), 
	DANGEROUS_SECTOR("Dangerous", '1'), 
	HUMAN_BASE("Human Base", '2'), 
	ALIEN_BASE("Alien Base", '3'), 
	SECURE_SECTOR("Secure", '4'), 
	ESCAPE_HATCH("Escape Hatch", '5');

	private String name;
	private char type;

	SectorType(String name, char type) {
		this.name = new String(name);
		this.type = type;
	}

}
