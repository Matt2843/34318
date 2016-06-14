package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelLeft extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel JPPublic, JPFriends, addChat;
	private JTabbedPane tabbedPanel;
	public ArrayList<UserInformation> publicChats = new ArrayList<UserInformation>();
	private ArrayList<UserInformation> privateChats = new ArrayList<UserInformation>();
	private MainFrame parent;
	
	public PanelLeft(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
		tabbedPanel.addTab("Public",MainFrame.IPublic,JPPublic);
		tabbedPanel.addTab("Friends", MainFrame.IPrivate, JPFriends);
		this.add(tabbedPanel, BorderLayout.CENTER);
		this.validate();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	public void setComponents(){
		publicChats.add(new UserInformation("Name 1"));
		publicChats.add(new UserInformation("Name 2"));
		privateChats.add(new UserInformation("User 1"));
		makePublicChat();
		JPFriends = new LeftUsersPanel(privateChats);
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
	}

	private void makePublicChat(){
		JPPublic = new JPanel(new BorderLayout());
		JPPublic.add(new LeftUsersPanel(publicChats), BorderLayout.CENTER);
		addChat = new JPanel(new BorderLayout());
		addChat.add(new JLabel(MainFrame.IAdd),BorderLayout.WEST);
		addChat.add(new JLabel(" Add public chat"),BorderLayout.CENTER);
		addChat.setFont(new Font("SansSerif", Font.BOLD, 14));
		addChat.addMouseListener(this);
		addChat.setBackground(Color.white);
		addChat.setPreferredSize(GeneralProperties.panelLeftaddChat);
		JPPublic.add(addChat,BorderLayout.SOUTH);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == addChat){
			System.out.println("add new chat here");
			new newPublicChat(this,parent);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}