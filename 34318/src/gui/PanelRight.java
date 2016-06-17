package gui;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JTabbedPane;

import chat.ChatRoom;

public class PanelRight extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	
	//public static ArrayList <ChatTab> chatTabs;
	public static HashMap<String, ChatTab> chatTabs;
	
	
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
	
	public boolean addTab(ChatRoom chatRoom){
		boolean makeTab = true;
		int index = getSelectedIndex();
		for (int i = 0; i< chatTabs.size();i++){
			if (chatTabs.get(i).getName().equals(chatRoom.toString())){
				index = i;
				makeTab = false;
			}
		}
		if (makeTab){
			ChatTab newTab = new ChatTab(this, chatRoom, parent);
			addTab(null, newTab);
			
			setTabComponentAt(getTabCount()-1, newTab.getTabContent());
			chatTabs.put(chatRoom.getChatID(), newTab);
			setSelectedIndex(getTabCount()-1);
			return true;
		}
		else{
			setSelectedIndex(index);
			return false;
		}
		
	}

}