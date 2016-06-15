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
public class PanelLeftPublicChats extends AbstractPanelList implements MouseListener{
	private static final long serialVersionUID = 1L;

	@Override
	public void setVariables() {
		model = new DefaultListModel<ChatRoom>();		
		list = new JList<ChatRoom>(super.model);
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
		ArrayList<ChatRoom> copy = (ArrayList<ChatRoom>) o;
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
	public void getListData() {
		new Thread(new Runnable() {
			public void run() {
				while (true){
					MainFrame.client.sendMessage("D100", null);
					MainFrame.stall(MainFrame.chatPanel,"D100");
					if (MainFrame.client.getStatus().equals("D100")){
						setList(MainFrame.client.getObject());
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			MainFrame.chatPanel.addTab(list.getSelectedValue().toString());
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
