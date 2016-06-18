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

public class UserInformation extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private String username, panelSide;
	private Point location;
	private JPanel JPAddFriend, JPBlockUser, JPSendMessage, userInfo;
	
	
	public UserInformation(String username, String panelSide) {
		this.username = username;
		this.panelSide = panelSide;
		setDefaultProperties();
		setComponents();
		this.pack();
		this.setLocation(location);
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
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		
	}
	
	private void setComponents(){
		JPAddFriend = new JPanel(new BorderLayout());
		JPAddFriend.add(new JLabel(MainFrame.IAddFriend),BorderLayout.WEST);
		JPAddFriend.add(new JLabel("  Add Friend"),BorderLayout.CENTER);
		JPAddFriend.addMouseListener(this);
		JPBlockUser = new JPanel(new BorderLayout());
		JPBlockUser.add(new JLabel(MainFrame.IBlock),BorderLayout.WEST);
		JPBlockUser.add(new JLabel("  Block User"), BorderLayout.CENTER);
		JPBlockUser.addMouseListener(this);
		JPSendMessage = new JPanel(new BorderLayout());
		JPSendMessage.add(new JLabel(MainFrame.ISendMessage),BorderLayout.WEST);
		JPSendMessage.add(new JLabel("  Send Message"),BorderLayout.CENTER);
		JPSendMessage.addMouseListener(this);
		if (panelSide.equals("left")){
			this.setPreferredSize(GeneralProperties.userInformationLeftTabSize);
			userInfo  = new JPanel(new GridLayout(2,1));
			userInfo.setVisible(true);
			userInfo.add(JPBlockUser); 
			userInfo.add(JPSendMessage);
		} else {
			this.setPreferredSize(GeneralProperties.userInformationRightTabSize);
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
			dispose();
			DialogMessage DMessage = new DialogMessage(username+" added as friend");
			DMessage.setAlwaysOnTop(true);
		}
		if (e.getSource() == JPBlockUser){
			dispose();
			DialogMessage DMessage = new DialogMessage(username +" has been blocked");
			DMessage.setAlwaysOnTop(true);
		}
		if(e.getSource() == JPSendMessage){
			//MainFrame.chatPanel.addTab(username);
			dispose();
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
