package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.UserInfo;
import utility.Utilities;

public class Login extends JDialog implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel middle;
	private JLabel status, newUser;
	private JButton action,cancel;
	private HintTextField username, password, repeatPassword;
	
	public Login(){
		setDefaultProperties();
		setComponents();
		makeContent();
		add(middle);
		pack();
		setLocationRelativeTo(null);
	}
	
	private void setDefaultProperties(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.white);
		setPreferredSize(new Dimension(500,350));
		setIconImage(new ImageIcon("pictures/blank.png").getImage());
		setVisible(true);
	}
	
	private void setComponents(){
		status = new JLabel("<HTML><U>Login</U></HTML>");
		status.setFont(new Font("SansSerif", Font.PLAIN, 25));
		
		newUser = new JLabel("<HTML><U>Create new user</U></HTML>");
		newUser.setFont(new Font("SansSerif",Font.PLAIN,14));
		newUser.setForeground(Color.blue);
		newUser.addMouseListener(this);
		
		username = new HintTextField("Username");
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		password = new HintTextField("Password");
		password.makePasswordField();
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		password.addActionListener(this);
		
		repeatPassword = new HintTextField("Repeat password");
		repeatPassword.makePasswordField();
		repeatPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		repeatPassword.setVisible(false);
		repeatPassword.addActionListener(this);
		
		action = new JButton("Login");
		action.setBackground(Color.white);
		action.addMouseListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setBackground(Color.white);
		cancel.addMouseListener(this);
	}
	
	private void makeContent(){
		JPanel buttons = new JPanel(new GridLayout(1,2));
		buttons.add(action);
		buttons.add(cancel);
		middle = new JPanel(new GridLayout(6,1,0,5));
		middle.setBackground(Color.white);
		middle.setPreferredSize(new Dimension(300,250));
		middle.add(status);
		middle.add(username);
		middle.add(password);
		middle.add(repeatPassword);
		middle.add(newUser);
		middle.add(buttons);
		
		
	}
	
	private void login(){
		String userInfo[] = {username.getText(), password.getText()};
		String encryptedPassword = Utilities.encryptString(userInfo[1]);
		userInfo[1] = encryptedPassword;
		MainFrame.client.sendMessage("L100", userInfo);
		
		//Find ud af hvordan jeg staller/ragerer på forkert login??
	}
	
	private void createNewUser(){
		if(password.getText().equals(repeatPassword.getText())){
			String newUsername = username.getText();
			String newPassword = repeatPassword.getText();
			String encryptedPassword = Utilities.encryptString(newPassword);
			UserInfo info = new UserInfo(newUsername, encryptedPassword);
			MainFrame.client.sendMessage("L101", null, info);
			login();
		}
		else{
//			message("Passwords doesn't match")
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==password && action.getText().equals("Login")){
			login();
		}
		if(e.getSource() == repeatPassword && action.getText().equals("Create")){
			createNewUser();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == newUser){
			repeatPassword.setVisible(true);
			status.setText("<HTML><U> Create new user</U></HTML>");
			action.setText("Create");
			newUser.setVisible(false);
		}
		if(e.getSource()==action && action.getText().equals("Login")){
			login();
		}
		if(e.getSource() == action && action.getText().equals("Create")){
			createNewUser();
		}
		if(e.getSource() == cancel && action.getText().equals("Create")){
			repeatPassword.setVisible(false);
			status.setText("<HTML><U>Login</U></HTML>");
			action.setText("Login");
			newUser.setVisible(true);
		}
		if(e.getSource() == cancel && action.getText().equals("Login")){
//			System.exit(0);
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

//	public static void main(String[] args) {
//		new Login();
//	}
}
