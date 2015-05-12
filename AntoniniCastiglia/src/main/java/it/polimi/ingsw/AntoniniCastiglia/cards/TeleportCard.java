package it.polimi.ingsw.AntoniniCastiglia.cards;

import it.polimi.ingsw.AntoniniCastiglia.players.Player;

public class TeleportCard implements ObjectCard {

	@Override
	public void action(Player p) {
		p.setCurrentSector(p.getMyBase());
	}

}
