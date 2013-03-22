package com.epam.cdp.jndi.exception;

public class InitContextException extends RuntimeException {

	private static final long serialVersionUID = -58246792437220980L;

	public InitContextException() {
		super();
	}

	public InitContextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InitContextException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitContextException(String message) {
		super(message);
	}

	public InitContextException(Throwable cause) {
		super(cause);
	}

}
