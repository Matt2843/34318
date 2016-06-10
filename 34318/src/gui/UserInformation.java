package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class UserInformation extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	private String username;
	
	public UserInformation(String username) {
		this.username = username;
		setDefaultProperties();
	}
	
	private void setDefaultProperties(){
		this.setUndecorated(true);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		this.validate();
	}
	@Override
	public String toString() {
		return username;
	}
	
	public void fun(){
		System.out.println("fun");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
