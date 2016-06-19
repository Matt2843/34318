package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Client;


public class MainFrame extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	
	public static Client client;
	public static Login DLogin;
	
	public static PanelLeft leftPanel;
	public static PanelRight rightPanel;
	
	public JPanel JPRight;
	
	public MainFrame(){	
		GeneralProperties.initializeIcons();
		
	    setDefaultProperties();
	    addElements(); 
	    
	    startDialogLogin();
	    startClient("localhost",1234);
//	    startClient("90.184.124.62", 1234);
	    
	    pack();
		setLocationRelativeTo(null);
	}

	private void startClient(String ip, int port) {
		client = new Client(ip, port);
		client.start();
		
	}

	private void startDialogLogin() {
		DLogin = new Login();
		DLogin.setAlwaysOnTop(true);
	    DLogin.setVisible(true);
	}

	private void addElements() {
	    rightPanel = new PanelRight();
	    leftPanel = new PanelLeft();
	    JPRight = new JPanel(new BorderLayout());
	    JPRight.setBackground(Color.white);;
	    JPRight.add(rightPanel);
	    add(leftPanel,BorderLayout.WEST);
	    add(JPRight,BorderLayout.CENTER);
		
	}

	private void setDefaultProperties(){
		setResizable(false);
		getContentPane().setBackground(Color.white);
		setTitle("Chat project");
		setIconImage(new ImageIcon("pictures/chat.png").getImage());
		setLayout(new BorderLayout());
		setPreferredSize(GeneralProperties.frameSize);
		addWindowListener(this);
	}
	
	
	
	
	
	public static void beforeClosing() {
		if(client.isRunning()) {
			for(String chatID : PanelRight.chatTabs.keySet()) {
				String[] params = {chatID};
				MainFrame.client.sendMessage("G103", params);
			}
			client.sendMessage("X999", null);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		beforeClosing();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
