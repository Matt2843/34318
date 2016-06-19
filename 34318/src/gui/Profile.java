package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private static JLabel username,friendRequests;
	private JPanel panel = new JPanel(new GridLayout(3,1,0,5));
	
	public Profile() {
		username = new JLabel("");
		username.setBorder((BorderFactory.createLineBorder(Color.black)));
		friendRequests = new JLabel(" Friend Reqests");
		setLayout(new BorderLayout());
		panel.add(username); panel.add(new JLabel());
		panel.add(friendRequests);
		panel.setBackground(Color.white);
		add(panel,BorderLayout.NORTH);
		add(new FriendRequests(),BorderLayout.CENTER);
		addMouseListener(this);
	}
	
	public static void setUsername(String s) {
	    username.setText(" "+s);
	    username.setFont(new Font("SanSerif",Font.BOLD,14));
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		new FriendRequests();
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
