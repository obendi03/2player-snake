package NHF;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the snake in the game.
 * This class implements essential methods for controlling the snake,
 * handling growth upon eating food, and methods for graphical representation.
 */
public class SnakePlayer implements Serializable {
    private char direction;
    private Color headColor;
    private int length = 5;
    private GameBoard board;
    private List<SnakeBodyPart> body;
    private Map<Integer, Character> control;

     /**
     * Constructor to create a SnakePlayer object.
     *
     * @param board    The GameBoard instance that the snake belongs to.
     * @param moveUP   Key code for moving the snake up.
     * @param moveDown Key code for moving the snake down.
     * @param moveLeft Key code for moving the snake left.
     * @param moveRight Key code for moving the snake right.
     * @param head     Color of the snake's head.
     * @param body     Body parts of the snake.
     */
    public SnakePlayer(GameBoard board, int moveUP, int moveDown, int moveLeft, int moveRight, Color head, List<SnakeBodyPart> body) {
        this.board = board;
        headColor = head;
        this.body = new ArrayList<>();
        this.body.addAll(body);

        control = new HashMap<>();
        control.put(moveUP, 'U');
        control.put(moveDown, 'D');
        control.put(moveLeft, 'L');
        control.put(moveRight, 'R');
    }

       /**
     * Moves the snake in the current direction.
     */
    public void move() {
    	int tmp;
		for (int i = body.size()-1; i > 0; i--) {
			body.get(i).setX(body.get(i-1).getX());
			body.get(i).setY(body.get(i-1).getY());
		}
		
		if (direction == 'L') {
			tmp = body.get(0).getX();
			body.get(0).setX(tmp - board.getUnitSize());
		} else if (direction == 'R') {
			tmp = body.get(0).getX();
			body.get(0).setX(tmp + board.getUnitSize());
		} else if (direction == 'U') {
			tmp = body.get(0).getY();
			body.get(0).setY(tmp - board.getUnitSize());
		} else {
			tmp = body.get(0).getY();
			body.get(0).setY(tmp + board.getUnitSize());
		}
    }

   /**
     * Sets the direction of the snake.
     *
     * @param direction The direction to set ('U', 'D', 'L', or 'R').
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }

     /**
     * Sets the position of the snake's head.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setHead(int x, int y) {
        body.get(0).setX(x);
        body.get(0).setY(y);
    }

  /**
     * Draws the snake on the provided Graphics object.
     *
     * @param graphics The Graphics object to draw on.
     */
    public void draw(Graphics graphics) {
		graphics.setColor(headColor);
		graphics.fillRect(body.get(0).getX(), body.get(0).getY(), board.getUnitSize(), board.getUnitSize());
		
		for (int i = 1; i < this.getLength(); i++) {
			graphics.setColor(body.get(i).getColor());
			graphics.fillRect(body.get(i).getX(), body.get(i).getY(), board.getUnitSize(), board.getUnitSize());
		}
	}

     /**
     * Returns the direction associated with the given key.
     *
     * @param key The key code for which the direction is requested.
     * @return The direction ('U', 'D', 'L', 'R') associated with the key.
     */
    public char getControl(int key) {
        return control.get(key);
    }

     /**
     * Checks if the control map contains the given key.
     *
     * @param key The key code to check.
     * @return True if the control map contains the key, otherwise false.
     */
    public Boolean containsKey(int key) {
        return control.containsKey(key) ? true : false;
    }

     /**
     * Increases the length of the snake by adding a new body part.
     */
    public void grow() {
        body.add(new SnakeBodyPart(body.get(1).getColor()));  // Adding a new body part to the snake
    }

     /**
     * Returns the current length of the snake.
     *
     * @return The length of the snake (excluding the head).
     */
    public int getLength() {
        length = body.size() - 1;
        return length;
    }

     /**
     * Retrieves a specific body part of the snake based on the index.
     *
     * @param i The index of the body part to retrieve.
     * @return The SnakeBodyPart object at the specified index.
     */
    public SnakeBodyPart getBodyPart(int i) {
        if (i >= 0 && i <= this.getLength()) {
            return body.get(i);
        }
        return null;
    }
}
