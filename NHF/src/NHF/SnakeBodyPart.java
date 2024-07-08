package NHF;

import java.awt.Color;
import java.io.Serializable;
/**
 * A Kígyó testrészét reprezentáló osztály, ebből az objektumból épitjük fel a kigyó testét.
 */
public class SnakeBodyPart implements Serializable{
	private int x;
	private int y;
	private Color tailColor;
	
    /**
     * Konstruktor a SnakeBodyPart osztályhoz.
     * Beállítja a farok színét és az (x, y) koordinátákat.
     *
     * @param tailColor A testrész színe.
     */
	public SnakeBodyPart(Color tailColor) {
		this.tailColor = tailColor;
		this.x = 0;
		this.y = 0;
	}
	

    /**
     * Visszaadja a testrész színét.
     *
     * @return A testrész színe.
     */
	public Color getColor() {return tailColor;}
	
    /**
     * Visszaadja a testrész X koordinátáját.
     *
     * @return Az X koordináta értéke.
     */
	public int getX() {return x;}
	
	  /**
     * Visszaadja a testrész Y koordinátáját.
     *
     * @return Az Y koordináta értéke.
     */
	public int getY() {return y;}
	
	  /**
     * Beállítja a testrész X koordinátáját.
     *
     * @param x Az X koordináta értéke.
     */
	public void setX(int x) {this.x = x;}
	

    /**
     * Beállítja a testrész Y koordinátáját.
     *
     * @param y Az Y koordináta értéke.
     */
	public void setY(int y) {this.y = y;}
}
