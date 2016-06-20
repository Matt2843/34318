package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Request extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Point location;
	private String username;
	private JPanel accept,reject,panel;
	
	public Request(String username){
		this.username = username;
		setDefaultProperties();
		setComponents();
		add(panel,BorderLayout.CENTER);
		pack();
		setLocation(location);
	}
	
	private void setDefaultProperties(){
		setPreferredSize(GeneralProperties.userInformationLeftTabSize);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setAlwaysOnTop(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		location = MouseInfo.getPointerInfo().getLocation();
		this.addFocusListener(new FocusListener(){
			@Override
		    public void focusLost( FocusEvent e ) {
		          dispose();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		
	}
	
	private void setComponents(){
		accept = new JPanel(new BorderLayout());
		accept.addMouseListener(this);
		accept.setBackground(Color.white);
		accept.add(new JLabel(GeneralProperties.IAccept), BorderLayout.WEST);
		accept.add(new JLabel("  Accept"),BorderLayout.CENTER);
		
		reject = new JPanel(new BorderLayout());
		reject.addMouseListener(this);
		reject.setBackground(Color.white);
		reject.add(new JLabel(GeneralProperties.IReject), BorderLayout.WEST);
		reject.add(new JLabel("  Reject"),BorderLayout.CENTER);
		
		panel = new JPanel(new GridLayout(2,1));
		panel.add(reject);
		panel.add(accept);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == )
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
