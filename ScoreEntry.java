package Components;

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
}
