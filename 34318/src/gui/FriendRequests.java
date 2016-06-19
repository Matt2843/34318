package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chat.ChatRoom;

public class FriendRequests extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private JPanel list;
	private JScrollPane scrollPane;
	
	public FriendRequests() {
		setPreferredSize(GeneralProperties.friendRequestsSize);
		setLayout(new BorderLayout());
		setBackground(Color.white);
		setComponents();
		add(scrollPane,BorderLayout.CENTER);
		setVisible(true);
		validate();
	}

	public void setComponents() {		
		list = new JPanel(new GridLayout(30,1));
		scrollPane = new JScrollPane(list);
		list.addMouseListener(this);
	}

	public void addItem(Object o) {
		ChatRoom copy = (ChatRoom) o;
		JLabel reject = new JLabel(GeneralProperties.IReject); reject.addMouseListener(this);
		JLabel accept = new JLabel(GeneralProperties.IAccept); accept.addMouseListener(this);
		JPanel choice = new JPanel(new GridLayout(1,2));
		choice.add(reject);
		choice.add(accept);
		JPanel listItem = new JPanel(new BorderLayout());
		listItem.add(new JLabel(copy.toString()),BorderLayout.WEST); listItem.add(new JLabel(),BorderLayout.CENTER);
		listItem.add(choice,BorderLayout.EAST);	
		
	}

	public void setList(Object o) {
		@SuppressWarnings("unchecked")
		ArrayList<ChatRoom> copy = (ArrayList<ChatRoom>) o;
		for (int i =0; i< copy.size();i++){
			addItem(copy.get(i));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
