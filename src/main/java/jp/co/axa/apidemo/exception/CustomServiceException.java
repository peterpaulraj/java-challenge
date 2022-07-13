package jp.co.axa.apidemo.exception;

/**
 * @author Peter
 */
public class CustomServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599415338714601231L;

	public CustomServiceException() {
		super();
	}

	public CustomServiceException(final String message) {
		super(message);
	}

}
