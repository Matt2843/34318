package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StallFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Component parent;
	
	public StallFrame(Component parent) {
		this.parent = parent;	
		configureFrameProperties();
	}
	
	private void configureFrameProperties() {
		setLayout(new BorderLayout());
		setUndecorated(true);
		JLabel stuff = new JLabel(new ImageIcon("pictures/wait.gif"));
		stuff.setOpaque(false);
		add(stuff, BorderLayout.CENTER);
		setVisible(false);
		setAlwaysOnTop(true);
		pack();
		setLocationRelativeTo(parent);
	}

	public void stall(ArrayList<String> args) {
		this.setVisible(true);
		parent.setEnabled(false);
		new Thread(new Runnable() {
			public void run() {
				int countdown = 10;
				while (!args.contains(MainFrame.client.getStatus())) {
					try {
						Thread.sleep(1000);
						countdown -= 1;
						if (countdown == 0) {
							dispose();
							break;
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (countdown > 0) {
					dispose();
				}
			}
		}).start();
	}
	
	@Override
	public void dispose() {
		parent.setEnabled(true);
		super.dispose();
	}

}
