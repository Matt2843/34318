//package gui;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JComponent;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class DialogMessage extends JDialog implements MouseListener, KeyListener{
//	
//	private static final long serialVersionUID = 1L;
//	private GridBagConstraints c = new GridBagConstraints();
//	private JPanel panel = new JPanel(new GridBagLayout());
//	private JLabel JLMessage; /*JLPasswordDoesntMatch, JLUserAlreadyExist;*/
//	private JButton JBOK, JBTryAgain, JBYes,JBNo;
//	
//	public DialogMessage(String message) {
//		setJComponents(message);		
//		this.setPreferredSize(new Dimension(400,150));
//		if (message == "Log out?"){
//			c.ipady = 50;
//			addC(JLMessage,0,0,2);
//			c.ipady = 0;
//			addC(JBYes,0,1,1);
//			addC(JBNo,1,1,1);
//		}else{
//			c.ipady = 50;
//			addC(JLMessage,0,0,1);
//			c.ipady = 0;
//			
//			if (message == "User already exists" || message == "Passwords doesn't match"){
//				addC(JBTryAgain,0,1,1);
//			} 
//				
//			else{
//				addC(JBOK,0,1,1);
//			}
//		}
//		
//		setDefaultProperties();
//		JBOK.addMouseListener(this);
//		JBTryAgain.addMouseListener(this);
//		panel.setBackground(Color.white);
//		getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
//		add(panel);
//		pack();
//		setVisible(true);
//		
//	}
//	
//	private void setDefaultProperties(){
//		setUndecorated(true);
//		pack();
//		setLocationRelativeTo(null);
//		setAlwaysOnTop(true);
//		setVisible(true);
//	}
//	
//	private void setJComponents(String msg){
//		JLMessage = new JLabel(msg);
//		setJLabel(JLMessage);
//		
//		JBOK = new JButton("OK");
//		setJButton(JBOK);
//		
//		JBTryAgain = new JButton("Try again");
//		setJButton(JBTryAgain);
//		
//		JBYes = new JButton("Yes");
//		setJButton(JBYes);
//		
//		JBNo = new JButton("No");
//		setJButton(JBNo);
//		
//		// DLogin  = new DialogLogin(); // ?????????????????????
//	}
//	
//	private void setJLabel(JLabel name){
//		name.setFont(new Font("SanSerif",Font.PLAIN,25));
//		name.setVisible(true);
//	}
//	
//	private void setJButton(JButton name){
//		name.setFont(new Font("SanSerif",Font.PLAIN,14));
//		name.setBackground(Color.white);
//		name.addMouseListener(this);
//		name.setVisible(true);
//	}
//	
//	private void addC(JComponent comp, int x, int y, int width){
//		c.gridx = x;
//		c.gridy = y;
//		c.gridwidth = width;
//		panel.add(comp,c);
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (e.getSource() == JBOK){
//			this.setVisible(false);
//		}
//		if(e.getSource() == JBTryAgain){
//			this.setVisible(false);
//		}
//		if (e.getSource()==JBYes){
//			this.setVisible(false);
//			MainFrame.client.sendMessage("L103",null);
//			GUIEngine.mainFrame.setVisible(false);
//			MainFrame.DLogin.setVisible(true);
//		}
//		if (e.getSource() == JBNo){
//			this.setVisible(false);
//		}
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_ENTER){
//			this.setVisible(false);
//		}
//		
//	}
//
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
