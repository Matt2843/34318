package gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelRight extends JPanel implements ActionListener, MouseListener {
	private JPanel panel = new JPanel(new GridBagLayout());
	private JTextArea JTChat = new JTextArea();
	
	public PanelRight(MainFrame parent){
		setDefaultProperties();
		this.add(panel);
	}
	
	private void setDefaultProperties(){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		this.setPreferredSize(GeneralProperties.panelRightSize);
		this.setVisible(true);
		this.validate();
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
