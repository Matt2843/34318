		package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.Utilities;

public class UserInformation extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private String username, panelSide;
	private Point location;
	private JPanel JPAddFriend, JPBlockUser, JPSendMessage,JPDeleteUser, userInfo;
	
	
	public UserInformation(String username, String panelSide) {;
		this.username = username;
		this.panelSide = panelSide;
		setDefaultProperties();
		setComponents();
		pack();
		setLocation(location);
	}
	
	private void setDefaultProperties(){
		location = MouseInfo.getPointerInfo().getLocation();
		this.addFocusListener(new FocusListener(){
			@Override
		    public void focusLost( FocusEvent e ) {
		          dispose();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		setUndecorated(true);
		setAlwaysOnTop(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		
	}
	
	private void setComponents(){
		JPAddFriend = new JPanel(new BorderLayout());
		JPAddFriend.add(new JLabel(GeneralProperties.IAddFriend),BorderLayout.WEST);
		JPAddFriend.add(new JLabel("  Add Friend"),BorderLayout.CENTER);
		JPAddFriend.addMouseListener(this);
		
		JPBlockUser = new JPanel(new BorderLayout());
		JPBlockUser.add(new JLabel(GeneralProperties.IBlock),BorderLayout.WEST);
		JPBlockUser.add(new JLabel("  Block User"), BorderLayout.CENTER);
		JPBlockUser.addMouseListener(this);

		JPDeleteUser = new JPanel(new BorderLayout());
		JPDeleteUser.add(new JLabel(GeneralProperties.IDelete),BorderLayout.WEST);
		JPDeleteUser.add(new JLabel("  Delete User"), BorderLayout.CENTER);
		JPDeleteUser.addMouseListener(this);
		
		JPSendMessage = new JPanel(new BorderLayout());
		JPSendMessage.add(new JLabel(GeneralProperties.ISendMessage),BorderLayout.WEST);
		JPSendMessage.add(new JLabel("  Send Message"),BorderLayout.CENTER);
		JPSendMessage.addMouseListener(this);
//		for (int i = 0;i<PanelRight.chatTabs.size();i++){
//			if (PanelRight.chatTabs.get(i).toString().equals(MainFrame.username)){
//				setPreferredSize(GeneralProperties.userInformationUser);
//				userInfo = new JPanel(new BorderLayout());
//				userInfo.setVisible(true);
//				userInfo.add(JPSendMessage, BorderLayout.CENTER);
//				break;
//			}
//		}
		if (panelSide.equals("left")){
			setPreferredSize(GeneralProperties.userInformationLeftTabSize);
			userInfo  = new JPanel(new GridLayout(2,1));
			userInfo.setVisible(true);
			userInfo.add(JPSendMessage);
			userInfo.add(JPDeleteUser); 
		} else {
			setPreferredSize(GeneralProperties.userInformationRightTabSize);
			userInfo  = new JPanel(new GridLayout(3,1));
			userInfo.setVisible(true);
			userInfo.add(JPAddFriend);
			userInfo.add(JPBlockUser); 
			userInfo.add(JPSendMessage);
		}
		
		this.add(userInfo);
	}
	
	@Override
	public String toString() {
		return username;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JPAddFriend){
			String[] params = Utilities.setParams(1, username);
			MainFrame.client.sendMessage("V100",params);
			//Stall indtil det er sket?
			PopUp DMessage = new PopUp("Send friend request to " + username, GUIEngine.mainFrame);
			DMessage.setAlwaysOnTop(true);
			dispose();
		}
		if (e.getSource() == JPBlockUser){
			String targetUser = PanelLeft.friendsList.getSelectedValue().toString();
			MainFrame.client.getProfile().removeFriend(targetUser);
			MainFrame.client.getProfile().blockUser(targetUser);
			String[] params = Utilities.setParams(1, MainFrame.client.getProfile().getUsername());
			MainFrame.client.sendMessage("V102", params);
			dispose();
//			PopUp DMessage = new PopUp(username +" has been blocked",this);
//			DMessage.setAlwaysOnTop(true);
		}
		if(e.getSource() == JPSendMessage){
			boolean exists = MainFrame.rightPanel.chatExists(username);
			if(!exists){
				String[] params = Utilities.setParams(1, username);
				MainFrame.client.sendMessage("G101", params);	
			}
			dispose();
		}
		if(e.getSource()== JPDeleteUser){
			String targetUser = PanelLeft.friendsList.getSelectedValue().toString();
			MainFrame.client.getProfile().removeFriend(targetUser);
			String[] params = Utilities.setParams(1, MainFrame.client.getProfile().getUsername());
			MainFrame.client.sendMessage("V102", params);
		}
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
