
/**
 * ScoreEntry.java is a class to help get the names, used board sizes, and
 * scores of the leaderboard in the 2048 program.
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
	 * Initialize the ScoreEntry with name, score, and size.
	 * 
	 * @param name  - name of the player.
	 * @param score - score of the player.
	 * @param size  - the custom board size.
	 */
	public ScoreEntry(String name, int score, int size) {
		this.name = name;
		this.score = score;
		this.size = size;
	}

	/**
	 * Return the name of the player.
	 * 
	 * @return name - name of player.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the score of the player.
	 * 
	 * @return score - the score of the player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Return the size of the board.
	 * 
	 * @return size - the size of the board.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns a String representation of this score entry.
	 * 
	 * @return playerName + " " + score + " " + boardSize.
	 */
	public String toString() {
		return name + " " + score + " " + size;
	}

}
