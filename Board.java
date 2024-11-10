package Components;

import java.util.ArrayList;

public class Board {
	// set global vars
	private int thisBoardSize;
	private Tiles tile;
	private ArrayList<Tiles> board;
	private int myScore;
	// initialize board with custom size
	public Board(int boardSize) {
		thisBoardSize = boardSize;
		myScore = 0;
		createBoard(thisBoardSize);
	}
	
	// create the board
	private void createBoard(int size) {
		
	}
	
	// shift tiles on the board
	private void shiftTile() {
		
	}
	
	// get the board (which is an arrayList of tiles)
	public ArrayList<Tiles> getBoard() {
		return board;
	}
	
	// set score value
	public void incrementScore(int score) {
		myScore = score;
	}
	
	// get score value
	public int getScore() {
		return myScore;
	}
}
