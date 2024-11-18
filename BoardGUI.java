package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardGUI extends JFrame {
	
	private GameManager manager;
	
	public static void main(String[] args) {
        BoardGUI gui = new BoardGUI();
        gui.setVisible(true);
	}
	
	public BoardGUI() {
		manager = new GameManager();
		setTitle("2048");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 2));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
	}
	
}
