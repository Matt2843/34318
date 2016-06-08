package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelRight extends JPanel {

	private static final long serialVersionUID = 1L;
	MainFrame parent;
	private JPanel JPUsers;
	private JLabel JLUsers;
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
		this.add(JLUsers, BorderLayout.NORTH);
		this.add(JPUsers,BorderLayout.CENTER);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.orange);
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){
		JPUsers = new JPanel();
		JLUsers = new JLabel("Users");
	}
}
