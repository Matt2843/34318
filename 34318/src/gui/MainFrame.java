package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JPanel implements GeneralProperties {
	private DialogLogin DLogin;
	private GridBagConstraints c = new GridBagConstraints();
	public MainFrame(){
		DLogin = new DialogLogin(this);
		DLogin.setAlwaysOnTop(true);
	    DLogin.setVisible(true);
	}
	
	private void addC(JComponent comp, int x, int y, int height, int width) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
		this.add(comp, c);
	}
	
	private void setDefaultProperties(){
		/*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.white);*/
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(GeneralProperties.frameSize);
	}
	
	public void mainFrameSetVisible(){
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
