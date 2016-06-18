package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Client;


public class MainFrame extends JFrame implements GeneralProperties, WindowListener {
	private static final long serialVersionUID = 1L;
	
	public static Client client;
	public static DialogLogin DLogin;
	
	public static PanelLeft leftPanel;
	public static PanelRight rightPanel;
	
	public JPanel JPRight;
	
	public static ImageIcon IPublic, IPrivate, IClose, ISmiley, IFile, IAdd, IBlock,IAddFriend, ISendMessage, ILogout;
	
	public MainFrame(){	
	    setDefaultProperties();
	    createImageIcons();
	    addElements(); 
	    
	    startDialogLogin();
	    startClient("90.184.124.62", 1234);
	    
	    pack();
		setLocationRelativeTo(null);
	}
	
	private void startClient(String ip, int port) {
		client = new Client(ip, port);
		client.start();
		
	}

	private void startDialogLogin() {
		DLogin = new DialogLogin();
		DLogin.setAlwaysOnTop(true);
	    DLogin.setVisible(true);
	}

	private void addElements() {
	    rightPanel = new PanelRight();
	    leftPanel = new PanelLeft();
	    JPRight = new JPanel(new BorderLayout());
	    JPRight.setBackground(Color.white);;
	    JPRight.add(rightPanel);
	    add(leftPanel,BorderLayout.WEST);
	    add(JPRight,BorderLayout.CENTER);
		
	}

	private void setDefaultProperties(){
		getContentPane().setBackground(Color.white);
		setTitle("Chat project");
		setIconImage(new ImageIcon("pictures/chat.png").getImage());
		setLayout(new BorderLayout());
		setPreferredSize(GeneralProperties.frameSize);
		addWindowListener(this);
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
		    ILogout = createResizedImageIcon(ImageIO.read(new File("pictures/logout.png")), 12, 12);
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
	
	public static void beforeClosing() {
		if(client.isRunning()) {
			for(String chatID : PanelRight.chatTabs.keySet()) {
				String[] params = {chatID};
				MainFrame.client.sendMessage("G103", params);
			}
			client.sendMessage("X999", null);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		beforeClosing();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
