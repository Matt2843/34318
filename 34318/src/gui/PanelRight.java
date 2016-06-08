package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
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
	private JPanel C1,C2,C3,C4, ChatBottom, Menu, JPSmiley,JPFile;
	private JTabbedPane TabbedPanel, TabbedPanelBottom;
	private JLabel JLSmiley, JLFile;
	private JButton JBSend;
	private JTextArea JTText;
	
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		this.setBackground(Color.white);
		setDefaultProperties();
		setComponents();
		createTabbedPanels();
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
		C1 = new JPanel();
		C2 = new JPanel();
		C3 = new JPanel();
		C4 = new JPanel();
		
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
				
		JLSmiley = new JLabel(parent.ISmiley, SwingConstants.LEFT);
		JLFile = new JLabel(parent.IFile,SwingConstants.LEFT);
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JTText = new JTextArea(5,10);
		System.out.println(GeneralProperties.panelRightBottomSize);
	}
	
	private void createTabbedPanels(){
		TabbedPanel.addTab("Chat1",parent.IClose, C1);
		TabbedPanel.addTab("Chat2",C2);
		TabbedPanel.addTab("Chat3",C3);
		TabbedPanel.addTab("Chat4",C4);
		
//		TabbedPanelBottom.addTab("",parent.IPrivate,JTText);
//		TabbedPanelBottom.addTab("",parent.ISmiley,JPSmiley);
//		TabbedPanelBottom.addTab("",parent.IFile,JPFile);
	}
	
	
	private void makeBottomPanel(){
		Menu.add(JLSmiley);
		Menu.add(JLFile);
		ChatBottom.add(Menu, BorderLayout.NORTH);
		ChatBottom.add(JTText,BorderLayout.CENTER);
		ChatBottom.add(JBSend, BorderLayout.EAST);
	}
}
