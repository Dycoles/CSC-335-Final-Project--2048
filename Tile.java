package Components;

import java.util.Random;

public class Tile {
	int value;
	
	public Tile() {
		value = 0;
	}
	
	public Tile(int val) {
		value = val;
	}
	
	public void initialize() {
		// Assign either 2 or 4 to this tile:
		Random rand = new Random();
		int valueChance = rand.nextInt(10);
		if (valueChance <= 6)	// 7/10 chance for a 2 TODO play with probability
			this.setVal(2);
		else
			this.setVal(4);
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
