/**
 * View.java is the Text Based User Interface that is used to implement the
 * classes for our 2048. 
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

import java.util.Scanner;

import Components.Board;
import Components.Direction;

public class View {
	
	public static void main(String[] args) {
		playGame();
	}

	private static void playGame() {
		Board board = new Board(4);
		board.printBoard();
		System.out.println("Use 'w a s d' to shift the board");
		Scanner scanner = new Scanner(System.in);
		char inputChar;
		
		while (board.gameLost() == false && board.gameWon() == false) {
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
}
