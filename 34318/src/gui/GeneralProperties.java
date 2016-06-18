package gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class GeneralProperties {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension frameSize = new Dimension((screenSize.width / 5) * 4, (screenSize.height / 4) * 3);
	public static Dimension panelLeftSize = new Dimension((frameSize.width / 5), (frameSize.height));
	public static Dimension panelLeftaddChat = new Dimension(panelLeftSize.width, 30);
	public static Dimension addPublicChat = new Dimension(300, 120);
	public static Dimension panelRightSize = new Dimension((frameSize.width / 3) * 2, (frameSize.height));
	public static Dimension panelRightBottomSize = new Dimension(panelRightSize.width, panelRightSize.height / 4);
	public static Dimension panelUsersSize = new Dimension(panelRightSize.width / 4, panelRightSize.height / 4 * 3);
	public static Dimension friendsPanelSize = new Dimension(panelRightSize.width / 3, panelRightSize.height / 4 * 3);
	public static Dimension userInformationRightTabSize = new Dimension(130, 80);
	public static Dimension userInformationLeftTabSize = new Dimension(130, 60);
	public static Dimension smileyTabSize = new Dimension(100, 100);
	
	public static ImageIcon IPublic, IPrivate, IClose, ISmiley, IFile, IAdd, IBlock,IAddFriend, ISendMessage, ILogout;
	
	
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

}
