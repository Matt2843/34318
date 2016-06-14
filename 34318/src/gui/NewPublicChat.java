package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chat.ChatRoom;

public class NewPublicChat extends JFrame implements ActionListener, MouseListener{
	private JButton JBCreate;
	private JTextField JTName;
	private JLabel JLName;
	private PanelLeft panelLeft;
	private MainFrame mainFrame;
	
	public NewPublicChat(PanelLeft panelLeft,MainFrame mainFrame){
		this.panelLeft = panelLeft;
		this.mainFrame = mainFrame;
		setDefaultProperties();
		makeComponents();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void makeComponents() {
		JLName = new JLabel("Enter new chat name",SwingConstants.CENTER);
		JLName.setFont(new Font("SansSerif", Font.BOLD, 18));
		JTName = new JTextField();
		JTName.addActionListener(this);
		JBCreate = new JButton("Create");
		JBCreate.setBackground(Color.white);
		JBCreate.addMouseListener(this);
		JPanel middle = new JPanel(new GridLayout(2,1));
		middle.setBackground(Color.white);;
		middle.add(JLName);
		middle.add(JTName);
		this.add(middle,BorderLayout.CENTER);
		this.add(JBCreate, BorderLayout.SOUTH);
	}

	private void setDefaultProperties() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.addPublicChat);
		this.setTitle("Add new public chat");
		this.setIconImage(new ImageIcon("pictures/public.png").getImage());
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
	}
	
	private void makeNewChat(){
		String name = JTName.getText();
		String[] namea = {name};
		if(!name.equals("")){
			MainFrame.client.sendMessage("C102",namea);
			MainFrame.stall(mainFrame,"C100","C400");
			dispose();		
		}
		else{
			new DialogMessage("Enter new name");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBCreate){
			makeNewChat();
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == JTName){
			makeNewChat();		
		}
	}
}
