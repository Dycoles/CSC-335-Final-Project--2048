package Components;

public class Tile {
	int value;
	
	public Tile() {
		value = 0;
	}
	
	public Tile(int val) {
		value = val;
	}
	
	public int getValue() {
		return value;
	}
	
	public void removeValue() {
		value = 0;
	}
	
	public boolean isEmpty() {
		return value != 0;
	}
	
	public boolean canMove(Tile to) {
		return to.isEmpty();
	}
	
	public boolean canMerge(Tile to) {
		return to.getValue() == this.getValue();
	}
	
	public void moveVal(Tile to) {
		to.setVal(value);
		value = 0;
	}
	
	public void mergeVal(Tile to) {
		to.doubleVal();
		value = 0;
	}
	
	private void setVal(int val) {
		value = val;
	}
	
	private void doubleVal() {
		value = value << 1;
	}
}
