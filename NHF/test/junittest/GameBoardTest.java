package junittest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import NHF.GameBoard;
import NHF.Obstacle;
import NHF.SnakeBodyPart;
import NHF.SnakeGamePanel;
import NHF.SnakePlayer;

public class GameBoardTest {
	GameBoard board;
	@Before
	public void setUp() throws Exception {		
		 board = new GameBoard();	
		
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
		
		player1body.add(new SnakeBodyPart(player1Head))	;
		player2body.add(new SnakeBodyPart(player2Head))	;
		 for(int i = 0; i<4;i++) {
			player1body.add(new SnakeBodyPart(player1Tail))	;
			player2body.add(new SnakeBodyPart(player2Tail))	;
	
		}
		
	    SnakePlayer player1 = new SnakePlayer(board,KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,player1Head,player1body);
	    SnakePlayer player2 = new SnakePlayer(board,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D,player2Head,player2body);

		player2.setHead(500, 500);

		board.setObstacles(obstacles);
		
		board.setPlayer(player1);
		board.setPlayer(player2);
	}

	@Test
	public void testGetPlayer() {
		assertEquals(Color.white,board.getPlayer(0).getBodyPart(0).getColor());
		
		for(int i=1;i<=board.getPlayer(0).getLength();i++ ) {
			assertEquals(Color.cyan,board.getPlayer(0).getBodyPart(i).getColor());
		}
		board.getPlayer(0).grow();
		assertEquals(5,board.getPlayer(0).getLength());
	}
	@Test
	public void testremovePlayer() {
		SnakePlayer removedPlayer = board.getPlayer(0);	
	
		board.removePlayer(3);
		assertEquals(2,board.getPlayers().size());
		
		board.removePlayer(0);
		assertEquals(1,board.getPlayers().size());
		assertNotEquals(removedPlayer,board.getPlayer(0));
	}


}
