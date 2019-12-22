package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class DomainModelDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No DomainModel with name: ''{0}'' exists in the metaDomain";

	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	public DomainModelDoesNotExist() {
	}

	public DomainModelDoesNotExist(String name) {
		super(message(name));
	}

	public DomainModelDoesNotExist(Throwable cause) {
		super(cause);
	}

	public DomainModelDoesNotExist(String name, Throwable cause) {
		super(message(name), cause);
	}

	public DomainModelDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

}
