package utility;

import java.io.Serializable;

public class Message<X, Y> implements Serializable {

	private static final long serialVersionUID = -2531116161088241327L;

	private final X string;
	private final Y object;
	
	public Message(X string) {
		this.string = string;
		object = null;
	}
	
	public Message(X string, Y object) {
		this.string = string;
		this.object = object;
	}

	public X getString() {
		return string;
	}

	public Y getObject() {
		return object;
	}
	
}
