package com.k2.metamodel.exception;

import java.text.MessageFormat;

import com.k2.metamodel.MetaClass;
import com.k2.metamodel.MetaDomain;
import com.k2.metamodel.MetaPackage;

public class MetaPackageAlreadyExists extends MetaModelException {
	
	private static final String MESSAGE = "A MetaPackage with name: ''{0}'' already exists in the metaModel";

	private static final String message(String name) {
		return MessageFormat.format(MESSAGE, name);
	}
	
	private static final String message(MetaPackage metaPackage) {
		return MessageFormat.format(MESSAGE, metaPackage.getPackageName());
	}

	public MetaPackageAlreadyExists() {
	}

	public MetaPackageAlreadyExists(String name) {
		super(message(name));
	}

	public MetaPackageAlreadyExists(Throwable cause) {
		super(cause);
	}

	public MetaPackageAlreadyExists(String name, Throwable cause) {
		super(message(name), cause);
	}

	public MetaPackageAlreadyExists(String name, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(name), cause, enableSuppression, writableStackTrace);
	}

	public MetaPackageAlreadyExists(MetaPackage metaPackage) {
		super(message(metaPackage));
	}

	public MetaPackageAlreadyExists(MetaPackage metaPackage, Throwable cause) {
		super(message(metaPackage), cause);
	}

	public MetaPackageAlreadyExists(MetaPackage metaPackage, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message(metaPackage), cause, enableSuppression, writableStackTrace);
	}

}
