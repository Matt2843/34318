package gui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings({"serial","rawtypes"})
public abstract class AbstractPanelList extends JPanel{
	
	public JList list;
	public DefaultListModel model;
	public JScrollPane scrollPane;
	
	public AbstractPanelList() {
		this.setLayout(new BorderLayout());
		setVariables();
		addElements();
	}
	
	public abstract void setVariables();
	public abstract void addItem(Object o);
	public abstract void setList(Object o);
	public abstract void addElements();
	public abstract void getListData();
}
