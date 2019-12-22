package com.k2.metamodel;

public class DomainModel {
	
	final MetaDomain metaDomain;
	final MetaClass metaClass;
	
	private DomainModel(MetaDomain metaDomain, MetaClass metaClass) {
		this.metaDomain = metaDomain;
		this.metaClass = metaClass;
	}

	public String canonicalClassName() {
		return metaClass.canonicalClassName();
	}

	public MetaClass getMetaClass() {
		return metaClass;
	}

}
