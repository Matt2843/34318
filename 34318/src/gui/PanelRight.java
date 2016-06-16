package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class PanelRight extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	public static ArrayList <ChatTab> chatTabs;
	
	
	public PanelRight(){
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
		
	}
	
	
	public String getName(ChatTab tab){
		return tab.getName();
	}
	
	public boolean addTab(String name){
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
			return true;
		}
		else{
			setSelectedIndex(index);
			return false;
		}
		
	}

}