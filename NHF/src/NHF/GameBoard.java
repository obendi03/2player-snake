package NHF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Representation of the game board class.
 * This class stores and manages obstacles and players during the game.
 * It handles player information and obstacles on the board.
 */
public class GameBoard implements Serializable {
	private  final int WIDTH = 600;
	private  final int HEIGHT = 600;
	private  final int UNIT_SIZE = 20;
	private  final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
	private List<SnakePlayer> players = new  ArrayList<>();
	private List<Obstacle> obstacles;
	
   /**
     * Sets the obstacles on the game board.
     *
     * @param obstacles The list of obstacles to set.
     */
	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = new ArrayList<>();
		this.obstacles.addAll(obstacles);
	}
	
  /**
     * Adds a player to the game board.
     *
     * @param player The SnakePlayer object to add.
     */
	public void setPlayer(SnakePlayer player) {
		players.add(player);
	}
	
   /**
     * Retrieves the player at the specified index.
     *
     * @param i The index of the player to retrieve.
     * @return The SnakePlayer object at the specified index, or null if not found.
     */
	public SnakePlayer getPlayer(int i) {
		if(i>=0 && i<players.size()) {
			 return players.get(i);
		}
		return null;
	}
	
 /**
     * Removes the player at the specified index from the game board.
     *
     * @param i The index of the player to remove.
     */
	public void removePlayer(int i) {
		if(i>=0 && i<players.size()) {
		 players.remove(i);
		}
	}
	
      /**
     * Retrieves the list of players on the game board.
     *
     * @return The list of SnakePlayer objects on the board.
     */
	public List<SnakePlayer> getPlayers() {
		return players;
	}
	
     /**
     * Retrieves the list of obstacles on the game board.
     *
     * @return The list of Obstacle objects representing the obstacles.
     */
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	
    /**
     * Sets the size of the obstacles list.
     *
     * @param size The size to set for the obstacles list.
     */
	public void setObstaclesSize(int size) {
		obstacles = new ArrayList<>(size);
	}
	
	
       /**
     * Retrieves the width of the game board.
     *
     * @return The width of the game board.
     */
    public int getWidth() {
        return this.WIDTH;
    }

    /**
     * Retrieves the height of the game board.
     *
     * @return The height of the game board.
     */
    public int getHeight() {
        return this.HEIGHT;
    }

    /**
     * Retrieves the unit size of a block on the game board.
     *
     * @return The unit size on the game board.
     */
    public int getUnitSize() {
        return this.UNIT_SIZE;
    }

    /**
     * Retrieves the number of units (blocks) on the game board.
     *
     * @return The number of units (blocks) on the game board.
     */
    public int getNumberOfUnits() {
        return this.NUMBER_OF_UNITS;
    }
}
