package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelLeft extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel JPPublic, JPPrivate;
	private JTabbedPane tabbedPanel;
	private  ArrayList<UserInformation> publicChats = new ArrayList<UserInformation>();
	private ArrayList<UserInformation> privateChats = new ArrayList<UserInformation>();
	
	public PanelLeft(){
		setDefaultProperties();
		setComponents();
		tabbedPanel.addTab("Public",MainFrame.IPublic,JPPublic);
		tabbedPanel.addTab("Private", MainFrame.IPrivate, JPPrivate);
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
		publicChats.add(new UserInformation("Name 1"));
		publicChats.add(new UserInformation("Name 2"));
		privateChats.add(new UserInformation("User 1"));
		JPPublic = new LeftUsersPanel(publicChats);
		JPPrivate = new LeftUsersPanel(privateChats);
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
	}
}