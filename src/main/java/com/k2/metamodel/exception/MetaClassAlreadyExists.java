package com.k2.metamodel.exception;

import java.text.MessageFormat;

import com.k2.metamodel.MetaClass;

public class MetaClassAlreadyExists extends MetaModelException {
	
	private static final String MESSAGE = "A MetaClass with name: ''{0}'' already exists in the metaModel";
	
	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	private static final String message(MetaClass metaClass) {
		return MessageFormat.format(MESSAGE, metaClass.canonicalClassName());
	}

	public MetaClassAlreadyExists() {
	}

	public MetaClassAlreadyExists(String name) {
		super(message(name));
	}

	public MetaClassAlreadyExists(Throwable cause) {
		super(cause);
	}

	public MetaClassAlreadyExists(String name, Throwable cause) {
		super(message(name), cause);
	}

	public MetaClassAlreadyExists(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

	public MetaClassAlreadyExists(MetaClass metaClass) {
		super(message(metaClass));
	}

	public MetaClassAlreadyExists(MetaClass metaClass, Throwable cause) {
		super(message(metaClass), cause);
	}

	public MetaClassAlreadyExists(MetaClass metaClass, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(metaClass), cause, enableSuppression, writableStackTrace);
	}

}
