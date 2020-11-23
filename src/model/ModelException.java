package model;
//calls errors for GUI errors

@SuppressWarnings("serial")
public class ModelException extends RuntimeException {

	public ModelException(String message, Exception exception) {
		super(message, exception);
	}

	public ModelException(String message) {
		super(message);
	}

	public ModelException() {
		super();
	}

}
