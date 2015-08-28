package latest;

/**
 * The ProgramException classes provides customized exception to the user.
 * 
 * @author Lydia Chandrakanthan
 * @since 2015-08-19
 */
public class ProgramException extends Exception {

	private String message = null;

	public ProgramException() {
		super();
	}

	public ProgramException(String message) {
		super(message);
		this.message = message;
	}

	public ProgramException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
