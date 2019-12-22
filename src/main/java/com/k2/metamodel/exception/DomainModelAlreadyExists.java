package com.k2.metamodel.exception;

import java.text.MessageFormat;

import com.k2.metamodel.DomainModel;
import com.k2.metamodel.MetaClass;

public class DomainModelAlreadyExists extends MetaModelException {
	
	private static final String MESSAGE = "A DomainModel with name: ''{0}'' already exists in the metaDomain";
	
	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	private static final String message(DomainModel domainModel) {
		return MessageFormat.format(MESSAGE, domainModel.canonicalClassName());
	}

	public DomainModelAlreadyExists() {
	}

	public DomainModelAlreadyExists(String name) {
		super(message(name));
	}

	public DomainModelAlreadyExists(Throwable cause) {
		super(cause);
	}

	public DomainModelAlreadyExists(String name, Throwable cause) {
		super(message(name), cause);
	}

	public DomainModelAlreadyExists(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

	public DomainModelAlreadyExists(DomainModel domainModel) {
		super(message(domainModel));
	}

	public DomainModelAlreadyExists(DomainModel domainModel, Throwable cause) {
		super(message(domainModel), cause);
	}

	public DomainModelAlreadyExists(DomainModel domainModel, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(domainModel), cause, enableSuppression, writableStackTrace);
	}

}
