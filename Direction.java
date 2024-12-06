
/**
 * Direction.java is an Enumeration class to help implement the tile shift
 * directions. This class adds direction constants for the 2048 game.
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */

public enum Direction {
	// Directions:
	UP, DOWN, LEFT, RIGHT;
	
	/**
	 *  Horizontal difference after shifting in this direction:
	 *  @return an int representing the horizontal difference after a shift
	 */
	/*// 
	public int horiDiff() {
		if (this.equals(Direction.LEFT))
			return -1;
		else if (this.equals(Direction.RIGHT))
			return 1;
		else
			return 0;
	}
	
	/**
	 *  Vertical difference after shifting in this direction:
	 *  @return an int representing the vertical difference after a shift
	 */
	/*//
	public int vertDiff() {
		if (this.equals(Direction.UP))
			return -1;
		else if (this.equals(Direction.DOWN))
			return 1;
		else
			return 0;
	}*/
	
	/**
	 * Return the Direction corresponding to the accepted String:
	 * @param str - the String to convert 
	 * @return Direction enum value that corresponds to the String, otherwise null
	 */
	public static Direction strToDir(String str) {
		str = str.toLowerCase();
		
		// Compare possible values:
		switch (str) {
			case "up":
				return Direction.UP;
			case "left":
				return Direction.LEFT;
			case "right":
				return Direction.RIGHT;
			case "down":
				return Direction.DOWN;
			default:
				return null;
		}
	}
}
