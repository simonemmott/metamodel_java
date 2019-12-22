package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaDomainDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No MetaDomain with name: ''{0}'' exists in the metaModel";

	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	public MetaDomainDoesNotExist() {
	}

	public MetaDomainDoesNotExist(String name) {
		super(message(name));
	}

	public MetaDomainDoesNotExist(Throwable cause) {
		super(cause);
	}

	public MetaDomainDoesNotExist(String name, Throwable cause) {
		super(message(name), cause);
	}

	public MetaDomainDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

}
