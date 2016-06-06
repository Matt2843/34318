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

public class DialogNewUser extends JDialog implements ActionListener, MouseListener{
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JLabel JLCreateNewUser, JLUsername, JLPassword, JLRepeatPassword;
	private JTextField JTUsername;
	private JPasswordField Password, RepeatPassword;
	private JButton JBCreate, JBCancel;
	private String username, password, repeatPassword;
	
	private DialogLogin DLogin;
	private DialogMessage DMessage;
	
	public DialogNewUser(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setJComponents();
		
		int i = 0;
		c.anchor = GridBagConstraints.NORTHWEST;	
		addC(JLCreateNewUser,0,i,1);i++;  c.insets = new Insets(2,2,2,2);
		addC(JLUsername,0,i,1); i++;
		addC(JTUsername,0,i,2); i++; c.insets = new Insets(10,2,2,2);
		addC(JLPassword,0,i,1); i++;
		addC(Password,0,i,2); i++;
		addC(JLRepeatPassword,0,i,1); i++;
		addC(RepeatPassword,0,i,2);i++;
		addC(JBCreate,0,i,1); 
		addC(JBCancel,1,i,1);
		
		RepeatPassword.addActionListener(this);
		JBCreate.addMouseListener(this);
		JBCancel.addMouseListener(this);
		
		this.add(panel);
	}
	
	private void setDefaultProperties() {
		this.setUndecorated(true);
		this.setPreferredSize(new Dimension(500,300));
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void setJComponents(){
		JLCreateNewUser = new JLabel("<HTML><U>Create new user</U></HTML>");
		setJLabel(JLCreateNewUser,25);
		
		JLUsername = new JLabel("Username");
		setJLabel(JLUsername,14);
		
		JLPassword = new JLabel("Password");
		setJLabel(JLPassword,14);
		
		JLRepeatPassword = new JLabel("Repeat Password");
		setJLabel(JLRepeatPassword,14);
		
		JTUsername = new JTextField(20);
		setJTextField(JTUsername);
		
		Password = new JPasswordField(20);
		setJTextField(Password);
		
		RepeatPassword = new JPasswordField(20);
		setJTextField(RepeatPassword);
		
		JBCreate = new JButton("Create");
		setJButton(JBCreate);
		
		JBCancel = new JButton("Cancel");
		setJButton(JBCancel);
	}
	
	private void addC(JComponent comp, int x, int y, int width){
    	c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(comp, c);
		panel.validate();
    }

	private void setJTextField(JTextField name) {
		name.setFont(new Font("SansSerif", Font.ITALIC, 14));
		name.setVisible(true);
	}

	private void setJLabel(JLabel name, int size) {
		name.setFont(new Font("SansSerif", Font.PLAIN, size));
		name.setVisible(true);
	}

	private void setJButton(JButton name) {
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setVisible(true);
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
		if (e.getSource() == JBCreate){
			createNewUser();
		}
		if ( e.getSource() == JBCancel){
			this.setVisible(false);
			DLogin = new DialogLogin(parent);
			DLogin.setVisible(true);
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
		if (e.getSource() == RepeatPassword){
			createNewUser();
		}
		
	}
}
