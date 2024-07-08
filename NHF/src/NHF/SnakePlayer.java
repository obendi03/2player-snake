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
 * A kígyót reprezentáló osztály a játékban. * 
 * Ebben az osztályban vannak megvalósitva a kigyó irányitásához szükséges fontosabb függények,
 * amik a kigyó vezérléséhez, étel felszedése esetén a növekedéshez szükséges függvények. 
 * Illetve a grafikus megjelenítéséhez szükséges függvény.
 *  */
public class SnakePlayer implements Serializable {
    private char direction;
    private Color headColor;
    private int length = 5;
    private GameBoard board;
    private List<SnakeBodyPart> body;
    private Map<Integer, Character> control;

    /**
     * Konstruktor létrehozza a SnakePlayer objektumot.
     *
     * @param board    A GameBoard példány, pálya amihez a kígyó tartozik.
     * @param moveUP   A felfelé mozgatást vezérlő billentyűkód.
     * @param moveDown A lefelé mozgatást vezérlő billentyűkód.
     * @param moveLeft A balra mozgatást vezérlő billentyűkód.
     * @param moveRight A jobbra mozgatást vezérlő billentyűkód.
     * @param head     A kígyó fejének színe.
     * @param body     A kígyó testrészei.
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
     * Mozgatja a kígyót az aktuális irányba.
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
     * Beállítja a kígyó irányát.
     *
     * @param direction Az irány beállítása. lehetséges értékek: ('F', 'L', 'J', vagy 'B').
     */
    public void setDirection(char direction) {
        this.direction = direction;
    }

    /**
     * Beállítja a kígyó fejének pozícióját.
     *
     * @param x Az x-koordináta.
     * @param y Az y-koordináta.
     */
    public void setHead(int x, int y) {
        body.get(0).setX(x);
        body.get(0).setY(y);
    }

    /**
     * Kirajzolja a kígyót a megadott Graphics objektumra.
     *
     * @param graphics A Graphics objektum, amire rajzol.
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
     * Visszaadja a megadott billentyűhöz tartozó irányt.
     *
     * @param key A billentyűkód, amelyhez tartozó irányt szeretnénk lekérni.
     * @return Az irány ahova fordul a kigyó('F', 'L', 'J', 'B').
     */
    public char getControl(int key) {
        return control.get(key);
    }

    /**
     * Ellenőrzi, hogy a vezérlési térkép tartalmazza-e a megadott kulcsot.
     *
     * @param key Az ellenőrizendő billentyűkód.
     * @return True, ha a vezérlési billentyűkódot tartalmazza, egyébként false.
     */
    public Boolean containsKey(int key) {
        return control.containsKey(key) ? true : false;
    }

    /**
     * Növeli a kígyó hosszát egy új testrész hozzáadásával.
     */
    public void grow() {
        body.add(new SnakeBodyPart(body.get(1).getColor()));  // Új testrész hozzáadása a kígyóhoz
    }

    /**
     * Visszaadja a kígyó jelenlegi hosszát.
     *
     * @return A kígyó hossza (fejet nem számítva).
     */
    public int getLength() {
        length = body.size() - 1;
        return length;
    }

    /**
     * Lekéri a kígyónak, egy adott testrészét az index alapján.
     *
     * @param i A lekérdezni kívánt testrész indexe.
     * @return A megadott indexű SnakeBodyPart objektum.
     */
    public SnakeBodyPart getBodyPart(int i) {
        if (i >= 0 && i <= this.getLength()) {
            return body.get(i);
        }
        return null;
    }
}
