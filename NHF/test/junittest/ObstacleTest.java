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

public class ObstacleTest {

	@Test
	public void testChangePosition() {
		GameBoard board = new GameBoard();
		Obstacle o = new Obstacle(board);
		List<Obstacle>obs  = new ArrayList<>();
		obs.add(o);
		board.setObstacles(obs);
		
		int x = board.getObstacles().get(0).getX();
		int y= board.getObstacles().get(0).getY();
		
		board.getObstacles().get(0).changePosition();
		
		assertNotEquals(x, board.getObstacles().get(0).getX());
		assertNotEquals(y, board.getObstacles().get(0).getY());
	}

}
