package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utility.Utilities;

public class Profile extends JPanel implements MouseListener,ActionListener{
	private static final long serialVersionUID = 1L;
	private static JLabel JLUsername,edit, friendRequests;
	private JPanel panel = new JPanel(new GridLayout(8,1,0,5)), top, bottom;
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
edit = new JLabel("<HTML><U>Edit Profile</U></HTML>");
		
		JLUsername = new JLabel("");
		JLUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		username = new HintTextField(" New Username");
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
		repeatPassword.addActionListener(this);
		
		save = new JButton("Save");
		save.setBackground(Color.white);
		save.addMouseListener(this);
		
		panel.add(edit);
		panel.add(JLUsername);
		panel.add(username);
		panel.add(password);
		panel.add(newPassword);
		panel.add(repeatPassword);
		panel.add(save);
		panel.setPreferredSize(GeneralProperties.editProfileSize);
		panel.setBackground(Color.white);
		
		top = new JPanel(new FlowLayout());
		top.setBackground(Color.white);
		top.add(panel);
		
		friendRequests = new JLabel(" Friend Reqests", SwingConstants.CENTER);
		friendRequests.setBackground(Color.white);
		
		bottom = new JPanel(new BorderLayout());
		bottom.setBackground(Color.white);
		bottom.add(friendRequests,BorderLayout.NORTH);
		bottom.add(new FriendRequests());
	}
	
	public static void setUsername(String s) {
	    JLUsername.setText(" "+s);
	}
	
	private void saveChanges(){
		if(newPassword.getText().equals(repeatPassword.getText())){
			String[] params = Utilities.setParams(3, username.getText(),password.getText(),newPassword.getText());
			MainFrame.client.sendMessage("L102", params);
		} else{
			new PopUp("Passwords doesn't match",this);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save){
			saveChanges();		
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== repeatPassword){
			saveChanges();
		}
		
	}

}
