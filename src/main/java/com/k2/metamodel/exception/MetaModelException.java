package com.k2.metamodel.exception;

public class MetaModelException extends Exception {

	public MetaModelException() {
	}

	public MetaModelException(String message) {
		super(message);
	}

	public MetaModelException(Throwable cause) {
		super(cause);
	}

	public MetaModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public MetaModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
