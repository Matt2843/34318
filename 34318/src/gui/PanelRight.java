package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelRight extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	MainFrame parent;
	private JPanel  ChatBottom, Menu, JPPictures, JPSmiley,JPFile;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextArea JTText;
	private int tabCounter = 0;
	private JScrollPane scrollPane;
	private String id = "Username";

	private ChatPanel chatPanel = new ChatPanel(parent);
	
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		this.setBackground(Color.white);
		setDefaultProperties();
		setComponents();
		makeBottomPanel();
		this.add(chatPanel,BorderLayout.NORTH);
		this.add(ChatBottom,BorderLayout.CENTER);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){

		ChatBottom = new JPanel(new BorderLayout());
		ChatBottom.setBackground(Color.white);
		
		Menu = new JPanel(new BorderLayout());
		Menu.setBackground(Color.white);
		

		JPPictures = new JPanel(new GridLayout(1,2));
		JPPictures.setBackground(Color.WHITE);
		
		JPSmiley = new JPanel();		
		JPFile = new JPanel();
				
		JLSmiley = new JLabel(parent.ISmiley);
		JLSmiley.setOpaque(false);
		
		JLFile = new JLabel(parent.IFile);
		JLFile.setOpaque(false);
		
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JBSend.setBackground(Color.WHITE);
		JBSend.addMouseListener(this);
		
		JTText = new JTextArea();
		JTText.addKeyListener(this);
		JTText.setLineWrap(true);
		scrollPane = new JScrollPane(JTText);
	}
	
	private void makeBottomPanel(){
		JPPictures.add(JLSmiley);
		JPPictures.add(JLFile);
		Menu.add(JPPictures, BorderLayout.WEST);
		Menu.add(new JLabel(),BorderLayout.CENTER);
		ChatBottom.add(Menu, BorderLayout.NORTH);
		ChatBottom.add(scrollPane,BorderLayout.CENTER);
		ChatBottom.add(JBSend, BorderLayout.EAST);
		
	}
	
	private void sendText(String message){
		System.out.println(message);
		JTText.setText(null);
		int selectedTab = chatPanel.getSelectedIndex();
		ChatPanel.chatTabs.get(selectedTab).append(id + ":   " +message);
//		JPanel panel = new JPanel(new BorderLayout());
//		JTextArea chat = new JTextArea(id + ":   " + message); chat.setEditable(false); chat.setLineWrap(true);
//		panel.add(chat,BorderLayout.CENTER);
//		panel.add(chatPanel.makeUsersTab("Inds�t ID"),BorderLayout.EAST);
//		chatPanel.setComponentAt(selectedTab,panel);
//		Component editTab = chatPanel.getComponentAt(selectedTab);
//		editTab.addMouseListener(new MouseListener() {
//		      
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int closeTabNumber = chatPanel.indexOfComponent(panel);
//		        chatPanel.removeTabAt(closeTabNumber);
//				
//			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		    });
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBSend){
			sendText(JTText.getText());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){			
			if (!JTText.getText().trim().equals("")){
				sendText(JTText.getText());
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
