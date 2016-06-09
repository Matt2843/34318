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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class PanelLeft extends JPanel{

	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private JPanel JPPublic, JPPrivate;
	private JTabbedPane tabbedPanel;
	private String[] publicChats = {"Title 1", "Title2"}, privateChats = {"Name 1", "Name 2", "Name 3", "Name 4"};
	private JScrollPane scrollPane;
	
	private JList<String> onlineUsers;
	private DefaultListModel<String> model;
	private ChatPanel chatPanel = new ChatPanel(parent);
		
	public PanelLeft(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
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
		JPPublic = onlineUsers(publicChats);
		JPPrivate = onlineUsers(privateChats);
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
	}
	
	
	private JPanel onlineUsers(String[] users) {
		JPanel panel = new JPanel(new BorderLayout());
		model = new DefaultListModel<String>();
		onlineUsers = new JList<String>(model);
		onlineUsers.addMouseListener(new MouseListener() {
		      
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chatPanel.addTab("Ny chat");
					System.out.println("trykket!");
				  }
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		    });
		scrollPane = new JScrollPane(onlineUsers);
		scrollPane.setBackground(Color.WHITE);
		panel.add(scrollPane, BorderLayout.CENTER);
		for (int i = 0; i<users.length;i++){
			model.addElement(users[i]);
		}
		return panel;
	}
}
