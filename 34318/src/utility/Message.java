package utility;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -2531116161088241327L;
	
	private final String COMMAND;
	private final String[] PARAMS;
	private final Object OBJECT, OBJECTTWO;
	
	public Message(String COMMAND, String[] PARAMS) {
		this.COMMAND = COMMAND;
		this.PARAMS = PARAMS;
		this.OBJECT = null;
		this.OBJECTTWO = null;
	}
	
	public Message(String COMMAND, String[] PARAMS, Object OBJECT) {
		this.COMMAND = COMMAND;
		this.PARAMS = PARAMS;
		this.OBJECT = OBJECT;
		this.OBJECTTWO = null;
	}
	
	public Message(String COMMAND, String[] PARAMS, Object OBJECT, Object OBJECTTWO) {
		this.COMMAND = COMMAND;
		this.PARAMS = PARAMS;
		this.OBJECT = OBJECT;
		this.OBJECTTWO = OBJECTTWO;
	}
	
	@Override
	public String toString() {
		String r = COMMAND;
		if(PARAMS != null) {
			for(int i = 0; i < PARAMS.length; i++) {
				r += "#" + PARAMS[i];
			}
		}
		return r;
	}

	public String getCommand() {
		return COMMAND;
	}

	public String[] getParams() {
		return PARAMS;
	}

	public Object getObject() {
		return OBJECT;
	}

	public Object getObjectTwo() {
		return OBJECTTWO;
	}
	
}
