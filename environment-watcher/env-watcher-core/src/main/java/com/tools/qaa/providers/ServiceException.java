package com.tools.qaa.providers;

public class ServiceException extends Exception {
	public ServiceException(final String message) {
		super(message);
	}

	public ServiceException(final String message, final Throwable exception) {
		super(message, exception);
	}
}

