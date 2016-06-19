package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Message extends JDialog implements MouseListener{
	private static final long serialVersionUID = 1L;
	private String message;
	private JPanel content,buttons,button;
	private JLabel JLMessage;
	private JButton action, yes, no;
	
	public Message(String message){
		this.message = message;
		setDefaultProperties();
		setComponents();
		makeContent();
		add(content);
		pack();
		setLocationRelativeTo(null);
	}
	
	private void setDefaultProperties(){
		setUndecorated(true);
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.white);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(400,150));
		setVisible(true);
	}
	
	private void setComponents(){
		content = new JPanel(new BorderLayout());
		content.setBackground(Color.white);
		content.setPreferredSize(new Dimension(350,130));
		
		JLMessage = new JLabel(message,SwingConstants.CENTER);
		JLMessage.setFont(new Font("SanSerif",Font.PLAIN,25));
		
		action = new JButton("OK");
		action.setBackground(Color.white);
		action.addMouseListener(this);

		yes = new JButton("Yes");
		yes.setBackground(Color.white);
		yes.addMouseListener(this);;
		
		no = new JButton("No");
		no.setBackground(Color.white);
		no.addMouseListener(this);;
		
		buttons = new JPanel(new GridLayout(1,2));
		buttons.add(yes);
		buttons.add(no);
		
		button = new JPanel(new GridLayout(1,3));
		button.setBackground(Color.white);
		button.add(new JLabel());
		button.add(action); button.add(new JLabel());
	}
	
	private void makeContent(){
		content.add(JLMessage,BorderLayout.CENTER);
		if (message == "User already exists" || message == "Passwords doesn't match"){
			action.setText("Try again");
		} else{
			action.setText("OK");
		}
		if (message == "Log out?"){
			content.add(buttons, BorderLayout.SOUTH);
		} else{
			content.add(button, BorderLayout.SOUTH);;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == yes){
			dispose();
			MainFrame.client.sendMessage("L103",null);
			GUIEngine.mainFrame.enable();
			GUIEngine.mainFrame.setVisible(false);
			new Login();
		} else{
			dispose();
			GUIEngine.mainFrame.enable();
			GUIEngine.mainFrame.setAlwaysOnTop(true);;
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
	
//	public static void main(String[] args){
//		new Message("Log out?");
//	}

}
