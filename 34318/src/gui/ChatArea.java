package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

public class ChatArea extends JPanel implements MouseListener, KeyListener{
	private static final long serialVersionUID = 1L;
	private JPanel  Menu, JPPictures;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextPane JTText;
	private JScrollPane scrollPane;
	private ChatTab parent;
	
	public ChatArea(ChatTab parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
		makeBottomPanel();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.panelRightBottomSize);
		this.setBackground(Color.white);
	}
	
	private void setComponents(){
		Menu = new JPanel(new BorderLayout());
		Menu.setBackground(Color.white);
		

		JPPictures = new JPanel(new GridLayout(1,2));
		JPPictures.setBackground(Color.WHITE);
				
		JLSmiley = new JLabel(MainFrame.ISmiley);
		JLSmiley.addMouseListener(this);
		JLSmiley.setOpaque(false);
		
		JLFile = new JLabel(MainFrame.IFile);
		JLFile.setOpaque(false);
		
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JBSend.setBackground(Color.WHITE);
		JBSend.addMouseListener(this);
		
		JTText = new JTextPane();
		JTText.addKeyListener(this);
		//JTText.setLineWrap(true);
		JTText.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
		JTText.setFont(new Font(("DejaVu Sans"), Font.PLAIN,14));
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
	
	private void sendText(String message){
		JTText.setText(null);
//		String[] chat = {((ChatTab) MainFrame.chatPanel.getSelectedComponent()).getChatID(), message};
//		MainFrame.client.sendMessage("S100", chat);
//		int selectedTab = MainFrame.chatPanel.getSelectedIndex();
//		MainFrame.chatPanel.chatTabs.get(selectedTab).appendToTextArea(message);
		parent.appendToTextArea(message);
	}
	

//	public void addSmiley(ImageIcon smiley){
//		JTText.insertIcon( smiley);
//	}
	
	public void addSmiley(String smiley){
		JTText.setText(JTText.getText()+ smiley);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBSend){
			sendText(JTText.getText());
		}
		if (e.getSource() == JLSmiley){
			new SmileyMenu(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && (e.getModifiers() & InputEvent.SHIFT_MASK) != 0){
            try {
				JTText.getDocument().insertString(JTText.getDocument().getLength(), "\n", null);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
        }
		else if (e.getKeyCode() == KeyEvent.VK_ENTER){			
			if (!JTText.getText().trim().equals("")){
				sendText(JTText.getText());
			}	
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
