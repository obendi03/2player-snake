package NHF;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
/**
 * Class representing obstacles on the game board.
 */
public class Obstacle implements Serializable{
	private int x;
	private int y;
	private Random random;
	private GameBoard board;
	private Color color;
	
    /**
     * Constructor for Obstacle class.
     * Sets the game board, initializes the color, and changes the position of the obstacle.
     *
     * @param board The game board where obstacles are placed.
     */
	public Obstacle(GameBoard board) {
		this.board = board;
		color = Color.RED;
		this.changePosition();
	}
	
   /**
     * Returns the X coordinate of the obstacle.
     *
     * @return The value of the X coordinate.
     */
	public int getX() {return x;}
	
   /**
     * Returns the Y coordinate of the obstacle.
     *
     * @return The value of the Y coordinate.
     */
	public int getY() {return y;}

 
    /**
     * Draws the obstacle on the provided graphics object.
     *
     * @param graphics The graphics object to draw the obstacle on.
     */
	public void draw(Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.fillRect(x, y, board.getUnitSize(), board.getUnitSize());
	}
	
	 /**
     * Changes the position of the obstacle within the game board.
     * The obstacle is randomly placed on the board.
     */
	public void changePosition() {
	 random = new Random();
	 int rX = random.nextInt((int)(board.getWidth() / board.getUnitSize()))*board.getUnitSize();
	 int rY = random.nextInt((int)(board.getHeight() / board.getUnitSize()))*board.getUnitSize();
	 
	 this.x = rX;
	 this.y = rY;
	}
}

