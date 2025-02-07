package junittest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import NHF.GameBoard;
import NHF.MenuFrame;
import NHF.Obstacle;
import NHF.SnakeBodyPart;
import NHF.SnakeGameFrame;
import NHF.SnakeGamePanel;
import NHF.SnakePlayer;

public class SnakeGamePanelTest {
	SnakeGamePanel panel;
	SnakePlayer player1;
	SnakePlayer player2;
	@Before
	public void setUp() throws Exception {		
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
			
			}
			
			 player1 = new SnakePlayer(board,KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,player1Head,player1body);
			 player2 = new SnakePlayer(board,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D,player2Head,player2body);

			player2.setHead(500, 500);
			
			board.setObstacles(obstacles);
			
			board.setPlayer(player1);
			board.setPlayer(player2);
			
			
			
			 this.panel = new SnakeGamePanel(board,'E');
	}

	@Test
	public void testSnakeRunsIntoSnake() {
		panel.play();
		player1.setDirection('U');
		panel.checkSnakeRunsIntoSnake();
		assertEquals(false,panel.gameIsRunning());
		assertEquals(player2,panel.getWinner());
	}
	@Test
	public void testSnakeRunsIntoObstacle() {
		panel.play();
		int x =panel.getBoard().getObstacles().get(0).getX();
		int y =panel.getBoard().getObstacles().get(0).getY();
	
		panel.getBoard().getPlayer(0).setHead(x,y-panel.getBoard().getUnitSize());
				
		panel.getBoard().getPlayer(0).move();
		
		panel.checkSnakeRunsIntoObstacle();
		assertEquals(false,panel.gameIsRunning());
		assertEquals(player2,panel.getWinner());
	}
	@Test
	public void testSnakeRunsIntoWall() {
		panel.play();
		panel.getBoard().getPlayer(0).move();
		panel.getBoard().getPlayer(0).move();
		panel.checkSnakeRunsIntoWall();
		
		assertNotEquals(false,panel.gameIsRunning());
		assertNotEquals(player2,panel.getWinner());
		
		panel.getBoard().getPlayer(0).setDirection('L');
		panel.getBoard().getPlayer(0).move();
				
		panel.checkSnakeRunsIntoWall();
		assertEquals(false,panel.gameIsRunning());
		assertEquals(player2,panel.getWinner());
	}
	@Test
	public void testgetPlayerScore() {
		panel.play();
		panel.getBoard().getPlayer(0).grow();
		panel.getBoard().getPlayer(0).grow();
		assertEquals(2, panel.getPlayerScore(0));
	}
}
