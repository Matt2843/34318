package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class ChatPanel extends JTabbedPane {
	private MainFrame parent;
	private JPanel ChatTab, JPUsers;
	private JLabel JLClose;
	private JTextArea JTUsersInChat;
	private String[] Chats = {"Chat 1", "Chat 2", "Chat 3", "Chat 4"};
	private String[] UsersInChat = {"User1", "User ", "User3","User 4"};
	private int tabCounter = 0;

	public static ArrayList <ChatTab> chatTabs;
	
	
	public ChatPanel(MainFrame parent){
		this.parent = parent;
		chatTabs = new ArrayList<ChatTab>();
		setDefaultProperties();
		setComponents();
	}
	
	private void setDefaultProperties(){
		this.setPreferredSize(GeneralProperties.panelRightTopSize);
		this.setBackground(Color.white);
		this.setVisible(true);
		this.validate();
		
	}
	
	private void setComponents(){
		for (int i = 0; i < Chats.length; i++){
			addTab(Chats[i]);
		}
	}
	
	public void addTab(String name){
		ChatTab newTab = new ChatTab(this,name,getTabCount());
		addTab(null, newTab);
		setTabComponentAt(getTabCount()-1, newTab.getTabContent());
		chatTabs.add(newTab);
	}
	
	
//	public JPanel makeUsersTab(String ID){
//		JPUsers = new JPanel(new BorderLayout());
//		//JPUsers.setPreferredSize(GeneralProperties.panelUsersSize);
//		JPUsers.setBorder(BorderFactory.createLineBorder(Color.black));
//		JPUsers.setBackground(Color.WHITE);
//		
//		JTUsersInChat = new JTextArea();
//		for (int i = 0; i<UsersInChat.length;i++){
//			JTUsersInChat.append("\n   " + UsersInChat[i] );
//		}
//		JPanel JUsersIcons = new JPanel(new GridLayout(1,3));
//		JUsersIcons.setOpaque(false);
//		JUsersIcons.add(new JLabel(parent.IAdd));
//		JUsersIcons.add(new JLabel(parent.IBlock)); JUsersIcons.add(new JLabel());
//		JPanel JPUsersTop = new JPanel(new BorderLayout());
//		JLabel JLUsers = new JLabel("  Users");
//		JLUsers.setFont(new Font("SansSerif", Font.BOLD, 14));
//		JPUsersTop.add(JLUsers,BorderLayout.WEST);
//		JPUsersTop.add(new JLabel(),BorderLayout.CENTER);
//		JPUsersTop.add(JUsersIcons,BorderLayout.EAST);
//		JPUsersTop.setBackground(Color.WHITE);	
//		JPUsers.add(JPUsersTop, BorderLayout.NORTH);
//		JPUsers.add(JTUsersInChat, BorderLayout.CENTER);
//		return JPUsers;
//	}

}
