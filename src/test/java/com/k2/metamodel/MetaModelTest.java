package com.k2.metamodel;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaDomainDoesNotExist;
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
		exceptionRule.expectMessage("No MetaClass with name: 'DOES_NOT_EXIST' exists in the metaModel");
		
		metaModel.metaClass("DOES_NOT_EXIST");

	}
	
	@Test
	public void test_metaClass_with_class_returns_expected_MetaClass() throws MetaModelException {
		MetaClass integer = Mockito.mock(MetaClass.class);
		MetaClass string = Mockito.mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("java.lang.Integer", integer);
		metaModel.metaClasses.put("java.lang.String", string);
		
		assertEquals(integer, metaModel.metaClass(Integer.class));
		assertEquals(string, metaModel.metaClass(String.class));
		
		
	}
	
	@Test
	public void test_metaDomain_returns_expected_MetaDomain() throws MetaModelException {
		MetaDomain metaDomainA = Mockito.mock(MetaDomain.class);
		MetaDomain metaDomainB = Mockito.mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		assertEquals(metaDomainA, metaModel.domain("metaDomainA"));
		assertEquals(metaDomainB, metaModel.domain("metaDomainB"));
		
	}
	
	@Test
	public void test_metaDomain_throws_MetaDomainDoesNotExist() throws MetaModelException {
		MetaDomain metaDomainA = Mockito.mock(MetaDomain.class);
		MetaDomain metaDomainB = Mockito.mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		exceptionRule.expect(MetaDomainDoesNotExist.class);
		exceptionRule.expectMessage("No MetaDomain with name: 'DOES_NOT_EXIST' exists in the metaModel");
		
		metaModel.domain("DOES_NOT_EXIST");

	}
	
	@Test
	public void test_domainNames_returns_set_of_domain_names() {
		MetaDomain metaDomainA = Mockito.mock(MetaDomain.class);
		MetaDomain metaDomainB = Mockito.mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		Set<String> expected = new HashSet<String>(Arrays.asList("metaDomainA", "metaDomainB"));
		
		assertEquals(expected, metaModel.domainNames());
	}
	
	

}
