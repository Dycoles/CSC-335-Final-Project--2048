
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
	private Leaderboard leaderboard;

	/**
	 * Creates new game with a given board size.
	 * 
	 * @param boardSize - the size of the board.
	 */
	public GameManager(int boardSize) {
		this.board = new Board(boardSize);
		this.leaderboard = new Leaderboard();
	}

	/**
	 * Checks if the game has been won.
	 * 
	 * @return returns true if a tile is 2048, otherwise false.
	 */
	public boolean isGameWon() {
		return board.gameWon();
	}

	/**
	 * Checks if game has been lost.
	 * 
	 * @return returns true if there are no more moves, otherwise false.
	 */
	public boolean isGameLost() {
		return board.gameLost();
	}

	/**
	 * Checks if the game is still active.
	 * 
	 * @return true if the game is still active, otherwise false.
	 */
	public boolean isPlaying() {
		return !board.gameWon() && !board.gameLost();
	}

	/**
	 * Print the current game board to the console.
	 */
	public void printCurrentBoard() {
		board.printBoard();
	}

	/**
	 * Shift all tiles on the board.
	 * 
	 * @param dir - the direction the tile will shift in.
	 */
	public void shift(Direction dir) {
		// Check to see if this game is still ongoing, shifting if so:
		if (this.isPlaying()) {
			board.shiftTile(dir);
		}
	}

	/**
	 * Get the current score.
	 * 
	 * @return the current score of the game.
	 */
	public int getCurScore() {
		return board.getScore();
	}

	/**
	 * Add a composite observer to the model.
	 * 
	 * @param o - the observer to add.
	 */
	public void addCompositeObserver(Composite2048Observer o) {
		board.addCompositeObserver(o);
	}

	/**
	 * Loads the leaderboard from the Leaderboard.txt file.
	 */
	public void loadLeaderboard() {
		leaderboard.loadScores();
	}

	/**
	 * Getter for leaderboard list.
	 * 
	 * @return an ArrayList of scores.
	 */
	public ArrayList<ScoreEntry> getLeaderboard() {
		return leaderboard.getScoreList();
	}

	/**
	 * Adds a name to the leaderboard with their corresponding score.
	 * 
	 * @param name - the name of the player.
	 */
	public void addScoreToLeaderboard(String name) {
		leaderboard.addScore(name, getCurScore(), board.getSize());
	}
}
