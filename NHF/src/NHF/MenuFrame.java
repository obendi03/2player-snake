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
 * Class representing the menu for the Snake game.
 * Handles game mode selection and loading of previous games.
 */
public class MenuFrame extends JFrame implements ActionListener {
	JButton easyButton;
	JButton normalButton;
	JButton hardButton;
	JButton reloadButton;
	JPanel jpanelMenu;
	private char gameModeChoice;
	
	  /**
     * Constructor for MenuFrame class.
     * Sets up the menu window and creates buttons for different game modes (Easy, Normal, Hard).
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
     * Initializes the game with the chosen game mode. The game mode affects the number of obstacles.
     *
     * @param gameType The chosen game mode.
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
     * ActionListener interface method to handle events for game mode selection buttons.
     *
     * @param ae The event that triggered the method.
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
     * Loads the last saved game from file.
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
		} catch(ClassNotFoundException ex) { System.out.println("No such a file exists");}
			
		SnakeGameFrame sf2 = new SnakeGameFrame(panel);
	}
    /**
     * Returns the selected game mode.
     *
     * @return The selected game mode.
     */
	public char getGameMode() {
		return gameModeChoice;
	}
	
}
