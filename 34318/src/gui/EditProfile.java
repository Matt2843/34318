package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditProfile extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel friendRequests,requestTop,profile;
	private JLabel addFriends;
	
	public EditProfile() {
		setProperties();
		makeFriendRequestPanel();
		makeProfilePanel();
		add(new FriendRequests(),BorderLayout.WEST);	
		add(profile,BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);;
	}
	
	private void setProperties(){
		setLayout(new BorderLayout());
		setPreferredSize(GeneralProperties.editProfileSize);
		setTitle("Edit Profile");
		setIconImage(GeneralProperties.IEdit.getImage());
		setVisible(true);
	}
	
	private void makeFriendRequestPanel(){
		makeTopPanel();
		friendRequests = new JPanel(new BorderLayout());
		friendRequests.add(requestTop,BorderLayout.NORTH);
		friendRequests.add(new FriendRequests(), BorderLayout.CENTER);
	}
	
	private void makeTopPanel(){
		addFriends = new JLabel(GeneralProperties.IAddFriend);
		requestTop = new JPanel(new BorderLayout());
		requestTop.setBackground(Color.white);
		requestTop.setVisible(true);
		requestTop.add(addFriends,BorderLayout.WEST);
		requestTop.add(new JLabel("  Friend requests"),BorderLayout.CENTER);
	}
	
	private void makeProfilePanel(){
		profile = new JPanel();
		profile.setBackground(Color.white);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
