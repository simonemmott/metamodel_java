package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaDomainDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No MetaDomain with name: ''{0}'' exists in the metaModel";

	public MetaDomainDoesNotExist() {
	}

	public MetaDomainDoesNotExist(String name) {
		super(MessageFormat.format(MESSAGE, name));
	}

	public MetaDomainDoesNotExist(Throwable cause) {
		super(cause);
	}

	public MetaDomainDoesNotExist(String name, Throwable cause) {
		super(MessageFormat.format(MESSAGE, name), cause);
	}

	public MetaDomainDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(MessageFormat.format(MESSAGE, name), cause, enableSuppression, writableStackTrace);
	}

}
