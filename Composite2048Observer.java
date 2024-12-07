
/**
 * Composite2048Observer.java is an observer interface to allow a Board object
 * to update a visual representation of its grid.
 * 
 * @author Dylan Coles (NetID: colesdylan12)
 * @author Sydney Farlow (NetID; sfarlow)
 * @author Jack Williams (NetID: jackmwilliams)
 * @author Arsha Wissinger (NetID: arshawissinger)
 */
public interface Composite2048Observer {
	public void updateObserver(int row, int col, int value);
}
