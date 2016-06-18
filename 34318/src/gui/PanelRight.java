package gui;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JTabbedPane;

import chat.ChatRoom;

public class PanelRight extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	
	public static HashMap<String, ChatTab> chatTabs; // 
	
	public PanelRight() {
		chatTabs = new HashMap<String, ChatTab>();
		setDefaultProperties();
		setComponents();
	}
	
	private void setDefaultProperties(){
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setBackground(Color.white);
		setVisible(true);
		validate();
	}
	
	private void setComponents(){
		
	}
	
	public String getName(ChatTab tab){
		return tab.getName();
	}
	
	public void removeTab(String chatID) {
		if(chatTabs.containsKey(chatID)) {
			chatTabs.remove(chatID);
		}
		removeTabAt(getSelectedIndex());
		System.out.println("everything " + chatTabs.size() + " " + chatTabs.toString());
	}
	
	public void addTab(ChatRoom chatRoom){
		for(ChatTab value : chatTabs.values()) {
			if(value.getChatRoom().toString().equals(chatRoom.toString())) {
				value.setOpen(true);
				setSelectedComponent(value);
				return;
			}
		}
		ChatTab newTab = new ChatTab(chatRoom);
		addTab(null, newTab);
		chatTabs.put(chatRoom.getChatID(), newTab);
		setSelectedComponent(newTab);
		setTabComponentAt(getSelectedIndex(), newTab.getTabContent());
		System.out.println("nothing " + chatTabs.size() + " " + chatTabs.toString());
	}

}