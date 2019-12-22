package com.k2.metamodel;

import java.util.HashMap;
import java.util.Map;

import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaDomainDoesNotExist;

public class MetaModel {
	
	String name;
	String description;
	String version;
	Map<String,MetaDomain> metaDomains;
	Map<String,MetaClass> metaClasses;
	Map<String,MetaPackage> metaPackages;
	
	public MetaModel() {
		metaDomains = new HashMap<String, MetaDomain>();
		metaClasses = new HashMap<String, MetaClass>();
		metaPackages = new HashMap<String, MetaPackage>();
	}

	public String getName() { return this.name; }
	public MetaModel setName(String name) { this.name = name; return this; }
	
	public String getDescription() { return this.description; }
	public MetaModel setDescription(String description) { this.description = description; return this; }
	
	public String getVersion() { return this.version; }
	public MetaModel setVersion(String version) { this.version = version; return this; }

	public MetaClass metaClass(String name) throws MetaClassDoesNotExist {
		MetaClass metaClass = metaClasses.get(name);
		if (metaClass == null) {
			throw new MetaClassDoesNotExist(name);
		}
		return metaClass;
	}

	public MetaClass metaClass(Class<?> cls) throws MetaClassDoesNotExist {
		
		return metaClass(cls.getCanonicalName());
	}

	public MetaDomain domain(String name) throws MetaDomainDoesNotExist {
		MetaDomain metaDomain = metaDomains.get(name);
		if (metaDomain == null) {
			throw new MetaDomainDoesNotExist(name);
		}
		return metaDomain;
	}
	
	
	

}
