package Components;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	// set vars
	public static int WIN_VAL = 2048;
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
	
	// determine if the game has been won
	public boolean gameWon() {
		for (int row = 0; row < thisBoardSize; row++)
			for (int col = 0; col < thisBoardSize; col++)
				if (board[row][col].getValue() == WIN_VAL)
					return true;
		return false;
	}
	
	// determine if the game has been lost
	public boolean gameLost() {
		// Check for any empty tiles:
		if (randEmpty() != null) {
			return false;
		}
		
		// No empty tiles, so check for possible merges:
		for (int row = 0; row < thisBoardSize; row++) {
			for (int col = 0; col < thisBoardSize; col++) {
				Tile thisTile = board[row][col];
				
				// Check if this tile can merge:
				ArrayList<Tile> tNeighbors = neighbors(row, col);
				for (Tile neighbor : tNeighbors) {
					if (thisTile.canMerge(neighbor)) {
						return false;
					}
				}
			}
		}
		
		// Nothing can move or merge, so game is lost:
		return true;
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
	private void shiftTile(Direction dir) {
		if (dir == Direction.UP) {
			shiftTileUp();
		} else if (dir == Direction.RIGHT) {
			
		} else if (dir == Direction.LEFT) {
			
		} else if (dir == Direction.DOWN) {
			
		}
	}
	
	// shift tiles upward
	private void shiftTileUp() {
		for (int col=0;col<thisBoardSize;col++) {
			boolean sorted = false; // if there is no more shifts within the column that can occur
			while (!sorted) {
				
			}
		}
		
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
	
	// get a list of a tile's neighbors
	private ArrayList<Tile> neighbors(int row, int col) {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		if (row != 0) {
			neighbors.add(board[row-1][col]); // potential escaping reference, maybe we opt for copy
		}
		if (col != thisBoardSize-1) {
			neighbors.add(board[row][col+1]); // potential escaping reference, maybe we opt for copy
		}
		if (row != thisBoardSize-1) {
			neighbors.add(board[row+1][col]); // potential escaping reference, maybe we opt for copy
		}
		if (col != 0) {
			neighbors.add(board[row][col-1]); // potential escaping reference, maybe we opt for copy
		}
		return neighbors;
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
