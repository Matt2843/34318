package server.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.Server;

public class ServerWindow extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel serverStatus;
	public static JTextArea serverLog;
	public static JScrollPane pane;
	private Server server;
	
	
	public ServerWindow(String title, int w, int h) {
		configureMainWindow(title, w, h);
		addContentToMainWindow();
		pack();
		setLocationRelativeTo(null);
		startServer();
	}

	private void startServer() {
		server = new Server(1234);
		server.start();
		while(!server.isReady()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void addContentToMainWindow() {
		// Server status label
		serverStatus = new JLabel("", JLabel.CENTER);
		serverStatus.setOpaque(false);
		contentPane.add(serverStatus, BorderLayout.NORTH);
		
		// Server log 
		serverLog = new JTextArea();
		serverLog.setEditable(false);
		pane = new JScrollPane(serverLog);
		contentPane.add(pane, BorderLayout.CENTER);
	}

	private void configureMainWindow(String title, int w, int h) {
		setSize(new Dimension(w, h));
		setPreferredSize(new Dimension(w, h));
		addWindowListener(this);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		setVisible(true);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
	}
	
	public static void main(String[] args) {
		new ServerWindow("Server", 500, 500);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Server.db.updateAndSaveDatabase();
		for(int i = 0; i < Server.db.getRegisteredUsers().size(); i++) {
			System.out.println(Server.db.getRegisteredUsers().keySet());
		}
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
