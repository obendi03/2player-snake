package NHF;

import java.awt.Color;
import java.io.Serializable;
/**
 * Class representing a body part of the snake, used to build the snake's body.
 */
public class SnakeBodyPart implements Serializable{
	private int x;
	private int y;
	private Color tailColor;
	
     /**
     * Constructor for SnakeBodyPart class.
     * Sets the color of the body part and its (x, y) coordinates.
     *
     * @param tailColor The color of the body part.
     */
	public SnakeBodyPart(Color tailColor) {
		this.tailColor = tailColor;
		this.x = 0;
		this.y = 0;
	}
	

       /**
     * Returns the color of the body part.
     *
     * @return The color of the body part.
     */
	public Color getColor() {return tailColor;}
	
   /**
     * Returns the X coordinate of the body part.
     *
     * @return The value of the X coordinate.
     */
	public int getX() {return x;}
	
  /**
     * Returns the Y coordinate of the body part.
     *
     * @return The value of the Y coordinate.
     */
	public int getY() {return y;}
	
    /**
     * Sets the X coordinate of the body part.
     *
     * @param x The value of the X coordinate.
     */
     public void setX(int x) {
          this.x = x;
     }

     /**
      * Sets the Y coordinate of the body part.
     *
     * @param y The value of the Y coordinate.
     */
     public void setY(int y) {
          this.y = y;
     }
}
