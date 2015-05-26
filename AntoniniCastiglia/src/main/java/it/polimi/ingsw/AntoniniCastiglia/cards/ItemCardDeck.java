package it.polimi.ingsw.AntoniniCastiglia.cards;

import java.util.ArrayList;

import it.polimi.ingsw.AntoniniCastiglia.Constants;

public class ItemCardDeck extends Deck {

	/**
	 * Constructor for the deck of item cards. The deck is created and shuffled.
	 */
	public ItemCardDeck() {

		for (int i = 0; i < Constants.ADRENALINECARD; i++) {
			deck.add(new AdrenalineCard());
		}
		for (int i = 0; i < Constants.ATTACKCARD; i++) {
			deck.add(new AttackCard());
		}
		for (int i = 0; i < Constants.DEFENSECARD; i++) {
			deck.add(new DefenseCard());
		}
		for (int i = 0; i < Constants.SEDATIVESCARD; i++) {
			deck.add(new SedativesCard());
		}
		for (int i = 0; i < Constants.SPOTLIGHTCARD; i++) {
			deck.add(new SpotlightCard());
		}
		for (int i = 0; i < Constants.TELEPORTCARD; i++) {
			deck.add(new TeleportCard());
		}
		shuffleDeck();
	}

	/*
	 * public static void main(String[] args) { Deck d = new ItemCardDeck(); for
	 * (int i = 0; i < (Constants.ADRENALINECARD + Constants.ATTACKCARD +
	 * Constants.DEFENSECARD + Constants.SEDATIVESCARD + Constants.SPOTLIGHTCARD
	 * + Constants.TELEPORTCARD); i++) { System.out.println(i + 1 + " " +
	 * d.drawCard()); } }
	 */

}
