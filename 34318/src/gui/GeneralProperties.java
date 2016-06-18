package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

public interface GeneralProperties {

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

}
