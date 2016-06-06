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

public class DialogWrongLogin extends JDialog implements MouseListener {
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JLabel JLWrongLogin, JLNewUser;
	private JButton JBTryAgain, JBForgotPassword;
	
	private DialogNewUser DNewUser;
	private DialogLogin DLogin;
	
	public DialogWrongLogin(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setJComponents();
		
		int i = 0;
		c.anchor = GridBagConstraints.CENTER;
		addC(JLWrongLogin,0,i,2); i++; c.insets = new Insets(20,2,2,2);
		addC(JLNewUser,0,i,2);i++;c.insets = new Insets(2,2,2,2);
		addC(JBTryAgain,0,i,1);
		addC(JBForgotPassword,1,i,1);
				
		JLNewUser.addMouseListener(this);
		JBTryAgain.addMouseListener(this);
		JBForgotPassword.addMouseListener(this);
			
		this.add(panel);
		this.pack();
	}
	
	private void addC(JComponent comp, int x, int y, int width){
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(comp, c);
	}
	
	private void setDefaultProperties(){
		this.setUndecorated(true);
		this.setPreferredSize(new Dimension(500,200));
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void setJComponents(){
		JLWrongLogin = new JLabel("<HTML><U>Wrong login!</U></HTML>");
		setJLabel(JLWrongLogin,25);
		
		JLNewUser = new JLabel("<HTML><U>Create new user</U></HTML>");
		JLNewUser.setForeground(Color.blue);
		setJLabel(JLNewUser,14);
		
		JBTryAgain = new JButton("Try Again");
		setJButton(JBTryAgain);
		
		JBForgotPassword = new JButton("Fogot Password");
		setJButton(JBForgotPassword);
	}
	
	private void setJLabel(JLabel name, int size){
		name.setFont(new Font("SanSerif",Font.PLAIN,size));
		name.setVisible(true);
	}
	
	private void setJButton(JButton name){
		name.setFont(new Font("SanSerif",Font.PLAIN,14));
		name.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JLNewUser){
			this.setVisible(false);
			DNewUser = new DialogNewUser(parent);
		}
		if (e.getSource() == JBTryAgain){
			this.setVisible(false);
			DLogin = new DialogLogin(parent);
			DLogin.setVisible(true);
		}
		if (e.getSource() == JBForgotPassword){
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

}
