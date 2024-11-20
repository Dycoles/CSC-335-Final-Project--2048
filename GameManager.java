
package mvc;

import Components.Board;
import Components.Direction;
import java.util.ArrayList;



public class GameManager {
	
	private Board board;
	private Boolean isPlaying;

	public GameManager(int boardSize) {
		this.board = new Board(boardSize);
		isPlaying = true;
	}
	
	// returns true if score is 2048
	public boolean isGameWon() {
		if (board.gameWon()) {
			isPlaying = false;
		}
		return board.gameWon();
	}
	
	// returns true if no more moves
	public boolean isGameLost() {
		if (board.gameLost()) {
			isPlaying = false;
		}
		return board.gameLost();
	}
	
	// checks if the game is still playing
	public boolean isPlaying() {
		return isPlaying;
	}

	// print current board
	public void printCurrentBoard() {
		board.printBoard();
	}
	
	// shift tiles on the board
	public void shift(Direction dir) {
		// check for valid game
		if (!isPlaying) {
			return;
		}
		// shift tile
		board.shiftTile(dir);
		// checks if game is over, sets isPlaying
		if (isGameWon() || isGameLost()) {
			isPlaying = false;
			return;
		}
	}
	
	// gets current score
	public int getCurScore() {
		return board.getScore();
	}
}
