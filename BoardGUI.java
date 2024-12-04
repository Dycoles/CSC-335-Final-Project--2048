
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class BoardGUI extends JFrame implements Composite2048Observer {

	private GameManager manager;
	private JPanel introPanel, gamePanel, boardPanel, leaderboardPanel, boardSizePanel, gameOverPanel;
	private JPanel[][] itemPanels;
	private JLabel titleLabel, gameLabel, scoreLabel, sizeLabel, leaderboardLabel, gameOverLabel;
	private JLabel nameLabel;
	private JButton startButton, exitButton, submitButton, mainMenuButton;
	private JButton upButton, leftButton, rightButton, downButton;
	private JButton leaderboardButton, boardSizeButton;
	private CardLayout layout;
	private BoxLayout gameLayout, boardSizeLayout;
	private JTextField inputTextField, nameTextField;
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
		introPanel.setBackground(Color.decode("#FFFAF0"));

		// set up the title label
		titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 45));
		titleLabel.setForeground(Color.decode("#9F6B53"));
		titleLabel.setBounds(395, 20, 200, 100);
		introPanel.add(titleLabel);

		// Create the size text field with its default size:
		inputTextField = new JTextField(String.valueOf(DEFAULT_BOARD_SIZE));

		// create start button
		startButton = new JButton("Start Game");
		startButton.setBounds(350, 150, 300, 75);
		startButton.setBackground(Color.decode("#F5DEB3"));
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);
		startButton.setFont(new Font("Verdana", Font.BOLD, 20));
		startButton.setForeground(Color.decode("#9F6B53"));
		startButton.setActionCommand("start");
		startButton.addActionListener(new ButtonListener());
		introPanel.add(startButton);

		// create board size button
		boardSizeButton = new JButton("Change Board Size");
		boardSizeButton.setBounds(350, 300, 300, 75);
		boardSizeButton.setBackground(Color.decode("#F5DEB3"));
		boardSizeButton.setOpaque(true);
		boardSizeButton.setBorderPainted(false);
		boardSizeButton.setFont(new Font("Verdana", Font.BOLD, 20));
		boardSizeButton.setForeground(Color.decode("#9F6B53"));
		boardSizeButton.setActionCommand("boardSize");
		boardSizeButton.addActionListener(new ButtonListener());
		introPanel.add(boardSizeButton);

		// create LeaderBoard button
		leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.setBounds(350, 450, 300, 75);
		leaderboardButton.setBackground(Color.decode("#F5DEB3"));
		leaderboardButton.setOpaque(true);
		leaderboardButton.setBorderPainted(false);
		leaderboardButton.setFont(new Font("Verdana", Font.BOLD, 20));
		leaderboardButton.setForeground(Color.decode("#9F6B53"));
		leaderboardButton.setActionCommand("leaderboard");
		leaderboardButton.addActionListener(new ButtonListener());
		introPanel.add(leaderboardButton);

		exitButton = new JButton("Exit");
		exitButton.setBounds(350, 600, 300, 75);
		exitButton.setBackground(Color.decode("#F5DEB3"));
		exitButton.setOpaque(true);
		exitButton.setBorderPainted(false);
		exitButton.setFont(new Font("Verdana", Font.BOLD, 20));
		exitButton.setForeground(Color.decode("#9F6B53"));
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(new ButtonListener());
		introPanel.add(exitButton);

		gamePanel = new JPanel();
		gamePanel.setLayout(gameLayout);
		gamePanel.setBackground(Color.decode("#FFFAF0"));
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		boardSizePanel = new JPanel();
		boardSizePanel.setLayout(boardSizeLayout);
		boardSizePanel.setBackground(Color.decode("#FFFAF0"));
		boardSizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		leaderboardPanel = new JPanel();
		leaderboardPanel.setLayout(gameLayout);
		leaderboardPanel.setBackground(Color.decode("#FFFAF0"));
		leaderboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		gameOverPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(new Color(255, 255, 255, 180));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		gameOverPanel.setLayout(null);
		gameOverPanel.setOpaque(false);

		gameOverLabel = new JLabel("Game Over", JLabel.CENTER);
		gameOverLabel.setFont(new Font("Title", Font.PLAIN, 30));
		gameOverLabel.setBounds(175, 270, 650, 75);
		gameOverPanel.add(gameOverLabel);

		setGlassPane(gameOverPanel);

		this.add(introPanel, "Intro");
		this.add(gamePanel, "Game");
		this.add(boardSizePanel, "Size");
		this.add(leaderboardPanel, "Leaderboard");
	}

	private void keyBindings() {
		InputMap inputMap = gamePanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = gamePanel.getActionMap();
		// Use ButtonListener to use with ActionListener
		ButtonListener newAction = new ButtonListener();

		// WASD key bindings
		inputMap.put(KeyStroke.getKeyStroke('W', 0), "up");
		inputMap.put(KeyStroke.getKeyStroke('A', 0), "left");
		inputMap.put(KeyStroke.getKeyStroke('S', 0), "down");
		inputMap.put(KeyStroke.getKeyStroke('D', 0), "right");

		// arrow key key bindings
		inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
		inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
		inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");

		actionMap.put("up", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				newAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "up"));
			}
		});

		actionMap.put("left", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				newAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "left"));
			}
		});

		actionMap.put("right", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				newAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "right"));
			}
		});

		actionMap.put("down", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				newAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "down"));
			}
		});
	}

	public void startGame() {
		// Retrieve and verify board size input:
		int boardSize;
		try {
			boardSize = Integer.parseInt(inputTextField.getText());
		} catch (NumberFormatException e) {
			sizeLabel.setText("Please enter a valid integer from 4 to 10.");
			return;
		}

		if (boardSize < 4 || boardSize > 10) {
			sizeLabel.setText("Please enter a valid integer from 4 to 10.");
			return;
		}

		// Remove intro panel contents:
		layout.show(getContentPane(), "Game");
		keyBindings();

		// Create the manager:
		manager = new GameManager(boardSize);

		// Create the board:
		boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setBounds(250, 100, 600, 600);
		GridLayout boardLayout = new GridLayout(boardSize, boardSize);
		boardPanel.setLayout(boardLayout);
		itemPanels = new JPanel[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				JPanel itemPanel = new JPanel();
				itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemPanel.setLayout(new GridBagLayout());
				JLabel itemLabel = new JLabel();
				itemLabel.setFont(new Font("Verdana", Font.BOLD, 20));
				itemLabel.setForeground(Color.decode("#9F6B53"));
				itemPanel.add(itemLabel);
				boardPanel.add(itemPanel);
				itemPanels[i][j] = itemPanel;
			}
		}
		gamePanel.add(boardPanel);
		manager.addCompositeObserver(this);

		// set up the title label
		titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 45));
		titleLabel.setForeground(Color.decode("#9F6B53"));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(300, -10, 400, 100);
		gamePanel.add(titleLabel);

		gameLabel = new JLabel("Move the tiles with the buttons below\nor the arrows on your keyboard!");
		gameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		gameLabel.setForeground(Color.decode("#9F6B53"));
		gameLabel.setBounds(25, -15, 600, 200);
		gamePanel.add(gameLabel);

		// Display player's current score
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		scoreLabel.setForeground(Color.decode("#9F6B53"));
		scoreLabel.setBounds(720, -115, 300, 400);
		gamePanel.add(scoreLabel);

		// Create the directional buttons:
		upButton = new JButton('\u2191' + "");
		upButton.setBackground(new Color(204, 192, 179));
		upButton.setOpaque(true);
		upButton.setBorderPainted(false);
		upButton.setFont(new Font("Verdana", Font.BOLD, 21));
		upButton.setForeground(Color.decode("#9F6B53"));
		upButton.setBounds(85, 287, 75, 75);
		upButton.setActionCommand("up");
		upButton.addActionListener(new ButtonListener());
		gamePanel.add(upButton);

		leftButton = new JButton('\u2190' + "");
		leftButton.setBackground(new Color(204, 192, 179));
		leftButton.setOpaque(true);
		leftButton.setBorderPainted(false);
		leftButton.setFont(new Font("Verdana", Font.BOLD, 21));
		leftButton.setForeground(Color.decode("#9F6B53"));
		leftButton.setBounds(10, 362, 75, 75);
		leftButton.setActionCommand("left");
		leftButton.addActionListener(new ButtonListener());
		gamePanel.add(leftButton);

		rightButton = new JButton('\u2192' + "");
		rightButton.setBackground(new Color(204, 192, 179));
		rightButton.setOpaque(true);
		rightButton.setBorderPainted(false);
		rightButton.setFont(new Font("Verdana", Font.BOLD, 21));
		rightButton.setForeground(Color.decode("#9F6B53"));
		rightButton.setBounds(160, 362, 75, 75);
		rightButton.setActionCommand("right");
		rightButton.addActionListener(new ButtonListener());
		gamePanel.add(rightButton);

		downButton = new JButton('\u2193' + "");
		downButton.setBackground(new Color(204, 192, 179));
		downButton.setOpaque(true);
		downButton.setBorderPainted(false);
		downButton.setFont(new Font("Verdana", Font.BOLD, 21));
		downButton.setForeground(Color.decode("#9F6B53"));
		downButton.setBounds(85, 437, 75, 75);
		downButton.setActionCommand("down");
		downButton.addActionListener(new ButtonListener());
		gamePanel.add(downButton);

		// Set up the exit button for gamePanel:
		exitButton = new JButton("Exit");
		exitButton.setBounds(875, 700, 100, 40);
		exitButton.setBackground(Color.decode("#F5DEB3"));
		exitButton.setOpaque(true);
		exitButton.setBorderPainted(false);
		exitButton.setFont(new Font("Verdana", Font.BOLD, 20));
		exitButton.setForeground(Color.decode("#9F6B53"));
		exitButton.setActionCommand("exit");
		exitButton.addActionListener(new ButtonListener());
		gamePanel.add(exitButton);

		// Set up the name field and submit buttons for the leaderboard:
		nameTextField = new JTextField("Name");
		nameTextField.setBounds(375, 390, 250, 75);
		nameTextField.setFont(new Font("Verdana", Font.BOLD, 21));
		nameTextField.setForeground(Color.decode("#9F6B53"));
		nameTextField.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.decode("#9F6B53")));
		nameTextField.setVisible(false);
		nameTextField.setOpaque(true);
		gameOverPanel.add(nameTextField);

		// submit button
		submitButton = new JButton("Submit");
		submitButton.setActionCommand("submit");
		submitButton.addActionListener(new ButtonListener());
		submitButton.setBounds(400, 475, 200, 60);
		submitButton.setBackground(Color.decode("#9F6B53"));
		submitButton.setOpaque(true);
		submitButton.setBorderPainted(false);
		submitButton.setFont(new Font("Verdana", Font.BOLD, 15));
		submitButton.setForeground(Color.decode("#F5DEB3"));
		submitButton.setVisible(false);
		submitButton.setOpaque(true);
		gameOverPanel.add(submitButton);

	}

	private void updateScore() {
		int currScore = manager.getCurScore();
		scoreLabel.setText("Score:" + currScore);
	}

	private void getBoardSize() {
		layout.show(getContentPane(), "Size");

		// set up the title label
		titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 45));
		titleLabel.setForeground(Color.decode("#9F6B53"));
		titleLabel.setBounds(395, 0, 200, 100);
		boardSizePanel.add(titleLabel);

		// set up the size label
		sizeLabel = new JLabel("Please Enter Custom Size From 4 To 10, Then Press 'Start'.", JLabel.CENTER);
		sizeLabel.setFont(new Font("Verdana", Font.BOLD, 17));
		sizeLabel.setForeground(Color.decode("#9F6B53"));
		sizeLabel.setBounds(200, -30, 600, 500);
		boardSizePanel.add(sizeLabel);

		// Set up the input text field:
		inputTextField.setBounds(450, 300, 100, 30);
		inputTextField.setFont(new Font("Verdana", Font.BOLD, 20));
		inputTextField.setForeground(Color.decode("#9F6B53"));
		boardSizePanel.add(inputTextField);

		// Set up the go button (for command execution):
		startButton = new JButton("Start Game");
		startButton.setActionCommand("start");
		startButton.setBackground(Color.decode("#F5DEB3"));
		startButton.setOpaque(true);
		startButton.setBorderPainted(false);
		startButton.setFont(new Font("Verdana", Font.BOLD, 20));
		startButton.setForeground(Color.decode("#9F6B53"));
		startButton.addActionListener(new ButtonListener());
		startButton.setBounds(350, 600, 300, 75);
		boardSizePanel.add(startButton);
	}

	private void getLeaderboard() {
		layout.show(getContentPane(), "Leaderboard");
		leaderboardPanel.removeAll();

		// set up the title label
		titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 45));
		titleLabel.setForeground(Color.decode("#9F6B53"));
		titleLabel.setBounds(395, 0, 200, 100);
		leaderboardPanel.add(titleLabel);

		// set up the title label
		leaderboardLabel = new JLabel("Leaderboard", JLabel.CENTER);
		leaderboardLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		leaderboardLabel.setForeground(Color.decode("#9F6B53"));
		leaderboardLabel.setBounds(200, -100, 600, 500);
		leaderboardPanel.add(leaderboardLabel);

		nameLabel = new JLabel("Top 10", JLabel.CENTER);
		nameLabel.setBounds(350, 160, 300, 75);
		nameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		nameLabel.setForeground(Color.decode("#9F6B53"));
		leaderboardPanel.add(nameLabel);

		// load the leaderboard file
		manager.loadLeaderboard();
		// get the top 10 scores from the leaderboard
		ArrayList<ScoreEntry> scores = manager.getLeaderboard();
		for (int i = 0; i < 10; i++) {
			if (scores.size() <= i) {
				break;
			}
			JLabel playerNameLabel = new JLabel(scores.get(i).getName());
			JLabel playerScoreLabel = new JLabel("" + scores.get(i).getScore());
			playerNameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
			playerNameLabel.setForeground(Color.decode("#9F6B53"));
			playerScoreLabel.setFont(new Font("Verdana", Font.BOLD, 20));
			playerScoreLabel.setForeground(Color.decode("#9F6B53"));
			playerNameLabel.setBounds(300, 125 + i * 25, 300, 250);
			playerScoreLabel.setBounds(625, 125 + i * 25, 300, 250);
			leaderboardPanel.add(playerScoreLabel);
			leaderboardPanel.add(playerNameLabel);
		}

		// create main menu button
		mainMenuButton = new JButton("Back to Main Menu");
		mainMenuButton.setBounds(375, 650, 250, 60);
		mainMenuButton.setBackground(Color.decode("#F5DEB3"));
		mainMenuButton.setOpaque(true);
		mainMenuButton.setBorderPainted(false);
		mainMenuButton.setFont(new Font("Verdana", Font.BOLD, 15));
		mainMenuButton.setForeground(Color.decode("#9F6B53"));
		mainMenuButton.setActionCommand("mainMenu");
		mainMenuButton.addActionListener(new ButtonListener());
		leaderboardPanel.add(mainMenuButton);
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
			case "boardsize":
				getBoardSize();
				break;
			case "leaderboard":
				getLeaderboard();
				break;
			case "mainmenu":
				gameOverPanel.setVisible(false);
				gameOverPanel.removeAll();
				getContentPane().removeAll();
				setUp();
				layout.show(getContentPane(), "Intro");
				break;
			case "submit":
				writeToLeaderboard(nameTextField.getText());
				gameOverLabel.setText("Score Saved!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				gameOverPanel.setVisible(false);
				gameOverPanel.removeAll();
				getContentPane().removeAll();
				setUp();
				layout.show(getContentPane(), "Intro");
				break;
			case "up":
			case "left":
			case "right":
			case "down":
				manager.shift(Direction.strToDir(cmd));
				manager.printCurrentBoard();
				updateScore();

				// Check to see if the game is over:
				if (manager.isGameWon() || manager.isGameLost()) {
					if (manager.isGameWon()) {
						gameOverLabel.setText("You won! Please enter your name.");
						gameOverPanel.setVisible(true);
					} else {
						gameOverLabel.setText("You lost! Please enter your name.");
						gameOverPanel.setVisible(true);
					}

					nameTextField.setVisible(true);
					submitButton.setVisible(true);
				}

				break;
			case "exit":
				System.exit(0);
				break;
			}
		}
	}

	private void writeToLeaderboard(String name) {
		manager.loadLeaderboard();
		manager.addScoreToLeaderboard(name);
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
