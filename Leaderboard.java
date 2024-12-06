
/**
 * Leaderboard.java is a class to help load and save a leaderboard with
 * names and scores of players for our 2048 game.
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Leaderboard {

	private ArrayList<ScoreEntry> scoreList;

	/**
	 * Initialize Leaderboard ArrayList
	 */
	public Leaderboard() {
		scoreList = new ArrayList<ScoreEntry>();
	}

	/**
	 * Adds score to loeaderboard
	 * 
	 * @param name  - name of player
	 * @param score - player's score
	 * @param size  - player's board size
	 */
	public void addScore(String name, int score, int size) {
		ScoreEntry entry = new ScoreEntry(name, score, size);
		scoreList.add(entry);
		// lambda expression sorts scores in descending order
		scoreList.sort((score1, score2) -> score2.getScore() - score1.getScore());
		// call add function
		addToFile();
	}

	/**
	 * Getter for the score list
	 * 
	 * @return an ArrayList of scores
	 */
	public ArrayList<ScoreEntry> getScoreList() {
		// return a new Arraylist to avoid making a copy
		ArrayList<ScoreEntry> copyScores = new ArrayList<ScoreEntry>();
		for (int i = 0; i < scoreList.size(); i++) {
			copyScores.add(scoreList.get(i));
		}
		return copyScores;
	}

	/**
	 * Removes a leaderboard score from the ArrayList
	 * @param name: String, the name of the player
	 * @param score: int, the score of the player
	 */
	public void removeScore(String name, int score) {
		for (int i=0;i<scoreList.size();i++) {
			ScoreEntry entry = scoreList.get(i);
			if (entry.getName() == name) {
				if (entry.getScore() == score) {
					scoreList.remove(i);
				}
			}
		}
	}
	
	/**
	 * Load the scores from the text file
	 */
	public void loadScores() {
		// call load function
		loadFromFile();
	}

	/**
	 * Add the scores to the text file
	 */
	private void addToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Leaderboard.txt"))) {
			for (ScoreEntry entry : scoreList) {
				writer.write(entry.getName() + "," + entry.getScore() + "," + entry.getSize());
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("Could not save leaderboard " + e.getMessage());
		}
	}

	/**
	 * Load the entire leaderboard from file
	 */
	private void loadFromFile() {
		scoreList.clear();
		File file = new File("Leaderboard.txt");
		if (!file.exists()) {
			System.out.println("File does not exist");
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 3) {
					String name = parts[0];
					int score = Integer.parseInt(parts[1]);
					int size = Integer.parseInt(parts[2]);
					scoreList.add(new ScoreEntry(name, score, size));
				}
			}
			// ensure scores are sorted
			scoreList.sort((score1, score2) -> score2.getScore() - score1.getScore());
		} catch (IOException | NumberFormatException e) {
			System.err.println("Could not load leaderboard " + e.getMessage());
		}
	}

	/**
	 * Provide a String array of the leaderboard for testing
	 * 
	 * @return a String loaderboard list
	 */
	public String[] arrayLeaderboard() {
		String[] answer = new String[scoreList.size()];
		for (int i = 0; i < scoreList.size(); i++) {
			answer[i] = stringLeaderboard(scoreList.get(i));
		}
		return answer;
	}

	/**
	 * Get string from leaderboard for testing
	 * 
	 * @param score - player's score
	 * @return a string of a player name, score, and board size
	 */
	private String stringLeaderboard(ScoreEntry score) {
		String answer = "";
		answer += score.getName() + " ";
		answer += score.getScore() + " ";
		answer += score.getSize();
		return answer;
	}
}
