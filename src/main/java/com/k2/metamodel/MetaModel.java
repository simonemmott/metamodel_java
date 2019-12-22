package com.k2.metamodel;

import java.util.HashMap;
import java.util.Map;

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
	

}
