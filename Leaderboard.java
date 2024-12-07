
/**
 * Leaderboard.java is a class to help load and save a leaderboard with
 * names, scores, and board sizes of players for our 2048 game.
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
	 * Initialize the Leaderboard.
	 */
	public Leaderboard() {
		scoreList = new ArrayList<ScoreEntry>();
	}

	/**
	 * Add a score to the leaderboard.
	 * 
	 * @param name  - name of the player.
	 * @param score - the player's score.
	 * @param size  - the player's board size.
	 */
	public void addScore(String name, int score, int size) {
		ScoreEntry entry = new ScoreEntry(name, score, size);
		scoreList.add(entry);

		// Sort the scores in descending order with a lambda expression:
		scoreList.sort((score1, score2) -> score2.getScore() - score1.getScore());

		// Add the updated score to the file:
		addToFile();
	}

	/**
	 * Getter for the score list.
	 * 
	 * @return an ArrayList of score entries.
	 */
	public ArrayList<ScoreEntry> getScoreList() {
		// Create a new Arraylist to avoid an escaping reference:
		ArrayList<ScoreEntry> copyScores = new ArrayList<ScoreEntry>();
		for (int i = 0; i < scoreList.size(); i++) {
			copyScores.add(scoreList.get(i));
		}
		return copyScores;
	}

	/**
	 * Removes a leaderboard score from the ArrayList.
	 * 
	 * @param name  - the name of the player, as a String.
	 * @param score - the integer score of the player.
	 */
	public void removeScore(String name, int score) {
		// Remove any matching scores:
		for (int i = 0; i < scoreList.size(); i++) {
			ScoreEntry entry = scoreList.get(i);
			if (entry.getName() == name) {
				if (entry.getScore() == score) {
					scoreList.remove(i);
				}
			}
		}
	}

	/**
	 * Load the scores from the text file.
	 */
	public void loadScores() {
		scoreList.clear();

		// Get the file to load from (if it exists):
		File file = new File("Leaderboard.txt");
		if (!file.exists()) {
			System.out.println("File does not exist");
			return;
		}

		// Read each score from the file:
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 3) {
					// Add the valid score:
					String name = parts[0];
					int score = Integer.parseInt(parts[1]);
					int size = Integer.parseInt(parts[2]);
					scoreList.add(new ScoreEntry(name, score, size));
				}
			}

			// Ensure the scores are sorted:
			scoreList.sort((score1, score2) -> score2.getScore() - score1.getScore());
		} catch (IOException | NumberFormatException e) {
			System.err.println("Could not load leaderboard " + e.getMessage());
		}
	}

	/**
	 * Add the current scores to the text file.
	 */
	private void addToFile() {
		// Add each score to the text file:
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
	 * Provide a String array of the leaderboard for testing.
	 * 
	 * @return a String leaderboard list.
	 */
	public String[] arrayLeaderboard() {
		// Generate the list of scores:
		String[] answer = new String[scoreList.size()];
		for (int i = 0; i < scoreList.size(); i++) {
			answer[i] = scoreList.get(i).toString();
		}
		return answer;
	}
}
