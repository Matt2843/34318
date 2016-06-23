package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import utility.FileLink;
import utility.Utilities;

public class ChatLinkListener extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private FileLink link;

	public ChatLinkListener(FileLink link) {
		this.link = link;
	}
	
	public void execute() {
		String[] params = Utilities.setParams(2, link.getFileID(), link.getFileName());
		MainFrame.client.sendMessage("F102", params);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

}
