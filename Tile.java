
/**
 * Tile.java represents an individual tile on the 2048 board. It will carry
 * out movement and merge operations and also manage the value of the tile.
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

import java.util.Random;

public class Tile {

	private int value;

	/**
	 * Create the tile and set its initial tile value to 0.
	 */
	public Tile() {
		value = 0;
	}

	/**
	 * Create the tile and set its initial value to the accepted val.
	 * 
	 * @param val - initial value of the tile
	 */
	public Tile(int val) {
		value = val;
	}

	/**
	 * Initializes the tile to 2 or 4 with a (7/10 chance for a 2).
	 */
	public void initialize() {
		// Assign either 2 or 4 to this tile (with a 7/10 for a 2):
		Random rand = new Random();
		int valueChance = rand.nextInt(10);
		if (valueChance <= 6)
			this.setVal(2);
		else
			this.setVal(4);
	}

	/**
	 * Getter for value.
	 * 
	 * @return value - value of the tile.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Removes the value of the tile.
	 */
	public void removeValue() {
		value = 0;
	}

	/**
	 * Checks if the tile is empty.
	 * 
	 * @return true if value is 0, otherwise false.
	 */
	public boolean isEmpty() {
		return value == 0;
	}

	/**
	 * Checks if the tile is able to make a move to an empty spot.
	 * 
	 * @param to - the tile in the desired direction.
	 * @return true if destination is empty, otherwise false.
	 */
	public boolean canMove(Tile to) {
		return to.isEmpty();
	}

	/**
	 * Checks if the tile is able to merge with another tile.
	 * 
	 * @param to - the tile in the desired direction.
	 * @return true if both tiles have the same value, otherwise false.
	 */
	public boolean canMerge(Tile to) {
		return to.getValue() == this.getValue();
	}

	/**
	 * Moves the tile value to other tile.
	 * 
	 * @param to - the tile spot in the desired direction.
	 */
	public void moveVal(Tile to) {
		if (to != this) {
			to.setVal(value);
			value = 0;
		}
	}

	/**
	 * Merges the tile value with another tile.
	 * 
	 * @param to - the tile spot in the desired direction.
	 * @return value after the merge, 0 if merge cannot be done.
	 */
	public int mergeVal(Tile to) {
		if (to != this) {
			to.doubleVal();
			value = 0;
			return to.getValue();
		}
		return 0;
	}

	/**
	 * Private setter for the tile's value.
	 * 
	 * @param val - the tile's value.
	 */
	private void setVal(int val) {
		value = val;
	}

	/**
	 * Doubles the tile's value.
	 */
	private void doubleVal() {
		value = value << 1;
	}
}
