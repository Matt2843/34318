/*package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessagePanel extends JPanel implements MouseListener, KeyListener{
	private MainFrame parent;
	private JPanel  Menu, JPPictures, JPSmiley,JPFile;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextArea JTText;
	private JScrollPane scrollPane;
	private String id = "Username";
	
	private ChatPanel chatPanel = new ChatPanel(parent);
	
	public MessagePanel(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
		makeBottomPanel();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){

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
		this.add(Menu, BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(JBSend, BorderLayout.EAST);
		
	}
	
	private void sendText(){
		String message = JTText.getText();
		System.out.println(message);
		JTText.setText(null);
		int selectedTab = chatPanel.getSelectedIndex();
		JPanel JPSelectedPanel = (JPanel) TabbedPanel.getComponentAt(selectedTab);
		JPSelectedPanel.setLayout(new GridLayout(30,1));
		JPSelectedPanel.add(new JLabel(id + ":   " + message));
		TabbedPanel.setComponentAt(selectedTab, JPSelectedPanel);
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea chat = new JTextArea(id + ":   " + message); chat.setEditable(false); chat.setLineWrap(true);
		panel.add(chat,BorderLayout.CENTER);
		panel.add(chatPanel.makeUsersTab("Inds�t ID"),BorderLayout.EAST);
		chatPanel.setComponentAt(selectedTab,panel);
		Component editTab = chatPanel.getComponentAt(selectedTab);
		editTab.addMouseListener(new MouseListener() {
		      
			@Override
			public void mouseClicked(MouseEvent e) {
				int closeTabNumber = chatPanel.indexOfComponent(panel);
		        chatPanel.removeTabAt(closeTabNumber);
				
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
		    });
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBSend){
			sendText();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER){			
			if (!JTText.getText().trim().equals("")){
				sendText();
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
*/