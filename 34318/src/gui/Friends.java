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
	private JList<UserInformation> friendList;
	private DefaultListModel<UserInformation> model;
	private JScrollPane scrollPane;
	
	public Friends(ChatTab chatTab, ChatPanel chatPanel){
		this.chatTab = chatTab;
//		this.cshatPanel = chatPanel;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.friendsPanelSize);
		model = new DefaultListModel<UserInformation>(); 
		friendList = new JList<UserInformation>(model);
		friendList.addMouseListener(this);
		scrollPane = new JScrollPane(friendList);
		scrollPane.setBackground(Color.WHITE);
		for (int i = 0; i<4;i++){
			UserInformation user = new UserInformation("Friend" + i);
			addUserToList(user);
		}
		this.setTitle("Add friend");
		this.setIconImage(new ImageIcon("pictures/addFriend.png").getImage());
		this.add(scrollPane, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();
		this.validate();
		this.setLocationRelativeTo(null);
	}
	
	public void addUserToList(UserInformation user) {
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
