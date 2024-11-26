import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {

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

		// another issue with testing private methods, maybe we remove unused methods or change
		// visibility for testing for coverage purposes 
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
		// how do we want to test board for coverage
		// as we have many private methods acting as helper 
		// methods to void methods
		
		// we could potentially create a method we can use to test which
		// allows us to grab specific tiles or prints a usable output for our shift methods
		// (ex. making our shift methods return 1 if there was a movement, and 0 if there was not)
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
		board.insertTestTile(2, 1, 16);
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
		
	}
	
	@Test
	public void testShiftLeftDontMerge() {
		
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
		
	}
	
	@Test
	public void testShiftRightDontMerge() {
		
	}
	
	@Test
	public void testGameWon() {
		
	}
	
	@Test
	public void testGameLost() {
		
	}
	
}
