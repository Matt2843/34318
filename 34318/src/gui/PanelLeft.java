package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLeft extends JPanel {
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JLabel JLPublic, JLPrivate;
	private JPanel ShownPanel,TabPanel;
	
	
	private PanelLeftPublic PLPublic = new PanelLeftPublic(parent);
	private PanelLeftPrivate PLPrivate = new PanelLeftPrivate(parent);
	
	public PanelLeft(MainFrame parent){
		setDefaultProperties();
		setJComponents();
		TabPanel.add(JLPublic);
		TabPanel.add(JLPrivate);
		this.add(TabPanel, BorderLayout.NORTH);
		this.add(ShownPanel,BorderLayout.CENTER);
		this.validate();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setJComponents(){
		JLPublic = new JLabel("Public",JLabel.CENTER);
		setJLabel(JLPublic);
		
		JLPrivate = new JLabel("Private",JLabel.CENTER);
		setJLabel(JLPrivate);
		
		ShownPanel = new JPanel();
		ShownPanel.setBackground(Color.red);
		
		TabPanel = new JPanel(new GridLayout(1,2));
		TabPanel.setPreferredSize(GeneralProperties.panelLeftTabPanelSize);
	}
	
	private void setJLabel(JLabel name){
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setVisible(true);		
	}
}
