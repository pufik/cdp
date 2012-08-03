package com.epam.cdp.concurrency.exception;

public class SystemException extends Exception {

	private static final long serialVersionUID = -933675090169347959L;

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}
