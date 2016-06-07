package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame implements GeneralProperties {
	private DialogLogin DLogin;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout()), JPLeft, JPRight;
	
	public MainFrame(){
		DLogin = new DialogLogin(this);
		DLogin.setAlwaysOnTop(true);
	    DLogin.setVisible(true);
	    setDefaultProperties();
	    JPLeft = new PanelLeft(this);
	    JPRight = new PanelRight(this);
	    this.add(JPLeft,BorderLayout.WEST);
	    this.add(JPRight,BorderLayout.CENTER);
	    this.validate();
	}
	
	
	private void setDefaultProperties(){	
		this.getContentPane().setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.frameSize);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void mainFrameSetVisible(){
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
