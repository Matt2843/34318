package gui;

import java.awt.BorderLayout;
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
	
	// The users-online list elements.
	private JList<String> onlineUsers;
	private DefaultListModel<String> model;
	private JScrollPane scrollPane;
	
	public ChatTab(ChatPanel parent, String tabName) {
		this.parent = parent;
		setLayout(new BorderLayout());
		configureChatArea(tabName);
		configureOnlineUsers();
		validate();
	}
	
	public void appendToTextArea(String string) {
		// TO-DO: Add standard format + timestamp?
		chatArea.append(string);
	}
	
	public void removeUserFromList(String user) {
		model.removeElement(user);
	}
	
	public void addUserToList(String user) {
		model.addElement(user);
	}

	private void configureOnlineUsers() {
		model = new DefaultListModel<String>();
		onlineUsers = new JList<String>(model);
		scrollPane = new JScrollPane(onlineUsers);
		add(scrollPane, BorderLayout.EAST);
	}

	private void configureChatArea(String tabName) {
		name = new JLabel(tabName);
		icon = new JLabel(MainFrame.IClose);
		chatArea = new JTextArea();
		icon.addMouseListener(this);
		tabContent = new JPanel(new BorderLayout());
		tabContent.setOpaque(false);
		tabContent.add(name, BorderLayout.WEST);
		tabContent.add(icon,BorderLayout.EAST);
		add(chatArea, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Der er trykket");
		
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
