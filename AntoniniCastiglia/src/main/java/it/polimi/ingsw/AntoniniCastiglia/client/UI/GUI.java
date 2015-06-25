package it.polimi.ingsw.AntoniniCastiglia.client.UI;



import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;

public class GUI implements UserInterface{
	private final JFrame jfrm= new JFrame("Welcome to EFTAIOS!");
	private final JFrame waitfrm = new JFrame("Waiting...");
	private final JFrame playfrm = new JFrame(".Escape From The Aliens In Outer Space.");
	private final JFrame Charfrm = new JFrame("That's Who you are!");
	private final JFrame ownedCard=new JFrame("Owned Card");
	final JFrame warning=new JFrame("Attention, please!");
	private boolean clicked;
	JButton close=new JButton("Close");
	JButton item1=new JButton("Item Card #1");
	JButton item2=new JButton("Item Card #2");
	JButton item3=new JButton("Item Card #3");
	
	@Override//DONEEEEEE
	public void connected(int gameID, int playerID) { 
		
		JLabel jlab=new JLabel("Welcome, player. Ready to start?\n");
		JLabel jlab1=new JLabel("You'll be taking part of game" +" "+ gameID +"\n");
		JLabel jlab2=new JLabel("as player number" + " " + playerID + ". Wait for other players to join you.");
		
		
		JButton jb=new JButton("Ok!");
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jfrm.setVisible(false);
				JLabel waitlbl=new JLabel("Wait for the game to start.");
				ImageIcon image = new ImageIcon("resources/gif-animata-oggetti-clessidra_43143.gif");
				JLabel label = new JLabel("", image, JLabel.CENTER);
				JPanel panel = new JPanel(new BorderLayout());
				panel.add( label, BorderLayout.SOUTH);
				waitfrm.add(waitlbl);
				waitfrm.add(panel, BorderLayout.CENTER);
				waitfrm.setLocationRelativeTo(null); 
				waitfrm.setSize(500,200);
				waitfrm.setVisible(true);
				waitfrm.setLayout(new FlowLayout());
				waitfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}});
		jfrm.add(jlab, BorderLayout.NORTH);
		jfrm.add(jlab1, BorderLayout.CENTER);
		jfrm.add(jlab2, BorderLayout.SOUTH);
		jfrm.add(jb, BorderLayout.PAGE_END);
		jfrm.setLocationRelativeTo(null); 
		jfrm.setLayout(new FlowLayout());
		jfrm.setSize(500,200);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setVisible(true);
	}
	
	@Override//DONEEEEEE
	public void whoYouAreComplete(String[] player) { //It does show, yet it is too fast! 
		
		String name = player[0];
		String role = player[1];
		JLabel namelbl=new JLabel("Here we are, dear\t" + name +".");
		JLabel rolelbl=new JLabel("Here's your role in this battle:\t" + role);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Charfrm.setVisible(false);
				playfrm.setVisible(true);}});	
		waitfrm.setVisible(false);
		
		if ("A".equals(player[2])) {
			JLabel naturelbl=new JLabel("Looks like you are an Alien, uh?");
			Charfrm.add(naturelbl);
		} else {
			JLabel naturelbl=new JLabel("Looks like you are a Human, uh?");
			Charfrm.add(naturelbl);
		}
    	
		Charfrm.setLocationRelativeTo(null);
		Charfrm.add(namelbl);
		Charfrm.add(rolelbl);
		Charfrm.add(close);
		Charfrm.setSize(500,200);
		Charfrm.setLayout(new FlowLayout());
		Charfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Charfrm.setVisible(true);
		
	}
	@Override
	public void printMap(String map) {
		
		try {
    		playfrm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/Space.jpg")))));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		String[] line=map.split("\n");
		String[] args;
		for (int i=0;i<line.length;){
			if (i%2!=0)
				line[i] = line[i].substring(5, line[i].length()-5);
			args = line[i].split("]   [");
			for (int j=0; j<args.length; j++);
				//drawHex(args[j]);
		}
		
		JPanel card=new JPanel();
		JPanel table =new JPanel(); 
		
		item1.setSize(90, 90);
		item2.setSize(90, 90);
		item3.setSize(90, 90);
		
		
		
		card.setOpaque(false);
		//card.setBackground(Color.CYAN);
		card.setBounds(900, 200, 200, 200);
		card.add(item1, BorderLayout.NORTH);
		card.add(item2, BorderLayout.CENTER);
		card.add(item3, BorderLayout.SOUTH);
		
		
		table.setOpaque(true); //Sfondo trasparente!
		table.setBackground(Color.GREEN); //just to check if the panel gets printed
		table.setBounds(70, 150, 800, 500);
		
		Container content = playfrm.getContentPane();
		content.add(card);
		content.add(table);
		table.setVisible(true);
		playfrm.setSize(1100,800);
		playfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setVisible(true);
		card.setVisible(true);
		warning.setVisible(false);
		
	}
	@Override //DONEEEEEE
	public void printItemCards(boolean canUseCards, String... card) {
		ownedCard.setVisible(false);
		
		JLabel error =new JLabel("You don't have any Item card right now. I feel it.");
		
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				warning.setVisible(false);}});	
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				action();
				}
			});			
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				action();
				}
			});			
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				action();
				}
			});			
		
		if (canUseCards) {
			for (int i = 0; i < card.length; i++) {
				if (clicked){
					if("null".equals(card[i])) {
						warning.add(error);
						warning.add(close,BorderLayout.CENTER);
					}
					else{
						JLabel preview=new JLabel((i + 1) + ". " + (card[i].split("_"))[1] + " card");
						JLabel choice = new JLabel("Now, please, click on the button-card you want to use.");
						warning.add(preview);
						warning.add(choice);
					}
				}
			}
		}
		warning.add(close,BorderLayout.CENTER);
		warning.setLayout(new FlowLayout());
		warning.setSize(500, 200);
		warning.setVisible(true);
		warning.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	@Override//DONEEEEEEE (and yet:how do I connect everything to the card action?)
	public int selectItemCard(String[] cards) {
		JLabel yourItems=new JLabel("You can take advantage of the card(s) you own, which are:");
		this.printItemCards(true, cards);
		
		ownedCard.add(yourItems);
		ownedCard.setLayout(new FlowLayout());
		ownedCard.setSize(500, 200);
		ownedCard.setVisible(true);
		
		return (Integer) null;
		
	}
	@Override
	public void drawDangerousSectorCard(String drawnCard) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String move(int playerID, String adjacentSectors) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void whereYouAre(String currentSector, String sectorType) {
		// color the current sector with a different color
		
	}
	@Override
	public String chooseAction(String possibleActions) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void attackResult(String attackResult) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String declareNoise(boolean noise, boolean yourSector, String currentSector) {
		JFrame makeNoise=new JFrame("Noise Time!");
		JLabel yours=new JLabel("You must declare a noise in your sector. " + currentSector);
		if (noise) {
			if (yourSector) {
				System.out.println("You must declare a noise in your sector. "
						+ "Hit RETURN to confirm: " + currentSector);
				return currentSector;
			}
			System.out.println("You must declare a noise in a sector of your choice. ");
			String chosenSector = null;
			do {
				System.out.print("Please, choose a valid sector: ");
				chosenSector = (CommonMethods.readLine()).toUpperCase();
			} while (!CommonMethods.validSector(chosenSector));
			return chosenSector;
		} else {
			System.out.println("Silence in all sectors");
			return currentSector;
		}

		return null;
	}
	@Override
	public int handleItemCard(String itemCard, String... cards) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override //DONEEEEEE
	public void printJournal(String journal) {
		JFrame report =new JFrame("Turn Report");
		JLabel title =new JLabel("Here's what happened:");
		JLabel jou=new JLabel("\n"+ journal);
		report.setLocationRelativeTo(null);
		report.add(title);
		report.add(jou);
		report.setSize(500,200);
		report.setLayout(new FlowLayout());
		report.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	@Override
	public void useResult(String useResult) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void escapeResult(String escapeResult) {
		// TODO Auto-generated method stub
		
	}
	
	public void action(){ //Card Button action
		clicked=true;
	}
}
