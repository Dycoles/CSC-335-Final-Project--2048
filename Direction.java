
/**
 * Direction.java is an enumerated type to help implement the tile shift
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
	 * Return the Direction corresponding to the accepted String.
	 * 
	 * @param str - the String to convert.
	 * @return the Direction value that corresponds to the String, otherwise null.
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
