package Components;

import java.util.ArrayList;

public class Board {
	// set global vars
	private int thisBoardSize;
	private Tile tile = new Tile();
	private ArrayList<Tile> board;
	// initialize board with custom size
	public Board(int boardSize) {
		thisBoardSize = boardSize;
		createBoard(thisBoardSize);
	}
	
	// create the board
	private void createBoard(int size) {
		
	}
	
	// shift tiles on the board
	private void shiftTile() {
		
	}
	
	// get the board (which is an arrayList of tiles)
	public ArrayList<Tile> getBoard() {
		return board;
	}
}
