package mvc;

import Components.Board;
import Components.Direction;
import Components.Leaderboard;
import java.util.ArrayList;


public class GameManager {
	
	private Board board;
	private Leaderboard leaderboard;
	private Boolean isPlaying;
	//private int currScore;
	//private int thisBoardSize;

	public GameManager(int boardSize) {
		this.board = new Board(boardSize);
		this.leaderboard = new Leaderboard();
		isPlaying = true;
	}
	
	// shift tiles on the board
	public void shift(Direction dir) {
		if (dir == Direction.UP) {
			board.shiftTileUp();
		} else if (dir == Direction.RIGHT) {
			board.shiftTileRight();
		} else if (dir == Direction.LEFT) {
			board.shiftTileLeft();
		} else if (dir == Direction.DOWN) {
			board.shiftTileDown();
		}
	}
}
