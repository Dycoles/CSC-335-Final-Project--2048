import java.util.ArrayList;

public class Leaderboard {
	
	private String name;
	private ArrayList<Integer> highScores;
	
	public Leaderboard() {
		name = "";
		highScores = new ArrayList<Integer>();
	}
	
	public String getName() {
		return name;
	}
	
	private void addToFile() {
		// TODO
	}
	
	private void loadFromFile() {
		// TODO
	}
	
	public ArrayList<Integer> getScores() {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores = highScores;
		return scores;
	}
}
