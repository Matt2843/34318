  package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import chat.ChatRoom;

@SuppressWarnings("deprecation")
public class ChatTab extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private ChatRoom chatRoom;
	
	private boolean open = false;
	
	private PanelRightUsersInChat onlineUsers;
	private JPanel top = new JPanel(new BorderLayout());
	private JFrame friends;
	
	// The chat window including tab headers.
	private JLabel icon, name;
	private JPanel tabContent;
	private JTextPane chatArea;
	private JScrollPane ChatScrollPane;
	
	// The users-online list elements.
	private JLabel JLAddUsers;
	private JPanel usersInChatRight, JPUsersTop;
	
	
	public ChatTab(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
		setLayout(new BorderLayout());
		configureChatArea(chatRoom.toString());
		setUsersInChat();
		add(top,BorderLayout.CENTER);
		add(new ChatArea(), BorderLayout.SOUTH);
		validate();
	}
	
	public void appendToTextArea(String string) {
		try {
			chatArea.getDocument().insertString(chatArea.getDocument().getLength(), string  + "\n", null);
			replaceWithSmileys(chatArea.getDocument().toString());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void replaceWithSmileys(String string){
//		int S1 = string.indexOf(":)");
//		int S2 = string.indexOf("8D");
//		int S3 = string.indexOf(":I");
//		int S4 = string.indexOf(";)");
//		int S5 = string.indexOf("<<33");
//		int S6 = string.indexOf(":(");
//		int S7 = string.indexOf(":'(");
//		int S8 = string.indexOf(":''(");
//		int S9 = string.indexOf("D:");
//		int S10 = string.indexOf(";P");
//		int S11 = string.indexOf("8I");
//		int S12 = string.indexOf("'I(");
//		int S13 = string.indexOf(":*");
//		int S14 = string.indexOf(":'D");
//		int S15 = string.indexOf(":D");
//		int S16 = string.indexOf(":P");
	}
	
	
	public String getName(){
		return chatRoom.toString();
	}
	
	private void setUsersInChat() {
		makeTopPanel();	
		usersInChatRight = new JPanel( new BorderLayout());
		onlineUsers = new PanelRightUsersInChat();
		onlineUsers.setPreferredSize(GeneralProperties.panelUsersSize);
		onlineUsers.setBackground(Color.white);
		usersInChatRight.add(JPUsersTop,BorderLayout.NORTH);
		usersInChatRight.add(onlineUsers,BorderLayout.CENTER);
		top.add(usersInChatRight, BorderLayout.EAST);
		
	}
	
	private void makeTopPanel(){
		JLAddUsers = new JLabel(GeneralProperties.IAdd);
		JLAddUsers.addMouseListener(this);
		JPUsersTop = new JPanel(new BorderLayout());
		JLabel JLUsers = new JLabel("Users");
		JLUsers.setFont(new Font("SansSerif", Font.BOLD, 14));
		JPUsersTop.add(JLUsers,BorderLayout.WEST); JPUsersTop.add(new JLabel(),BorderLayout.CENTER);
		JPUsersTop.add(JLAddUsers,BorderLayout.EAST);
		JPUsersTop.setBackground(Color.WHITE);
	}

	private void configureChatArea(String tabName) {
		System.out.println(tabName);
		name = new JLabel(tabName);
		icon = new JLabel(GeneralProperties.IClose);
		chatArea = new JTextPane();
		chatArea.setEditable(false);
		ChatScrollPane = new JScrollPane(chatArea);
		icon.addMouseListener(this);
		tabContent = new JPanel(new BorderLayout());
		tabContent.setOpaque(false);
		tabContent.add(name, BorderLayout.WEST);
		tabContent.add(icon,BorderLayout.EAST);
		top.add(ChatScrollPane, BorderLayout.CENTER);
	}
	
	private void makeFriendFrame(){
		GUIEngine.mainFrame.disable();
		friends = new JFrame();
		friends.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                GUIEngine.mainFrame.enable();
                e.getWindow().dispose();
            }
        });
		friends.setPreferredSize(GeneralProperties.friendsPanelSize);
		friends.setTitle("Add friend");
		friends.setIconImage(new ImageIcon("pictures/addFriend.png").getImage());
		friends.add(new Friends(), BorderLayout.CENTER);
		friends.setVisible(true);
		friends.pack();
		friends.setLocationRelativeTo(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == icon){
			MainFrame.rightPanel.setSelectedComponent(this);
			MainFrame.rightPanel.removeTab(chatRoom.getChatID());
			String[] params = {chatRoom.getChatID()};
			MainFrame.client.sendMessage("G103", params);
		}
		if(e.getSource() == JLAddUsers){
			makeFriendFrame();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public JPanel getTabContent() {
		return tabContent;
	}

	public JTextPane getChatArea() {
		return chatArea;
	}
	
	public ChatRoom getChatRoom() {
		return chatRoom;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public PanelRightUsersInChat getOnlineUsers() {
		return onlineUsers;
	}
	
	
}
