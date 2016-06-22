package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import chat.ChatRoom;

@SuppressWarnings("unchecked")
public class Friends extends AbstractPanelList implements MouseListener{
	private static final long serialVersionUID = 1L;
	private PanelRightUsersInChat parent;
	
	public Friends(PanelRightUsersInChat parent){
		this.parent = parent;
	}
	
	@Override
	public void setVariables() {
		model = new DefaultListModel<String>(); 
		list = new JList<String>(model);
		list.addMouseListener(this);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane(list);
		scrollPane.setBackground(Color.WHITE);
	}

	@Override
	public void addItem(Object o) {
		String copy = (String) o;
		model.addElement(copy);	
	}

	@Override
	public void setList(Object o) {
		model.removeAllElements();
		ArrayList<String> copy = (ArrayList<String>) o;
		for (int i =0; i< copy.size();i++){
			addItem(copy.get(i).toString());
		}
	}

	@Override
	public void addElements() {
		add(scrollPane,BorderLayout.CENTER);
		validate();
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
//		if (e.getSource() == list){
//			if (e.getClickCount() == 2) {				
//				ChatRoom room = ((ChatTab)MainFrame.rightPanel.getSelectedComponent()).getChatRoom();
//				String targetChatID = room.getChatID();
//				String[] params = {targetChatID, list.getModel().getElementAt(list.getSelectedIndex()).toString()};
//				MainFrame.client.sendMessage("G102",params);
//			}
//		}		
	}
}


//private void addPerson(){
//	String name = list.getSelectedValue().toString();
//	String[] namea = {name};
//	new Thread(new Runnable() {
//		public void run() {
//			MainFrame.client.sendMessage("G101", namea);
//			MainFrame.stall(MainFrame.chatPanel,"G101","G400");
//			if (MainFrame.client.getStatus().equals("G400")){
//				new DialogMessage("Failed to add person",mainFrame);
//			} 
//			bool = false;
//			frame.dispose();
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//	}).start();
//
//}



//public void setList(Object o) {
//	model.removeAllElements();
//	ArrayList<ChatRoom> copy = (ArrayList<ChatRoom>) o;
//	for (int i =0; i< copy.size();i++){
//		addUserToList(copy.get(i).toString());
//	}
//}
//
//public void addUserToList(String user) {
//	model.addElement(user);
//}
//
//
//private void addUser(){
//	String name = friendList.getSelectedValue();
//	String[] namea = {name};
//	if(!name.equals("")){
//		new Thread(new Runnable() {
//			public void run() {
//				MainFrame.client.sendMessage("G101", namea);
//				MainFrame.stall(MainFrame.chatPanel,"G101","G400");
//				if (MainFrame.client.getStatus().equals("G101")){
//					dispose();
//				} else{
//					new DialogMessage("Failed to add person",parent);
//				}
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//			}
//		}).start();
//		
//	}
//}

