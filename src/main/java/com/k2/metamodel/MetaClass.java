package com.k2.metamodel;

public class MetaClass {
	
	final MetaModel metaModel;
	final Class<?> cls;
	
	private MetaClass(MetaModel metaModel, Class<?> cls) {
		this.metaModel = metaModel;
		this.cls = cls;
	}
	
	public static MetaClass instance(MetaModel metaModel, Class<?> cls) {
		return new MetaClass(metaModel, cls);
	}
	
	public String canonicalClassName() {
		return cls.getCanonicalName();
	}

}
