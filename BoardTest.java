import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {

	@Test
	public void testTile() {
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
	
}
