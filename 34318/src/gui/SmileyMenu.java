package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import utility.Utilities;

public class SmileyMenu extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	public static ImageIcon[] Smileys = new ImageIcon[16];
	public static JLabel[] JLSmileys = new JLabel[16];
	private int smileys = 16;
	private Point location;
	private int x,y;
	private ChatArea parent;
	
	public SmileyMenu(ChatArea parent) {
		this.parent = parent;
		createImageIcons();
		location = MouseInfo.getPointerInfo().getLocation();
		x = (int) location.getX();
		y = (int) location.getY();
		this.setLayout(new GridLayout(4,4));
		addSmileys();
		this.setLocation(x,y);
		this.setPreferredSize(GeneralProperties.smileyTabSize);
		this.addFocusListener(new FocusListener(){
			@Override
		    public void focusLost( FocusEvent e ) {
		          dispose();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		this.pack();
		this.validate();
	}
	
	public void createImageIcons(){
		try {
			Smileys[0] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s1.png")), 20, 20);
			Smileys[1] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s2.png")), 20, 20);
			Smileys[2] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s3.png")), 20, 20);
			Smileys[3] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s4.png")), 20, 20);
			Smileys[4] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s5.png")), 20, 20);
			Smileys[5] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s6.png")), 20, 20);
			Smileys[6] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s7.png")), 20, 20);
			Smileys[7] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s8.png")), 20, 20);
			Smileys[8] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s9.png")), 20, 20);
			Smileys[9] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s10.png")), 20, 20);
			Smileys[10] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s11.png")), 20, 20);
			Smileys[11] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s12.png")), 20, 20);
			Smileys[12] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s13.png")), 20, 20);
			Smileys[13] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s14.png")), 20, 20);
			Smileys[14] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s15.png")), 20, 20);
			Smileys[15] = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/s16.png")), 20, 20);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i<smileys;i++){
			JLSmileys[i] = new JLabel(Smileys[i]);
			JLSmileys[i].addMouseListener(this);
		}
		
	}

	private void addSmileys(){
		for (int i = 0; i<smileys; i++){
			this.add(JLSmileys[i]);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
//		for (int i = 0; i<smileys;i++)
//			if (e.getSource() == JLSmileys[i]){
//				parent.addSmiley(Smileys[i]);
//				dispose();
//			}
		if(e.getSource() == JLSmileys[0]){
			parent.addSmiley(":)");
			dispose();
		}
		if(e.getSource() == JLSmileys[1]){
			parent.addSmiley("8D");
			dispose();
		}
		if(e.getSource() == JLSmileys[2]){
			parent.addSmiley(":I");
			dispose();
		}
		if(e.getSource() == JLSmileys[3]){
			parent.addSmiley(";)");
			dispose();
		}
		if(e.getSource() == JLSmileys[4]){
			parent.addSmiley("<<33");
			dispose();
		}
		if(e.getSource() == JLSmileys[5]){
			parent.addSmiley(":(");
			dispose();
		}
		if(e.getSource() == JLSmileys[6]){
			parent.addSmiley(":'(");
			dispose();
		}
		if(e.getSource() == JLSmileys[7]){
			parent.addSmiley(":''(");
			dispose();
		}
		if(e.getSource() == JLSmileys[8]){
			parent.addSmiley("D:");
			dispose();
		}
		if(e.getSource() == JLSmileys[9]){
			parent.addSmiley(";P");
			dispose();
		}
		if(e.getSource() == JLSmileys[10]){
			parent.addSmiley("8I");
			dispose();
		}
		if(e.getSource() == JLSmileys[11]){
			parent.addSmiley("'I(");
			dispose();
		}
		if(e.getSource() == JLSmileys[12]){
			parent.addSmiley(":*");
			dispose();
		}
		if(e.getSource() == JLSmileys[13]){
			parent.addSmiley(":'D");
			dispose();
		}
		if(e.getSource() == JLSmileys[14]){
			parent.addSmiley(":D");
			dispose();
		}
		if(e.getSource() == JLSmileys[15]){
			parent.addSmiley(":P");
			dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
