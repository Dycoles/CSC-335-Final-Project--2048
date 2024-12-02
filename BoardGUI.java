
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	private JButton startButton;
	private JButton upButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton downButton;
	private CardLayout layout;
	private BoxLayout gameLayout;

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

		// set up the title label
		titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setFont(new Font("Title", Font.PLAIN, 24));
		titleLabel.setBounds(450, 200, 100, 50);
		introPanel.add(titleLabel);

		// create start button
		startButton = new JButton("Start Game");
		startButton.setSize(800, 50);
		startButton.setActionCommand("start");
		startButton.addActionListener(new ButtonListener());
		introPanel.add(startButton, BorderLayout.SOUTH);

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
		
		// Create the leaderboard (maybe) (TODO):
		
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
			case "up":
			case "left":
			case "right":
			case "down":
				manager.shift(Direction.strToDir(cmd));
				manager.printCurrentBoard();
				break;
			}
		}
	}
	
	public void updateObserver(int row, int col, int value) {
		JPanel thisPanel = itemPanels[row][col];
		JLabel thisLabel = ((JLabel)thisPanel.getComponent(0));
		
		if (value == 0) {
			thisLabel.setText("");
		} else {
			thisLabel.setText(String.valueOf(value));
		}
		
		// Colors taken from https://github.com/daniel-huang-1230/Game-2048/blob/master/Gui2048.java
		// TODO idk if this is plagiarism
		Color[] colorsToUse = new Color[] {new Color(204, 192, 179), new Color(238, 228, 218),
				new Color(237, 224, 200), new Color(242, 177, 121), new Color(245, 149, 99),
				new Color(246, 124, 95), new Color(246, 94, 59), new Color(237, 207, 114),
				new Color(237, 204, 97), new Color(237, 200, 80), new Color(237, 197, 63),
				new Color(237, 194, 46)};
		int colorI = (value > 0 ? (int)(Math.log(value)/Math.log(2)) : 0);
		Color colorToUse = colorsToUse[Math.min(
				colorsToUse.length-1, colorI)];
		itemPanels[row][col].setBackground(colorToUse);
	}

	public static void main(String[] args) {
		BoardGUI gui = new BoardGUI();
		gui.setVisible(true);
	}
}
