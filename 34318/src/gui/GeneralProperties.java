package gui;
import java.awt.Dimension;
import java.awt.Toolkit;

public interface GeneralProperties {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension frameSize = new Dimension((screenSize.width / 3)*2, (screenSize.height / 4)*3);
	public static Dimension panelLeftSize = new Dimension((frameSize.width/3), (frameSize.height));
	public static Dimension panelRightSize = new Dimension((frameSize.width/3)*2, (frameSize.height));
	public static Dimension panelRightTopSize = new Dimension(panelRightSize.width, panelRightSize.height/3*2);
	public static Dimension panelRightBottomSize = new Dimension(panelRightSize.width, panelRightSize.height/3);
	
	
}
