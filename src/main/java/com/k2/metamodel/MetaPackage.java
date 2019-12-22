package com.k2.metamodel;

public class MetaPackage {
	
	private final MetaModel metaModel;
	private final Package pkg;
	private MetaPackage parentMetaPackage;
	
	private MetaPackage(MetaModel metaModel, Package pkg) {
		this.metaModel = metaModel;
		this.pkg = pkg;
	}
	
	public static MetaPackage instance(MetaModel metaModel, Package pkg) {
		return new MetaPackage(metaModel, pkg);
	}

	public String getPackageName() {
		return pkg.getName();
	}

}
