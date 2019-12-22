package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaDomainAlreadyExists extends MetaModelException {
	
	private static final String MESSAGE = "A MetaDomain with name: ''{0}'' already exists in the metaModel";

	public MetaDomainAlreadyExists() {
	}

	public MetaDomainAlreadyExists(String name) {
		super(MessageFormat.format(MESSAGE, name));
	}

	public MetaDomainAlreadyExists(Throwable cause) {
		super(cause);
	}

	public MetaDomainAlreadyExists(String name, Throwable cause) {
		super(MessageFormat.format(MESSAGE, name), cause);
	}

	public MetaDomainAlreadyExists(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(MessageFormat.format(MESSAGE, name), cause, enableSuppression, writableStackTrace);
	}

}
