package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import chat.ChatRoom;

public class FriendRequests extends AbstractPanelList implements MouseListener{	
	private static final long serialVersionUID = 1L;

	public FriendRequests() {
		ArrayList<ChatRoom> test = new ArrayList<ChatRoom>();
		test.add(new ChatRoom("NyVen"));
		test.add(new ChatRoom("NyVen2"));
		setList(test);
	}
	
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
	public void mouseClicked(MouseEvent e) {
		
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
		if (e.getSource()== list){
			if(list.getModel().getSize()>0){
				String username = list.getSelectedValue().toString();
				new Request(username);
			}			
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}





//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.util.ArrayList;
//
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.ListCellRenderer;
//
//@SuppressWarnings("unchecked")
//public class FriendRequests extends JPanel{
//	private static final long serialVersionUID = 1L;
//	
//	private FriendRequest fr[] = {new FriendRequest("Friend 1"), new FriendRequest("Friend 2")};
//	private JList frList;
//	
//	
//	public FriendRequests() {
//		setDefaultProperties();
////		setList(fr);
//		JScrollPane scrollpane = new JScrollPane(frList);
//		add(scrollpane,BorderLayout.CENTER);
//		
//		setVisible(true);
//		validate();
//	}
//
//	private void setDefaultProperties(){
//		setPreferredSize(GeneralProperties.friendRequestsSize);
//		setLayout(new BorderLayout());
//		setBackground(Color.white);
//	}
//	
//	
//
//
//	public void setList(Object o) {
//		@SuppressWarnings("unchecked")
////		ArrayList<FriendRequest> copy = (ArrayList<FriendRequest>) o;
//		FriendRequest[] copy = (FriendRequest[]) o;
//		frList = new JList(copy);
//		frList.setCellRenderer(new UserCellRenderer());
//		frList.setVisibleRowCount(copy.length);
//	}
//}
//
//class FriendRequest{
//	private final String user;
//	private ImageIcon reject = GeneralProperties.IReject, accept = GeneralProperties.IAccept;
//	
//	public FriendRequest(String user){
//		this.user = user;
//	}
//	
//	public String getUser(){
//		return user;
//	}
//	
//	public ImageIcon getImage(){
//		return reject;
//	}
//	
//	public String toString(){
//		return user;
//	}
//}
//
//class UserCellRenderer extends JLabel implements ListCellRenderer{
//	private static final long serialVersionUID = 1L;
//	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
//	
//	public UserCellRenderer(){
//		setOpaque(true);
//		setIconTextGap(12);
//	}
//
//	@Override
//	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//		FriendRequest request = (FriendRequest) value;
//		setText(request.getUser());
//		setIcon(request.getImage());
//		if (isSelected){
//			setBackground(HIGHLIGHT_COLOR);
//		    setForeground(Color.white);
//		} else {
//			setBackground(Color.white);
//		    setForeground(Color.black);
//		}
//		return this;
//	}
//}