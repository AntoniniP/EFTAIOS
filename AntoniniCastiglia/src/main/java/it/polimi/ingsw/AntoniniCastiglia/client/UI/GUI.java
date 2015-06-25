package it.polimi.ingsw.AntoniniCastiglia.client.UI;



import it.polimi.ingsw.AntoniniCastiglia.Constants;
import it.polimi.ingsw.AntoniniCastiglia.client.CommonMethods;
import it.polimi.ingsw.AntoniniCastiglia.maps.MapConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	@Override
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
	
	@Override
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
	
	
	private String map;
	
	@Override
	public void printMap(String myMap) {
		
		map=myMap;

		try {
			playfrm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(
					"resources/Space.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		createAndShowGUI();

		JPanel card = new JPanel();

		item1.setSize(90, 90);
		item2.setSize(90, 90);
		item3.setSize(90, 90);

		card.setOpaque(false);
		card.setBackground(Color.CYAN);
		card.setBounds(900, 200, 200, 200);
		card.add(item1, BorderLayout.NORTH);
		card.add(item2, BorderLayout.CENTER);
		card.add(item3, BorderLayout.SOUTH);

		table.setBounds(70, 150, 800, 500);

		Container content = playfrm.getContentPane();
		content.add(card);
		content.add(table);
		// table.setVisible(true);
		playfrm.setSize(1100, 800);
		playfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table.setVisible(true);
		card.setVisible(true);
		warning.setVisible(false);

	}
	
	@Override
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
	
	
	
	
	
	




	/*
#define HEXEAST 0
#define HEXSOUTHEAST 1
#define HEXSOUTHWEST 2
#define HEXWEST 3
#define HEXNORTHWEST 4
#define HEXNORTHEAST 5
	 */

	//Constants
	public final static boolean orFLAT= true;
	public final static boolean orPOINT= false;
	public static boolean ORIENT= orFLAT;  //this is not used. We're never going to do pointy orientation

	public static boolean XYVertex=false;	//true: x,y are the co-ords of the first vertex.
	//false: x,y are the co-ords of the top left rect. co-ord.

	private static int BORDERS=50;	//default number of pixels for the border.
	
	final static Color COLOURBACK =  Color.WHITE;
	final static Color COLOURCELL =  Color.ORANGE;	 
	final static Color COLOURGRID =  Color.BLACK;	 
	final static Color COLOURONE = new Color(255,255,255,200);
	final static Color COLOURONETXT = Color.BLUE;
	final static Color COLOURTWO = new Color(0,0,0,200);
	final static Color COLOURTWOTXT = new Color(255,100,255);
	final static int EMPTY = 0;
	final static int HEXSIZE = 40;	//hex size in pixels
	// final static int BORDERS = 15;  
	final static int SCRSIZE = HEXSIZE * (MapConstants.WIDTH + 1) + BORDERS*3; //screen size (vertical dimension).
	
	int[][] board = new int [MapConstants.HEIGHT][MapConstants.WIDTH];
	
	private static int s=0;	// length of one side
	private static int t=0;	// short side of 30o triangle outside of each hex
	private static int r=0;	// radius of inscribed circle (centre to middle of each side). r= h/2
	private static int h=0;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.

	

	/** This functions takes the Side length in pixels and uses that as the basic dimension of the hex.
            It calculates all other needed constants from this dimension.
	*/
	private static void setSide(int side) {
		s=side;
		t =  (int) (s / 2);			//t = s sin(30) = (int) CalculateH(s);
		r =  (int) (s * 0.8660254037844);	//r = s cos(30) = (int) CalculateR(s); 
		h=2*r;
	}
	private static void setHeight(int height) {
		h = height;			// h = basic dimension: height (distance between two adj centresr aka size)
		r = h/2;			// r = radius of inscribed circle
		s = (int) (h / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
		t = (int) (r / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
	}

/*********************************************************
Name: hex()
Parameters: (x0,y0) This point is normally the top left corner 
    of the rectangle enclosing the hexagon. 
    However, if XYVertex is true then (x0,y0) is the vertex of the 
    top left corner of the hexagon. 
Returns: a polygon containing the six points.
Called from: drawHex(), fillhex()
Purpose: This function takes two points that describe a hexagon
and calculates all six of the points in the hexagon.
*********************************************************/
	private static Polygon hex (int x0, int y0) {

		int y = y0 + BORDERS;
		int x = x0 + BORDERS; 
		
		setSide(40);
		setHeight(40);
		
		

		int[] cx,cy;

		if (XYVertex) {
			cx = new int[] {x,x+s,x+s+t,x+s,x,x-t};  //this is for the top left vertex being at x,y. Which means that some of the hex is cutoff.
		}else{
			cx = new int[] {x+t,x+s+t,x+s+t+t,x+s+t,x+t,x};	//this is for the whole hexagon to be below and to the right of this point
		}
		cy = new int[] {y,y,y+r,y+r+r,y+r+r,y+r};
		
		return new Polygon(cx,cy,6);

		
	}

	
	
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();

	
	
	
	
	
/********************************************************************
Name: drawHex()
Parameters: (i,j) : the x,y coordinates of the inital point of the hexagon
	    g2: the Graphics2D object to draw on.
Returns: void
Calls: hex() 
Purpose: This function draws a hexagon based on the initial point (x,y).
The hexagon is drawn in the colour specified in hexgame.COLOURELL.
*********************************************************************/
	public static void drawHex(int i, int j, Graphics2D g2) {
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		Polygon poly = hex(x,y);
		g2.setColor(COLOURCELL);
		//g2.fillPolygon(hexmech.hex(x,y));
		g2.fillPolygon(poly);
		g2.setColor(COLOURGRID);
		g2.drawPolygon(poly);
	}

/***************************************************************************
* Name: fillHex()
* Parameters: (i,j) : the x,y coordinates of the initial point of the hexagon
		n   : an integer number to indicate a letter to draw in the hex
		g2  : the graphics context to draw on
* Return: void
* Called from:
* Calls: hex()
*Purpose: This draws a filled in polygon based on the coordinates of the hexagon.
	  The colour depends on whether n is negative or positive.
	  The colour is set by hexgame.COLOURONE and hexgame.COLOURTWO.
	  The value of n is converted to letter and drawn in the hexagon.
*****************************************************************************/
	public static void fillHex(int i, int j, int n, Graphics2D g2) {
		char c='o';
		int x = i * (s+t);
		int y = j * h + (i%2) * h/2;
		
		
		String letter = ((Character) ALPHABET[i]).toString(); // casting char to String
		String number = ((Integer) (j + 1)).toString(); // casting int to String
		if (j < 9) {
			number = "0" + number; // number must be a two-character string
		}
		
		
		
		if (n < 0) {
			g2.setColor(COLOURONE);
			g2.fillPolygon(hex(x,y));
			g2.setColor(COLOURONETXT);
			c = (char)(-n);


			g2.drawString(letter+number, x+BORDERS+r/2, y+BORDERS+r);

		}
		if (n > 0) {
			g2.setColor(COLOURTWO);
			g2.fillPolygon(hex(x,y));
			g2.setColor(COLOURTWOTXT);
			c = (char)n;

			
			g2.drawString(letter+number, x+BORDERS+r/2, y+BORDERS+r);

		}
		
		
		
		
		
	}

	//This function changes pixel location from a mouse click to a hex grid location
/*****************************************************************************
* Name: pxtoHex (pixel to hex)
* Parameters: mx, my. These are the co-ordinates of mouse click.
* Returns: point. A point containing the coordinates of the hex that is clicked in.
           If the point clicked is not a valid hex (ie. on the borders of the board, (-1,-1) is returned.
* Function: This only works for hexes in the FLAT orientation. The POINTY orientation would require
            a whole other function (different math).
            It takes into account the size of borders.
            It also works with XYVertex being True or False.
*****************************************************************************/
	public static Point pxtoHex(int mx, int my) {
		Point p = new Point(-1,-1);

		//correction for BORDERS and XYVertex
		mx -= BORDERS;
		my -= BORDERS;
		if (XYVertex) mx += t;

		int x = (int) (mx / (s+t)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
		int y = (int) ((my - (x%2)*r)/h); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

		/******FIX for clicking in the triangle spaces (on the left side only)*******/
		//dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
		int dx = mx - x*(s+t);
		int dy = my - y*h;

		if (my - (x%2)*r < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

		//System.out.println("dx=" + dx + " dy=" + dy + "  > " + dx*r/t + " <");
		
		//even columns
		if (x%2==0) {
			if (dy > r) {	//bottom half of hexes
				if (dx * r /t < dy - r) {
					x--;
				}
			}
			if (dy < r) {	//top half of hexes
				if ((t - dx)*r/t > dy ) {
					x--;
					y--;
				}
			}
		} else {  // odd columns
			if (dy > h) {	//bottom half of hexes
				if (dx * r/t < dy - h) {
					x--;
					y++;
				}
			}
			if (dy < h) {	//top half of hexes
				//System.out.println("" + (t- dx)*r/t +  " " + (dy - r));
				if ((t - dx)*r/t > dy - r) {
					x--;
				}
			}
		}
		p.x=x;
		p.y=y;
		return p;
	}
	
	
	private DrawingPanel table=new DrawingPanel();
	
	private void createAndShowGUI()
	{
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		table.setSize( (int)(SCRSIZE/1.23), SCRSIZE);
		table.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*************************************************************************************/
	
	class DrawingPanel extends JPanel
	{		
		//mouse variables here
		//Point mPt = new Point(0,0);
 
		
		private static final long serialVersionUID = 1L;

		public DrawingPanel() //constructor
		{	
			//setBackground(COLOURBACK);
			MyMouseListener ml = new MyMouseListener();            
			addMouseListener((MouseListener) ml);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//super.paintComponent(g2);
			String myMap = new String(map);
			String[] lines = myMap.split("\n");
			int x = 0;
			for (int i = 0; i < lines.length; i++) {
				x = 0;
				for (int j = 0; j < lines[i].length();) {
					if ("     ".regionMatches(0, lines[i], j, 5)) {
						j += 5;

					} else if (" A ".regionMatches(0, lines[i], j, 3)
							|| " H ".regionMatches(0, lines[i], j, 3)
							|| " E ".regionMatches(0, lines[i], j, 3)) {
						j += 3;
					} else if (Constants.ANSI_RESET.regionMatches(0, lines[i], j, 6)) {
						j += 10;

					} else if ("[".regionMatches(0, lines[i], j, 1)
							|| "]".regionMatches(0, lines[i], j, 1)) {
						j++;
					} else {
						x++;
						drawHex(x, i, g2);
						j += 3;

					}

				}
			}

			/*
			 * 
			 * //draw grid for (int i=0;i<MapConstants.HEIGHT;i++) { for (int
			 * j=0;j<MapConstants.WIDTH;j++) { } }
			 */

			
		
			// fill in hexes
			for (int i = 0; i < MapConstants.HEIGHT; i++) {
				for (int j = 0; j < MapConstants.WIDTH; j++) {
					fillHex(i, j, board[i][j], g2);
				}
			}

			// g.setColor(Color.RED);
			// g.drawLine(mPt.x,mPt.y, mPt.x,mPt.y);
		}
 
		class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel 
			public void mouseClicked(MouseEvent e) { 
				int x = e.getX(); 
				int y = e.getY(); 
				//mPt.x = x;
				//mPt.y = y;
				Point p = new Point( pxtoHex(e.getX(),e.getY()) );
				//if (p.x < 0 || p.y < 0 || p.x >= MapConstants.HEIGHT || p.y >= MapConstants.WIDTH) return;
 
				//DEBUG: colour in the hex which is supposedly the one clicked on
				//clear the whole screen first.
				/* for (int i=0;i<BSIZE;i++) {
					for (int j=0;j<BSIZE;j++) {
						board[i][j]=EMPTY;
					}
				} */
 
				//What do you want to do when a hexagon is clicked?
				try {
					board[p.x][p.y] = (int)'X';
				} catch (ArrayIndexOutOfBoundsException exeption){
					return;
				}
				repaint();
			}		 
		} //end of MyMouseListener class 
	} // end of DrawingPanel class
}
	
	
	
	
	
	
	
	

