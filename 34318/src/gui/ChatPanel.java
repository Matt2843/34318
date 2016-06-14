package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class ChatPanel extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	private String[] Chats = {"Name 1", "Name 2", "Chat 3", "Chat 4"};

	public static ArrayList <ChatTab> chatTabs;
	
	
	public ChatPanel(){
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
			addFirstTabs(Chats[i]);
		}
	}
	
	private void addFirstTabs(String name){
		ChatTab newTab = new ChatTab(this,name,getTabCount());
		addTab(null, newTab);
		setTabComponentAt(getTabCount()-1, newTab.getTabContent());
		chatTabs.add(newTab);
	}
	
	public String getName(ChatTab tab){
		return tab.getName();
	}
	
	public void addTab(String name){
		boolean makeTab = true;
		int index = getSelectedIndex();
		for (int i = 0; i< chatTabs.size();i++){
			if (chatTabs.get(i).getName().equals(name)){
				index = i;
				makeTab = false;
			}
		}
		if (makeTab){
			ChatTab newTab = new ChatTab(this,name,getTabCount());
			addTab(null, newTab);
			setTabComponentAt(getTabCount()-1, newTab.getTabContent());
			chatTabs.add(newTab);
			setSelectedIndex(getTabCount()-1);
		}
		else{
			System.out.println(index);
			setSelectedIndex(index);
		}
		
	}
}