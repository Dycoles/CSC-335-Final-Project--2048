package Components;

import java.util.Random;

public class Board {
	// set global vars
	private int thisBoardSize;
	private Tile[][] board;
	private int myScore;
	
	// initialize board with custom size
	public Board(int boardSize) {
		thisBoardSize = boardSize;
		myScore = 0;
		board = initializeBoard(thisBoardSize);
	}
	
	// create the board
	private Tile[][] initializeBoard(int size) {
		Random rand = new Random();
		
		Tile[][] newBoard = new Tile[size][size];
		
		// Create the initial two tile values:
		int t1Row = rand.nextInt(size);
		int t1Col = rand.nextInt(size);
		newBoard[t1Row][t1Col].initialize();
		
		// Create the second tile value, ensuring a different one:
		int t2Row, t2Col;
		do {
			t2Row = rand.nextInt(size);
			t2Col = rand.nextInt(size);
		} while (t2Row != t1Row && t2Col != t1Col);
		newBoard[t2Row][t2Col].initialize();
		
		return newBoard;
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
