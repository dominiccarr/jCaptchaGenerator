import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class UsefulJFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	/**
	 * The main panel
	 */
	protected JPanel panel;
	/**
	 * Tool kit used to find the middle of the screen
	 */
	private java.awt.Toolkit kit;
	/**
	 * Dimension used to find the middle of the screen
	 */
	private Dimension d;
	int WIDTH;
	
	/**
	 * Constructor for the GUI
	 */
	public UsefulJFrame(String t, int w, int h) {
		
		super(t);
		WIDTH = w;
		lookandFeel();
		setResizable(false);
		setVisible(true);
		setBounds(0, 0, w, h);
		setCenter();
		addPanels();
		pack();
		setSize(w, h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		update(this.getGraphics());
	}
	
	/**
	 * adds the panels
	 */
	private void addPanels() {
		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
	}
	
	/**
	 * sets the look and feel depending on the OS
	 */
	private void lookandFeel() {
		if (System.getProperty("os.name").equals("Mac OS X")) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}
		}
	}

	/**
	 * positions the JFrame in the center of the screen
	 */
	private void setCenter() {
		kit = getToolkit();
		d = kit.getScreenSize();
		setLocation(d.width / 2 - (WIDTH / 2) + 20, 0);
	}
}
