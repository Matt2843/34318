package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Profile extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private static JLabel JLUsername,edit, friendRequests;
	private JPanel panel = new JPanel(new GridLayout(7,1,0,5)), top, bottom;
	private HintTextField username,password,newPassword, repeatPassword;
	private JButton save;
	
	public Profile() {
		setComponents();		
		setLayout(new BorderLayout());
		add(top,BorderLayout.NORTH);
		add(bottom,BorderLayout.CENTER);
		addMouseListener(this);
	}
	
	private void setComponents(){
		JLUsername = new JLabel("");
		JLUsername.setBorder((BorderFactory.createLineBorder(Color.black)));
		
		edit = new JLabel("<HTML><U>Edit Profile</U></HTML>");
		
		username = new HintTextField(JLUsername.getText());
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		password = new HintTextField("Password");
		password.makePasswordField();
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		newPassword = new HintTextField("New password");
		newPassword.makePasswordField();
		newPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		repeatPassword = new HintTextField("Repeat new password");
		repeatPassword.makePasswordField();
		repeatPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		save = new JButton("Save");
		save.setBackground(Color.white);
		save.addMouseListener(this);
		
		panel.add(edit);
		panel.add(username);
		panel.add(password);
		panel.add(newPassword);
		panel.add(repeatPassword);
		panel.add(save);
		panel.setBackground(Color.white);
		
		top = new JPanel(new FlowLayout());
		top.setBackground(Color.white);
		top.add(panel);
		
		friendRequests = new JLabel(" Friend Reqests", SwingConstants.CENTER);
//		friendRequests.setBorder(BorderFactory.createLineBorder(Color.black));
		friendRequests.setBackground(Color.white);
		
		bottom = new JPanel(new BorderLayout());
		bottom.setBackground(Color.white);
		bottom.add(friendRequests,BorderLayout.NORTH);
		bottom.add(new FriendRequests());
	}
	
	public static void setUsername(String s) {
	    JLUsername.setText(" "+s);
	    JLUsername.setFont(new Font("SanSerif",Font.BOLD,14));
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
