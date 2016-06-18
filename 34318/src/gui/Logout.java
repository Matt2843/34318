package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logout extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	public Logout() {
		setLayout(new BorderLayout());
		setPreferredSize(GeneralProperties.panelLeftaddChat);
		add(new JLabel(GeneralProperties.ILogout),BorderLayout.WEST);
		add(new JLabel(" Log out"), BorderLayout.CENTER);
		setBackground(Color.white);
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		new DialogMessage("Log out?");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
