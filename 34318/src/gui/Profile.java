package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	public Profile() {
		setLayout(new BorderLayout());
		setPreferredSize(GeneralProperties.panelLeftaddChat);
		add(new JLabel(GeneralProperties.IEdit),BorderLayout.WEST);
		add(new JLabel(MainFrame.client.getProfile().getUsername()),BorderLayout.WEST);
//		add(new JLabel("   Username"), BorderLayout.CENTER);
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
