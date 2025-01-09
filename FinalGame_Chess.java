//Name: Avneet Nijjer
//Date: 01/04/2023
//Purpose: Final ICS3U Project- Chess Game

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.*;
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream.*;


public class FinalGame_Chess extends Applet implements ActionListener
{
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5, card6, card7; //the screens for the game
    CardLayout cdLayout = new CardLayout ();

    JButton reset;

    //turns
    JLabel turnpic;
    char turn = 'w';
    int last = -1;

    //undo variables
    int undos = 1;
    int movecount = 0;

    AudioClip soundFile;


    //grid
    int row = 8;
    int col = 8;

    JButton a[] = new JButton [row * col];

    //Default Array's
    char piece[] [] = {{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}, {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}};

    char select[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};

    char colour[] [] = {{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}, {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}};

    char bg[] [] = {{'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}}; //

    //Resetting Board and Resetting Function
    char resetpiece[] [] = {{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}, {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}};

    char resetselect[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};

    char resetcolour[] [] = {{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}, {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'}};

    char resetbg[] [] = {{'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'},
	    {'w', 'b', 'w', 'b', 'w', 'b', 'w', 'b'}, {'b', 'w', 'b', 'w', 'b', 'w', 'b', 'w'}}; //

    //3rd Array's for the undo extra function
    //They are 3rd so that the player can undo as many times as they would like
    char undoPiece3[] [] [] = new char [150] [8] [8];
    char undoSelect3[] [] [] = new char [150] [8] [8];
    char undoColour3[] [] [] = new char [150] [8] [8];
    char undoBg3[] [] [] = new char [150] [8] [8];


    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 ();
	sound ();
	screen2 ();
	screen3 ();
	screen4 ();
	screen5 ();
	screen6 ();
	screen7 ();
	resize (1200, 800);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void screen1 ()
    { //screen 1 is set up.
	//introduction screen, has the weloming image and the button to go to the next screen

	//
	setLayout (null); //learned from stackoverflow
	card1 = new Panel (null); // for layout purposes
	card1.setBackground (new Color (0, 0, 0));

	JLabel title = new JLabel ("Welcome to");
	title.setBounds (300, 0, 100, 100); //x,y,width,height

	//Welcome Screen JPG picture is displayed
	JLabel welcome = new JLabel (createImageIcon ("WelcomeScreen.jpg"));
	welcome.setBounds (350, 100, 500, 500);

	//Leads to the instruction screen of the game ( the next screen)
	//Used commands to change up the formatting and stuff
	JButton next = new JButton ("Next");
	next.setBounds (550, 600, 100, 50);
	next.setForeground (new Color (255, 255, 255));
	next.setBackground (new Color (0, 0, 0));
	next.setActionCommand ("s2");
	next.addActionListener (this);

	//Adding all the widgets to the screen
	card1.add (title);
	card1.add (next);
	card1.add (welcome);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    { //screen 2 is set up.
	//Instructions screen that has all the game rules for how to play
	card2 = new Panel ();
	card2.setBackground (new Color (0, 0, 0));

	//title on top of the screen
	JLabel title = new JLabel ("Instructions");

	//Imports the picture for the screen
	JLabel instructions = new JLabel (createImageIcon ("gamerules.png"));
	instructions.setBounds (300, 0, 100, 100);

	//Button allows for user to go to the next screen
	//Used more commands to format the button to be nicer
	JButton next = new JButton ("Next");
	next.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	next.setForeground (new Color (255, 255, 255));
	next.setBackground (new Color (0, 0, 0));
	next.setPreferredSize (new Dimension (275, 50));
	next.setActionCommand ("s3");
	next.addActionListener (this);

	//Button allows for user to go to the next screen
	//Used more commands to format the button to be nicer
	JButton back = new JButton ("Back");
	back.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	back.setForeground (new Color (255, 255, 255));
	back.setBackground (new Color (0, 0, 0));
	back.setPreferredSize (new Dimension (300, 50));
	back.setActionCommand ("s1");
	back.addActionListener (this);


	//Goodluck text Message written on screen before you go into the game
	JLabel ins1 = new JLabel ("Good Luck Have fun!");
	ins1.setFont (new Font ("Notable", Font.BOLD, 30));
	ins1.setForeground (new Color (255, 255, 255));


	//All widgets are added here
	card2.add (title);
	card2.add (instructions);
	card2.add (ins1);
	card2.add (next);
	p_card.add ("2", card2);
    }


    public void screen3 ()
    {
	//Movement instructions screen number 1
	//Movement for pawn, rook, knight

	card3 = new Panel ();
	card3.setBackground (new Color (0, 0, 0));

	//Picture with the pawn, rook and knight pieces, with pictures and descriptions - made on canva
	JLabel info1 = new JLabel (createImageIcon ("movement1.png"));
	info1.setBounds (0, 0, 800, 750);

	//Button to allow the user to go to the next screen once they have read the instructions
	JButton next = new JButton ("Next");
	next.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	next.setForeground (new Color (255, 255, 255));
	next.setBackground (new Color (0, 0, 0));
	next.setPreferredSize (new Dimension (300, 50));
	next.setActionCommand ("s4");
	next.addActionListener (this);

	//Back button used here incase user wehats to go back and read the last instructions or missed a detail
	JButton back = new JButton ("Back");
	back.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	back.setForeground (new Color (255, 255, 255));
	back.setBackground (new Color (0, 0, 0));
	back.setPreferredSize (new Dimension (300, 50));
	back.setActionCommand ("s2");
	back.addActionListener (this);

	//All the widgets above added here
	card3.add (info1);
	card3.add (back);
	card3.add (next);
	p_card.add ("3", card3);

    }


    public void screen4 ()
    {
	//Movement Instructions Screen number 2
	//Movement for Bishop, Queen, King

	card4 = new Panel ();
	card4.setBackground (new Color (0, 0, 0));

	//Picture with the Bishop, Queen and King pieces, with pictures and descriptions - made on canva
	JLabel info2 = new JLabel (createImageIcon ("movement2.png"));
	info2.setBounds (0, 0, 800, 750);

	//Button to allow the user to go to the next screen once they have read the instructions
	JButton next = new JButton ("Next");
	next.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	next.setForeground (new Color (255, 255, 255));
	next.setBackground (new Color (0, 0, 0));
	next.setPreferredSize (new Dimension (300, 50));
	next.setActionCommand ("s5");
	next.addActionListener (this);

	//Back button to go to the last screen
	JButton back = new JButton ("Back");
	back.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	back.setForeground (new Color (255, 255, 255));
	back.setBackground (new Color (0, 0, 0));
	back.setPreferredSize (new Dimension (300, 50));
	back.setActionCommand ("s3");
	back.addActionListener (this);

	//All widgets added here
	card4.add (info2);
	card4.add (back);
	card4.add (next);

	p_card.add ("4", card4);

    }


    public void screen5 ()
    { //screen 3 is set up.
	//The Game Screen

	//creating the screen in the panel
	card5 = new Panel ();
	card5.setBackground (new Color (0, 0, 139));
	JLabel title = new JLabel (" Car Logo Chess!");
	title.setFont (new Font ("Notable", Font.BOLD, 30));
	title.setForeground (new Color (255, 255, 255));

	//Turns, used to show whos turn it currently is on the board using the pawn image
	JLabel title1 = new JLabel ("Player's move:");
	title1.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	title1.setForeground (new Color (255, 255, 255));
	title1.setBackground (new Color (0, 0, 0));

	turnpic = new JLabel (createImageIcon ("pwwu.jpg"));

	//Quit button incase the players do not want to play anymore and decide to end the game or leave.
	JButton quit = new JButton ("Quit");
	quit.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	quit.setForeground (new Color (255, 255, 255));
	quit.setBackground (new Color (0, 0, 139));
	quit.setPreferredSize (new Dimension (275, 50));
	quit.setActionCommand ("s6");
	quit.addActionListener (this);

	//Reset button on the screen
	JButton reset = new JButton ("Reset");
	reset.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	reset.setForeground (new Color (255, 255, 255));
	reset.setBackground (new Color (0, 0, 139));
	reset.setPreferredSize (new Dimension (275, 50));
	reset.setActionCommand ("reset");
	reset.addActionListener (this);

	//The button on screen for undo's
	JButton undo = new JButton ("Undo");
	undo.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	undo.setForeground (new Color (255, 255, 255));
	undo.setBackground (new Color (0, 0, 139));
	undo.setPreferredSize (new Dimension (275, 50));
	undo.setActionCommand ("undo");
	undo.addActionListener (this);


	//Button to go back to the instructions screen
	JButton ins = new JButton ("Instructions");
	ins.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	ins.setForeground (new Color (255, 255, 255));
	ins.setBackground (new Color (0, 0, 139));
	ins.setPreferredSize (new Dimension (275, 50));
	ins.setActionCommand ("s2");
	ins.addActionListener (this);


	//Set up grid
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move] = new JButton (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".jpg"));
		a [move].setPreferredSize (new Dimension (75, 75));
		a [move].addActionListener (this);
		a [move].setActionCommand ("" + move);
		p.add (a [move]);
		move++;
	    }
	}
	//Adding all the widgets to the screen.
	card5.add (quit);
	card5.add (reset);
	card5.add (undo);
	card5.add (ins);
	card5.add (title1);
	card5.add (turnpic);
	card5.add (p);
	card5.add (title);
	p_card.add ("5", card5);
    }


    public void screen6 ()
    { //screen 4 is set up.
	//The win screen

	card6 = new Panel ();
	card6.setBackground (new Color (0, 0, 0));
	JLabel title = new JLabel ("You Win!");

	//Adding the win screen image
	JLabel WinScreen1 = new JLabel (createImageIcon ("WinScreen1.png"));
	WinScreen1.setBounds (300, 0, 100, 100);

	//Play again button that leads back to the start
	JButton playagain = new JButton ("Play Again?");
	playagain.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	playagain.setForeground (new Color (255, 255, 255));
	playagain.setBackground (new Color (0, 0, 0));
	playagain.setPreferredSize (new Dimension (275, 50));
	playagain.setActionCommand ("s1");
	playagain.addActionListener (this);

	//Quit button to leave the game and close it
	JButton quit = new JButton ("Quit");
	quit.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	quit.setForeground (new Color (255, 255, 255));
	quit.setBackground (new Color (0, 0, 0));
	quit.setPreferredSize (new Dimension (275, 50));
	quit.setActionCommand ("s6");
	quit.addActionListener (this);

	//Adding all the widgets
	card6.add (title);
	card6.add (WinScreen1);
	card6.add (playagain);
	card6.add (quit);
	p_card.add ("6", card6);
    }


    public void screen7 ()
    { //screen 5 is set up.
	//Losing Screen

	card7 = new Panel ();
	card7.setBackground (new Color (255, 255, 255));

	//Title
	JLabel title = new JLabel ("You Lose.");
	//Image with the you lose written on it
	JLabel lose = new JLabel (createImageIcon ("youlose.png"));
	lose.setBounds (300, 0, 100, 100);

	//Back to introduction screen
	JButton next = new JButton ("Back to Introduction?");
	next.setActionCommand ("s1");
	next.addActionListener (this);

	//Quit the game
	JButton quit = new JButton ("Quit");
	quit.setFont (new Font ("Times new roman", Font.PLAIN, 15));
	quit.setForeground (new Color (255, 255, 255));
	quit.setBackground (new Color (0, 0, 0));
	quit.setPreferredSize (new Dimension (275, 50));
	quit.setActionCommand ("s6");
	quit.addActionListener (this);

	//Adding all the widgets
	card7.add (title);
	card7.add (lose);
	card7.add (next);
	card7.add (quit);
	p_card.add ("7", card7);
    }


    protected static ImageIcon createImageIcon (String path)
    { //change the red to your class name
	java.net.URL imgURL = FinalGame_Chess.class.getResource (path);

	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    public void undo ()  //Undo button method
    {
	if (movecount > 0)
	{
	    movecount--;
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    colour [i] [j] = undoColour3 [movecount] [i] [j];
		    piece [i] [j] = undoPiece3 [movecount] [i] [j];
		    select [i] [j] = 'u';
		}
	    }
	    if (undos == 1)
	    {
		if (turn == 'b')
		{
		    turn = 'w';
		    turnpic.setIcon (createImageIcon ("pbbu.jpg"));
		}
		else
		{
		    turn = 'b';
		    turnpic.setIcon (createImageIcon ("pwwu.jpg"));
		}
	    }
	}
    }





    public void redraw ()
    {
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move].setIcon (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".jpg"));
		move++;
	    }
	}
    }


    public void selectPawn (int x, int y)
    {
	if (x == 6)
	{
	    select [x - 1] [y] = 's';
	    select [x - 2] [y] = 's';
	}
	if (x - 1 >= 0 && colour [x - 1] [y] == 'x') //blank space selection
	{
	    select [x - 1] [y] = 's';
	}
	if (x - 1 >= 0 && y + 1 < col && colour [x - 1] [y + 1] == 'b') //kill top-right (black)
	{
	    select [x - 1] [y + 1] = 's';
	}
	if (x - 1 >= 0 && y - 1 >= 0 && colour [x - 1] [y - 1] == 'b') //kill top-left (black)
	{
	    select [x - 1] [y - 1] = 's';
	}
	if (x - 1 >= 0 && y - 1 >= 0 && colour [x - 1] [y - 1] == 'w') //kill top-right (white)
	{
	    select [x - 1] [y - 1] = 's';
	}
	if (x - 1 >= 0 && y + 1 < col && colour [x - 1] [y + 1] == 'w') //kill top-left (white)
	{
	    select [x - 1] [y + 1] = 's';
	}

    }


    public void selectKing (int x, int y)
    {
	if (x - 1 >= 0 && y + 1 < col && colour [x - 1] [y + 1] != turn) //top right
	    select [x - 1] [y + 1] = 's';

	if (x + 1 < row && y + 1 < col && colour [x + 1] [y + 1] != turn) //bottom right
	    select [x + 1] [y + 1] = 's';

	if (y - 1 >= 0 && colour [x] [y - 1] != turn) //middle left
	    select [x] [y - 1] = 's';

	if (x - 1 >= 0 && colour [x - 1] [y] != turn) //middle top
	    select [x - 1] [y] = 's';

	if (x - 1 >= 0 && y - 1 >= 0 && colour [x - 1] [y - 1] != turn) //top left
	    select [x - 1] [y - 1] = 's';

	if (y + 1 < col && colour [x] [y + 1] != turn) //middle right
	    select [x] [y + 1] = 's';

	if (x + 1 < row && colour [x + 1] [y] != turn) //middle bottom
	    select [x + 1] [y] = 's';

	if (x + 1 < row && y - 1 >= 0 && colour [x + 1] [y - 1] != turn) //bottom left
	    select [x + 1] [y - 1] = 's';
    }


    public void selectKnight (int x, int y)
    {
	if (x - 2 >= 0 && y + 1 < col && colour [x - 2] [y + 1] != turn) // 1 o' clock
	    select [x - 2] [y + 1] = 's';

	if (x - 1 >= 0 && y + 2 < col && colour [x - 1] [y + 2] != turn) // 2 o' clock
	    select [x - 1] [y + 2] = 's';

	if (x + 1 < row && y + 2 < col && colour [x + 1] [y + 2] != turn) // 4 o' clock
	    select [x + 1] [y + 2] = 's';

	if (x + 2 < row && y + 1 < col && colour [x + 2] [y + 1] != turn) // 5 o' clock
	    select [x + 2] [y + 1] = 's';

	if (x + 2 < row && y - 1 >= 0 && colour [x + 2] [y - 1] != turn) // 7 o' clock
	    select [x + 2] [y - 1] = 's';

	if (x + 1 < row && y - 2 >= 0 && colour [x + 1] [y - 2] != turn) // 8 o' clock
	    select [x + 1] [y - 2] = 's';

	if (x - 1 >= 0 && y - 2 >= 0 && colour [x - 1] [y - 2] != turn) // 10 o' clock
	    select [x - 1] [y - 2] = 's';

	if (x - 2 >= 0 && y - 1 >= 0 && colour [x - 2] [y - 1] != turn) // 11 o' clock
	    select [x - 2] [y - 1] = 's';
    }


    public void selectRook (int x, int y)
    {
	//UP
	int cx1 = x - 1;
	//while rook has not fallen off edge and it is blank
	while (cx1 >= 0 && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1--;
	}


	//kill condition
	//if on the board, it isn't us, and it isn't blank
	if (cx1 >= 0 && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	    select [cx1] [y] = 's';

	//DOWN
	cx1 = x + 1;
	while (cx1 < row && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1++;
	}


	if (cx1 < row && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	    select [cx1] [y] = 's';

	//LEFT
	int cy1 = y - 1;
	while (cy1 >= 0 && colour [x] [cy1] == 'x')
	{
	    select [x] [cy1] = 's';
	    cy1--;
	}


	if (cy1 >= 0 && colour [x] [cy1] != turn && colour [x] [cy1] != 'x')
	    select [x] [cy1] = 's';

	//RIGHT
	cy1 = y + 1;
	while (cy1 < row && colour [x] [cy1] == 'x')
	{
	    select [x] [cy1] = 's';
	    cy1++;
	}


	if (cy1 < row && colour [x] [cy1] != turn && colour [x] [cy1] != 'x')
	    select [x] [cy1] = 's';
    }


    public void selectBishop (int x, int y)
    {
	//UP RIGHT
	int cx1 = x - 1;
	int cy1 = y + 1;
	while (cx1 >= 0 && cy1 < col && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cx1--;
	    cy1++;
	}


	if (cx1 >= 0 && cy1 < col && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';

	//UP LEFT
	cx1 = x - 1;
	cy1 = y - 1;
	while (cx1 >= 0 && cy1 >= 0 && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cx1--;
	    cy1--;
	}


	if (cx1 >= 0 && cy1 >= 0 && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';

	//DOWN RIGHT
	cx1 = x + 1;
	cy1 = y + 1;
	while (cx1 < row && cy1 < col && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cx1++;
	    cy1++;
	}


	if (cx1 < row && cy1 < col && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';

	//DOWN LEFT
	cx1 = x + 1;
	cy1 = y - 1;
	while (cx1 < row && cy1 >= 0 && colour [cx1] [cy1] == 'x')
	{
	    select [cx1] [cy1] = 's';
	    cx1++;
	    cy1--;
	}


	if (cx1 < row && cy1 >= 0 && colour [cx1] [cy1] != turn && colour [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';

    }


    public void selectQueen (int x, int y)
    {
	selectRook (x, y); // I'm a genius
	selectBishop (x, y);
    }


    /*
    public boolean check(int x, int y)
    {
     // make the king have the vision of all the pieces so that when a piece
    }
    */

    public void reset ()
    {
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = resetpiece [i] [j];
		select [i] [j] = resetselect [i] [j];
		colour [i] [j] = resetcolour [i] [j];
		bg [i] [j] = resetbg [i] [j];
	    }


	turn = 'w';
	turnpic.setIcon (createImageIcon ("pwwu.jpg"));
	redraw ();
    }


    public void resetbutton ()  //reset button
	// Credit to Deep for helping me fix it up
    {
	Object[] options = {"Yes", "No"};
	int ans = JOptionPane.showOptionDialog (null, "Would you like to restart the game?", " ", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options [0]);
	if (ans == 0)
	    reset ();
	else if (ans == 1)
	    redraw ();
    }


    public void flipboard ()
	//so the board flips to the associated player when their turn comes (help from umer)
    {
	char a[] [] = new char [8] [8];

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [i] [j] = piece [7 - i] [7 - j];
	    }
	}


	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = a [i] [j];
	    }
	}


	char b[] [] = new char [8] [8];

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		b [i] [j] = colour [7 - i] [7 - j];
	    }
	}


	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		colour [i] [j] = b [i] [j];
	    }
	}


	char c[] [] = new char [8] [8];

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		c [i] [j] = select [7 - i] [7 - j];
	    }
	}


	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		select [i] [j] = c [i] [j];
	    }
	}
    }


    public void win ()
	// Method for the win condition, if the king dies on any side the screen goes to the win screen.
    {
	int king = 0;
	int x = -1;
	int y = -1;

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (piece [i] [j] == 'k')
		{
		    king++;
		    x = i;
		    y = j;
		}
	    }
	}
	if (king == 1 && colour [x] [y] == 'b')
	{
	    cdLayout.show (p_card, "3");
	}
	else if (king == 1 && colour [x] [y] == 'w')
	{
	    cdLayout.show (p_card, "6");
	}
    }


    public void sound ()
    {

	soundFile = getAudioClip (getDocumentBase (), "backgroundmusic.wav");
	//this attaches the sound file "letitrock"
	soundFile.loop ();
	//put the sound on repeat

    }


    //For a single sound effect
    public void soundEffect (String filepath)
    {
	//initialize objects
	//declare sound effect player
	AudioPlayer SEP = AudioPlayer.player;
	//declare sound effect
	AudioStream SE;
	//declare audio data
	AudioData MA;
	//set as single run (NOT LOOP)
	AudioDataStream play = null;

	try
	{
	    //set file
	    SE = new AudioStream (new FileInputStream (filepath + "soundeffect.wav"));
	    MA = SE.getData ();
	    //set data to play once (NOT LOOP)
	    play = new AudioDataStream (MA);
	}
	catch (IOException error)
	{
	    System.out.println ("Audio - File not found.");
	}
	SEP.start (play);
    }



    public void actionPerformed (ActionEvent e)
    { //moves between the screens
	if (e.getActionCommand ().equals ("s1"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("s2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("s3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("s4"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("s5"))
	    cdLayout.show (p_card, "5");
	else if (e.getActionCommand ().equals ("reset"))
	    resetbutton ();
	else if (e.getActionCommand ().equals ("undo"))
	    undo ();
	else if (e.getActionCommand ().equals ("ins"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("next2"))
	    cdLayout.show (p_card, "6");
	else if (e.getActionCommand ().equals ("s6"))
	    System.exit (0);
	else
	{ //code to handle the game
	    int n = Integer.parseInt (e.getActionCommand ());
	    int x = n / col;
	    int y = n % col;
	    showStatus ("(" + x + ", " + y + ")");

	    if (turn != colour [x] [y] && last == -1)
		//click #1, not your piece
		showStatus ("It's not your turn.");

	    else if (last == -1 && turn == colour [x] [y])
	    { //click #1, need to select places to go
		if (piece [x] [y] == 'p')
		    selectPawn (x, y);
		else if (piece [x] [y] == 'k')
		    selectKing (x, y);
		else if (piece [x] [y] == 'r')
		    selectRook (x, y);
		else if (piece [x] [y] == 'b')
		    selectBishop (x, y);
		else if (piece [x] [y] == 'n')
		    selectKnight (x, y);
		else if (piece [x] [y] == 'q')
		    selectQueen (x, y);


		last = n;
	    }

	    else
	    { //click #2, neeed to move
		int lastx = last / col;
		int lasty = last % col;
		//move
		if (select [x] [y] == 's')
		{
		    for (int i = 0 ; i < row ; i++)
		    {
			for (int j = 0 ; j < col ; j++)
			{
			    undoColour3 [movecount] [i] [j] = colour [i] [j];
			    undoSelect3 [movecount] [i] [j] = select [i] [j];
			    undoPiece3 [movecount] [i] [j] = piece [i] [j];
			}
		    }
		    //move
		    piece [x] [y] = piece [lastx] [lasty];
		    piece [lastx] [lasty] = 'x';
		    colour [x] [y] = colour [lastx] [lasty];
		    colour [lastx] [lasty] = 'x';


		    //switch turn
		    if (turn == 'w')
		    {
			turn = 'b';
			turnpic.setIcon (createImageIcon ("pbbu.jpg"));
			//call the method to flip the board
			flipboard ();
		    }
		    else
		    {
			turn = 'w';
			turnpic.setIcon (createImageIcon ("pwwu.jpg"));
			//call the method to flip the board
			flipboard ();

		    }

		} //move done
		movecount++;
		//reset select
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			select [i] [j] = 'u';
		    }
		}
		last = -1;
		undos = 1;

	    } //move else
	} // grid else


	redraw ();
	win ();
    } //AP
} //full program

