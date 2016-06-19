package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private static JLabel username;
	
	public Profile() {
		username = new JLabel("");	
		setLayout(new BorderLayout());
		add(username,BorderLayout.NORTH);
		add(new EditProfile(),BorderLayout.CENTER);
		setBackground(Color.white);
		addMouseListener(this);
	}
	
	public static void setUsername(String s) {
	    username.setText("   "+s);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		new EditProfile();
//		ArrayList<ChatRoom> test = new ArrayList<ChatRoom>();
//		test.add(new ChatRoom("her","iD"));
//		FriendRequests.setList(test);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
