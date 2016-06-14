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

public class UserInformation implements MouseListener{
	private String username;
	private Point location;
	private int x,y;
	private JFrame frame = new JFrame();
	private JPanel JPAddFriend,JPBlockUser,JPSendMessage, userInfo;
	
	private ChatPanel parent;
	private UserInformation info;
	
	public UserInformation(String username) {
		this.username = username;
		setDefaultProperties();
	}
	
	private void setDefaultProperties(){
	}
	
	public void addJFrame(UserInformation info, ChatPanel parent){
		this.info = info;
		this.parent = parent; 
		addUserInformation(info);
		location = MouseInfo.getPointerInfo().getLocation();
		x = (int) location.getX();
		y = (int) location.getY();
		frame.setLocation(x,y);
		frame.setPreferredSize(GeneralProperties.userInformationTabSize);
		frame.addFocusListener(new FocusListener(){
			@Override
		    public void focusLost( FocusEvent e ) {
		          frame.dispose();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		frame.setUndecorated(true);
		frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		frame.setVisible(true);
		frame.pack();
		frame.validate();
	}
	
	public void addUserInformation(UserInformation info){
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
		userInfo  = new JPanel(new GridLayout(4,1));
		userInfo.setVisible(true);
		userInfo.add(JPAddFriend);
		userInfo.add(JPBlockUser); 
		userInfo.add(JPSendMessage);
		frame.add(userInfo);
	}
	
	@Override
	public String toString() {
		return username;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JPAddFriend){
			DialogMessage DMessage = new DialogMessage(info.toString()+" added as friend");
			DMessage.setAlwaysOnTop(true);
			frame.dispose();
		}
		if (e.getSource() == JPBlockUser){
			DialogMessage DMessage = new DialogMessage(info.toString() +" has been blocked");
			DMessage.setAlwaysOnTop(true);
			frame.dispose();
		}
		if(e.getSource() == JPSendMessage){
			parent.addTab(info.toString());
			frame.dispose();
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
