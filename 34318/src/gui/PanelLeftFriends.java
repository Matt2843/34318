package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import utility.Utilities;

@SuppressWarnings("unchecked")
public class PanelLeftFriends extends AbstractPanelList implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setVariables() {
		model = new DefaultListModel<String>();		
		list = new JList<String>(super.model);
		scrollPane = new JScrollPane(list);
		list.addMouseListener(this);
	}

	public Object getSelectedValue() {
		return list.getSelectedValue();
	}
	
	@Override
	public void addItem(Object o) {
		String copy = (String) o;
		model.addElement(copy);		
	}
	
	public void removeItem(String s) {
		model.removeElement(s);
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
	public void emptyList() {
		if(list.getModel().getSize()>0&& !list.isSelectionEmpty()){
			int x = list.getSelectedIndex();
			new UserInformation(list.getModel().getElementAt(x).toString(), "left");
		}	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && e.getSource() == list) {
			String[] params = Utilities.setParams(1, list.getSelectedValue().toString());
			MainFrame.client.sendMessage("G101", params);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == list){
			if (e.getButton() == MouseEvent.BUTTON3){
				emptyList();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	

}
