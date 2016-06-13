package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class ChatPanel extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	private String[] Chats = {"Chat 1", "Chat 2", "Chat 3", "Chat 4"};

	public static ArrayList <ChatTab> chatTabs;
	
	
	public ChatPanel(){
		chatTabs = new ArrayList<ChatTab>();
		setDefaultProperties();
		setComponents();
	}
	
	private void setDefaultProperties(){
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
}