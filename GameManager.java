
/**
 * GameManager.java is the Controller class used to implement the operations
 * from the Model class (Board.java) for the View class (BoardGUI.java).
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */
import java.util.ArrayList;

public class GameManager {
	
	private Board board;
	private Boolean isPlaying;
	private Leaderboard leaderboard;

	/**
	 * Creates new game with a given board size.
	 * @param boardSize - the size of the board
	 */
	public GameManager(int boardSize) {
		this.board = new Board(boardSize);
		this.leaderboard = new Leaderboard();
		isPlaying = true;
	}
	
	/**
	 * Checks if the game has been won
	 * @return returns true if a tile is 2048, otherwise false
	 */
	public boolean isGameWon() {
		if (board.gameWon()) {
			isPlaying = false;
		}
		return board.gameWon();
	}
	
	/**
	 * Checks if game has been lost
	 * @return returns true if no more moves, otherwise false
	 */
	public boolean isGameLost() {
		if (board.gameLost()) {
			isPlaying = false;
		}
		return board.gameLost();
	}
	
	/**
	 * Checks if the game is still active
	 * @return true if the game is still active, otherwise false
	 */
	public boolean isPlaying() {
		return isPlaying;
	}

	/**
	 * Print current game board
	 */
	public void printCurrentBoard() {
		board.printBoard();
	}
	
	/**
	 * Shift tiles on the board
	 * @param dir - the direction the tile will shift
	 */
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
	
	/**
	 * Gets current score
	 * @return current score if the game
	 */
	public int getCurScore() {
		return board.getScore();
	}
	
	/**
	 * Add a composite observer to the model:
	 * @param o - observer
	 */
	public void addCompositeObserver(Composite2048Observer o) {
		board.addCompositeObserver(o);
	}
	
	/**
	 * Loads the leaderboard
	 */
	public void loadLeaderboard() {
		leaderboard.loadScores();
	}
	
	/**
	 * Getter for leaderboard list
	 * @return an ArrayList of scores.
	 */
	public ArrayList<ScoreEntry> getLeaderboard() {
		return leaderboard.getScoreList();
	}
	
	/**
	 * Adds name to the leaderboard with corresponding score
	 * @param name - the name of the player
	 */
	public void addScoreToLeaderboard(String name) {
		leaderboard.addScore(name, getCurScore(), board.getSize());
	}
}
