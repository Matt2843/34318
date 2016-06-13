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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

public class PanelRight extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel  ChatBottom, Menu, JPPictures;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextPane JTText;
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
		JTText.setPreferredSize(GeneralProperties.panelRightBottomSize);
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
		ChatBottom.add(Menu, BorderLayout.NORTH);
		ChatBottom.add(scrollPane,BorderLayout.CENTER);
		ChatBottom.add(JBSend, BorderLayout.EAST);
	}
	
	private void sendText(String message){
		JTText.setText(null);
		int selectedTab = MainFrame.chatPanel.getSelectedIndex();
		ChatPanel.chatTabs.get(selectedTab).appendToTextArea(id + ":   " +message);	
	}
	
	public void addSmiley(ImageIcon smiley){
		JTText.insertIcon( smiley);
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
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
	}
}
