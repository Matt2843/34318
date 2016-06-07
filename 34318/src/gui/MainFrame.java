package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame implements GeneralProperties {
	private DialogLogin DLogin;
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel panel = new JPanel(new GridBagLayout()), JPLeft, JPRight;
	public static ImageIcon IPublic, IPrivate;
	private BufferedImage BPublic, BPrivate;
	
	public MainFrame(){
		DLogin = new DialogLogin(this);
		DLogin.setAlwaysOnTop(true);
	    DLogin.setVisible(true);
	    setDefaultProperties();
	    createImageIcons();
	    JPLeft = new PanelLeft(this);
	    JPRight = new PanelRight(this);
	    this.add(JPLeft,BorderLayout.WEST);
	    this.add(JPRight,BorderLayout.CENTER);
	    this.validate();
	}
	
	
	private void setDefaultProperties(){	
		this.getContentPane().setBackground(Color.white);
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
			BPublic = ImageIO.read(new File("public.png"));
			BPrivate = ImageIO.read(new File("private.png"));
			IPublic = createResizedImageIcon(BPublic, 20, 20);
		    IPrivate = createResizedImageIcon(BPrivate, 20, 20);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
