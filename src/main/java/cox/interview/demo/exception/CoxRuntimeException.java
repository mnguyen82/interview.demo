/**
 * 
 */
package cox.interview.demo.exception;

/**
 * @author minhnguyen
 *
 */
public class CoxRuntimeException  extends Exception {
	/** UID */
	private static final long serialVersionUID = 4300515597719652042L;

	/**
	 * Constructs a new CoxRuntimeException with the specified detail message and
     * cause.
	 * @param message an error message
	 * @param cause {@code Throwable}}
	 */
	public CoxRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new CoxRuntimeException with the specified detail message.
	 * @param message an error message
	 */
	public CoxRuntimeException(String message) {
		super(message);
	}
}
