package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import chat.ChatRoom;

public class PanelLeft extends JPanel implements MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	public static PanelLeftPublicChats PLPublicChats;
	public static PanelLeftFriends friendsList;
	
	private JPanel  JPPublic,JPFriends,JPProfile, addChat;
	private JTabbedPane tabbedPanel;
	private JTextField search;
	private JLabel close;
	
	private ArrayList<ChatRoom> chats;
	private boolean searching = false;
	
	
	public PanelLeft() {
		setDefaultProperties();
		setComponents();
		tabbedPanel.addTab("Public",GeneralProperties.IPublic,JPPublic);
		tabbedPanel.addTab("Friends", GeneralProperties.IPrivate, JPFriends);
		tabbedPanel.addTab("Profile", GeneralProperties.IProfile,JPProfile);
		this.add(tabbedPanel, BorderLayout.CENTER);
		this.validate();
	}
	
	private void setDefaultProperties() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	public void setComponents(){
		PLPublicChats = new PanelLeftPublicChats();
		makePublicChat();
		makePrivateChat();
		makeProfile();
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
		tabbedPanel.addMouseListener(this);
	}

	private void makePublicChat(){
		JPPublic = new JPanel(new BorderLayout());
		JPPublic.add(PLPublicChats, BorderLayout.CENTER);
		
		addChat = new JPanel(new BorderLayout());
		addChat.setPreferredSize(GeneralProperties.panelLeftaddChat);
		addChat.add(new JLabel(GeneralProperties.IAdd),BorderLayout.WEST);
		addChat.add(new JLabel("   Add public chat"),BorderLayout.CENTER);
		addChat.setFont(new Font("SansSerif", Font.BOLD, 14));
		addChat.addMouseListener(this);
		addChat.setBackground(Color.white);
		

		search = new JTextField("Search");
		search.addMouseListener(this);
		search.addKeyListener(this);
		close = new JLabel(GeneralProperties.IClose);
		close.addMouseListener(this);
		close.setBackground(Color.white);
		
		JPanel bottom = new JPanel(new GridLayout(2,1));
		bottom.add(addChat);
		bottom.add(new Logout());
		JPPublic.add(search, BorderLayout.NORTH);
		JPPublic.add(bottom, BorderLayout.SOUTH);
	}
	
	private void makePrivateChat(){
		JPFriends = new JPanel(new BorderLayout());
		friendsList = new PanelLeftFriends();
		JPFriends.add(friendsList, BorderLayout.CENTER);
		JPFriends.add(new Logout(), BorderLayout.SOUTH);
	}
	
	private void makeProfile(){
		JPProfile = new JPanel(new BorderLayout());
		JPProfile.add(new Profile(), BorderLayout.CENTER);
		JPProfile.add(new Logout(), BorderLayout.SOUTH);
	}
		
	private void Search(){
		searching = true;
		System.out.println("her");
		chats = PLPublicChats.getList();
		ArrayList<ChatRoom> chatSearch = new ArrayList<ChatRoom>();
		String word = search.getText();		
		for (int i = 0; i<chats.size();i++){
			if(chats.get(i).toString().contains(word)){
				chatSearch.add(new ChatRoom(chats.get(i).toString(),chats.get(i).getChatID()));
			} 
		}
		PLPublicChats.setList(chatSearch);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == addChat){
			new NewPublicChat();
		}
		if(e.getSource()== search){
			search.setText("");
		}
		if(e.getSource()== close){
			search.setText("Search");
			chats = PLPublicChats.getList();
			PLPublicChats.setList(chats);
		}
		if(e.getSource() == tabbedPanel) {
			tabbedPanel.setBackgroundAt(tabbedPanel.getSelectedIndex(), Color.white);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource()==search){
			if (search.getText().isEmpty()){
				searching = false;
				PLPublicChats.setList(chats);
			}else{
				Search();
				
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public boolean isSearching() {
		return searching;
	}
	
	public void profileUpdatedNotification(int which) {
		if(which == 0) { // Friend Request Notification
			tabbedPanel.setBackgroundAt(2, Color.blue);
			Profile.friendRequestList.setList(MainFrame.client.getProfile().getFriendRequests());
		}
		if(which == 1) { // Friend Added Notification
			tabbedPanel.setBackgroundAt(1, Color.blue);
			friendsList.setList(MainFrame.client.getProfile().getFriends());
		}
	}

	public void setProfileProperties() {
		Profile.friendRequestList.setList(MainFrame.client.getProfile().getFriendRequests());
		friendsList.setList(MainFrame.client.getProfile().getFriends());
	}
	
}