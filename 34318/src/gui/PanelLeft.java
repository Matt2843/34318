package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLeft extends JPanel {
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout());
	private JLabel JLPublic, JLPrivate;
	private JPanel ShownPanel;
	
	
	private PanelLeftPublic PLPublic = new PanelLeftPublic(parent);
	private PanelLeftPrivate PLPrivate = new PanelLeftPrivate(parent);
	
	public PanelLeft(MainFrame parent){
		setDefaultProperties();
		/*addC(JLPublic,0,0,1,1);
		addC(JLPrivate,1,0,1,1);
		addC(ShownPanel,0,1,1,2);
		this.add(panel);
		this.validate();*/
	}
	
	private void setDefaultProperties(){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.blue);
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
		this.validate();
	}
	
	private void setJComponents(){
		JLPublic = new JLabel("Public");
		JLPublic.setPreferredSize(GeneralProperties.panelLeftTabSize);
		setJLabel(JLPublic);
		
		JLPrivate = new JLabel("Private");
		JLPrivate.setPreferredSize(GeneralProperties.panelLeftTabSize);
		setJLabel(JLPrivate);
		
		ShownPanel = new JPanel();
		ShownPanel.setPreferredSize(GeneralProperties.panelLeftOverviewSize);
		this.setVisible(true);
	}
	
	private void setJLabel(JLabel name){
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setVisible(true);		
	}
	
	private void addC(JComponent comp, int x, int y, int height, int width) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
		panel.add(comp, c);
	}
	
	/*public PanelLeft(){
		setDefaultProperties();
		setJComponents();
		ShownPanel = PLPublic;
		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = GeneralProperties.panelLeftWidth/2;
		c.ipady = GeneralProperties.panelLeftTabSizeHeight;
		addC(JLPublic,0,0,1,1);
		addC(JLPrivate,1,0,1,1);
		c.ipadx = GeneralProperties.panelLeftWidth;
		c.ipady = GeneralProperties.panelLeftOverviewSizeHeight;
		addC(ShownPanel,0,1,1,2);
		JLPublic.addMouseListener(this);
		JLPrivate.addMouseListener(this);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(GeneralProperties.panelLeftSize);
		this.setVisible(true);
	}
	
	private void setJComponents(){
		JLPublic = new JLabel("Public");	
		setJLabel(JLPublic);
		
		JLPrivate = new JLabel("Private");
		setJLabel(JLPrivate);
		
		ShownPanel = new JPanel();
		ShownPanel.setPreferredSize(GeneralProperties.panelLeftOverviewSize);
		this.setVisible(true);
	}
	
	private void addC(JComponent comp, int x, int y, int height, int width) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		c.gridheight = height;
		this.add(comp, c);
	}
	
	private void setJLabel(JLabel name){
		name.setFont(new Font("SansSerif", Font.PLAIN, 14));
		name.setVisible(true);		
	}
	
	public void addPanel(JPanel panel){
		removePanel();
		ShownPanel = panel;
		addC(panel,0,1,1,2);
		panel.setVisible(true);
	}
	
	public void removePanel(){
		this.ShownPanel.setVisible(false);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JLPublic){
			addPanel(PLPublic);
		}
		if (e.getSource() == JLPrivate){
			addPanel(PLPrivate);
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

	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setPreferredSize(GeneralProperties.panelLeftSize);
		test.setUndecorated(true);
		test.setVisible(true);
		test.pack();
		test.setLocationRelativeTo(null);
		
		test.add(new PanelLeft());
		test.validate();
	}*/
}
