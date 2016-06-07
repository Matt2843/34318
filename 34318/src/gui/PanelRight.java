package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelRight extends JPanel{
	MainFrame parent;
	private JPanel ChatButtom, C1,C2;
	private JTextArea JTChat = new JTextArea();
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.panelRightSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){
		
	}


}
