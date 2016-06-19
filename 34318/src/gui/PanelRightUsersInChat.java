package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import chat.ChatRoom;

@SuppressWarnings("unchecked")
public class PanelRightUsersInChat extends AbstractPanelList implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setVariables() {
		model = new DefaultListModel<String>();		
		list = new JList<String>(model);
		scrollPane = new JScrollPane(list);
		list.addMouseListener(this);
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
			addItem(copy.get(i));
		}
	}

	@Override
	public void addElements() {
		add(scrollPane,BorderLayout.CENTER);
		validate();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO - DOUBLE CLICK, DIRECT MESSAGE AT PERSON.
		// TODO - RIGHT-CLICK, OPEN ACTION LIST.
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			int x = list.getSelectedIndex();	
			new UserInformation(list.getModel().getElementAt(x).toString(), "right");
		}
		if (e.getClickCount() == 2 && e.getSource() == list) {
			//Hvordan får jeg fat i ID?
//			String ID ="";
//			ChatRoom selectedChatRoom = (ChatRoom) message.getObject();
//			MainFrame.rightPanel.addTab(selectedChatRoom);
//			ChatRoom selectedChatRoom = new ChatRoom(list.getSelectedValue().toString(),ID);
//			MainFrame.rightPanel.addTab(selectedChatRoom);
//			
//			// Send message to server as the only thing. Params: username
//			MainFrame.client.sendMessage("", params);
		}
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

//	@Override
//	public void mouseClicked(MouseEvent e) {	
//		if (e.getClickCount() == 2 && e.getSource() == list) {
//			ChatRoom selectedChatRoom = (ChatRoom) list.getSelectedValue();
//			MainFrame.chatPanel.addTab(selectedChatRoom);
//		}
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		if(e.getButton() == MouseEvent.BUTTON3){
//			int x = list.getSelectedIndex();
//			new UserInformation(list.getModel().getElementAt(x).toString(),"right",mainFrame);		
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//	}



}
