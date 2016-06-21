package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utility.Utilities;

public final class GeneralProperties {

	public final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final static Dimension frameSize = new Dimension((screenSize.width / 5) * 4, (screenSize.height / 4) * 3);
	public final static Dimension panelLeftSize = new Dimension((frameSize.width / 4), (frameSize.height));
	public final static Dimension panelLeftaddChat = new Dimension(panelLeftSize.width, 30);
	public final static Dimension addPublicChat = new Dimension((frameSize.width/3),frameSize.height/3);
	public final static Dimension panelRightSize = new Dimension((frameSize.width / 3) * 2, (frameSize.height));
	public final static Dimension panelRightBottomSize = new Dimension(panelRightSize.width, panelRightSize.height / 4);
	public final static Dimension panelUsersSize = new Dimension(panelRightSize.width / 4, panelRightSize.height / 4 * 3);
	public final static Dimension friendsPanelSize = new Dimension(panelRightSize.width / 3, panelRightSize.height / 4 * 3);
	public final static Dimension userInformationRightTabSize = new Dimension(130, 80);
	public final static Dimension userInformationLeftTabSize = new Dimension(130, 60);
	public final static Dimension userInformationUser = new Dimension(130, 30);
	public final static Dimension smileyTabSize = new Dimension(100, 100);
	public final static Dimension editProfileSize = new Dimension((panelLeftSize.width / 3) * 2, (panelLeftSize.height / 5*2));
	public final static Dimension friendRequestsSize = new Dimension(editProfileSize.width / 3, editProfileSize.height);
	
	
	public  static ImageIcon 	IPublic, IPrivate,IProfile, IClose, ISmiley, IFile, IAdd, IBlock, 
								IAddFriend, ISendMessage, ILogout,IEdit,IAccept,IReject,IDelete;
	
	public final static void initializeIcons() {
		try {
			IPublic = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/public.png")), 20, 20);
			IPrivate = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/private.png")), 20, 20);
			IProfile = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/profile.png")), 16, 16);
			IClose = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/close.png")), 20, 20);
			ISmiley = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/smiley.png")), 15, 15);
			IFile = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/file.png")), 20, 20);
			IAdd = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/add.png")), 12, 12);
			IBlock = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/block.png")), 12, 12);
			IAddFriend = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/addFriend.png")), 12, 12);
			ISendMessage = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/sendMessage.png")), 12, 12);
			ILogout = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/logout.png")), 12, 12);
			IEdit = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/edit.png")), 12, 12);
			IAccept = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/accept.png")), 12, 12);
			IReject = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/reject.png")), 12, 12);
			IDelete = Utilities.createResizedImageIcon(ImageIO.read(new File("pictures/delete.png")), 12, 12);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
