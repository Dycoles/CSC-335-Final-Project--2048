/**
 * ScoreEntry.java is a class to help get the names and scores of 
 * the leaderboard in our 2048. 
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

public class ScoreEntry {
	private String name;
	private int score;
	private int size;
	
	public ScoreEntry(String name, int score, int size) {
		this.name = name;
		this.score = score;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getSize() {
		return size;
	}

	public String toString() {
		return name + " " + score + " " + size;
	}
	
}
