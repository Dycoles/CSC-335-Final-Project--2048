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
	
	public Leaderboard() {
		scoreList = new ArrayList<ScoreEntry>();
	}
	
	public void addScore(String name, int score, int size) {
		ScoreEntry entry = new ScoreEntry(name, score, size);
		scoreList.add(entry);
		// lambda expression sorts scores in descending order
		scoreList.sort((score1, score2) -> score2.getScore() - score1.getScore());
		// call add function
		addToFile();
	}
	
	public ArrayList<ScoreEntry> getScoreList() {
		// return a new Arraylist to avoid making a copy
		ArrayList<ScoreEntry> copyScores = new ArrayList<ScoreEntry>();
		for (int i = 0; i < scoreList.size(); i++) {
			copyScores.add(scoreList.get(i));
		}
		return copyScores;
	}
	
	public void loadScores() {
		// call load function
		loadFromFile();
	}
	
	// for testing
	public String[] arrayLeaderboard() {
		String[] answer = new String[scoreList.size()];
		for (int i=0; i < scoreList.size(); i++) {
			answer[i] = stringLeaderboard(scoreList.get(i));
		}
		return answer;
	}
	
	// for testing
	private String stringLeaderboard(ScoreEntry score) {
		String answer = "";
		answer += score.getName() + " ";
		answer += score.getScore() + " "; 
		answer += score.getSize();
		return answer;
	}
	
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
}
