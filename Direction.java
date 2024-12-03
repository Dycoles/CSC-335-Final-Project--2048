
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	/*// Horizontal difference after shifting in this direction:
	public int horiDiff() {
		if (this.equals(Direction.LEFT))
			return -1;
		else if (this.equals(Direction.RIGHT))
			return 1;
		else
			return 0;
	}
	
	// Vertical difference after shifting in this direction:
	public int vertDiff() {
		if (this.equals(Direction.UP))
			return -1;
		else if (this.equals(Direction.DOWN))
			return 1;
		else
			return 0;
	}*/
	
	// Return the Direction corresponding to the accepted String:
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
