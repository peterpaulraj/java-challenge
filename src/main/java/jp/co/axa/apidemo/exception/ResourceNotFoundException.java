package jp.co.axa.apidemo.exception;

/**
 * @author Peter
 */
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3444074908825303089L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}
