package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import chat.ChatRoom;

public class PanelLeft extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	public static PanelLeftPublicChats PLPublicChats;
	
	private JPanel  JPPublic,JPFriends, addChat;
	private JTabbedPane tabbedPanel;
	public static ArrayList<String> publicChats = new ArrayList<String>();
	private ArrayList<String> privateChats = new ArrayList<String>();
	private MainFrame parent;
	
	
	public PanelLeft(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
		tabbedPanel.addTab("Public",MainFrame.IPublic,JPPublic);
		tabbedPanel.addTab("Friends", MainFrame.IPrivate, JPFriends);
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
	
	public void setComponents(){
		PLPublicChats = new PanelLeftPublicChats();
		makePublicChat();
		JPFriends = new JPanel(new BorderLayout());
		JPFriends.add(new PanelLeftFriends(parent),BorderLayout.CENTER);
		JPFriends.add(new Logout(parent),BorderLayout.SOUTH);
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
	}

	private void makePublicChat(){
		JPPublic = new JPanel(new BorderLayout());
		JPPublic.add(PLPublicChats, BorderLayout.CENTER);
		addChat = new JPanel(new BorderLayout());
		addChat.setPreferredSize(GeneralProperties.panelLeftaddChat);
		addChat.add(new JLabel(MainFrame.IAdd),BorderLayout.WEST);
		addChat.add(new JLabel(" Add public chat"),BorderLayout.CENTER);
		addChat.setFont(new Font("SansSerif", Font.BOLD, 14));
		addChat.addMouseListener(this);
		addChat.setBackground(Color.white);
		JPanel bottom = new JPanel(new GridLayout(2,1));
		bottom.add(addChat);
		bottom.add(new Logout(parent));
		JPPublic.add(bottom,BorderLayout.SOUTH);
	}
	
	public void updatePublicChats(ArrayList<ChatRoom> publicChats){
		PLPublicChats.setList(publicChats);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == addChat){
			new NewPublicChat(parent);
		}		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}