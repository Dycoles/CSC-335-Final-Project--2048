
package mvc;

import Components.Board;
import Components.Direction;
import java.util.ArrayList;


public class GameManager {
	
	private Board board;
	private Boolean isPlaying;
	private int curScore;
	//private int thisBoardSize;

	public GameManager(int boardSize) {
		this.board = new Board(boardSize);
		isPlaying = true;
		curScore = 0;
	}
	
	public boolean isGameWon() {
		return board.gameWon();
	}
	
	public boolean isGameLost() {
		return board.gameLost();
	}

	public void printCurrentBoard() {
		board.printBoard();
	}
	
	// shift tiles on the board
	public void shift(Direction dir) {
		if (dir == Direction.UP) {
			curScore += board.shiftUp();
		} else if (dir == Direction.RIGHT) {
			curScore += board.shiftRight();
		} else if (dir == Direction.LEFT) {
			curScore += board.shiftLeft();
		} else if (dir == Direction.DOWN) {
			curScore += board.shiftDown();
		}
	}
	
	public int getCurScore() {
		return curScore;
	}
}
