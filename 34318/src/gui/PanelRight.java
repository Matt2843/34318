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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class PanelRight extends JPanel implements ActionListener, MouseListener{
	MainFrame parent;
	private String[] Chats = {"Chat 1", "Chat 2", "Chat 3", "Chat 4"};
	private JPanel ChatTab, ChatBottom, Menu, JPPictures, JPSmiley,JPFile;
	private JTabbedPane TabbedPanel, TabbedPanelBottom;
	private JLabel JLSmiley, JLFile, JLClose;
	private JButton JBSend;
	private JTextField JTText;
	private GridBagConstraints c = new GridBagConstraints();
	private int tabCounter = 0, chatCounter = 0;
	
	
	public PanelRight(MainFrame parent){
		this.parent = parent;
		this.setBackground(Color.white);
		setDefaultProperties();
		setComponents();
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
		
		Menu = new JPanel(new BorderLayout());
		Menu.setBackground(Color.white);
		

		JPPictures = new JPanel(new GridLayout(1,2));
		JPPictures.setBackground(Color.WHITE);
		
		JPSmiley = new JPanel();
		JPSmiley.setPreferredSize(new Dimension(200,200));
		
		JPFile = new JPanel();
		JPFile.setPreferredSize(new Dimension(200,200));
		
		TabbedPanelBottom = new JTabbedPane();
		
		TabbedPanel = new JTabbedPane();
		TabbedPanel.setPreferredSize(GeneralProperties.panelRightTopSize);
				
		JLSmiley = new JLabel(parent.ISmiley);
		JLSmiley.setOpaque(false);
		
		JLFile = new JLabel(parent.IFile);
		JLFile.setOpaque(false);
		
		JBSend = new JButton("Send");
		JBSend.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JBSend.setBackground(Color.WHITE);
		JBSend.addMouseListener(this);
		
		JTText = new JTextField(200);
		//JTText.addActionListener(this);
		
		for (int i = 0; i < Chats.length; i++){
			addTab();
		}
	}
	
	
	private void addTab(){
		final JTextArea content = new JTextArea();
		//JScrollPane scrollPane = new JScrollPane(content);
		content.setLineWrap(true);
		content.setText(Chats[chatCounter]);chatCounter++;
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
		
		ChatTab = new JPanel(new BorderLayout());
		ChatTab.setOpaque(false);
		ChatTab.add(new JLabel("Chat" + (++tabCounter) + "    "),BorderLayout.WEST);
		ChatTab.add(JLClose,BorderLayout.EAST);
		TabbedPanel.setBackground(Color.white);
		TabbedPanel.addTab(null, content);
		TabbedPanel.setTabComponentAt(TabbedPanel.getTabCount() - 1, ChatTab);
	}
	
	
	private void makeBottomPanel(){
		JPPictures.add(JLSmiley);
		JPPictures.add(JLFile);
		Menu.add(JPPictures, BorderLayout.WEST);
		Menu.add(new JLabel(),BorderLayout.CENTER);
		ChatBottom.add(Menu, BorderLayout.NORTH);
		ChatBottom.add(JTText,BorderLayout.CENTER);
		ChatBottom.add(JBSend, BorderLayout.EAST);
		
//		TabbedPanelBottom.addTab("",parent.IPrivate,JTText);
//		TabbedPanelBottom.addTab("",parent.ISmiley,JPSmiley);
//		TabbedPanelBottom.addTab("",parent.IFile,JPFile);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JBSend){
			JTText.setText(null);
		}
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
