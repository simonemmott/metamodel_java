package com.k2.metamodel;

import java.util.HashMap;
import java.util.Map;

public class MetaDomain {
	
	final String name;
	final MetaModel metaModel;
	final Map<String,DomainModel> domainModels;
	
	MetaDomain(MetaModel metaModel, String name) {
		this.metaModel = metaModel;
		this.name = name;
		this.domainModels = new HashMap<>();
	}

	public String getName() { return this.name; }

	public static MetaDomain instance(MetaModel metaModel, String name) {
		return new MetaDomain(metaModel, name);
	}

}
