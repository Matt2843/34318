package gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

class HintTextField extends JPasswordField implements FocusListener {
	private static final long serialVersionUID = 1L;
	
	public String hint;
	private boolean showingHint;
	private boolean isPasswordField = false;

	public HintTextField(final String hint) {
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
		setEchoChar((char) 0);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(isPasswordField) {
			setEchoChar('\u25AA');
		}
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.blue));
		//setBorder(BorderFactory.createLineBorder(Style.highlight));
		if (this.getText().isEmpty()) {
			super.setText("");
			showingHint = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(getText().equals("") && isPasswordField) {
			setEchoChar((char)0);
		}
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.black));
		if (this.getText().isEmpty()) {
			super.setText(hint);
			showingHint = true;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
		setText(hint);
	}
	
	public void makePasswordField() {
		isPasswordField = true;
	}
}