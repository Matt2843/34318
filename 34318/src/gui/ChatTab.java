  package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class ChatTab extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private PanelRight panelRight;
	private int tabIndex;
	private String tabName;
	private MainFrame mainFrame;
	private String chatID;
	private JPanel top = new JPanel(new BorderLayout());
	
	// The chat window including tab headers.
	private JLabel icon,name;
	private JPanel tabContent;
	private JTextPane chatArea;
	private JScrollPane ChatScrollPane;
	
	// The users-online list elements.
	private JLabel JLAddUsers;
	private JPanel usersInChatRight, JPUsersTop;
	
	
	public ChatTab(PanelRight panelRight, String tabName, int tabIndex, MainFrame mainFrame) {
		this.panelRight = panelRight;
		this.tabIndex = tabIndex;
		this.tabName = tabName;
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout());
		configureChatArea(tabName);
		setUsersInChat();
		add(top,BorderLayout.CENTER);
		add(new ChatArea(this),BorderLayout.SOUTH);
		validate();
	}
	
	public void updateTabIndex(int index) {
		if(tabIndex != index && tabIndex > index) {
			tabIndex -= 1;
		}
	}
	
	public void appendToTextArea(String string) {
		// TO-DO: Add standard format + timestamp?
		string = ("Username:    " + string);
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
		return tabName;
	}
	
	private void setUsersInChat() {
		makeTopPanel();	
		PanelRightUsersInChat panel = new PanelRightUsersInChat(mainFrame);
		panel.setPreferredSize(GeneralProperties.panelUsersSize);
		panel.setBackground(Color.white);
		usersInChatRight.add(JPUsersTop,BorderLayout.NORTH);
		usersInChatRight.add(panel,BorderLayout.CENTER);
		top.add(usersInChatRight, BorderLayout.EAST);
		
	}
	
	private void makeTopPanel(){

		JLAddUsers = new JLabel(MainFrame.IAdd);
		JLAddUsers.addMouseListener(this);
		usersInChatRight = new JPanel( new BorderLayout());;
		JPUsersTop = new JPanel(new BorderLayout());
		JLabel JLUsers = new JLabel("Users");
		JLUsers.setFont(new Font("SansSerif", Font.BOLD, 14));
		JPUsersTop.add(JLUsers,BorderLayout.WEST); JPUsersTop.add(new JLabel(),BorderLayout.CENTER);
		JPUsersTop.add(JLAddUsers,BorderLayout.EAST);
		JPUsersTop.setBackground(Color.WHITE);
	}

	private void configureChatArea(String tabName) {
		name = new JLabel(tabName);
		icon = new JLabel(MainFrame.IClose);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == icon){
			panelRight.removeTabAt(tabIndex);
			for(int i = 0; i < PanelRight.chatTabs.size(); i++) {
				PanelRight.chatTabs.get(i).updateTabIndex(tabIndex);
			}
		}
		if(e.getSource() == JLAddUsers){
			new Friends(mainFrame);
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

	public String getChatID() {
		return chatID;
	}

	public void setChatID(String chatID) {
		this.chatID = chatID;
	}
	
	
	
}
