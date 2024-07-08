package junittest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import NHF.GameBoard;
import NHF.SnakeBodyPart;
import NHF.SnakePlayer;

public class SnakePlayerTest {
	SnakePlayer player;
	@Before
	public void setUp() throws Exception {
		GameBoard board = new GameBoard();	
		
		Color player1Head = Color.white;
		Color player1Tail = Color.cyan;
		List<SnakeBodyPart> player1body = new ArrayList<>();
	   
		
		
		 for(int i = 0; i<5;i++) {
				player1body.add(new SnakeBodyPart(player1Tail))	;	;
			}
		player = new SnakePlayer(board,KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,player1Head,player1body);
		board.setPlayer(player);
		
	}

	@Test
	public void testMove() {
		int x,y;
		x = player.getBodyPart(0).getX();
		y = player.getBodyPart(0).getY();
		player.move();
		player.move();
		player.move();
		player.move();
		player.move();
		assertEquals(y+100,player.getBodyPart(0).getY());
		assertEquals(y+20,player.getBodyPart(4).getY());
		
		x = player.getBodyPart(0).getX();
		y = player.getBodyPart(0).getY();
		player.setDirection('L');
		player.move();
		assertEquals(x-20,player.getBodyPart(0).getX());
		assertEquals(x,player.getBodyPart(4).getX());
	}
	@Test
	public void TestGrow() {
		player.grow();
		player.grow();
		player.grow();
		assertEquals(7,player.getLength());
	}
	@Test
	public void testgetBodyPart() {
		assertEquals(null,player.getBodyPart(5));
		player.grow();
		assertNotEquals(null,player.getBodyPart(5));
	}

}
