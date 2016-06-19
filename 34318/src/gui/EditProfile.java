package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditProfile extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel friendRequests,requestTop;
	private JLabel addFriends;
	
	public EditProfile() {
		setProperties();
		makeFriendRequestPanel();
		add(new FriendRequests(),BorderLayout.CENTER);	
	}
	
	private void setProperties(){
		setLayout(new BorderLayout());
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
