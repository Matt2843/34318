package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Friends extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ChatTab chatTab;
//	private ChatPanel chatPanel;
	private JList<String> friendList;
	private DefaultListModel<String> model;
	private JScrollPane scrollPane;
	
	public Friends(ChatTab chatTab, ChatPanel chatPanel){
		this.chatTab = chatTab;
//		this.cshatPanel = chatPanel;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.friendsPanelSize);
		model = new DefaultListModel<String>(); 
		friendList = new JList<String>(model);
		friendList.addMouseListener(this);
		scrollPane = new JScrollPane(friendList);
		scrollPane.setBackground(Color.WHITE);
		addUserToList("Friend");
		this.setTitle("Add friend");
		this.setIconImage(new ImageIcon("pictures/addFriend.png").getImage());
		this.add(scrollPane, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();
		this.validate();
		this.setLocationRelativeTo(null);
	}
	
	public void addUserToList(String user) {
		model.addElement(user);
	}
	@Override
	public void mouseClicked(MouseEvent e) {			
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == friendList){
			if (e.getClickCount() == 2) {
				chatTab.addUserToList(friendList.getSelectedValue());
//				int selectedIndex = chatPanel.getSelectedIndex();
//				ChatTab selectedTab = chatPanel.chatTabs.get(selectedIndex);
//				String name = selectedTab.getName();
//				String newName = friendList.getSelectedValue().toString();
				//chatPanel.chatTabs.set(selectedIndex, name + newName);
				dispose();
			}
		}		
	}
}
