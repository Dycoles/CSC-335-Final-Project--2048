package mvc;

public class GameManager {
	
	private Board board;

	public GameManager(int boardSize) {
		board = new Board(boardSize);
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
