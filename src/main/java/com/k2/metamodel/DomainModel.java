package com.k2.metamodel;

import com.k2.metamodel.exception.DomainModelAlreadyExists;
import com.k2.metamodel.exception.DomainModelDoesNotExist;

public class DomainModel {
	
	final MetaDomain metaDomain;
	final MetaClass metaClass;
	
	DomainModel(MetaDomain metaDomain, MetaClass metaClass) {
		this.metaDomain = metaDomain;
		this.metaClass = metaClass;
	}

	public MetaDomain getMetaDomain() {
		return metaDomain;
	}

	public MetaClass getMetaClass() {
		return metaClass;
	}
	
	public String canonicalClassName() {
		return metaClass.canonicalClassName();
	}

	public static DomainModel instance(MetaDomain metaDomain, MetaClass metaClass) {
		DomainModel domainModel;
		try {
			domainModel = metaDomain.model(metaClass);
		} catch (DomainModelDoesNotExist err) {
			domainModel = new DomainModel(metaDomain, metaClass);
			try {
				metaDomain.add(domainModel);
			} catch (DomainModelAlreadyExists e) {
				throw new RuntimeException("THIS SHOULD NOT HAPPEN", e);
			}
		}
		return domainModel;
	}

}
