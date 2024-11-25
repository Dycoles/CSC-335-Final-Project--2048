package tests;

import Components.Board;
import Components.Direction;
import Components.Tile;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class testModel {
	// setup for tests
	Board board = new Board(4);
	
	// test shiftTileUp()
	@Test
	public void testShiftTileUp() {
		// shift tile
		board.shiftTile(Direction.UP);
		// check if tile when where it is supposed to
		// check if new tile was created (tileCount = 2)
		// try to merge tiles 
	}
	// test shiftTileDown()
	@Test
	public void testShiftTileDown() {
		board.shiftTile(Direction.DOWN);
		// check if tile when where it is supposed to
		// check if new tile was created (tileCount = 2)
		// try to merge tiles 
	}
	
	// test shiftTileLeft()
	@Test
	public void testShiftTileLeft() {
		board.shiftTile(Direction.LEFT);
		// check if tile when where it is supposed to
		// check if new tile was created (tileCount = 2)
		// try to merge tiles 
	}
	
	// test shiftTileRight()
	@Test
	public void testShiftTileRight() {
		board.shiftTile(Direction.RIGHT);
		// check if tile when where it is supposed to
		// check if new tile was created (tileCount = 2)
		// try to merge tiles 
	}
			
	// test gameWon()
	@Test
	public void testGameWon() {
		// create a board state where 1 value is equivalent to 2048 and assert if true
	}
	
	// test gameLost()
	@Test
	public void testGameLost() {
		// create a filled board where no moves can be made
	}
}
