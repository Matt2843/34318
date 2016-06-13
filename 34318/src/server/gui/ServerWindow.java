package server.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import server.Server;

public class ServerWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel serverStatus;
	public static JTextArea serverLog;
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
		contentPane.add(serverLog, BorderLayout.CENTER);
	}

	private void configureMainWindow(String title, int w, int h) {
		setSize(new Dimension(w, h));
		setPreferredSize(new Dimension(w, h));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		setVisible(true);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
	}
	
	public static void main(String[] args) {
		new ServerWindow("Server", 500, 500);
	}

}
