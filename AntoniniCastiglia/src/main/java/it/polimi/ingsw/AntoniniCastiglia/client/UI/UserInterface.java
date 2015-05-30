package it.polimi.ingsw.AntoniniCastiglia.client.UI;

import java.util.List;

public interface UserInterface {
	public void connecting();
	public void youAre(String name);
	public void printMap(String map);
	public void printAllCards(String... card);
	public void chooseAction(List<Character> possibleActions);
	public void chooseCards();

}
