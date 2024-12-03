
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.event.*;

public class BoardGUI extends JFrame implements Composite2048Observer {

	private GameManager manager;
	private JPanel introPanel;
	private JPanel gamePanel;
	private JPanel boardPanel;
	private JPanel[][] itemPanels;
	private JLabel titleLabel;
	private JLabel gameLabel;
	private JLabel scoreLabel;
	private JButton startButton;
	private JButton upButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton downButton;
	private JButton exitButton;
	private JButton leaderboardButton;
	private JButton boardSizeButton;
	private CardLayout layout;
	private BoxLayout gameLayout;
	private static final int DEFAULT_BOARD_SIZE = 4;

	public BoardGUI() {
		manager = new GameManager(4);
		setUp();
	}

	public void setUp() {
		setTitle("2048");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new CardLayout();
		setLayout(layout);

		// create new title panel
		introPanel = new JPanel();
		introPanel.setLayout(null);

		// set up the title label
		titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setFont(new Font("Title", Font.PLAIN, 40));
		titleLabel.setBounds(450, 0, 100, 100);
		introPanel.add(titleLabel);

		// create start button
		startButton = new JButton("Start Game");
		startButton.setBounds(400, 150, 200, 75);
		startButton.setActionCommand("start");
		startButton.addActionListener(new ButtonListener());
		introPanel.add(startButton);

		// create board size button
		boardSizeButton = new JButton("Change Board Size");
		boardSizeButton.setBounds(400, 300, 200, 75);
		boardSizeButton.setActionCommand("boardSize");
		boardSizeButton.addActionListener(new ButtonListener());
		introPanel.add(boardSizeButton);

		// create LeaderBoard button
		leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.setBounds(400, 450, 200, 75);
		leaderboardButton.setActionCommand("leaderboard");
		leaderboardButton.addActionListener(new ButtonListener());
		introPanel.add(leaderboardButton);

		exitButton = new JButton("Exit");
		exitButton.setBounds(400, 600, 200, 75);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(new ButtonListener());
		introPanel.add(exitButton);

		gamePanel = new JPanel();
		gamePanel.setLayout(gameLayout);
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.add(introPanel);
		this.add(gamePanel);
		layout.addLayoutComponent(introPanel, "Intro");
		layout.addLayoutComponent(gamePanel, "Game");
	}

	public void startGame() {
		// Remove intro panel contents:
		layout.removeLayoutComponent(introPanel);

		// Create button dimensions:
		Dimension buttonSize = new Dimension(180, 40);
		int gap = 40;

		// Create the board:
		boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setBounds(250, 100, 600, 600);
		GridLayout boardLayout = new GridLayout(4, 4);
		boardPanel.setLayout(boardLayout);
		itemPanels = new JPanel[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				JPanel itemPanel = new JPanel();
				itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemPanel.setLayout(new GridBagLayout());
				JLabel itemLabel = new JLabel();
				itemLabel.setFont(new Font("Item", Font.PLAIN, 30));
				itemPanel.add(itemLabel);
				boardPanel.add(itemPanel);
				itemPanels[i][j] = itemPanel;
			}
		}
		gamePanel.add(boardPanel);
		manager.addCompositeObserver(this);

		// TODO: add key listener
		gameLabel = new JLabel("Move the tiles with the buttons below\nor the arrows on your keyboard!");
		gameLabel.setBounds(50, -50, 600, 200);
		gamePanel.add(gameLabel);
		
		// Display player's current score
		scoreLabel = new JLabel("Current Score: 0");
		scoreLabel.setFont(new Font("score", Font.PLAIN, 20));
		scoreLabel.setBounds(10, 50, 400, 400);
		gamePanel.add(scoreLabel);

		// Create the directional buttons:
		upButton = new JButton('\u2191' + "");
		upButton.setFont(new Font("Button", Font.PLAIN, 21));
		upButton.setBounds(85, 287, 75, 75);
		upButton.setActionCommand("up");
		upButton.addActionListener(new ButtonListener());
		gamePanel.add(upButton);

		leftButton = new JButton('\u2190' + "");
		leftButton.setFont(new Font("Button", Font.PLAIN, 21));
		leftButton.setBounds(10, 362, 75, 75);
		leftButton.setActionCommand("left");
		leftButton.addActionListener(new ButtonListener());
		gamePanel.add(leftButton);

		rightButton = new JButton('\u2192' + "");
		rightButton.setFont(new Font("Button", Font.PLAIN, 21));
		rightButton.setBounds(160, 362, 75, 75);
		rightButton.setActionCommand("right");
		rightButton.addActionListener(new ButtonListener());
		gamePanel.add(rightButton);

		downButton = new JButton('\u2193' + "");
		downButton.setFont(new Font("Button", Font.PLAIN, 21));
		downButton.setBounds(85, 437, 75, 75);
		downButton.setActionCommand("down");
		downButton.addActionListener(new ButtonListener());
		gamePanel.add(downButton);

		// Set up the exit button for gamePanel:
		exitButton = new JButton("Exit");
		exitButton.setBounds(875, 700, 100, 40);
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(new ButtonListener());
		gamePanel.add(exitButton);

		// Create the leaderboard (maybe) (TODO):

	}
	
	private void updateScore() {
		int currScore = manager.getCurScore();
		scoreLabel.setText("Current Score:" + currScore);
	}

	// Listener class to respond to button presses:
	private class ButtonListener implements ActionListener {

		// Carry out the command for the button press:
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand().toLowerCase();
			switch (cmd) {
			case "start":
				startGame();
				break;
			case "boardSize":
			case "leaderboard":
			case "up":
				updateScore();
			case "left":
				updateScore();
			case "right":
				updateScore();
			case "down":
				manager.shift(Direction.strToDir(cmd));
				manager.printCurrentBoard();
				updateScore();
				break;
			case "exit":
				System.exit(0);
				break;
			}
		}
	}
	
	private void getBoardSize() {
		layout.removeLayoutComponent(introPanel);
		
	}

	public void updateObserver(int row, int col, int value) {
		JPanel thisPanel = itemPanels[row][col];
		JLabel thisLabel = ((JLabel) thisPanel.getComponent(0));

		if (value == 0) {
			thisLabel.setText("");
		} else {
			thisLabel.setText(String.valueOf(value));
		}

		// Colors taken from
		// https://github.com/daniel-huang-1230/Game-2048/blob/master/Gui2048.java
		// TODO idk if this is plagiarism
		Color[] colorsToUse = new Color[] { new Color(204, 192, 179), new Color(238, 228, 218),
				new Color(237, 224, 200), new Color(242, 177, 121), new Color(245, 149, 99), new Color(246, 124, 95),
				new Color(246, 94, 59), new Color(237, 207, 114), new Color(237, 204, 97), new Color(237, 200, 80),
				new Color(237, 197, 63), new Color(237, 194, 46) };
		int colorI = (value > 0 ? (int) (Math.log(value) / Math.log(2)) : 0);
		Color colorToUse = colorsToUse[Math.min(colorsToUse.length - 1, colorI)];
		itemPanels[row][col].setBackground(colorToUse);
	}

	public static void main(String[] args) {
		BoardGUI gui = new BoardGUI();
		gui.setVisible(true);
	}
}
