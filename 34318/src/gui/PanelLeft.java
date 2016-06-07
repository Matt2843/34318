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
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelLeft extends JPanel {
	private MainFrame parent;
	private JPanel JPPublic, JPPrivate;
	private JPanel ShownPanel,TabPanel;
	private JTabbedPane tabbedPanel;
	
	
	private PanelLeftPublic PLPublic = new PanelLeftPublic(parent);
	private PanelLeftPrivate PLPrivate = new PanelLeftPrivate(parent);
	
	public PanelLeft(MainFrame parent){
		setDefaultProperties();
		setComponents();
		tabbedPanel.addTab("Public",parent.IPublic,JPPublic);
		tabbedPanel.addTab("Private", parent.IPrivate, JPPrivate);
		this.add(tabbedPanel, BorderLayout.CENTER);
		this.validate();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){
		JPPublic = new JPanel();
		JPPublic.setBackground(Color.blue);
		
		JPPrivate = new JPanel();
		JPPrivate.setBackground(Color.red);
		
		
		tabbedPanel = new JTabbedPane();
		
	}
	
	private void setJLabel(JLabel name){
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setVisible(true);		
	}
}
