
/**
 * Board.java is the Model of the 2048 game that is used to implement the
 * game rules and mechanics. Will manage tile movement, merges, scores, and
 * game state.
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	// set vars
	public static int WIN_VAL = 2048;
	private int thisBoardSize;
	private Tile[][] board;
	private int myScore;
	public static Random rand = new Random();
	private ArrayList<Composite2048Observer> compObservers;
	
	/**
	 * Initialize board with custom size
	 * @param boardSize - custom board size
	 */
	public Board(int boardSize) {
		thisBoardSize = boardSize;
		myScore = 0;
		board = initializeBoard(thisBoardSize);
		compObservers = new ArrayList<>();
	}
	
	/**
	 * Getter for board size
	 * @return an int representing the board size
	 */
	public int getSize() {
		return thisBoardSize;
	}
	
	/**
	 * Determines if the game has been won
	 * @return true is game has been won, otherwise false
	 */
	public boolean gameWon() {
		for (int row = 0; row < thisBoardSize; row++) {
			for (int col = 0; col < thisBoardSize; col++) {
				if (board[row][col].getValue() == WIN_VAL) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determine if the game has been lost
	 * @return true if game has been lost, false otherwise
	 */
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
	
	/**
	 * Creates the board
	 * @param size - the size of the board
	 * @return a new board with corresponding amount of tiles
	 */
	private Tile[][] initializeBoard(int size) {
		Tile[][] newBoard = new Tile[size][size];
		
		// initialize all empty tiles
		for (int row = 0; row < size; row++) {
	        for (int col = 0; col < size; col++) {
	            newBoard[row][col] = new Tile();
	        }
	    }
		
		// Create the initial two tile values:
		int t1Row = rand.nextInt(size);
		int t1Col = rand.nextInt(size);
		newBoard[t1Row][t1Col].initialize();
		
		// Create the second tile value, ensuring a different one:
		int t2Row, t2Col;
		do {
			t2Row = rand.nextInt(size);
			t2Col = rand.nextInt(size);
		} while (t2Row == t1Row && t2Col == t1Col);
		newBoard[t2Row][t2Col].initialize();
		
		// debug print to see if board is created
		System.out.println("Initializing board");
		return newBoard;
	}
	
	/**
	 * Shift tiles on the board
	 * @param dir - desired direction shift of a tile
	 */
	public void shiftTile(Direction dir) {
		if (dir == Direction.UP) {
			shiftTileUp();
		} else if (dir == Direction.RIGHT) {
			shiftTileRight();
		} else if (dir == Direction.LEFT) {
			shiftTileLeft();
		} else if (dir == Direction.DOWN) {
			shiftTileDown();
		}
		
		updateObservers();
	}

	/**
	 *  Shifts tiles upward
	 */
	private void shiftTileUp() {
		System.out.println("Shifting up");
		boolean canShift = canMove(Direction.UP);
		// Iterate through each column:
		for (int col = 0; col < thisBoardSize; col++) {
			// Move everything up:
			int moveI = 0, emptyI = 0;
			while (moveI < thisBoardSize) {
				if (!(board[moveI][col].isEmpty())) {
					board[moveI][col].moveVal(board[emptyI][col]);
					emptyI++;
				}
				moveI++;
			}
			// Make any merges:
			for (int mergeI = 0; mergeI < thisBoardSize-1; mergeI++) {
				if (board[mergeI+1][col].canMerge(board[mergeI][col])) {
					board[mergeI+1][col].mergeVal(board[mergeI][col]);
					myScore+=board[mergeI][col].getValue();
					// Shift over all other values:
					for (int shiftI = mergeI+1; shiftI < thisBoardSize-1; shiftI++) {
						board[shiftI+1][col].moveVal(board[shiftI][col]);
					}
					board[thisBoardSize-1][col].removeValue();
				}
			}
		}
		if (canShift == true) {
			createNewTile();
		}
	}
	
	/**
	 * Shifts tiles to the right
	 */
	private void shiftTileRight() {
		System.out.println("Shifting right");
		boolean canShift = canMove(Direction.RIGHT);
		// Iterate through each row:
		for (int row = 0; row < thisBoardSize; row++) {
			// Move everything right:
			int moveI = thisBoardSize-1, emptyI = thisBoardSize-1;
			while (moveI >= 0) {
				if (!(board[row][moveI].isEmpty())) {
					board[row][moveI].moveVal(board[row][emptyI]);
					emptyI--;
				}
				moveI--;
			}
			
			// Make any merges:
			for (int mergeI = thisBoardSize-1; mergeI > 0; mergeI--) {
				if (board[row][mergeI-1].canMerge(board[row][mergeI])) {
					board[row][mergeI-1].mergeVal(board[row][mergeI]);
					myScore+=board[row][mergeI].getValue();
					// Shift over all other values:
					for (int shiftI = mergeI-1; shiftI > 0; shiftI--) {
						board[row][shiftI-1].moveVal(board[row][shiftI]);
					}
					board[row][0].removeValue();
				}
			}
		}
		if (canShift == true) {
			createNewTile();
		}
	}

	/**
	 * Shifts tiles to the left
	 */
	private void shiftTileLeft() {
		System.out.println("Shifting left");
		boolean canShift = canMove(Direction.LEFT);
		// Iterate through each row:
		for (int row = 0; row < thisBoardSize; row++) {
			// Move everything left:
			int moveI = 0, emptyI = 0;
			while (moveI < thisBoardSize) {
				if (!(board[row][moveI].isEmpty())) {
					board[row][moveI].moveVal(board[row][emptyI]);
					emptyI++;
				}
				moveI++;
			}
			
			// Make any merges:
			for (int mergeI = 0; mergeI < thisBoardSize-1; mergeI++) {
				if (board[row][mergeI+1].canMerge(board[row][mergeI])) {
					board[row][mergeI+1].mergeVal(board[row][mergeI]);
					myScore+=board[row][mergeI].getValue();
					// Shift over all other values:
					for (int shiftI = mergeI+1; shiftI < thisBoardSize-1; shiftI++) {
						board[row][shiftI+1].moveVal(board[row][shiftI]);
					}
					board[row][thisBoardSize-1].removeValue();
				}
			}
		}
		if (canShift == true) {
			createNewTile();
		}
	}
	
	/**
	 * Shifts tiles down
	 */
	private void shiftTileDown() {
		System.out.println("Shifting down");
		boolean canShift = canMove(Direction.DOWN);
		// Iterate through each column:
		for (int col = 0; col < thisBoardSize; col++) {
			// Move everything up:
			int moveI = thisBoardSize-1, emptyI = thisBoardSize-1;
			while (moveI >= 0) {
				if (!(board[moveI][col].isEmpty())) {
					board[moveI][col].moveVal(board[emptyI][col]);
					emptyI--;
				}
				moveI--;
			}
			
			// Make any merges:
			for (int mergeI = thisBoardSize-1; mergeI > 0; mergeI--) {
				if (board[mergeI-1][col].canMerge(board[mergeI][col])) {
					board[mergeI-1][col].mergeVal(board[mergeI][col]);
					myScore+=board[mergeI][col].getValue();
					// Shift over all other values:
					for (int shiftI = mergeI-1; shiftI > 0; shiftI--) {
						board[shiftI-1][col].moveVal(board[shiftI][col]);
					}
					board[0][col].removeValue();
				}
			}
		}
		if (canShift == true) {
			createNewTile();
		}
	}
	
	/**
	 * Checks if tiles can move in given direction
	 * @param dir - desired direction shift of the tiles
	 * @return true if the tiles can move/merge, false if they cannot
	 */
	private Boolean canMove(Direction dir) {
		for (int row=0; row<thisBoardSize;row++) {
			for (int col=0;col<thisBoardSize;col++) {
				if (board[row][col].isEmpty()==false) {
					if (dir == Direction.UP){
						if (row!=0) {
							if (board[row][col].canMerge(board[row-1][col]) || board[row][col].canMove(board[row-1][col])) {
								return true;
							}
						}
					} else if (dir == Direction.RIGHT) {
						if (col!=thisBoardSize-1) {
							if (board[row][col].canMerge(board[row][col+1]) || board[row][col].canMove(board[row][col+1])) {
								return true;
							}
						}
					} else if (dir == Direction.DOWN) {
						if (row!=thisBoardSize-1) {
							if (board[row][col].canMerge(board[row+1][col]) || board[row][col].canMove(board[row+1][col])) {
								return true;
							}
						}
					} else if (dir == Direction.LEFT) {
						if (col!=0) {
							if (board[row][col].canMerge(board[row][col-1]) || board[row][col].canMove(board[row][col-1])) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Get a random empty tile
	 * @return a random empty tile
	 */
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
	
	/**
	 * Creates a new tile on the board
	 */
	private void createNewTile() {
		System.out.println("Generating tile");
		// get a random empty tile
		Tile newTile = randEmpty();
		
		// NOTE THAT THIS IF STATEMENT IS SUBJECT TO CHANGE ONCE MERGE AND GAME LOST/WON ARE CORRECTLY IMPLEMENTED. IF ERRORS OCCUR CHECK HERE
		if (newTile == null) {
			gameLost();
			return;
		}
		// initialize the tile
		newTile.initialize();
	}
	
	/**
	 * Gets a list of a tile's neighbors
	 * @param row - the row of the tile
	 * @param col - the column of a tile
	 * @return an ArrayList of the tiles neighbors
	 */
	private ArrayList<Tile> neighbors(int row, int col) {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		if (row != 0) {
			neighbors.add(board[row-1][col]); 
		}
		if (col != thisBoardSize-1) {
			neighbors.add(board[row][col+1]); 
		}
		if (row != thisBoardSize-1) {
			neighbors.add(board[row+1][col]); 
		}
		if (col != 0) {
			neighbors.add(board[row][col-1]); 
		}
		return neighbors;
	}
	
	/**
	 * Print the board and it's values for testing purposes
	 */
	public void printBoard() {
		for (int row = 0; row < thisBoardSize; row++) {
	       for (int col = 0; col < thisBoardSize; col++) {
	        	System.out.print("[" + board[row][col].getValue() + "]");
	      	}
	        System.out.println();
		}
	}
		
	/**
	 * Getter for score
	 * @return the player's score
	 */
	public int getScore() {
		return myScore;
	}
	
	/**
	 * Checks an empty board
	 */
	public void emptyBoard() {
		for (int row = 0; row < thisBoardSize; row++) {
			for (int col = 0; col < thisBoardSize; col++) {
				board[row][col] = new Tile();
			}
		}
	}
	
	/**
	 * Get a copy of the board
	 * @return the board copy
	 */
	public Tile[][] getBoardCopy() {
		Tile[][] boardCopy = new Tile[thisBoardSize][thisBoardSize];
		for (int row = 0; row < thisBoardSize; row++) {
			for (int col = 0; col < thisBoardSize; col++) {
				boardCopy[row][col] = board[row][col];
			}
		}
		return boardCopy;
	}
	
	/**
	 * Helps test by inserting tiles
	 * @param x - horizontal position of the tile
	 * @param y - vertical position of the tile
	 * @param val - the value of the tile
	 */
	public void insertTestTile(int x, int y, int val) {
		// create new tile with specific value
		Tile newTile = new Tile(val);
		board[x][y] = newTile;
	}
	
	/**
	 * Adds a composite observer to the model
	 * @param o - observer
	 */
	public void addCompositeObserver(Composite2048Observer o) {
		compObservers.add(o);
		updateObservers();
	}
	
	/**
	 * Updates all current composite observers
	 */
	private void updateObservers() {
		for (Composite2048Observer observer : compObservers) {
			for (int row = 0; row  < thisBoardSize; row++) {
				for (int col = 0; col < thisBoardSize; col++) {
					observer.updateObserver(row, col, board[row][col].getValue());
				}
			}
		}
	}
}
