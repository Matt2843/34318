package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Client;


public class MainFrame extends JFrame implements GeneralProperties {

	private static final long serialVersionUID = 1L;
	private DialogLogin DLogin;
	public JPanel JPLeft, JPRight;
	public static ImageIcon IPublic, IPrivate, IClose, ISmiley, IFile, IAdd, IBlock,IAddFriend, ISendMessage;
	public static ChatPanel chatPanel;
	public static Client client;
	public static JLabel pleaseWait;
	public static JDialog loading;
	
	public MainFrame(Client client){
		this.client = client;
		DLogin = new DialogLogin(this);
		DLogin.setAlwaysOnTop(true);
	    DLogin.setVisible(true);
	    setDefaultProperties();
	    createImageIcons();
	    makeLoadingJFrame();
	    chatPanel = new ChatPanel();
	    JPLeft = new PanelLeft();
	    JPRight = new PanelRight();
	    this.add(JPLeft,BorderLayout.WEST);
	    this.add(JPRight,BorderLayout.CENTER);
	    this.validate();
	}
	
	
	private void setDefaultProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setTitle("Chat project");
		this.setIconImage(new ImageIcon("pictures/chat.png").getImage());
		this.setLayout(new BorderLayout());
		this.setPreferredSize(GeneralProperties.frameSize);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void mainFrameSetVisible(){
		this.setVisible(true);
	}
	
	public void createImageIcons(){
		try {
			IPublic = createResizedImageIcon(ImageIO.read(new File("pictures/public.png")), 20, 20);
		    IPrivate = createResizedImageIcon(ImageIO.read(new File("pictures/private.png")), 20, 20);
		    IClose = createResizedImageIcon(ImageIO.read(new File("pictures/close.png")), 20, 20);
			ISmiley = createResizedImageIcon(ImageIO.read(new File("pictures/smiley.png")), 15, 15);
		    IFile = createResizedImageIcon(ImageIO.read(new File("pictures/file.png")), 20, 20);
		    IAdd = createResizedImageIcon(ImageIO.read(new File("pictures/add.png")), 12, 12);
		    IBlock = createResizedImageIcon(ImageIO.read(new File("pictures/block.png")), 12, 12);
		    IAddFriend = createResizedImageIcon(ImageIO.read(new File("pictures/addFriend.png")), 12, 12);
		    ISendMessage = createResizedImageIcon(ImageIO.read(new File("pictures/sendMessage.png")), 12, 12);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ImageIcon createResizedImageIcon(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        ImageIcon result = new ImageIcon(bi);
        return result;
    }
	
	public static boolean stall(ArrayList<String> okayFlags){
		int countdown = 200;
		loading.setVisible(true);
//		while(!okayFlags.contains(client.getStatus())) {
//		    try {
//		        Thread.sleep(50);
//		        countdown -= 1;
//		        if(countdown == 0) {
//		            return false;              
//		        }
//		    } catch (InterruptedException e) {
//		        e.printStackTrace();
//		    }
//		}
		return true;
	}
	

	
	public void makeLoadingJFrame(){
	    pleaseWait = new JLabel(new ImageIcon("pictures/wait.gif"));
	    pleaseWait.setVisible(true);
	    loading = new JDialog();
	    loading.setSize(50,50);
	    loading.setUndecorated(true);
	    loading.add(pleaseWait);
	    loading.validate();
	    loading.setAlwaysOnTop(true);
	    loading.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		//new MainFrame(new Client("192.168.1.52", 1234));
		new MainFrame(new Client("localhost", 1234));
	}
}
