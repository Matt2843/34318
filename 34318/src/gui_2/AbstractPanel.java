package gui_2;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame parent;
	
	public AbstractPanel(JFrame parent) {
		this.parent = parent;
		setBG();
	}
	
	public abstract void setBG();

}
