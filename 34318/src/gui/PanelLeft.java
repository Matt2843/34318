package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class PanelLeft extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame parent;
	private JPanel JPPublic, JPPrivate;
	private JTabbedPane tabbedPanel;
	private String[] publicChats = {"Title 1", "Title2"}, privateChats = {"Name 1", "Name 2", "Name 3", "Name 4"};
	private JScrollPane SPPublic, SPPrivate;
	
	private ChatPanel chatPanel = new ChatPanel(parent);
	

	
	public PanelLeft(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setComponents();
		addContent();
		tabbedPanel.addTab("Public",parent.IPublic,SPPublic);
		tabbedPanel.addTab("Private", parent.IPrivate, SPPrivate);
		this.add(tabbedPanel, BorderLayout.CENTER);
		this.validate();
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){
		JPPublic = new JPanel(new GridLayout(30,1));
		SPPublic = new JScrollPane(JPPublic);
		JPPublic.setBackground(Color.LIGHT_GRAY);
		
		JPPrivate = new JPanel(new GridLayout(30,1));
		SPPrivate = new JScrollPane(JPPrivate);
		JPPrivate.setBackground(Color.red);
				
		tabbedPanel = new JTabbedPane();
		tabbedPanel.setBackground(Color.white);
		
	}
	
	private void addContent(){
		for (int i = 0; i<publicChats.length; i++){
			JLabel chat = new JLabel(publicChats[i]);
			String chatter = publicChats[i];
			chat.addMouseListener(new MouseListener() {
			      
				@Override
				public void mouseClicked(MouseEvent e) {
					chatPanel.addTab(chatter);
					System.out.println("trykket!");
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
			    });
			JPPublic.add(chat);	
		}
		for (int i = 0; i<privateChats.length;i++){
			JPPrivate.add(new JLabel(privateChats[i]));
		}
	}
}
