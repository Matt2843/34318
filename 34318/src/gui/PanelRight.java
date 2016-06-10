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
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

public class PanelRight extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	//MainFrame parent;
	private JPanel  ChatBottom, Menu, JPPictures, JPSmiley,JPFile;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextArea JTText;
	private int tabCounter = 0;
	private JScrollPane scrollPane;
	private String id = "Username";
	
	
	public PanelRight(){
		this.setBackground(Color.white);
		setDefaultProperties();
		setComponents();
		makeBottomPanel();
		this.add(MainFrame.chatPanel,BorderLayout.NORTH);
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
				
		JLSmiley = new JLabel(MainFrame.ISmiley);
		JLSmiley.setOpaque(false);
		
		JLFile = new JLabel(MainFrame.IFile);
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
		JTText.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
		//SJTText.put(KeyStroke.getKeyStroke("shift ENTER"), inser);
		int selectedTab = MainFrame.chatPanel.getSelectedIndex();
		ChatPanel.chatTabs.get(selectedTab).appendToTextArea(id + ":   " +message);		
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
