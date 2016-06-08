package gui_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private GreenPanel test;
	private BlackPanel test2;
	private pinkPanel test3;
	public MainFrame() {
		setFrameProperties();
		test3 = new pinkPanel(this);
		addPanel(test3);
	}

	private void addPanel(JPanel panel) {
		this.add(panel, BorderLayout.CENTER);
		JLabel lab = new JLabel("TEST");
		lab.setOpaque(false);
		panel.add(lab);
		this.repaint();
	}

	private void setFrameProperties() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2));
		this.setLayout(new BorderLayout());
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
