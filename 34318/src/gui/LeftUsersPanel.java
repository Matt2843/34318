package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LeftUsersPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JList<UserInformation> onlineUsers;
	private DefaultListModel<UserInformation> model;
	private JScrollPane scrollPane;
	
	public LeftUsersPanel(ArrayList<UserInformation> users){
		this.setLayout(new BorderLayout());
		model = new DefaultListModel<UserInformation>();
		onlineUsers = new JList<UserInformation>(model);
		onlineUsers.addMouseListener(this);

		scrollPane = new JScrollPane(onlineUsers);
		scrollPane.setBackground(Color.WHITE);
		this.add(scrollPane, BorderLayout.CENTER);
		for (int i = 0; i<users.size();i++){
			model.addElement(users.get(i));
		}
	}   
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			MainFrame.chatPanel.addTab(onlineUsers.getSelectedValue().toString());
		  }
		
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
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == onlineUsers){
			if (e.getButton() == MouseEvent.BUTTON3){
				int x = onlineUsers.getSelectedIndex();
				onlineUsers.getModel().getElementAt(x).addJFrame(onlineUsers.getModel().getElementAt(x),MainFrame.chatPanel);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
