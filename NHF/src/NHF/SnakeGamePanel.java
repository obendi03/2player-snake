package NHF;


import java.awt.*;

import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/**
 * Class responsible for controlling the game.
 * 
 * This class invokes functions responsible for drawing game elements.
 * It manages the game execution, snake movements, and gameplay.
 * It checks collisions with obstacles, walls, and between snakes.
 * Also, it is responsible for drawing individual game components.
 */
public class SnakeGamePanel extends JPanel implements ActionListener,Serializable{

	private GameBoard board;
	private int foodX;
	private int foodY;
	private boolean running = false;
	private int playerWon = -1;
	private int obstaclesNumber = 1;
	private int obstaclesMaxNumber;
	private SnakePlayer winner;
	private Random random; 
	private HashMap<SnakePlayer,Integer> playerIndex = new HashMap<>();
	
	private Timer timer;

	  /**
     * Constructor initializing the game panel.
     * Sets up the game board, difficulty level, and initializes default game settings.
     *
     * @param board      The game board on which the game is played.
     * @param difficulty The difficulty level of the game ('E' - easy, 'N' - normal, 'H' - hard).
     */
	public SnakeGamePanel(GameBoard board,char difficulty) {
		random = new Random();
		switch(difficulty) {
			case 'E':
			obstaclesMaxNumber = 3;
			break;
			case 'N':
			obstaclesMaxNumber = 5;
			break;
			case 'H':
			obstaclesMaxNumber = 10;
			break;
		}
		winner = null;
		this.board = board;
		
		for(int i = 0;i<board.getPlayers().size();i++) {
			playerIndex.put(board.getPlayer(i), i+1);
		}
		
		
		this.setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
		this.setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
	}	
	
	 /**
     * Returns the winner of the game.
     *
     * @return The winning player (SnakePlayer object).
     */
	public SnakePlayer getWinner() {
		return winner;
	}
	
	  /**
     * Starts the game, updates players' scores, and begins game execution.
     */
	public void startGame() {
		for(SnakePlayer s:board.getPlayers()) {
				refreshPlayerScore(s);
		}
		play();
	}
  /**
     * Closes the game and returns to the menu.
     *
     * @param reloadCalled true if the game ended with pressing the Save button, otherwise false.
     */
	public void closeGame(boolean reloadCalled) {
		
		if(!running || reloadCalled) {		
			timer.stop();
			
			Object[] options = {"OK"};
	   	    int n = JOptionPane.showOptionDialog(this,
	   	                   "Nyomjon az OK gombra a menübe való visszatéréshez! ","üzenet",
	   	                   JOptionPane.PLAIN_MESSAGE,
	   	                   JOptionPane.QUESTION_MESSAGE,
	   	                   null,
	   	                   options,
	   	                   options[0]);
	   	   if(n==0) {
		   	     final Window parentWindow = SwingUtilities.getWindowAncestor(SnakeGamePanel.this);
	             parentWindow.dispose();	  	  
             }
		}
	}
	
   /**
     * Returns the game board where the game is played.
     *
     * @return The game board (GameBoard object).
     */
	public GameBoard getBoard() {
		return board;
	}
	
	  /**
     * Returns the score of the specified player.
     *
     * @param i The index identifying the player on the game board.
     * @return The player's score.
     */
	public int getPlayerScore(int i) {
		return board.getPlayer(i).getLength()-4;
	}
	
	  /**
     * Checks if the game is currently running.
     *
     * @return True if the game is running, otherwise false.
     */
	public boolean gameIsRunning() {
		return running;
	}
	
	/**
     * Starts the game, adds food to the game board, and sets the game running.
     * Sets the timer that invokes the actionPerformed method at certain intervals.
     */
	public void play() {
		addFood();
		running = true;		
		timer = new Timer(100, this);//80 volt eredetileg
		timer.start();	
	}
	
	
	
   /**
     * Paints the component, overriding the JPanel's paintComponent method.
     *
     * @param graphics The graphics object on which to draw.
     */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		draw(graphics);
	}
/**
     * Checks if obstacles need to be added to the game board.
     * Adds one obstacle if there are fewer obstacles than the maximum allowed.
     */
	public void checkAddHurdles() {
		if(obstaclesNumber<obstaclesMaxNumber) {
			obstaclesNumber++;
			addHurdle();
		}
		if(obstaclesNumber==obstaclesMaxNumber) {
			obstaclesNumber=obstaclesMaxNumber;
			addHurdle();
		}
	}
	
  /**
     * Refreshes the score of the given player and updates the score display in the game menu.
     *
     * @param player The player to update (SnakePlayer object).
     */
	public void refreshPlayerScore(SnakePlayer player) {
		int index = board.getPlayers().indexOf(player);
		JFrame parentFrame =(JFrame) SwingUtilities.getWindowAncestor(SnakeGamePanel.this);
		parentFrame.getContentPane();
		
		Component[] components = parentFrame.getContentPane().getComponents();
		
		String score = Integer.toString(getPlayerScore(index));
		for (Component c : components) {
			if(c instanceof JPanel && c.getName()=="gameMenu") {
				JPanel gameMenuPanel = (JPanel) c;
			
				for (Component gameMenuPanelcomponent : gameMenuPanel.getComponents()) {						 
					 if(gameMenuPanelcomponent instanceof JLabel && gameMenuPanelcomponent.getName()=="score1"  && board.getPlayers().indexOf(player)==0) {
						 ((JLabel)gameMenuPanelcomponent).setText(("Player1 score: "+score));
					 	}
					 else if(gameMenuPanelcomponent instanceof JLabel &&  gameMenuPanelcomponent.getName()=="score2"&&  board.getPlayers().indexOf(player)==1) {
						 ((JLabel)gameMenuPanelcomponent).setText(("Player2 score: "+score));
					 	}
				 }
			}
		}
	}
	
  /**
     * Checks if any snake has reached the food on the game board.
     * Increases the length of the snake that reaches the food, checks obstacles, and changes the food position.
     */
	public void checkFood() {
		int playerHeadX;
		int playerHeadY;
	
		for(int i = 0;i<board.getPlayers().size();i++) {
			 playerHeadX = board.getPlayer(i).getBodyPart(0).getX();
			 playerHeadY = board.getPlayer(i).getBodyPart(0).getY();
			
			 if(playerHeadX == foodX && playerHeadY == foodY) {
				board.getPlayer(i).grow();
				checkAddHurdles();
				addFood();
				refreshPlayerScore(board.getPlayer(i));
			}	
		}
	}

   /**
     * Draws the food on the specified graphics surface.
     *
     * @param graphics The graphics object on which to draw the food.
     */
	public void drawFood(Graphics graphics) {
		graphics.setColor(new Color(210, 115, 90));
		graphics.fillOval(foodX, foodY, board.getUnitSize(), board.getUnitSize());
	}
	
    /**
     * Draws elements of the board object on the specified graphics surface.
     * Calls the drawing methods of individual objects.
     *
     * @param graphics The graphics object on which to draw the game board and its elements.
     */
	public void drawBoard(Graphics graphics) {
		
		for(int i = 0;i<board.getPlayers().size();i++) {
			board.getPlayer(i).draw(graphics);
		}
		
		List<Obstacle> obs = new ArrayList<>();
		obs.addAll(board.getObstacles()) ;
		
		for(int i = 0;i<obstaclesNumber;i++) {
			obs.get(i).draw(graphics);
		}
	}
    /**
     * Draws the game elements on the screen based on the running flag.
     * If the game is over, calls the gameOver method to display the winner.
     *
     * @param graphics The graphics object on which to draw all game elements.
     */
	public void draw(Graphics graphics) {

		if (running){
			drawBoard(graphics);
			drawFood(graphics);
		 }	
		else if(winner != null){
			gameOver(graphics,winner);
		}
	}
	
	  /**
     * Adds food to the game board at a new random position.
     * Ensures that the food does not fall on any obstacle.
     */
	public void addFood() {
		foodX = random.nextInt((int)(board.getWidth() / board.getUnitSize()))*board.getUnitSize();
		foodY = random.nextInt((int)(board.getHeight() / board.getUnitSize()))* board.getUnitSize();
		
			for(int i=0; i<obstaclesNumber;i++) {
				if(board.getObstacles().get(i).getX()==foodX && board.getObstacles().get(i).getY()==foodY) {
					foodX = random.nextInt((int)(board.getWidth() / board.getUnitSize()))*board.getUnitSize();
					foodY = random.nextInt((int)(board.getHeight() / board.getUnitSize()))* board.getUnitSize();
				}
			}
	}
	
	 /**
     * Adds an obstacle to the game board at a new random position.
     * Ensures that the obstacle does not overlap with any other game element.
     */
	public void addHurdle() {
		
		List<Obstacle> obs = new ArrayList<>();
		obs.addAll(board.getObstacles()) ;
		for(int i = 0;i<obstaclesMaxNumber;i++) {
			obs.get(i).changePosition();
			for(SnakePlayer s: board.getPlayers()){
				if(obs.get(i).getX()==s.getBodyPart(0).getX() && obs.get(i).getY()==s.getBodyPart(0).getY()) {
					obs.get(i).changePosition();
				}
			}
		}
	}
	
	
	    /**
     * Checks if the snakes have collided with themselves or each other.
     */
	public void checkSnakeRunsIntoSnake() {
		
		int playerHeadX;
		int playerHeadY;
		int playerIndex;
		for(int i = 0;i<board.getPlayers().size();i++) {
				 playerHeadX = board.getPlayer(i).getBodyPart(0).getX();
				 playerHeadY = board.getPlayer(i).getBodyPart(0).getY();
		
				 for(int j = 0;j<board.getPlayers().size();j++) {	
					 if( board.getPlayer(j)!= null) {
					 
					 for (int k = 1; k<=board.getPlayer(j).getLength();k++) { 					
						
						 if(board.getPlayer(j).getBodyPart(k) != null ) {						
							//snake runs itself
							if(playerHeadX == board.getPlayer(j).getBodyPart(k).getX() && playerHeadY  == board.getPlayer(j).getBodyPart(k).getY()&& board.getPlayer(j) ==board.getPlayer(i)) {
								playerIndex = i;
								
									if(playerIndex != -1) {
										board.removePlayer(playerIndex);	
																				
										if(board.getPlayers().size()==1 && board.getPlayer(0) != null) {	
											running = false;	
											winner = board.getPlayer(0);	
											return;
										}							
									}								
							}
							
							//snake runs into the other snake
							if (playerHeadX == board.getPlayer(j).getBodyPart(k).getX() && playerHeadY  == board.getPlayer(j).getBodyPart(k).getY()){						
								playerIndex = i;	
								if(playerIndex != -1) {
										
									if(board.getPlayers().size()==2) {	
										running = false;
										if(board.getPlayer(0).getLength()>board.getPlayer(1).getLength()) {
											winner = board.getPlayer(0);
											return;
										}
										else {
											winner = board.getPlayer(1);
											return;
										}
									
									}				
								 }
							   }
							 }
					 		}
						}
				 }
			}
		}
	
	/**
	 * Checks if snakes have collided with the walls of the game board.
	 */
	public void checkSnakeRunsIntoWall() {
		int playerHeadX;
		int playerHeadY;
		int playerIdx;
		for(int i = 0;i<board.getPlayers().size();i++) {		
			 playerHeadX = board.getPlayer(i).getBodyPart(0).getX();
			 playerHeadY = board.getPlayer(i).getBodyPart(0).getY();
			if (playerHeadX < 0 || playerHeadX > board.getWidth() || playerHeadY < 0 || playerHeadY > board.getHeight()) {
				playerIdx = i;
				if(playerIdx != -1) {
					
					board.removePlayer(playerIdx);
					if(board.getPlayers().size()==1) {
						running = false;
						winner = board.getPlayer(0);
					}
				}
			}
		}
	}
	
	/**
	 * Checks if snakes have collided with obstacles on the game board.
	 */
	public void checkSnakeRunsIntoObstacle() {
		int playerHeadX;
		int playerHeadY;
		int playerIdx;
		
		for(int i = 0;i<board.getPlayers().size();i++) {
			 playerHeadX = board.getPlayer(i).getBodyPart(0).getX();
			 playerHeadY = board.getPlayer(i).getBodyPart(0).getY();
			
			 for (int j = 0; j<=obstaclesNumber;j++) {
				if (playerHeadX == board.getObstacles().get(j).getX() && playerHeadY ==  board.getObstacles().get(j).getY()) {			
					playerIdx = i;
					if(playerIdx != -1) {
						board.removePlayer(playerIdx);
						if(board.getPlayers().size()==1) {
							
							winner = board.getPlayer(0);
							running = false;
						}
					}
				}
		 }
		}
	}
	
	/**
	 * Calls the methods responsible for checking if snakes have collided with walls, obstacles, or each other.
	 * 
	 * */
	public void checkHit() {
		checkSnakeRunsIntoWall();
		checkSnakeRunsIntoSnake();
		checkSnakeRunsIntoObstacle();
			if(!running) {
				timer.stop();
			}
	}
	/**
	 *The game over method displays the winner of the game.
	 * @param graphics The graphics object on which to draw the game over message.
	 * @param winner The winning player (SnakePlayer object).
	 * 
	 */
	public void gameOver(Graphics graphics,SnakePlayer winner) {
		graphics.setColor(Color.red);
		graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
		FontMetrics metrics = getFontMetrics(graphics.getFont());
			
		graphics.drawString("Player"+playerIndex.get(winner)+" won!", (board.getWidth() - metrics.stringWidth("Player lost!")) / 2, board.getHeight() / 4);
	}
	
	/**
 * Handles game events from snake movements to collision checks.
 * If the value of `running` is false, closes the window displaying the game.
 *
 * @param arg0 The event object that triggered this method.
 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (running) {	
			for(int i = 0;i<board.getPlayers().size();i++) {
				board.getPlayer(i).move();
			}
			checkFood();
			checkHit();
		}
		repaint();
		closeGame(false);
	}
	
	/**
     * Inner class extending the KeyAdapter class to handle keyboard events for controlling snake movements.
     */
	private class MyKeyAdapter extends KeyAdapter implements Serializable{
		
	/**
 * Returns the direction in which the snake should turn upon pressing a keyboard key.
 * 
 * @param e The pressed key as a parameter.
 */	
	public void keyPressed(KeyEvent e) {
			char dir;
				for(int i = 0; i< board.getPlayers().size();i++) {
					if(board.getPlayer(i).containsKey(e.getKeyCode())) {
						dir = board.getPlayer(i).getControl(e.getKeyCode());
						board.getPlayer(i).setDirection(dir);
					}
			}
		}
	}
	/**
	 * Saves the game to a file.
	 */
	public void saveToFile() {
		try {
			FileOutputStream f = new FileOutputStream("lastgame.txt");
			ObjectOutputStream out =new ObjectOutputStream(f);
			out.writeObject(this);
			out.close();
			}
			catch(IOException ex) { 
				System.out.println("Nem letezik az allomany, IO hiba keltkezett!");
			}
		}
	}