package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import chat.ChatRoom;

public class ChatArea extends JPanel implements ActionListener, KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel  Menu, JPPictures;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextPane JTText;
	private JScrollPane scrollPane;
	private ChatRoom room;
	
	public ChatArea(ChatRoom room) {
		this.room = room;
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
		

		JPPictures = new JPanel(new GridLayout(1,2,5,5));
		JPPictures.setBackground(Color.WHITE);
				
		JLSmiley = new JLabel(GeneralProperties.ISmiley);
		JLSmiley.addMouseListener(this);
		JLSmiley.setOpaque(false);
		
		JLFile = new JLabel(GeneralProperties.IFile);
		JLFile.addMouseListener(this);
		JLFile.setOpaque(false);
		
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JBSend.setBackground(Color.WHITE);
		JBSend.addActionListener(this);
		
		JTText = new JTextPane();
		JTText.addKeyListener(this);
		//JTText.setLineWrap(true);
		JTText.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
		JTText.setFont(new Font(("DejaVu Sans"), Font.PLAIN,14));
		scrollPane = new JScrollPane(JTText);
	}
	
	private void makeBottomPanel(){
		JPPictures.add(JLSmiley);
		if (room.getChatID().substring(0, 2).equals("PR")){
			JPPictures.add(JLFile);
		}		
		Menu.add(JPPictures, BorderLayout.WEST);
		Menu.add(new JLabel(),BorderLayout.CENTER);
		this.add(Menu, BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(JBSend, BorderLayout.EAST);
	}
	
	private String getChatID(){
		ChatRoom room = ((ChatTab)MainFrame.rightPanel.getSelectedComponent()).getChatRoom();
		return room.getChatID();
		
	}
	
	private void sendText(String message){
		JTText.setText(null);
		String targetChatID = getChatID();
		String[] params = {targetChatID, message};
		MainFrame.client.sendMessage("S100", params);
	}
	
//	public void addSmiley(ImageIcon smiley){
//	JTText.insertIcon( smiley);
//}

	public void addSmiley(String smiley){
		JTText.setText(JTText.getText()+ smiley);
	}
	
	public void openFileWindow(){
		 JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(GUIEngine.mainFrame);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       String path = chooser.getSelectedFile().getAbsolutePath();
		       MainFrame.client.sendFile(path, getChatID(), chooser.getSelectedFile().getName());
		    }	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JLSmiley){
			new SmileyMenu(this);
		}
		if (e.getSource()==JLFile){
			openFileWindow();
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
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){			
			if (!JTText.getText().trim().equals("")){
				sendText(JTText.getText());
			}	
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JBSend){
			sendText(JTText.getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}


}
