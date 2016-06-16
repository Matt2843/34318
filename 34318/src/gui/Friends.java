package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Friends extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private JList<String> friendList;
	private DefaultListModel<String> model;
	private JScrollPane scrollPane;
	private MainFrame parent;
	
	public Friends(MainFrame parent){
		this.parent = parent;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.friendsPanelSize);
		model = new DefaultListModel<String>(); 
		friendList = new JList<String>(model);
		friendList.addMouseListener(this);
		scrollPane = new JScrollPane(friendList);
		scrollPane.setBackground(Color.WHITE);
		addUserToList("Friend");
		this.setTitle("Add friend");
		this.setIconImage(new ImageIcon("pictures/addFriend.png").getImage());
		this.add(scrollPane, BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();
		this.validate();
		this.setLocationRelativeTo(null);
	}
	
	public void addUserToList(String user) {
		model.addElement(user);
	}
	private void addUser(){
		String name = friendList.getSelectedValue();
		String[] namea = {name};
		if(!name.equals("")){
			new Thread(new Runnable() {
				public void run() {
					MainFrame.client.sendMessage("G101", namea);
					MainFrame.stall(MainFrame.chatPanel,"G101","G400");
					if (MainFrame.client.getStatus().equals("G101")){
						MainFrame.chatPanel.addTab(name);
						dispose();
					} else{
						new DialogMessage("Failed to add person",parent);
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {			
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == friendList){
			if (e.getClickCount() == 2) {
				addUser();
				dispose();
			}
		}		
	}
}
