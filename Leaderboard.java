package Components;

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
	
	public ArrayList<Integer> getScores() {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores = highScores;
		return scores;
	}
}
