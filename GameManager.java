package mvc;

import java.util.Scanner;

import Components.Board;
import Components.Direction;

public class GameManager {
	public void play() {
		// we can use scanner to create board size later; will use 4 as a temporary value
		Board thisBoard = new Board(4);
		playGame(thisBoard);
	}

	private void playGame(Board board) {
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
