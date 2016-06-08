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
	private String[] publicChats = {"Title 1", "Title2"}, privateChats = {"Name 1", "Name 2", "Name 3", "Name 4"};
	
	
	private PanelLeftPublic PLPublic = new PanelLeftPublic(parent);
	private PanelLeftPrivate PLPrivate = new PanelLeftPrivate(parent);
	
	public PanelLeft(MainFrame parent){
		setDefaultProperties();
		setComponents();
		addContent();
		tabbedPanel.addTab("Public",parent.IPublic,JPPublic);
		tabbedPanel.addTab("Private", parent.IPrivate, JPPrivate);
		this.add(tabbedPanel, BorderLayout.CENTER);
		this.validate();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){
		JPPublic = new JPanel(new GridLayout(30,1));
		JPPublic.setBackground(Color.green);
		
		JPPrivate = new JPanel(new GridLayout(30,1));
		JPPrivate.setBackground(Color.red);
				
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
		
	}
	
	private void addContent(){
		for (int i = 0; i<publicChats.length; i++){
			JPPublic.add(new JLabel(publicChats[i]));
		}
		for (int i = 0; i<privateChats.length;i++){
			JPPrivate.add(new JLabel(privateChats[i]));
		}
	}
}
