package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaClassDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No MetaClass with name '{0}' exists in the metaModel";

	public MetaClassDoesNotExist() {
	}

	public MetaClassDoesNotExist(String name) {
		super(MessageFormat.format(MESSAGE, name));
	}

	public MetaClassDoesNotExist(Throwable cause) {
		super(cause);
	}

	public MetaClassDoesNotExist(String name, Throwable cause) {
		super(MessageFormat.format(MESSAGE, name), cause);
	}

	public MetaClassDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(MessageFormat.format(MESSAGE, name), cause, enableSuppression, writableStackTrace);
	}

}
