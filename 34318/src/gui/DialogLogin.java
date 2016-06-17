package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.UserInfo;



public class DialogLogin extends JDialog implements ActionListener, MouseListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel JPLogin,JPButtonsLogin, JPNewUser;
	private JTextField JTUsername;
	private JPasswordField Password;
	private JLabel JLLogin, JLUsername, JLPassword, JLNewUser, JLForgotPassword;
	private JButton JBLogin, JBCancel;
	private String username, password;
    private Insets normalInsets = new Insets(2,2,2,2);
    private Insets biggerInsets = new Insets(10,2,2,2);
    
    private JLabel JLCreateNewUser, JLNewUsername, JLNewPassword, JLRepeatPassword;
	private JTextField JTNewUsername;
	private JPasswordField NewPassword, RepeatPassword;
	private JButton JBCreate, JBNewCancel;
	private JLabel wait;
    
    private DialogMessage DMessage;
	
	public DialogLogin(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setJComponents();
		setJPLogin();
		setJPNewUser();
		panel = JPLogin;
    	panel.setBackground(Color.WHITE);
		this.add(panel);		
        setResizable(false);
	}
	
	private void addC(JPanel p, JComponent comp, int x, int y, int width){
    	c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		p.add(comp, c);
}
	private void setDefaultProperties(){
		addWindowListener(this);
    	setPreferredSize(new Dimension(500,350));
    	pack();
    	setLocationRelativeTo(null);
    }
	
	private void setJComponents(){

		JLLogin = new JLabel("<HTML><U>Login</U></HTML>");
		setJLabel(JLLogin, 25);
		
		JTUsername = new JTextField(20);
		setJTextField(JTUsername);
		
		JLUsername = new JLabel("Username: ");
		setJLabel(JLUsername,14);
		
		JLPassword = new JLabel("Password:");
		setJLabel(JLPassword,14);
		
		Password = new JPasswordField(20);
		setJTextField(Password);
		
		JLNewUser = new JLabel("<HTML><U>Create new user</U></HTML>");
		JLNewUser.setForeground(Color.blue);
		setJLabel(JLNewUser,14);
		
		JLForgotPassword = new JLabel("Forgot password?");
		setJLabel(JLForgotPassword,10);
		
		JBLogin = new JButton("Login");
		setJButton(JBLogin);
		
		JBCancel = new JButton("Cancel");
		setJButton(JBCancel);
		
		JLCreateNewUser = new JLabel("<HTML><U>Create new user</U></HTML>");
		setJLabel(JLCreateNewUser,25);
		
		JLNewUsername = new JLabel("Username");
		setJLabel(JLNewUsername,14);
		
		JLNewPassword = new JLabel("Password");
		setJLabel(JLNewPassword,14);
		
		JLRepeatPassword = new JLabel("Repeat Password");
		setJLabel(JLRepeatPassword,14);
		
		JTNewUsername = new JTextField(20);
		setJTextField(JTNewUsername);
		
		NewPassword = new JPasswordField(20);
		setJTextField(NewPassword);
		
		RepeatPassword = new JPasswordField(20);
		setJTextField(RepeatPassword);
		
		JBCreate = new JButton("Create");
		setJButton(JBCreate);
		
		JBNewCancel = new JButton("Cancel");
		setJButton(JBNewCancel);
		
		JPButtonsLogin = new JPanel(new GridLayout(1,2));
		
		wait = new JLabel(new ImageIcon("pictures/wait.gif"));
		wait.setVisible(true);
	}

	private void setJPLogin(){
		JPLogin = new JPanel(new GridBagLayout());
		JPButtonsLogin.add(JBLogin);
		JPButtonsLogin.add(JBCancel);
		int i = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
        addC(JPLogin, JLLogin,0,i,1);i++; c.insets = biggerInsets;
        addC(JPLogin, JLUsername, 0,i,1);i++;c.insets = biggerInsets;
        addC(JPLogin, JTUsername,0,i,2);i++;
        addC(JPLogin, JLPassword,0,i,1);i++;
        addC(JPLogin, Password,0,i,2);i++; c.insets = biggerInsets;
        addC(JPLogin, JLNewUser,0,i,1);i++; c.insets = normalInsets;
        addC(JPLogin,JLForgotPassword,0,i,1);i++; c.insets = biggerInsets;
        addC(JPLogin,JPButtonsLogin,0,i,2);
		
		Password.addActionListener(this);
		JLNewUser.addMouseListener(this);
		JLForgotPassword.addMouseListener(this);
		JBLogin.addMouseListener(this);
		JBCancel.addMouseListener(this);
	}

	private void setJPNewUser(){
		JPNewUser = new JPanel(new GridBagLayout());
		int i = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		addC(JPNewUser, JLCreateNewUser,0,i,2);i++; c.insets = normalInsets;
		addC(JPNewUser, JLNewUsername,0,i,1); i++;
		addC(JPNewUser, JTNewUsername,0,i,2); i++; c.insets = biggerInsets;
		addC(JPNewUser, JLNewPassword,0,i,1); i++;
		addC(JPNewUser, NewPassword,0,i,2); i++;
		addC(JPNewUser, JLRepeatPassword,0,i,1); i++;
		addC(JPNewUser, RepeatPassword,0,i,2);i++;
		addC(JPNewUser, JBCreate,0,i,1); 
		addC(JPNewUser, JBNewCancel,1,i,1);
		JPNewUser.setBackground(Color.WHITE);
		RepeatPassword.addActionListener(this);
		JBCreate.addMouseListener(this);
		JBNewCancel.addMouseListener(this);
	}
	
	private void setJTextField(JTextField name){
		name.setFont(new Font("SansSerif", Font.ITALIC, 14));
		name.setVisible(true);	
	}
	
	private void setJLabel(JLabel name, int size){
		name.setFont(new Font("SansSerif", Font.PLAIN, size));
		name.setVisible(true);	
	}
	
	private void setJButton(JButton name){
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setBackground(Color.WHITE);
		name.setVisible(true);		
	}

	@SuppressWarnings("deprecation")
	private String[] getLoginInfo(){
    	username = JTUsername.getText();
    	password = Password.getText();
    	String[] returnInfo = {username, password};
    	return returnInfo;
    }
	
	public void removePanel() {
		this.panel.setVisible(false);
	}

	public void addPanel(JPanel pan) {
		removePanel();
		panel = pan;
		this.add(panel);
		panel.setVisible(true);
		this.validate();
		this.pack();
	}
	
	@SuppressWarnings("deprecation")
	private void createNewUser(){
		
		if(NewPassword.getText().equals(RepeatPassword.getText())){
			String newUsername = JTNewUsername.getText();
			String newPassword = NewPassword.getText();
			UserInfo info = new UserInfo(newUsername, newPassword);
			MainFrame.client.sendMessage("L101", null, info);
		}
//			MainFrame.stall(this,"L101","L401");
//			if(MainFrame.client.getStatus().equals("L101")){
//				DMessage = new DialogMessage("Creation Successfull",parent);
//				this.setVisible(false);
//				parent.setVisible(true);
//			}
//			else{
//
//				DMessage = new DialogMessage("User already exists",parent);
//				DMessage.setAlwaysOnTop(true);
//			}
			
		
//		else{
//			DMessage = new DialogMessage("Passwords doesn't match", parent);
//			DMessage.setAlwaysOnTop(true);
//		}
	
}
	
	public void login(){
		String loginInfo[] = getLoginInfo();
		MainFrame.client.sendMessage("L100", loginInfo);		
		setVisible(false);
//		MainFrame.stall(this,"L400","L100");
//		if(MainFrame.client.getStatus().equals("L100")){
//			this.setVisible(false);
//			parent.setVisible(true);
//		}
//		else{
//			DMessage = new DialogMessage("Wrong login",parent);
//			DMessage.setAlwaysOnTop(true);
//		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JLNewUser){
			addPanel(JPNewUser);
		}
		if (e.getSource() == JLForgotPassword){
			System.exit(0);
		}
		if (e.getSource() == JBLogin){
			login();
		}
		if (e.getSource() == JBCancel){
			System.exit(0);
		}
		if (e.getSource() == JBCreate){
			createNewUser();
		}
		if ( e.getSource() == JBNewCancel){
			addPanel(JPLogin);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == RepeatPassword){
			createNewUser();
		}
		if (e.getSource() == Password){
			login();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MainFrame.beforeClosing();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
