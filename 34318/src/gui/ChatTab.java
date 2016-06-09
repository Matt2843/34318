package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChatTab extends JTextArea implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private JLabel icon,name;
	private JPanel tabContent;
	private ChatPanel parent;
	
	public ChatTab(ChatPanel parent,String tabName) {
		this.parent = parent;
		name = new JLabel(tabName);
		icon = new JLabel(MainFrame.IClose);
		tabContent = new JPanel(new BorderLayout());
		tabContent.setOpaque(false);
		tabContent.add(name, BorderLayout.WEST);
		tabContent.add(icon,BorderLayout.EAST);
		tabContent.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Der er trykket");
		
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
	
	
	
}
