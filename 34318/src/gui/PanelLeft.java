package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLeft extends JPanel implements ActionListener, MouseListener{
	private MainFrame parent;
	private GridBagConstraints c = new GridBagConstraints();
	private JLabel JLPublic, JLPrivate;
	private JPanel ShownPanel;
	
	
	private PanelLeftPublic PLPublic;
	private PanelLeftPrivate PLPrivate;
	
	public PanelLeft(MainFrame parent){
		this.parent = parent;
		setDefaultProperties();
		setJComponents();
		addC(JLPublic,0,0,1,1);
		addC(JLPrivate,1,0,1,1);
		addC(ShownPanel,0,1,1,2);
		
	}
	
	private void setDefaultProperties(){
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
	}
	
	private void setJComponents(){
		JLPublic = new JLabel("Public");
		setJLabel(JLPublic);
		
		JLPrivate = new JLabel("Private");
		setJLabel(JLPrivate);
		
		ShownPanel = new PanelLeftPublic(this);
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
		// TODO Auto-generated method stub
		
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
