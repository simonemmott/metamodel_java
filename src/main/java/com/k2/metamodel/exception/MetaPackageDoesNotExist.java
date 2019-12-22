package com.k2.metamodel.exception;

import java.text.MessageFormat;

public class MetaPackageDoesNotExist extends MetaModelException {
	
	private static final String MESSAGE = "No MetaPackage with name: ''{0}'' exists in the metaModel";

	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	public MetaPackageDoesNotExist() {
	}

	public MetaPackageDoesNotExist(String name) {
		super(message(name));
	}

	public MetaPackageDoesNotExist(Throwable cause) {
		super(cause);
	}

	public MetaPackageDoesNotExist(String name, Throwable cause) {
		super(message(name), cause);
	}

	public MetaPackageDoesNotExist(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

}
