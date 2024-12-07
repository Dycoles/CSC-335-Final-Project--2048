
/**
 * View.java is the text-based user interface for the 2048 program.
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

import java.util.Scanner;

public class View {

	/**
	* Main method, calls playGame.
	*/
	public static void main(String[] args) {
		playGame();
	}

	/**
	* Initializes the board and starts the game loop, which continuously
  	* allows the player to make moves until they have won or lost.
	*/
	private static void playGame() {
		Board board = new Board(4);
		
		board.printBoard();
		System.out.println("Use 'w a s d' to shift the board");
		
		Scanner scanner = new Scanner(System.in);
		char inputChar;
		
		while (board.gameLost() == false && board.gameWon() == false) {
			// Get the shift input, verifying:
			String input = scanner.nextLine().trim();
			if (input.length() == 1) {
				inputChar = input.charAt(0);
			} else {
				System.out.println("Invalid movement input.");
				continue;
			}
			
			// Read the direction:
			Direction dir;
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
				System.out.println("Please enter a valid direction.");
				continue;
			}

			// Shift the board in the input direction:
			System.out.println("Moving " + dir.toString().toLowerCase() + "\n");
			board.shiftTile(dir);
			board.printBoard();
		}
		
		// Game is over, so see if it was a win or loss:
		if (board.gameWon() == true) {
			System.out.println("You win!");
		} else {
			System.out.println("You lose!");
		}
		
		scanner.close();
	}
}
