package Components;

public class Board {
	// set global vars
	private int thisBoardSize;
	private Tile[][] board;
	private int myScore;
	
	// initialize board with custom size
	public Board(int boardSize) {
		thisBoardSize = boardSize;
		myScore = 0;
		board = createBoard(thisBoardSize);
	}
	
	// create the board
	private Tile[][] createBoard(int size) {
		return null;
	}
	
	// shift tiles on the board
	private void shiftTile() {
		
	}
	
	// get the board (which is an arrayList of tiles)
	/*public ArrayList<Tiles> getBoard() {
		return board;
	}*/
	
	// set score value
	public void incrementScore(int incBy) {
		myScore += incBy;
	}
	
	// get score value
	public int getScore() {
		return myScore;
	}
}
