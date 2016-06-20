package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import chat.ChatRoom;

public class PanelLeft extends JPanel implements MouseListener,ActionListener{
	private static final long serialVersionUID = 1L;
	
	public static PanelLeftPublicChats PLPublicChats;
	
	private JPanel  JPPublic,JPFriends,JPProfile, addChat;
	private JTabbedPane tabbedPanel;
	private JTextField search;
//	public static ArrayList<String> publicChats = new ArrayList<String>();
	private ArrayList<ChatRoom> chats;
//	private ArrayList<String> privateChats = new ArrayList<String>();
	
	
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
		search.addActionListener(this);
		search.addFocusListener(new FocusListener(){
			@Override
		    public void focusLost( FocusEvent e ) {
		          search.setText("Search");
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		
		JPanel bottom = new JPanel(new GridLayout(2,1));
		bottom.add(addChat);
		bottom.add(new Logout());
		JPPublic.add(search,BorderLayout.NORTH);
		JPPublic.add(bottom,BorderLayout.SOUTH);
	}
	
	private void makePrivateChat(){
		JPFriends = new JPanel(new BorderLayout());
		JPFriends.add(new PanelLeftFriends(),BorderLayout.CENTER);
		JPFriends.add(new Logout(),BorderLayout.SOUTH);
	}
	
	private void makeProfile(){
		JPProfile = new JPanel(new BorderLayout());
		JPProfile.add(new Profile(),BorderLayout.CENTER);
		JPProfile.add(new Logout(),BorderLayout.SOUTH);
	}
	
	
//	public void updatePublicChats(ArrayList<ChatRoom> publicChats){
//		PLPublicChats.setList(publicChats);
//		this.chats = publicChats;
//	}
	
	private void Search(){
		chats = PLPublicChats.getList();
		System.out.println(chats);
		ArrayList<ChatRoom> chatSearch = new ArrayList<ChatRoom>();
		String word = search.getText();		
		for (int i = 0; i<chats.size();i++){
			if(chats.get(i).toString().contains(word)){
				chatSearch.add(new ChatRoom(chats.get(i).toString()));
			}
		}
		
		System.out.println("her"+chatSearch.get(0).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==search){
			Search();
		}
		
	}
}