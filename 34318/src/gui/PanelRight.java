package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelRight extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	MainFrame parent;
	private String[] Chats = {"Chat 1", "Chat 2", "Chat 3", "Chat 4"};
	private JPanel ChatTab, ChatBottom, Menu, JPPictures, JPSmiley,JPFile, JPUsers;
	private JTabbedPane TabbedPanel;
	private JLabel JLSmiley, JLFile, JLClose;
	private JButton JBSend;
	private JTextArea JTText, JTUsersInChat;
	private int tabCounter = 0, chatCounter = 0;
	private JScrollPane scrollPane;
	private String id = "Username";
	private String[] UsersInChat = {"User1", "User ", "User3","User 4"};
	
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		this.setBackground(Color.white);
		setDefaultProperties();
		setComponents();
		makeBottomPanel();
		this.add(TabbedPanel,BorderLayout.NORTH);
		this.add(ChatBottom,BorderLayout.CENTER);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.panelRightSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){

		ChatBottom = new JPanel(new BorderLayout());
		ChatBottom.setBackground(Color.white);
		
		Menu = new JPanel(new BorderLayout());
		Menu.setBackground(Color.white);
		

		JPPictures = new JPanel(new GridLayout(1,2));
		JPPictures.setBackground(Color.WHITE);
		
		JPSmiley = new JPanel();
		JPSmiley.setPreferredSize(new Dimension(200,200));
		
		JPFile = new JPanel();
		JPFile.setPreferredSize(new Dimension(200,200));
				
		TabbedPanel = new JTabbedPane();
		TabbedPanel.setPreferredSize(GeneralProperties.panelRightTopSize);
				
		JLSmiley = new JLabel(parent.ISmiley);
		JLSmiley.setOpaque(false);
		
		JLFile = new JLabel(parent.IFile);
		JLFile.setOpaque(false);
		
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JBSend.setBackground(Color.WHITE);
		JBSend.addMouseListener(this);
		
		JTText = new JTextArea();
		JTText.addKeyListener(this);
		JTText.setLineWrap(true);
		scrollPane = new JScrollPane(JTText);
		
		for (int i = 0; i < Chats.length; i++){
			addTab();
		}
	}
	
	
	private void addTab(){
		final JPanel content = new JPanel(new BorderLayout());
		makeUsersTab("Indsæt ID her");
		JTextArea JTText = new JTextArea();
		JTText.setLineWrap(true);
		JTText.setText(Chats[chatCounter]);chatCounter++;
		content.add(JTText,BorderLayout.CENTER);
		content.add(JPUsers,BorderLayout.EAST);
		JLClose = new JLabel(parent.IClose);		
		JLClose.addMouseListener(new MouseListener() {
		      
			@Override
			public void mouseClicked(MouseEvent e) {
				int closeTabNumber = TabbedPanel.indexOfComponent(content);
		        TabbedPanel.removeTabAt(closeTabNumber);
				
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
		    });
		
		ChatTab = new JPanel(new BorderLayout());
		ChatTab.setOpaque(false);
		ChatTab.add(new JLabel("Chat" + (++tabCounter) + "    "),BorderLayout.WEST);
		ChatTab.add(JLClose,BorderLayout.EAST);
		TabbedPanel.setBackground(Color.white);
		TabbedPanel.addTab(null, content);
		TabbedPanel.setTabComponentAt(TabbedPanel.getTabCount() - 1, ChatTab);
	}
	
		private JPanel makeUsersTab(String ID){
		JPUsers = new JPanel(new GridLayout(30,1));
		JPUsers.setPreferredSize(GeneralProperties.panelUsersSize);
		JPUsers.setBorder(BorderFactory.createLineBorder(Color.black));
		JPUsers.setBackground(Color.WHITE);
		
		JTUsersInChat = new JTextArea();
		for (int i = 0; i<UsersInChat.length;i++){
			JTUsersInChat.append(UsersInChat[i]+ "\n");
		}
		JPanel JUsersIcons = new JPanel(new GridLayout(1,3));
		JUsersIcons.setOpaque(false);
		JUsersIcons.add(new JLabel(parent.IAdd));
		JUsersIcons.add(new JLabel(parent.IBlock)); JUsersIcons.add(new JLabel());
		JPanel JPUsersTop = new JPanel(new BorderLayout());
		JLabel JLUsers = new JLabel("  Users");
		JLUsers.setFont(new Font("SansSerif", Font.BOLD, 14));
		JPUsersTop.add(JLUsers,BorderLayout.WEST);
		JPUsersTop.add(JTUsersInChat,BorderLayout.CENTER);
		JPUsersTop.add(JUsersIcons,BorderLayout.EAST);
		JPUsersTop.setBackground(Color.WHITE);	
		JPUsers.add(JPUsersTop);
		return JPUsers;
	}
	
	
	private void makeBottomPanel(){
		JPPictures.add(JLSmiley);
		JPPictures.add(JLFile);
		Menu.add(JPPictures, BorderLayout.WEST);
		Menu.add(new JLabel(),BorderLayout.CENTER);
		ChatBottom.add(Menu, BorderLayout.NORTH);
		ChatBottom.add(scrollPane,BorderLayout.CENTER);
		ChatBottom.add(JBSend, BorderLayout.EAST);
		
	}
	
	private void sendText(){
		String message = JTText.getText();
		System.out.println(message);
		JTText.setText(null);
		int selectedTab = TabbedPanel.getSelectedIndex();
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea chat = new JTextArea(id + ":   " + message); chat.setEditable(false); chat.setLineWrap(true);
		panel.add(chat,BorderLayout.CENTER);
		panel.add(makeUsersTab("Indsæt ID"),BorderLayout.EAST);
		TabbedPanel.setComponentAt(selectedTab,panel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBSend){
			sendText();
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


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER){			
			if (!JTText.getText().trim().equals("")){
				sendText();
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
