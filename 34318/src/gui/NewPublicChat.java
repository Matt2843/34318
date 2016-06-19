package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("deprecation")
public class NewPublicChat extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JButton JBCreate,JBCancel;
	private JTextField JTName;
	private JLabel JLName;
	
	
	public NewPublicChat() {
		GUIEngine.mainFrame.disable();
		setDefaultProperties();
		makeComponents();		
		add(content);
		pack();
		setLocationRelativeTo(null);
	}
	
	private void setDefaultProperties() {
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setTitle("Add new public chat");
//		setIconImage(new ImageIcon("pictures/public.png").getImage());
		setUndecorated(true);
		getContentPane().setBackground(Color.white);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridBagLayout());
		setPreferredSize(GeneralProperties.addPublicChat);
		
		setAlwaysOnTop(true);
		setVisible(true);
		
	}

	private void makeComponents() {
		JLName = new JLabel("Enter new chat name",SwingConstants.CENTER);
		JLName.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		JTName = new JTextField();
		JTName.addActionListener(this);
		
		JBCreate = new JButton("Create");
		JBCreate.setBackground(Color.white);
		JBCreate.addMouseListener(this);
		
		JBCancel = new JButton("Cancel");
		JBCancel.setBackground(Color.white);
		JBCancel.addMouseListener(this);
		
		JPanel middle = new JPanel(new GridLayout(2,1));
		middle.setBackground(Color.white);;
		middle.add(JLName);
		middle.add(JTName);
		
		JPanel buttons = new JPanel(new GridLayout(1,2));
		buttons.add(JBCreate);
		buttons.add(JBCancel);
		

		content = new JPanel(new GridLayout(3,1,0,5));
		content.setBackground(Color.white);
		content.add(JLName);
		content.add(JTName);
		content.add(buttons);
	}

	
	
	private void makeNewChat(){
		String name = JTName.getText();
		String[] namea = {name};
		if(!name.equals("")){
			MainFrame.client.sendMessage("C101",namea);
//			MainFrame.stall(mainFrame,"C100","C400");
//			if (MainFrame.client.getStatus().equals("C100")){
//				MainFrame.chatPanel.addTab(name);
//			}
			GUIEngine.mainFrame.enable();
            dispose();
		}
		else{
			new Message("Enter new name");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBCreate){
			makeNewChat();
		}
		if(e.getSource() == JBCancel){
			dispose();
			GUIEngine.mainFrame.enable();
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
