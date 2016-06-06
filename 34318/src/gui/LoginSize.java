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
