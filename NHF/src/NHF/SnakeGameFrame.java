package NHF;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The window where the game board and players are displayed during the game.
 *
 * Displays the scores of the two players and provides a "Save" button to save the game state during gameplay.
 */
public class SnakeGameFrame extends JFrame implements ActionListener,Serializable{

	private JPanel gameMenu;
	private JButton btn;
	private SnakeGamePanel panel;
	private JLabel score1;
	private JLabel score2;
	
    /**
     * Constructor for SnakeGameFrame.
     * Sets up the button and score display labels on the window.
     *
     * @param panel The SnakeGamePanel object to be added to the frame.
     */
	public SnakeGameFrame(SnakeGamePanel panel) {
		this.panel = panel;
		this.add(this.panel);
		this.setTitle("snake");
		
		this.setResizable(false);
		
		score1 = new JLabel("Player1 score:0");
		score1.setName("score1");
		
		score2 = new JLabel("Player2 score:0");
		score2.setName("score2");

		btn = new JButton("Save");
		btn.addActionListener(this);
		
		GridLayout g = new GridLayout(1,3);
	
		gameMenu = new JPanel(new FlowLayout());
		gameMenu.setName("gameMenu");
		gameMenu.add(score1,BorderLayout.WEST);
		gameMenu.add(btn,BorderLayout.CENTER);
		gameMenu.add(score2,BorderLayout.EAST);
		
		g.setHgap(60);
		gameMenu.setLayout(g);
		
		this.add(gameMenu,BorderLayout.SOUTH);
	
		this.pack();
		this.setLocationRelativeTo(null);	
		this.setVisible(true);
		panel.startGame();
  }
	  /**
     * Handles the action performed when the "Save" button is pressed.
     * Saves the current game state to a file and closes the game.
     *
     * @param ae The event triggered by pressing the "Save" button.
     */
	public void actionPerformed(ActionEvent ae) {
		panel.saveToFile();
		panel.closeGame(true);
	}
}