package gui;

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
	    /*addC(JPLeft,0,0,1,1);
	    addC(JPRight,1,0,1,1);
	    mainFrameSetVisible();*/
	}
	
	private void addC(JComponent comp, int x, int y, int height, int width) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
		this.add(comp, c);
	}
	
	private void setDefaultProperties(){	
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.white);
		this.setPreferredSize(GeneralProperties.frameSize);
	}
	
	public void mainFrameSetVisible(){
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
