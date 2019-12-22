package com.k2.metamodel;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

public class MetaModelTest {
	
	@Test
	public void testNewInstance() throws Exception {
		
		MetaModel metaModel = new MetaModel();
		
		assertNotNull(metaModel.metaDomains);
		assertNotNull(metaModel.metaClasses);
		assertNotNull(metaModel.metaPackages);
		
	}

}
