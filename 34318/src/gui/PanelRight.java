package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelRight extends JPanel{
	MainFrame parent;
	private String[] Chats = {"Chat 1", "Chat 2", "Chat 3", "Chat 4"};
	private JPanel C1,C2,C3,C4,C1Tab, ChatBottom, Menu, JPSmiley,JPFile;
	private JTabbedPane TabbedPanel, TabbedPanelBottom;
	private JLabel JLSmiley, JLFile, JLClose;
	private JButton JBSend;
	private JTextArea JTText;
	private GridBagConstraints c = new GridBagConstraints();
	private int tabCounter = 0;
	
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		this.setBackground(Color.white);
		setDefaultProperties();
		setComponents();
		//createTabbedPanels();
		makeBottomPanel();
		this.add(TabbedPanel,BorderLayout.NORTH);
		//this.add(TabbedPanelBottom, BorderLayout.CENTER);
		this.add(ChatBottom,BorderLayout.CENTER);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.panelRightSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setComponents(){

		ChatBottom = new JPanel(new BorderLayout());
		ChatBottom.setPreferredSize(GeneralProperties.panelRightBottomSize);
		ChatBottom.setBackground(Color.white);
		
		Menu = new JPanel(new GridLayout(1,10));
		Menu.setBackground(Color.white);
		
//		JPSmiley = new JPanel();
//		JPSmiley.setPreferredSize(new Dimension(200,200));
//		
//		JPFile = new JPanel();
//		JPFile.setPreferredSize(new Dimension(200,200));
//		
//		TabbedPanelBottom = new JTabbedPane();
		
		TabbedPanel = new JTabbedPane();
		TabbedPanel.setPreferredSize(GeneralProperties.panelRightTopSize);
				
		JLSmiley = new JLabel(parent.ISmiley);
		JLFile = new JLabel(parent.IFile);
		
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JTText = new JTextArea();
		for (int i = 0; i < Chats.length; i++){
			addTab();
		}
	}
	
	private void createTabbedPanels(){

//		TabbedPanelBottom.addTab("",parent.IPrivate,JTText);
//		TabbedPanelBottom.addTab("",parent.ISmiley,JPSmiley);
//		TabbedPanelBottom.addTab("",parent.IFile,JPFile);
	}
	
	private void addTab(){
		final JPanel content = new JPanel();
		C1 = new JPanel();
		JLClose = new JLabel(parent.IClose);		
		JLClose.addMouseListener(new MouseListener() {
		      
			@Override
			public void mouseClicked(MouseEvent e) {
				int closeTabNumber = TabbedPanel.indexOfComponent(content);
		        TabbedPanel.removeTabAt(closeTabNumber);
				
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
		
		C1Tab = new JPanel(new BorderLayout());
		C1Tab.add(new JLabel("Chat" + (++tabCounter) + "    "),BorderLayout.WEST);
		C1Tab.add(JLClose,BorderLayout.EAST);
		TabbedPanel.addTab(null, content);
		TabbedPanel.setTabComponentAt(TabbedPanel.getTabCount() - 1, C1Tab);
	}
	
	
	private void makeBottomPanel(){
		Menu.add(JLSmiley);
		Menu.add(JLFile);
		ChatBottom.add(Menu, BorderLayout.NORTH);
		ChatBottom.add(JTText,BorderLayout.CENTER);
		ChatBottom.add(JBSend, BorderLayout.EAST);
	}
}
