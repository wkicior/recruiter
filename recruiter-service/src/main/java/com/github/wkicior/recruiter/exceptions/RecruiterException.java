package com.github.wkicior.recruiter.exceptions;

public class RecruiterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2876104601908563723L;

	public RecruiterException() {
		super();
	}

	public RecruiterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecruiterException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecruiterException(String message) {
		super(message);
	}

	public RecruiterException(Throwable cause) {
		super(cause);
	}

}
