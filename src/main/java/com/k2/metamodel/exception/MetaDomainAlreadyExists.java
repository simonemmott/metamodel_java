package com.k2.metamodel.exception;

import java.text.MessageFormat;

import com.k2.metamodel.MetaClass;
import com.k2.metamodel.MetaDomain;

public class MetaDomainAlreadyExists extends MetaModelException {
	
	private static final String MESSAGE = "A MetaDomain with name: ''{0}'' already exists in the metaModel";

	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	private static final String message(MetaDomain metaDomain) {
		return MessageFormat.format(MESSAGE, metaDomain.getName());
	}

	public MetaDomainAlreadyExists() {
	}

	public MetaDomainAlreadyExists(String name) {
		super(message(name));
	}

	public MetaDomainAlreadyExists(Throwable cause) {
		super(cause);
	}

	public MetaDomainAlreadyExists(String name, Throwable cause) {
		super(message(name), cause);
	}

	public MetaDomainAlreadyExists(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

	public MetaDomainAlreadyExists(MetaDomain metaDomain) {
		super(message(metaDomain));
	}

	public MetaDomainAlreadyExists(MetaDomain metaDomain, Throwable cause) {
		super(message(metaDomain), cause);
	}

	public MetaDomainAlreadyExists(MetaDomain metaDomain, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(metaDomain), cause, enableSuppression, writableStackTrace);
	}

}
