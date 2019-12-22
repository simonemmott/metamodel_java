package com.k2.metamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.k2.metamodel.exception.DomainModelDoesNotExist;
import com.k2.metamodel.exception.MetaDomainAlreadyExists;
import com.k2.metamodel.exception.MetaDomainDoesNotExist;

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
		
		MetaDomain domain;
		try {
			domain = metaModel.domain(name);
		} catch (MetaDomainDoesNotExist err) {
			domain = new MetaDomain(metaModel, name);
			try {
				metaModel.add(domain);
			} catch (MetaDomainAlreadyExists e) {
				throw new RuntimeException("THIS SHOULD NOT HAPPEN", e);
			}
		}
		return domain;
	}

	public DomainModel model(String name) throws DomainModelDoesNotExist {
		DomainModel domainModel = domainModels.get(name);
		if (domainModel == null) { throw new DomainModelDoesNotExist(name); }
		return domainModel;
	}

	public DomainModel model(Class<?> cls) throws DomainModelDoesNotExist {
		return model(cls.getCanonicalName());
	}

	public DomainModel model(MetaClass metaClass) throws DomainModelDoesNotExist {
		return model(metaClass.canonicalClassName());
	}

	public Set<String> modelNames() {
		return domainModels.keySet();
	}

	public Set<MetaClass> metaClasses() {
		Set<MetaClass> metaClasses = new HashSet<MetaClass>();
		domainModels.values().forEach(domainModel -> {
			metaClasses.add(domainModel.getMetaClass());
		});
		return metaClasses;
	}

}
