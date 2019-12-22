package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaPackageDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No MetaPackage with name: ''{0}'' exists in the metaModel";

	public MetaPackageDoesNotExist() {
	}

	public MetaPackageDoesNotExist(String name) {
		super(MessageFormat.format(MESSAGE, name));
	}

	public MetaPackageDoesNotExist(Throwable cause) {
		super(cause);
	}

	public MetaPackageDoesNotExist(String name, Throwable cause) {
		super(MessageFormat.format(MESSAGE, name), cause);
	}

	public MetaPackageDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(MessageFormat.format(MESSAGE, name), cause, enableSuppression, writableStackTrace);
	}

}
