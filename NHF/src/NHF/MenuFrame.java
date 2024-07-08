package NHF;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * A menüt reprezentáló osztály, a Kígyó játékhoz.
 * Kezeli a játékmód kiválasztását és a korábbi játék betöltését.Illetve a korábban lementett játék betöltését
 */
public class MenuFrame extends JFrame implements ActionListener {
	JButton easyButton;
	JButton normalButton;
	JButton hardButton;
	JButton reloadButton;
	JPanel jpanelMenu;
	private char gameModeChoice;
	
	  /**
     * Konstruktor az MenuFrame osztályhoz.
     * Beállítja a menü ablakot és létrehozza a gombokat a különböző játékmódokhoz (könnyű, normál, nehéz).
     */
	public MenuFrame() {
		super("2 Player Snake Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GridLayout gridLayout=new GridLayout(5,1);
		gridLayout.setVgap(50);
		
		this.setLayout(gridLayout);
		
		this.jpanelMenu = new JPanel(new FlowLayout());
		jpanelMenu.setSize(100,200);
		this.add(jpanelMenu);
		
		easyButton = new JButton();
		easyButton.setText("Easy");
		this.add(easyButton);
		easyButton.addActionListener(this);
		
		normalButton = new JButton();
		normalButton.setText("Normal");
		this.add(normalButton);
		normalButton.addActionListener(this);
		
		hardButton =  new JButton();
		hardButton.setText("Hard");
		this.add(hardButton);
		hardButton.addActionListener(this);
		
		reloadButton =  new JButton();
		reloadButton.setText("Reload");
		this.add(reloadButton);
		reloadButton.addActionListener(this);
		
		
		this.pack();
		this.setSize(400, 600);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	 /**
     * Inicializálja a játékot a választott játékmóddal. A választott játékmód az akadályok számában mutatkozik meg
     *
     * @param gameType A választott játékmód.
     */
	public void initGame(char gameType) {
	GameBoard board = new GameBoard();	
	
	Color player1Head = Color.white;
	Color player1Tail = Color.cyan;
	List<SnakeBodyPart> player1body = new ArrayList<>();
	
	Color player2Head = Color.black;
	Color player2Tail = Color.orange;
	List<SnakeBodyPart> player2body = new ArrayList<>();
	
	Color player3Head = Color.pink;
	Color player3Tail = Color.cyan;
	List<SnakeBodyPart> player3body = new ArrayList<>();

	
	List<Obstacle> obstacles = new ArrayList<>();
	
	for(int i = 0; i<10;i++) {
			obstacles.add(new Obstacle(board));
	}
	
	 for(int i = 0; i<5;i++) {
		player1body.add(new SnakeBodyPart(player1Tail))	;
		player2body.add(new SnakeBodyPart(player2Tail))	;
		player3body.add(new SnakeBodyPart(player3Tail))	;
	}
	
	SnakePlayer player1 = new SnakePlayer(board,KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,player1Head,player1body);
	SnakePlayer player2 = new SnakePlayer(board,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D,player2Head,player2body);
//	SnakePlayer player3 = new SnakePlayer(board,KeyEvent.VK_I,KeyEvent.VK_K,KeyEvent.VK_J,KeyEvent.VK_L,player3Head,player3body);
	player2.setHead(500, 0);
	//player3.SetHead(200, 200);
	board.setObstacles(obstacles);
	
	board.setPlayer(player1);
	board.setPlayer(player2);
	//board.setPlayer(player3);
	
	MenuFrame mf = new MenuFrame();
	SnakeGamePanel panel = new SnakeGamePanel(board,gameType);
	
	this.setVisible(false);
	SnakeGameFrame sf = new SnakeGameFrame(panel);
		
}
	  /**
     * ActionListener interfész metódusa a játékmódkiválasztását biztositó gombok eseménykezeléséhez.
     * @param ae Az esemény, amely kiváltja a metódust.
     */
	public void actionPerformed(ActionEvent ae) {
	
		JButton choice = (JButton)ae.getSource(); 
		switch(choice.getText()) {
				case "Easy":
				gameModeChoice = 'E';
				initGame(gameModeChoice);
				break;
				case "Normal":
				gameModeChoice = 'N';
				initGame(gameModeChoice);	
				break;
				
				case "Hard":
				gameModeChoice = 'H';
				initGame(gameModeChoice);
				break;
			
				case "Reload":
				lastGameFromFile();
				break;
		}

	}
	   /**
     * Betölti a legutóbb lementett játékot a fájlból.
     */
	public void lastGameFromFile() {
		SnakeGamePanel panel = null;
	try {
		FileInputStream f =	new FileInputStream("lastgame.txt");
		ObjectInputStream in =
		new ObjectInputStream(f);
		panel = (SnakeGamePanel)in.readObject();
		in.close();
		} catch(IOException ex) {
			System.out.println("tipus hiba");
		} catch(ClassNotFoundException ex) { System.out.println("nincs ilyen fajl");}
			
		SnakeGameFrame sf2 = new SnakeGameFrame(panel);
	}
    /**
     * Visszaadja a kiválasztott játékmódot.
     *
     * @return A kiválasztott játékmód.
     */
	public char getGameMode() {
		return gameModeChoice;
	}
	
}
