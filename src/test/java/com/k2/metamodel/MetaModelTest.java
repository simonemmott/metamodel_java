package com.k2.metamodel;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaModelException;

@RunWith(MockitoJUnitRunner.class)
public class MetaModelTest {
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void testNewInstance() throws Exception {
		
		MetaModel metaModel = new MetaModel();
		
		assertNotNull(metaModel.metaDomains);
		assertNotNull(metaModel.metaClasses);
		assertNotNull(metaModel.metaPackages);
		
	}
	
	@Test
	public void test_metaClass_returns_expected_MetaClass() throws MetaModelException {
		MetaClass metaClassA = Mockito.mock(MetaClass.class);
		MetaClass metaClassB = Mockito.mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("metaClassA", metaClassA);
		metaModel.metaClasses.put("metaClassB", metaClassB);
		
		assertEquals(metaClassA, metaModel.metaClass("metaClassA"));
		assertEquals(metaClassB, metaModel.metaClass("metaClassB"));
		
	}
	
	@Test
	public void test_metaClass_throws_MetaClassDoesNotExist() throws MetaModelException {
		MetaClass metaClassA = Mockito.mock(MetaClass.class);
		MetaClass metaClassB = Mockito.mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("metaClassA", metaClassA);
		metaModel.metaClasses.put("metaClassB", metaClassB);
		
		exceptionRule.expect(MetaClassDoesNotExist.class);
		
		metaModel.metaClass("DOES_NOT_EXIST");

	}
	
	

}
