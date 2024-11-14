
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	// Horizontal difference after shifting in this direction:
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
	}
}
