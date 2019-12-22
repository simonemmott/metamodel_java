package com.k2.metamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.k2.metamodel.exception.MetaClassAlreadyExists;
import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaDomainAlreadyExists;
import com.k2.metamodel.exception.MetaDomainDoesNotExist;
import com.k2.metamodel.exception.MetaPackageDoesNotExist;

public class MetaModel {
	
	String name;
	String description;
	String version;
	Map<String,MetaDomain> metaDomains;
	Map<String,MetaClass> metaClasses;
	Map<String,MetaPackage> metaPackages;
	
	public MetaModel() {
		metaDomains = new HashMap<>();
		metaClasses = new HashMap<>();
		metaPackages = new HashMap<>();
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

	public Set<String> domainNames() {
		return metaDomains.keySet();
	}

	public Set<MetaClass> metaClasses() {
		return new HashSet<MetaClass>(metaClasses.values());
	}

	public MetaPackage metaPackage(String name) throws MetaPackageDoesNotExist {
		MetaPackage metaPackage = metaPackages.get(name);
		if (metaPackage == null) {
			throw new MetaPackageDoesNotExist(name);
		}
		return metaPackage;
	}

	public void createDomain(String name) throws MetaDomainAlreadyExists {
		if (metaDomains.containsKey(name)) { throw new MetaDomainAlreadyExists(name); }	
		MetaDomain metaDomain = MetaDomain.instance(this, name);
		metaDomains.put(name,  metaDomain);
	}

	public void add(MetaClass metaClass) throws MetaClassAlreadyExists {
		if (metaClasses.containsKey(metaClass.canonicalClassName())) { throw new MetaClassAlreadyExists(metaClass); }
		metaClasses.put(metaClass.canonicalClassName(), metaClass);
		
	}
	
	
	

}
