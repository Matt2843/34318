package gui;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JTabbedPane;

import chat.ChatRoom;

public class PanelRight extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	
	//public static ArrayList <ChatTab> chatTabs;
	public static HashMap<String, ChatTab> chatTabs; // 
	
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
//		chatTabs = new ArrayList<ChatTab>();
		chatTabs = new HashMap<String, ChatTab>();
		setDefaultProperties();
		setComponents();
	}
	
	private void setDefaultProperties(){
		this.setBackground(Color.white);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){
		
	}
	
	public String getName(ChatTab tab){
		return tab.getName();
	}
	
	public void removeTab(ChatRoom chatRoom) {
		if(chatTabs.containsKey(chatRoom.getChatID())) {
			chatTabs.remove(chatRoom.getChatID());
		}
		removeTabAt(getSelectedIndex());
	}
	
	public void addTab(ChatRoom chatRoom){
		for(ChatTab value : chatTabs.values()) {
			if(value.getChatRoom().toString().equals(chatRoom.toString())) {
				value.setOpen(true);
				setSelectedComponent(value);
				return;
			}
		}
		ChatTab newTab = new ChatTab(parent, this, chatRoom);
		addTab(null, newTab);
		chatTabs.put(chatRoom.getChatID(), newTab);
		setSelectedComponent(newTab);
		setTabComponentAt(getSelectedIndex(), newTab.getTabContent());
	}

}