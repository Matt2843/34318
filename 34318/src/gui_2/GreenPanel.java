package gui_2;

import java.awt.Color;

import javax.swing.JFrame;

public class GreenPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	public GreenPanel(JFrame parent) {
		super(parent);
	}

	@Override
	public void setBG() {
		this.setBackground(Color.GREEN);
		this.validate();
	}

}
