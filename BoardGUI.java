import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGUI extends JFrame {
	
	private GameManager manager;
	private JPanel titlePanel;
	
	public static void main(String[] args) {
        BoardGUI gui = new BoardGUI();
        gui.setVisible(true);
	}
	
	public BoardGUI() {		
		manager = new GameManager(4);
		setUp();
		
	}
	
	public void setUp() {
		setTitle("2048");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// set up the title label
		JLabel title = new JLabel("2048", JLabel.CENTER);
		title.setSize(1000, 100);
		this.add(title);
		
		// 
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 2));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(controlPanel);
	}
	
}
