package client;

import gui.GUIEngine;
import gui.MainFrame;
import gui.PanelLeft;
import utility.Message;

public class ClientSlave extends Thread {
	
	private Client master;
	
	public ClientSlave(Client master) {
		this.master = master;
	}
	
	public void translate(Message message) {
		switch(message.getCommand()) {
		case "L100":	// LOGIN SUCCEEDED
			System.out.println("KAPPA KAPPA");
			GUIEngine.mainFrame.setVisible(true);
			break;
		case "L400":	// LOGIN FAILED
			break;
		case "L101": 
			break;
		case "L401":
			break;
		case "L102":
			break;
		case "L402":
			break;
			
		case "F100":
			break;
		case "F400":
			break;
		case "F101":
			break;
		case "F401":
			break;
			
		case "S100":
			break;
		case "S101":
			break;
		
		case "V100":
			break;
		case "V400":
			break;
		case "V101":
			break;
		case "V401":
			break;
		case "V102":
			break;
		case "V402":
			break;
			
		case "G100":
			break;
		case "G400":
			break;
		case "G101":
			break;
		case "G401":
			break;
		case "G102":
			break;
		case "G402":
			break;
			
		case "C100": // Public Chat Creation Succeeded.
			PanelLeft.PLPublicChats.setList(message.getObject());
			break;
		case "C400": // Public Chat Creation Failed.
			break;
		case "C101":
			break;
		case "C401":
			break;	
		
		default:
			break;
		}
	}

}
