package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogMessage extends JDialog implements MouseListener{
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JLabel JLMessage; /*JLPasswordDoesntMatch, JLUserAlreadyExist;*/
	private JButton JBOK, JBTryAgain;
	
	public DialogMessage(MainFrame parent, String message){
		this.parent = parent;
		setJComponents(message);
		
		this.setPreferredSize(new Dimension(400,150));
		c.ipady = 50;
		addC(JLMessage,0,0,1);
		c.ipady = 0;
		
		if (message == "User already exists"){
			addC(JBTryAgain,0,1,1);
		}
		if (message == "Passwords doesn't match"){
			addC(JBTryAgain,0,1,1);
		}
		else{
			addC(JBOK,0,1,1);
		}
		setDefaultProperties();
		JBOK.addMouseListener(this);
		JBTryAgain.addMouseListener(this);
		this.add(panel);
		this.pack();
		this.setVisible(true);
		
	}
	
	private void setDefaultProperties(){
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	private void setJComponents(String msg){
		JLMessage = new JLabel(msg);
		setJLabel(JLMessage);
		
		JBOK = new JButton("OK");
		setJButton(JBOK);
		
		JBTryAgain = new JButton("Try again");
		setJButton(JBTryAgain);
	}
	
	private void setJLabel(JLabel name){
		name.setFont(new Font("SanSerif",Font.PLAIN,25));
		name.setVisible(true);
	}
	
	private void setJButton(JButton name){
		name.setFont(new Font("SanSerif",Font.PLAIN,14));
		name.addMouseListener(this);
		name.setVisible(true);
	}
	
	private void addC(JComponent comp, int x, int y, int width){
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(comp,c);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBOK){
			this.setVisible(false);
		}
		if(e.getSource() == JBTryAgain){
			this.setVisible(false);
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
