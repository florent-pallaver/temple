package com.temple;

/**
 * Thrown whenever a throwable that cannot be fixed by the application is thrown.
 * 
 * @author crezik
 * @version 1.0
 */
public final class UnexpectedThrowableException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param cause - the unexpected {@link Throwable}.
	 */
	public UnexpectedThrowableException(Throwable cause) {
		super(Module.DEFAULT, cause);
	}
}
