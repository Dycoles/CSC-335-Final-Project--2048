/**
 * ScoreEntry.java is a class to help get the names and scores of 
 * the leaderboard in our 2048. 
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

public final class ScoreEntry {
	private final String name;
	private final int score;
	private final int size;

	/**
	* Initialize ScoreEntry with name, score, and size
	* @param name - name of player
  	* @param score - score of player
   	* @param size - custom board size
	*/
	public ScoreEntry(String name, int score, int size) {
		this.name = name;
		this.score = score;
		this.size = size;
	}

	/**
	* return this.name, the name of the player
	* @return name - name of player
	*/
	public String getName() {
		return name;
	}

	/**
	* return this.score, the score of the player
	* @return score - the score of the player
	*/
	public int getScore() {
		return score;
	}

	/**
	* return this.size, the size of the board
	* @return size - the size of the board
	*/
	public int getSize() {
		return size;
	}

	/**
	* returns string representation of object. (for testing)
	* @return name + " " + score + " " + size, name of player + score of player + size of board
	*/
	public String toString() {
		return name + " " + score + " " + size;
	}
	
}
