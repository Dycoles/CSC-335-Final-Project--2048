/**
 * GameManager.java is the Controller for our game used to implement the
 * View (BoardGUI.java) for our 2048 game. 
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */
import java.util.ArrayList;

import Components.Board;
import Components.Composite2048Observer;
import Components.Direction;
import Components.Leaderboard;
import Components.ScoreEntry;

public class GameManager {
	
	private Board board;
	private Boolean isPlaying;
	private Leaderboard leaderboard;

	public GameManager(int boardSize) {
		this.board = new Board(boardSize);
		this.leaderboard = new Leaderboard();
		isPlaying = true;
	}
	
	// returns true if score is 2048
	public boolean isGameWon() {
		if (board.gameWon()) {
			isPlaying = false;
		}
		return board.gameWon();
	}
	
	// returns true if no more moves
	public boolean isGameLost() {
		if (board.gameLost()) {
			isPlaying = false;
		}
		return board.gameLost();
	}
	
	// checks if the game is still playing
	public boolean isPlaying() {
		return isPlaying;
	}

	// print current board
	public void printCurrentBoard() {
		board.printBoard();
	}
	
	// shift tiles on the board
	public void shift(Direction dir) {
		// check for valid game
		if (!isPlaying) {
			return;
		}
		// shift tile
		board.shiftTile(dir);
		// checks if game is over, sets isPlaying
		if (isGameWon() || isGameLost()) {
			isPlaying = false;
			return;
		}
	}
	
	// gets current score
	public int getCurScore() {
		return board.getScore();
	}
	
	// Add a composite observer to the model:
	public void addCompositeObserver(Composite2048Observer o) {
		board.addCompositeObserver(o);
	}
	
	public void loadLeaderboard() {
		leaderboard.loadScores();
	}
	
	public ArrayList<ScoreEntry> getLeaderboard() {
		return leaderboard.getScoreList();
	}
	
	public void addScoreToLeaderboard(String name) {
		leaderboard.addScore(name, getCurScore(), board.getSize());
	}
}
