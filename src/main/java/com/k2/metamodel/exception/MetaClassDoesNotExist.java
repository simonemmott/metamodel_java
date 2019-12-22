package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaClassDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No MetaClass with name: ''{0}'' exists in the metaModel";

	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	public MetaClassDoesNotExist() {
	}

	public MetaClassDoesNotExist(String name) {
		super(message(name));
	}

	public MetaClassDoesNotExist(Throwable cause) {
		super(cause);
	}

	public MetaClassDoesNotExist(String name, Throwable cause) {
		super(message(name), cause);
	}

	public MetaClassDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

}
