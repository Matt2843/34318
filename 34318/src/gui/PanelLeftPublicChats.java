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
public class PanelLeftPublicChats extends AbstractPanelList implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<ChatRoom> chats = new ArrayList<ChatRoom>();

	@Override
	public void setVariables() {
		model = new DefaultListModel<ChatRoom>();		
		list = new JList<ChatRoom>(model);
		scrollPane = new JScrollPane(list);
		list.addMouseListener(this);
	}

	@Override
	public void addItem(Object o) {
		ChatRoom copy = (ChatRoom) o;
		model.addElement(copy);		
	}

	@Override
	public void setList(Object o) {
		model.removeAllElements();
		ArrayList<ChatRoom> copy = (ArrayList<ChatRoom>) o;
		chats = copy;
		for (int i =0; i< copy.size();i++){
			addItem(copy.get(i));
		}
	}
	
	public ArrayList<ChatRoom> getList(){
		return chats;
	}

	@Override
	public void addElements() {
		add(scrollPane,BorderLayout.CENTER);
		validate();
	}
	
	@Override
	public void emptyList() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getSource() == list) {
			ChatRoom selectedChatRoom = (ChatRoom) list.getSelectedValue();
			String[] params = {selectedChatRoom.getChatID()};
			MainFrame.rightPanel.addTab(selectedChatRoom);
			if(!((ChatTab)MainFrame.rightPanel.getSelectedComponent()).isOpen()) {
				MainFrame.client.sendMessage("G100", params);
			}
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
	
}
