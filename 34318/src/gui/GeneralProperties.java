package gui;
import java.awt.Dimension;
import java.awt.Toolkit;

public interface GeneralProperties {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension frameSize = new Dimension((screenSize.width / 3)*2, (screenSize.height / 4)*3);
	public static Dimension panelLeftSize = new Dimension((frameSize.width/3), (frameSize.height));
	public static Dimension panelRightSize = new Dimension((frameSize.width/3)*2, (frameSize.height));
	public static Dimension panelLeftOverviewSize = new Dimension((panelLeftSize.width),(panelLeftSize.height/20)*19);
	public static Dimension panelLeftTabSize = new Dimension((panelLeftSize.width)/2, panelLeftSize.height/10);
	public static Dimension panelLeftTabPanelSize = new Dimension((panelLeftSize.width), panelLeftSize.height/20);
	
	
	public static int panelLeftTabSizeHeight = panelLeftSize.height/10;
	public static int panelLeftOverviewSizeHeight = panelLeftSize.height/10*9;
	public static int panelLeftWidth = panelLeftSize.width;
	
	
}
