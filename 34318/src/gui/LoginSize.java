package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class LoginSize extends JPanel{
	
	public LoginSize(){
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	this.setPreferredSize(new Dimension(500,300));
	}

}

/* package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class DialogLogin extends JDialog implements ActionListener, MouseListener {
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel();
	private JPanel panelLogin = new JPanel(new GridBagLayout());
	private JPanel panelNewUser = new JPanel(new GridBagLayout());
	private JTextField JTUsername;
	private JPasswordField Password;
	private JLabel JLLogin, JLUsername, JLPassword, JLNewUser;
	private JButton JBLogin, JBCancel;
	private String username, password, info;
    private Insets normalInsets = new Insets(2,2,2,2);
    private Insets biggerInsets = new Insets(10,2,2,2);
    
    private JLabel JLCreateNewUser, JLNewUsername, JLNewPassword, JLRepeatPassword;
	private JTextField JTNewUsername;
	private JPasswordField NewPassword, RepeatPassword;
	private JButton JBCreate, JBNewCancel;
	private String newUsername, newPassword, repeatPassword;
    
    private DialogNewUser DNewUser;
    private DialogWrongLogin DWrongLogin;
    private DialogMessage DMessage;
	
	public DialogLogin(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setJComponents();
		setPanelLogin();
		setPanelNewUser();
		panel = panelLogin;
        this.add(panel);		
        setResizable(false);
	}
	
	private void setDefaultProperties(){
    	this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
    	this.setUndecorated(true);
    	this.setPreferredSize(new Dimension(500,300));
    	this.pack();
    	this.setLocationRelativeTo(null);
    	
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
		
		JBLogin = new JButton("Login");
		setJButton(JBLogin);
		
		JBCancel = new JButton("Cancel");
		setJButton(JBCancel);		
	}

	private void setPanelLogin(){
		int i = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
        addC(panelLogin, JLLogin,0,i,1);i++; c.insets = biggerInsets;
        addC(panelLogin, JLUsername, 0,i,1);i++;c.insets = biggerInsets;
        addC(panelLogin,JTUsername,0,i,2);i++;
        addC(panelLogin, JLPassword,0,i,1);i++;
        addC(panelLogin, Password,0,i,2);i++; c.insets = biggerInsets;
        addC(panelLogin, JLNewUser,0,i,1);i++; c.insets = normalInsets;
        addC(panelLogin, JBLogin,0,i,1);
        addC(panelLogin, JBCancel,1,i,1);
        
        Password.addActionListener(this);
		JLNewUser.addMouseListener(this);
		JBLogin.addMouseListener(this);
		JBCancel.addMouseListener(this);
	}
	
	private void setPanelNewUser(){
		int i = 0;
		c.anchor = GridBagConstraints.NORTHWEST;	
		addC(panelNewUser, JLCreateNewUser,0,i,2);i++;  c.insets = new Insets(2,2,2,2);
		addC(panelNewUser, JLNewUsername,0,i,1); i++;
		addC(panelNewUser, JTNewUsername,0,i,2); i++; c.insets = new Insets(10,2,2,2);
		addC(panelNewUser, JLNewPassword,0,i,1); i++;
		addC(panelNewUser, NewPassword,0,i,2); i++;
		addC(panelNewUser, JLRepeatPassword,0,i,1); i++;
		addC(panelNewUser, RepeatPassword,0,i,2);i++;
		addC(panelNewUser, JBCreate,0,i,1); 
		addC(panelNewUser, JBNewCancel,1,i,1);
		
		RepeatPassword.addActionListener(this);
		JBCreate.addMouseListener(this);
		JBNewCancel.addMouseListener(this);
	}
	
	private void addC(JPanel pan, JComponent comp, int x, int y, int width){
	    	c.gridx = x;
			c.gridy = y;
			c.gridwidth = width;
			pan.add(comp, c);
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
		name.setVisible(true);		
	}

	@SuppressWarnings("deprecation")
	private String getInfo(){
    	username = JTUsername.getText();
    	password = Password.getText();
    	info = "L100#" + username + "#" + password;
    	return info;
    }
	
	private void createNewUser(){
		
		if(false){
			this.setVisible(false);
        	parent.mainFrameSetVisible();
		}
		else if (false){
			DMessage = new DialogMessage(parent, "User already exists");
			DMessage.setAlwaysOnTop(true);
		}
		else{
			DMessage = new DialogMessage(parent, "Passwords doesn't match");
			DMessage.setAlwaysOnTop(true);
		}
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JLNewUser){
			panel = panelNewUser;
			this.pack();
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
			panel = panelLogin;
			this.pack();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Password){
			login();
		}
		if (e.getSource() == RepeatPassword){
			createNewUser();
		}
		
	}
	
	public void login(){
		if (false){
			
		}
		else{
			this.setVisible(false);
			DWrongLogin = new DialogWrongLogin(parent);
		}
	}
	

}
*/
