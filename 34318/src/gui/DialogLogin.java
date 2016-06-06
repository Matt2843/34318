package gui;

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
	private JPanel panel = new JPanel(new GridBagLayout());
	private JTextField JTUsername;
	private JPasswordField Password;
	private JLabel JLLogin, JLUsername, JLPassword, JLNewUser;
	private JButton JBLogin, JBCancel;
	private String username, password, info;
    private Insets normalInsets = new Insets(2,2,2,2);
    private Insets biggerInsets = new Insets(10,2,2,2);
    
    private DialogNewUser DNewUser;
    private DialogWrongLogin DWrongLogin;
	
	public DialogLogin(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setJComponents();
		
		int i = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
        addC(JLLogin,0,i,1);i++; c.insets = biggerInsets;
        addC(JLUsername, 0,i,1);i++;c.insets = biggerInsets;
        addC(JTUsername,0,i,2);i++;
        addC(JLPassword,0,i,1);i++;
        addC(Password,0,i,2);i++; c.insets = biggerInsets;
        addC(JLNewUser,0,i,1);i++; c.insets = normalInsets;
        addC(JBLogin,0,i,1);
        addC(JBCancel,1,i,1);
        this.add(panel);
		
		Password.addActionListener(this);
		JLNewUser.addMouseListener(this);
		JBLogin.addMouseListener(this);
		JBCancel.addMouseListener(this);
		
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

	 private void addC(JComponent comp, int x, int y, int width){
	    	c.gridx = x;
			c.gridy = y;
			c.gridwidth = width;
			panel.add(comp, c);
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JLNewUser){
			this.setVisible(false);
			DNewUser = new DialogNewUser(parent);
			DNewUser.setVisible(true);
		}
		if (e.getSource() == JBLogin){
			login();
		}
		if (e.getSource() == JBCancel){
			System.exit(0);
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
