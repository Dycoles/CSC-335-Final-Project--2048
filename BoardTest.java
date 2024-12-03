import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {

	@Test
	public void testDirection() {
		assertEquals(Direction.UP.horiDiff(),0);
		assertEquals(Direction.LEFT.horiDiff(),-1);
		assertEquals(Direction.RIGHT.horiDiff(),1);
		
		assertEquals(Direction.LEFT.vertDiff(),0);
		assertEquals(Direction.UP.vertDiff(),-1);
		assertEquals(Direction.DOWN.vertDiff(),1);
		
		assertEquals(Direction.strToDir("left"),Direction.LEFT);
		assertEquals(Direction.strToDir("up"),Direction.UP);
		assertEquals(Direction.strToDir("down"),Direction.DOWN);	
		assertEquals(Direction.strToDir("right"),Direction.RIGHT);
		assertEquals(Direction.strToDir("not this direction"),null);
	}

	@Test
	public void testLeaderboard() {
		Leaderboard leaderboard = new Leaderboard();
		assertEquals(leaderboard.getName(),"");
		
		//leaderboard.setName("hip hip");					# ADD TESTS AFTER IMPLEMENTATION
		//assertEquals(leaderboard.getName(),"hip hip");
		
		
		//leaderboard.addToFile();						# ADD TESTS AFTER IMPLEMENTATION
		//leaderboard.loadFromFile();
		
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertEquals(leaderboard.getScores(),empty);
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
		
		// testing canMove/canMerge
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
		// initialize board
		Board board = new Board(4);
		// empty board 
		board.emptyBoard();
		// insert specific tile
		board.insertTestTile(1, 1, 8);
		// shift
		board.shiftTile(Direction.UP);
		// use copy of board to compare vals
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[0][1].getValue(), 8);
	}
	
	@Test
	public void testShiftUpMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.UP);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[0][1].getValue(), 16);
		assertEquals(board.getScore(), 16);
		board.insertTestTile(1, 1, 16);
		board.shiftTile(Direction.UP);
		copy = board.getBoardCopy();
		assertEquals(copy[0][1].getValue(), 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftUpDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(3, 1, 16);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.UP);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[1][1].getValue(), 16);
		assertEquals(copy[0][1].getValue(), 8);
		assertEquals(board.getScore(), 0);
		// shift again for coverage; no new tile created
		board.shiftTile(Direction.UP);
	}
	
	@Test
	public void testShiftDown() {
		Board board = new Board(4);
		// empty board 
		board.emptyBoard();
		// insert specific tile
		board.insertTestTile(1, 1, 8);
		// shift
		board.shiftTile(Direction.DOWN);
		// use copy of board to compare vals
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[3][1].getValue(), 8);
	}
	
	@Test
	public void testShiftDownMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.DOWN);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[3][1].getValue(), 16);
		assertEquals(board.getScore(), 16);
		board.insertTestTile(2, 1, 16);
		board.shiftTile(Direction.DOWN);
		copy = board.getBoardCopy();
		assertEquals(copy[3][1].getValue(), 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftDownDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 1, 16);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.DOWN);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[3][1].getValue(), 16);
		assertEquals(copy[2][1].getValue(), 8);
		assertEquals(board.getScore(), 0);
		// shift again for coverage; no new tile created
		board.shiftTile(Direction.DOWN);
	}
	
	@Test
	public void testShiftLeft() {
		Board board = new Board(4);
		// empty board 
		board.emptyBoard();
		// insert specific tile
		board.insertTestTile(1, 1, 8);
		// shift
		board.shiftTile(Direction.LEFT);
		// use copy of board to compare vals
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[1][0].getValue(), 8);
	}
	
	@Test
	public void testShiftLeftMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(1, 2, 8);
		board.shiftTile(Direction.LEFT);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[1][0].getValue(), 16);
		assertEquals(board.getScore(), 16);
		board.insertTestTile(1, 1, 16);
		board.shiftTile(Direction.LEFT);
		copy = board.getBoardCopy();
		assertEquals(copy[1][0].getValue(), 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftLeftDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 1, 16);
		board.insertTestTile(0, 1, 8);
		board.shiftTile(Direction.DOWN);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[3][1].getValue(), 16);
		assertEquals(copy[2][1].getValue(), 8);
		assertEquals(board.getScore(), 0);
		// shift again for coverage; no new tile created
		board.shiftTile(Direction.DOWN);
	}
	
	@Test 
	public void testShiftRight() {
		Board board = new Board(4);
		// empty board 
		board.emptyBoard();
		// insert specific tile
		board.insertTestTile(1, 1, 8);
		// shift
		board.shiftTile(Direction.RIGHT);
		// use copy of board to compare vals
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[1][3].getValue(), 8);
	}
	
	@Test
	public void testShiftRightMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 1, 8);
		board.insertTestTile(1, 2, 8);
		board.shiftTile(Direction.RIGHT);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[1][3].getValue(), 16);
		assertEquals(board.getScore(), 16);
		board.insertTestTile(1, 2, 16);
		board.shiftTile(Direction.RIGHT);
		copy = board.getBoardCopy();
		assertEquals(copy[1][3].getValue(), 32);
		assertEquals(board.getScore(), 48);
	}
	
	@Test
	public void testShiftRightDontMerge() {
		Board board = new Board(4);
		board.emptyBoard();
		board.insertTestTile(1, 2, 16);
		board.insertTestTile(1, 1, 8);
		board.shiftTile(Direction.RIGHT);
		Tile[][] copy = board.getBoardCopy();
		assertEquals(copy[1][3].getValue(), 16);
		assertEquals(copy[1][2].getValue(), 8);
		assertEquals(board.getScore(), 0);
		// shift again for coverage; no new tile created
		board.shiftTile(Direction.RIGHT);
	}

	// checks the edge case where we shift in a direction but no movement 
	// or merges occur so no new tiles are formed
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
		Tile[][] copy = board.getBoardCopy();
		board.shiftTile(Direction.UP);
		assertEquals(copy[0][2].getValue(), 16);
		assertEquals(copy[0][3].getValue(), 8);
		assertEquals(copy[1][3].getValue(), 16);
		assertEquals(copy[1][2].getValue(), 8);
		assertEquals(copy[2][2].getValue(), 16);
		assertEquals(copy[2][3].getValue(), 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	// checks the edge case where we shift in a direction but no movement 
	// or merges occur so no new tiles are formed
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
		Tile[][] copy = board.getBoardCopy();
		board.shiftTile(Direction.LEFT);
		assertEquals(copy[1][0].getValue(), 16);
		assertEquals(copy[1][1].getValue(), 8);
		assertEquals(copy[2][0].getValue(), 16);
		assertEquals(copy[2][1].getValue(), 8);
		assertEquals(copy[3][0].getValue(), 16);
		assertEquals(copy[3][1].getValue(), 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	// checks the edge case where we shift in a direction but no movement 
	// or merges occur so no new tiles are formed
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
		Tile[][] copy = board.getBoardCopy();
		board.shiftTile(Direction.DOWN);
		assertEquals(copy[1][2].getValue(), 16);
		assertEquals(copy[1][3].getValue(), 8);
		assertEquals(copy[2][3].getValue(), 16);
		assertEquals(copy[2][2].getValue(), 8);
		assertEquals(copy[3][2].getValue(), 16);
		assertEquals(copy[3][3].getValue(), 8);
		assertEquals(board.getScore(), 0);
		assertFalse(board.gameWon());
		assertFalse(board.gameLost());
	}
	
	// checks the edge case where we shift in a direction but no movement 
	// or merges occur so no new tiles are formed
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
		Tile[][] copy = board.getBoardCopy();
		board.shiftTile(Direction.RIGHT);
		assertEquals(copy[1][3].getValue(), 16);
		assertEquals(copy[1][2].getValue(), 8);
		assertEquals(copy[2][3].getValue(), 16);
		assertEquals(copy[2][2].getValue(), 8);
		assertEquals(copy[3][3].getValue(), 16);
		assertEquals(copy[3][2].getValue(), 8);
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
		// test condition where board is full but merge possible
		board.emptyBoard();
		// tiles neighbor each other and can be merged
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
}
