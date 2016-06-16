package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PanelRight extends JPanel{

	private static final long serialVersionUID = 1L;
	private ChatArea chatBottom;
	
	
	public PanelRight(){
		this.setBackground(Color.white);
		setDefaultProperties();
		chatBottom = new ChatArea();
		this.add(MainFrame.chatPanel,BorderLayout.NORTH);
		this.add(chatBottom,BorderLayout.CENTER);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.validate();
	}
}
