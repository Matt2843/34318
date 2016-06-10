package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatTab extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ChatPanel parent;
	private int tabIndex;
	
	// The chat window including tab headers.
	private JLabel icon,name;
	private JPanel tabContent;
	private JTextArea chatArea;
	private JScrollPane ChatScrollPane;
	
	// The users-online list elements.
	private JList<UserInformation> usersInChat;
	private DefaultListModel<UserInformation> model;
	private JScrollPane scrollPane;
	
	
	public ChatTab(ChatPanel parent, String tabName, int tabIndex) {
		this.parent = parent;
		this.tabIndex = tabIndex;
		setLayout(new BorderLayout());
		configureChatArea(tabName);
		setUsersInChat();
		validate();
		for (int i = 0; i<2; i++){
			UserInformation user = new UserInformation("User" + i);
			addUserToList(user);
		}
		System.out.println("Hej");
	}
	
	public void updateTabIndex(int index) {
		if(tabIndex != index && tabIndex > index) {
			tabIndex -= 1;
		}
	}
	
	public void appendToTextArea(String string) {
		// TO-DO: Add standard format + timestamp?
		chatArea.append(string + "\n");
	}
	
	public void removeUserFromList(String user) {
		model.removeElement(user);
	}
	
	public void addUserToList(UserInformation user) {
		model.addElement(user);
	}

	private void setUsersInChat() {
		model = new DefaultListModel<UserInformation>();
		usersInChat = new JList<UserInformation>(model);
		usersInChat.addMouseListener(this);
		scrollPane = new JScrollPane(usersInChat);
		scrollPane.setPreferredSize(GeneralProperties.panelUsersSize);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane, BorderLayout.EAST);
		
	}

	private void configureChatArea(String tabName) {
		name = new JLabel(tabName);
		icon = new JLabel(MainFrame.IClose);
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		ChatScrollPane = new JScrollPane(chatArea);
		icon.addMouseListener(this);
		tabContent = new JPanel(new BorderLayout());
		tabContent.setOpaque(false);
		tabContent.add(name, BorderLayout.WEST);
		tabContent.add(icon,BorderLayout.EAST);
		add(ChatScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == icon){
			parent.removeTabAt(tabIndex);
			for(int i = 0; i < ChatPanel.chatTabs.size(); i++) {
				ChatPanel.chatTabs.get(i).updateTabIndex(tabIndex);
			}
		}
		if (e.getSource() == usersInChat){
			int x = usersInChat.getSelectedIndex();
			usersInChat.getModel().getElementAt(x).fun();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JPanel getTabContent() {
		return tabContent;
	}

	public JTextArea getChatArea() {
		return chatArea;
	}
	
	
	
}
