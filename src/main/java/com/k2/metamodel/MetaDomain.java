package com.k2.metamodel;

public class MetaDomain {
	
	String name;
	MetaModel metaModel;
	
	private MetaDomain(MetaModel metaModel, String name) {
		this.metaModel = metaModel;
		this.name = name;
	}

	public String getName() { return this.name; }

	public static MetaDomain instance(MetaModel metaModel, String name) {
		return new MetaDomain(metaModel, name);
	}

}
