package com.k2.metamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.k2.metamodel.exception.MetaClassAlreadyExists;
import com.k2.metamodel.exception.MetaClassDoesNotExist;

public class MetaClass {
	
	final MetaModel metaModel;
	final Class<?> cls;
	final Map<String,DomainModel> domainModels;
	MetaPackage metaPackage;
	MetaClass superMetaClass;
	final Set<MetaClass> subMetaClasses;
	final Set<MetaField> declaredMetaFields;
	final Set<MetaMethod> declaredMetaMethods;
	boolean scanned = false;
	
	
	
	MetaClass(MetaModel metaModel, Class<?> cls) {
		this.metaModel = metaModel;
		this.cls = cls;
		this.domainModels = new HashMap<>();
		this.subMetaClasses = new HashSet<>();
		this.declaredMetaFields = new HashSet<>();
		this.declaredMetaMethods = new HashSet<>();
	}
	
	public static MetaClass instance(MetaModel metaModel, Class<?> cls) {
		MetaClass metaClass;
		try {
			metaClass = metaModel.metaClass(cls);
		} catch (MetaClassDoesNotExist err) {
			metaClass = new MetaClass(metaModel, cls);
			try {
				metaModel.add(metaClass);
			} catch (MetaClassAlreadyExists e) {
				throw new RuntimeException("THIS SHOULD NOT HAPPEN", e);
			}
		}
		
		return metaClass;
	}
	
	public String canonicalClassName() {
		return cls.getCanonicalName();
	}

	public Class<?> getModelledClass() {
		return cls;
	}

}
