package Components;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	// set vars
	private int thisBoardSize;
	private Tile[][] board;
	private int myScore;
	public static Random rand = new Random();
	
	// initialize board with custom size
	public Board(int boardSize) {
		thisBoardSize = boardSize;
		myScore = 0;
		board = initializeBoard(thisBoardSize);
	}
	
	// create the board
	private Tile[][] initializeBoard(int size) {
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

	// get a random empty tile
	private Tile randEmpty() {
		ArrayList<Tile> empties = new ArrayList<>();
		
		// Get a list of empty tiles:
		for (int row = 0; row < thisBoardSize; row++) {
			for (int col = 0; col < thisBoardSize; col++) {
				if (board[row][col].isEmpty()) {
					empties.add(board[row][col]);
				}
			}
		}
		
		// If empty, return null:
		if (empties.isEmpty()) {
			return null;
		}
		
		// Pick a random tile from the list of empties:
		return empties.get(rand.nextInt(empties.size()));
	}
	
	// increment score value
	private void incrementScore(int incBy) {
		myScore += incBy;
	}
	
	// get score value
	public int getScore() {
		return myScore;
	}
}
