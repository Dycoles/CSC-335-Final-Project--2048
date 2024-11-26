import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Scanner;

public class BoardGUI extends JFrame {
	
	private GameManager manager;
	private JPanel titlePanel;
	private JPanel gamePanel;
	
	public static void main(String[] args) {
        BoardGUI gui = new BoardGUI();
        gui.setVisible(true);
	}
	
	public BoardGUI() {		
		manager = new GameManager(4);
		setUp();
		
	}
	
	public void setUp() {
		setTitle("2048");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new CardLayout());
		
		// create new title panel
		titlePanel = new JPanel();
		// set up the title label
		JLabel titleLabel = new JLabel("2048", JLabel.CENTER);
		titleLabel.setSize(1000, 100);
		titlePanel.add(titleLabel, BorderLayout.NORTH);
		
		// create start button
		JButton startButton = new JButton("Start Game");
		startButton.setActionCommand("start game");
		titlePanel.setLayout(new BorderLayout());
		
		// create game panel with the board
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(1, 2));
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(titlePanel);
		this.add(gamePanel);
	}
	
	public void startGame() {
		// Create button dimensions:
		Dimension buttonSize = new Dimension(180, 40);
		int gap = 40;

	}
	
	// Carry out command for button press
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand().toLowerCase();
		
		switch (cmd) {
		case "start":
			playGame();
		}
		
	}
	
	// start game
	private void playGame() {
		System.out.println("Use 'w a s d' to shift the board");
		Scanner scanner = new Scanner(System.in);
		char inputChar;
		while (manager.isGameLost() == false && manager.isGameWon() == false) {
			// get input to swipe
			String input = scanner.nextLine().trim();
			boolean cont = false;
			if (input.length() == 1) {
				inputChar = input.charAt(0);
			} else {
				System.out.println("Invalid movement input");
				continue;
			}
			// temporary test input; may decide on better system later
			Direction dir = Direction.UP;
			switch (inputChar) {
			case 'w':
				dir = Direction.UP;
				break;
			case 'a':
				dir = Direction.LEFT;
				break;
			case 's':
				dir = Direction.DOWN;
				break;
			case 'd':
				dir = Direction.RIGHT;
				break;
			default:
				cont = true;
				System.out.println("Please enter a valid direction");
				break;
			}
			if (cont == true) {
				continue;
			}
			// shift the board in the inputed direction
			System.out.println("Moving " + dir.toString() + "\n");
			board.shiftTile(dir);
			board.printBoard();
		}
		
		if (board.gameWon() == true) {
			System.out.println("You win!");
		} else {
			System.out.println("You lose!");
		}
		scanner.close();
	}
