
/**
 * BoardTest.java tests the Model (Board.java) and Controller (GameManager.java)
 * for the 2048 game. 
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BoardTest {

	@Test
	public void testDirection() {		
		assertEquals(Direction.strToDir("left"),Direction.LEFT);
		assertEquals(Direction.strToDir("up"),Direction.UP);
		assertEquals(Direction.strToDir("down"),Direction.DOWN);	
		assertEquals(Direction.strToDir("right"),Direction.RIGHT);
		assertEquals(Direction.strToDir("not this direction"),null);
	}

	@Test
	public void testLeaderboard() {
		GameManager game = new GameManager(4);
		ArrayList<ScoreEntry> testScores = new ArrayList<ScoreEntry>();
		assertEquals(game.getLeaderboard(), testScores);
		
		Leaderboard leaderboard = new Leaderboard();
		
		ArrayList<ScoreEntry> scores = new ArrayList<ScoreEntry>();
		leaderboard.addScore("TEST", 0, 4);
		
		ScoreEntry testScore = new ScoreEntry("TEST", 0, 4);
		scores.add(testScore);
		
		leaderboard.loadScores();
		
		ArrayList<ScoreEntry> testScoreList = leaderboard.getScoreList();
		
		assertEquals(leaderboard.arrayLeaderboard()[0], "TEST 0 4");
		assertEquals(testScoreList.get(0).toString(), testScore.toString());
		
		leaderboard.addScore("TEST1", 0, 4);
		leaderboard.addScore("TEST2", 0, 4);
		
		assertEquals(leaderboard.arrayLeaderboard()[1], "TEST1 0 4");
		assertEquals(leaderboard.arrayLeaderboard()[2], "TEST2 0 4");
		
		// Remove the test entries from leaderboard.txt:
		leaderboard.removeScore("TEST", 0);
		leaderboard.removeScore("TEST1", 0);
		leaderboard.removeScore("TEST2", 0);
	}
	
	@Test
	public void testTile() {
		Tile tile = new Tile();
		assertEquals(tile.getValue(), 0);
		
		Tile twoTile = new Tile(2);
		assertEquals(twoTile.getValue(), 2);
		
		twoTile.removeValue();
		assertEquals(twoTile.getValue(), 0);

		assertEquals(twoTile.isEmpty(), true);
		
		// Test canMove/canMerge:
		assertEquals(tile.canMove(twoTile), true);
		assertEquals(tile.canMerge(twoTile), true);
	}
	
	@Test
	public void testBoard() {
		Board board = new Board(4);
		assertEquals(board.gameWon(), false);
		assertEquals(board.gameLost(), false);
		
		board.shiftTile(Direction.UP);
		board.shiftTile(Direction.RIGHT);
		board.shiftTile(Direction.LEFT);
		board.shiftTile(Direction.DOWN);
	}
	
	@Test
	public void testShiftUp() {
		// Initialize and empty the board:
		Board board = new Board(4);
		board.emptyBoard();
		
		// Insert a specific tile, shift, and use copy to compare:
		board.insertTestTile(1, 1, 8);
		board.shiftTile(Direction.UP);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[0][1], 8);
	}
	
	@Test
	public void testShiftUpMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.UP);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[0][1], 16);
		assertEquals(board.getScore(), 16);
		
		board.insertTestTile(1, 1, 16);
		board.shiftTile(Direction.UP);
		copy = board.getBoardValues();
		assertEquals(copy[0][1], 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftUpDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(3, 1, 16);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.UP);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[1][1], 16);
		assertEquals(copy[0][1], 8);
		assertEquals(board.getScore(), 0);
		
		// Shift again for coverage; no new tile created:
		board.shiftTile(Direction.UP);
	}
	
	@Test
	public void testShiftDown() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 8);
		board.shiftTile(Direction.DOWN);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[3][1], 8);
	}
	
	@Test
	public void testShiftDownMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.DOWN);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[3][1], 16);
		assertEquals(board.getScore(), 16);
		
		board.insertTestTile(2, 1, 16);
		board.shiftTile(Direction.DOWN);
		copy = board.getBoardValues();
		assertEquals(copy[3][1], 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftDownDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 16);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.DOWN);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[3][1], 16);
		assertEquals(copy[2][1], 8);
		assertEquals(board.getScore(), 0);
		
		// Shift again for coverage; no new tile created:
		board.shiftTile(Direction.DOWN);
	}
	
	@Test
	public void testShiftLeft() {
		Board board = new Board(4);
		board.emptyBoard();

		board.insertTestTile(1, 1, 8);
		board.shiftTile(Direction.LEFT);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[1][0], 8);
	}
	
	@Test
	public void testShiftLeftMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(1, 2, 8);
		board.shiftTile(Direction.LEFT);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[1][0], 16);
		assertEquals(board.getScore(), 16);
		
		board.insertTestTile(1, 1, 16);
		board.shiftTile(Direction.LEFT);
		copy = board.getBoardValues();
		assertEquals(copy[1][0], 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftLeftDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 16);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.DOWN);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[3][1], 16);
		assertEquals(copy[2][1], 8);
		assertEquals(board.getScore(), 0);
		
		// Shift again for coverage; no new tile created:
		board.shiftTile(Direction.DOWN);
	}
	
	@Test 
	public void testShiftRight() {
		Board board = new Board(4);
		board.emptyBoard();

		board.insertTestTile(1, 1, 8);
		board.shiftTile(Direction.RIGHT);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[1][3], 8);
	}
	
	@Test
	public void testShiftRightMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(1, 2, 8);
		board.shiftTile(Direction.RIGHT);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[1][3], 16);
		assertEquals(board.getScore(), 16);
		
		board.insertTestTile(1, 2, 16);
		board.shiftTile(Direction.RIGHT);
		copy = board.getBoardValues();
		assertEquals(copy[1][3], 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftRightDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 2, 16);
		board.insertTestTile(1, 1, 8);
		board.shiftTile(Direction.RIGHT);
		int[][] copy = board.getBoardValues();
		assertEquals(copy[1][3], 16);
		assertEquals(copy[1][2], 8);
		assertEquals(board.getScore(), 0);
		
		// Shift again for coverage; no new tile created:
		board.shiftTile(Direction.RIGHT);
	}

	// The next few tests check the edge case where we shift in a direction,
	// but no movement or merges occur, so no new tiles are formed.
	@Test
	public void testShiftUpEdge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(0, 2, 16);
		board.insertTestTile(0, 3, 8);
		board.insertTestTile(1, 3, 16);
		board.insertTestTile(1, 2, 8);
		board.insertTestTile(2, 2, 16);
		board.insertTestTile(2, 3, 8);
		int[][] copy = board.getBoardValues();
		board.shiftTile(Direction.UP);
		
		assertEquals(copy[0][2], 16);
		assertEquals(copy[0][3], 8);
		assertEquals(copy[1][3], 16);
		assertEquals(copy[1][2], 8);
		assertEquals(copy[2][2], 16);
		assertEquals(copy[2][3], 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	@Test
	public void testShiftLeftEdge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 0, 16);
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(2, 0, 16);
		board.insertTestTile(2, 1, 8);
		board.insertTestTile(3, 0, 16);
		board.insertTestTile(3, 1, 8);
		int[][] copy = board.getBoardValues();
		board.shiftTile(Direction.LEFT);
		
		assertEquals(copy[1][0], 16);
		assertEquals(copy[1][1], 8);
		assertEquals(copy[2][0], 16);
		assertEquals(copy[2][1], 8);
		assertEquals(copy[3][0], 16);
		assertEquals(copy[3][1], 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	@Test
	public void testShiftDownEdge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 2, 16);
		board.insertTestTile(1, 3, 8);
		board.insertTestTile(2, 3, 16);
		board.insertTestTile(2, 2, 8);
		board.insertTestTile(3, 2, 16);
		board.insertTestTile(3, 3, 8);
		int[][] copy = board.getBoardValues();
		board.shiftTile(Direction.DOWN);
		
		assertEquals(copy[1][2], 16);
		assertEquals(copy[1][3], 8);
		assertEquals(copy[2][3], 16);
		assertEquals(copy[2][2], 8);
		assertEquals(copy[3][2], 16);
		assertEquals(copy[3][3], 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	@Test
	public void testShiftRightEdge() {
		Board board = new Board(4);
		board.emptyBoard();
		
		board.insertTestTile(1, 3, 16);
		board.insertTestTile(1, 2, 8);
		board.insertTestTile(2, 3, 16);
		board.insertTestTile(2, 2, 8);
		board.insertTestTile(3, 3, 16);
		board.insertTestTile(3, 2, 8);
		int[][] copy = board.getBoardValues();
		board.shiftTile(Direction.RIGHT);
		
		assertEquals(copy[1][3], 16);
		assertEquals(copy[1][2], 8);
		assertEquals(copy[2][3], 16);
		assertEquals(copy[2][2], 8);
		assertEquals(copy[3][3], 16);
		assertEquals(copy[3][2], 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	@Test
	public void testGameWon() {
		Board board = new Board(4);
		board.emptyBoard();
		
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
		
		board.insertTestTile(0, 0, 2048);
		assertTrue(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	@Test
	public void testGameLost() {
		Board board = new Board(4);
		board.emptyBoard();
		
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
		
		board.insertTestTile(0, 0, 1);
		board.insertTestTile(0, 1, 2);
		board.insertTestTile(0, 2, 3);
		board.insertTestTile(0, 3, 4);
		board.insertTestTile(1, 0, 5);
		board.insertTestTile(1, 1, 6);
		board.insertTestTile(1, 2, 7);
		board.insertTestTile(1, 3, 8);
		board.insertTestTile(2, 0, 9);
		board.insertTestTile(2, 1, 10);
		board.insertTestTile(2, 2, 11);
		board.insertTestTile(2, 3, 12);
		board.insertTestTile(3, 0, 13);
		board.insertTestTile(3, 1, 14);
		board.insertTestTile(3, 2, 15);
		board.insertTestTile(3, 3, 16);
		
		assertTrue(board.gameLost());
		assertFalse(board.gameWon());
		
		// Test the condition where board is full but a merge is possible:
		board.emptyBoard();
		board.insertTestTile(0, 0, 2);
		board.insertTestTile(0, 1, 2);
		board.insertTestTile(0, 2, 3);
		board.insertTestTile(0, 3, 4);
		board.insertTestTile(1, 0, 5);
		board.insertTestTile(1, 1, 6);
		board.insertTestTile(1, 2, 7);
		board.insertTestTile(1, 3, 8);
		board.insertTestTile(2, 0, 9);
		board.insertTestTile(2, 1, 10);
		board.insertTestTile(2, 2, 11);
		board.insertTestTile(2, 3, 12);
		board.insertTestTile(3, 0, 13);
		board.insertTestTile(3, 1, 14);
		board.insertTestTile(3, 2, 15);
		board.insertTestTile(3, 3, 16);
		assertFalse(board.gameLost());
	}

	@Test
	public void testGameManagerGetters() {
		GameManager game = new GameManager(4);
		
		assertFalse(game.isGameWon());
		assertFalse(game.isGameLost());
		assertTrue(game.isPlaying());
		assertEquals(game.getCurScore(),0);
	}
	
}
