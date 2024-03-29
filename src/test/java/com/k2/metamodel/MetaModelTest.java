package com.k2.metamodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.k2.metamodel.exception.MetaClassAlreadyExists;
import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaDomainAlreadyExists;
import com.k2.metamodel.exception.MetaDomainDoesNotExist;
import com.k2.metamodel.exception.MetaModelException;
import com.k2.metamodel.exception.MetaPackageAlreadyExists;
import com.k2.metamodel.exception.MetaPackageDoesNotExist;

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
		MetaClass metaClassA = mock(MetaClass.class);
		MetaClass metaClassB = mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("metaClassA", metaClassA);
		metaModel.metaClasses.put("metaClassB", metaClassB);
		
		assertEquals(metaClassA, metaModel.metaClass("metaClassA"));
		assertEquals(metaClassB, metaModel.metaClass("metaClassB"));
		
	}
	
	@Test
	public void test_metaClass_throws_MetaClassDoesNotExist() throws MetaModelException {
		MetaClass metaClassA = mock(MetaClass.class);
		MetaClass metaClassB = mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("metaClassA", metaClassA);
		metaModel.metaClasses.put("metaClassB", metaClassB);
		
		exceptionRule.expect(MetaClassDoesNotExist.class);
		exceptionRule.expectMessage("No MetaClass with name: 'DOES_NOT_EXIST' exists in the metaModel");
		
		metaModel.metaClass("DOES_NOT_EXIST");

	}
	
	@Test
	public void test_metaClass_with_class_returns_expected_MetaClass() throws MetaModelException {
		MetaClass integer = mock(MetaClass.class);
		MetaClass string = mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("java.lang.Integer", integer);
		metaModel.metaClasses.put("java.lang.String", string);
		
		assertEquals(integer, metaModel.metaClass(Integer.class));
		assertEquals(string, metaModel.metaClass(String.class));
		
		
	}
	
	@Test
	public void test_metaDomain_returns_expected_MetaDomain() throws MetaModelException {
		MetaDomain metaDomainA = mock(MetaDomain.class);
		MetaDomain metaDomainB = mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		assertEquals(metaDomainA, metaModel.domain("metaDomainA"));
		assertEquals(metaDomainB, metaModel.domain("metaDomainB"));
		
	}
	
	@Test
	public void test_metaDomain_throws_MetaDomainDoesNotExist() throws MetaModelException {
		MetaDomain metaDomainA = mock(MetaDomain.class);
		MetaDomain metaDomainB = mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		exceptionRule.expect(MetaDomainDoesNotExist.class);
		exceptionRule.expectMessage("No MetaDomain with name: 'DOES_NOT_EXIST' exists in the metaModel");
		
		metaModel.domain("DOES_NOT_EXIST");

	}
	
	@Test
	public void test_domainNames_returns_set_of_domain_names() {
		MetaDomain metaDomainA = mock(MetaDomain.class);
		MetaDomain metaDomainB = mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		Set<String> expected = new HashSet<>(Arrays.asList("metaDomainA", "metaDomainB"));
		
		assertEquals(expected, metaModel.domainNames());
	}
	
	@Test
	public void test_metaClasses_returns_set_of_all_metaClass_instances() {
		MetaClass metaClassA = mock(MetaClass.class);
		MetaClass metaClassB = mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("metaClassA", metaClassA);
		metaModel.metaClasses.put("metaClassB", metaClassB);
		
		Set<MetaClass> expected = new HashSet<>(Arrays.asList(metaClassA, metaClassB));
		
		assertEquals(expected, metaModel.metaClasses());
		
	}
	
	@Test
	public void test_metaPackage_returns_expected_MetaPackage() throws MetaModelException {
		MetaPackage metaPackageA = mock(MetaPackage.class);
		MetaPackage metaPackageB = mock(MetaPackage.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaPackages.put("metaPackageA", metaPackageA);
		metaModel.metaPackages.put("metaPackageB", metaPackageB);
		
		assertEquals(metaPackageA, metaModel.metaPackage("metaPackageA"));
		assertEquals(metaPackageB, metaModel.metaPackage("metaPackageB"));
		
	}
	
	@Test
	public void test_metaPackage_throws_MetaPackageDoesNotExist() throws MetaModelException {
		MetaPackage metaPackageA = mock(MetaPackage.class);
		MetaPackage metaPackageB = mock(MetaPackage.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaPackages.put("metaPackageA", metaPackageA);
		metaModel.metaPackages.put("metaPackageB", metaPackageB);
		
		exceptionRule.expect(MetaPackageDoesNotExist.class);
		exceptionRule.expectMessage("No MetaPackage with name: 'DOES_NOT_EXIST' exists in the metaModel");
		
		metaModel.metaPackage("DOES_NOT_EXIST");
	}
	
	@Test
	public void test_createDomain_creates_a_new_domain_in_the_metaModel() throws MetaModelException {
		MetaModel metaModel = new MetaModel();

		metaModel.createDomain("NEW_DOMAIN");
		
		MetaDomain result = metaModel.domain("NEW_DOMAIN");
		
		assertEquals("NEW_DOMAIN", result.getName());
	}
	
	@Test
	public void test_createDomain_throws_MetaDomainAlreadyExists() throws MetaModelException {
		MetaDomain metaDomainA = mock(MetaDomain.class);
		MetaDomain metaDomainB = mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);

		exceptionRule.expect(MetaDomainAlreadyExists.class);
		exceptionRule.expectMessage("A MetaDomain with name: 'metaDomainB' already exists in the metaModel");
		
		metaModel.createDomain("metaDomainB");
		
	}
	
	@Test
	public void test_add_MetaClass_adds_MetaClass_to_metaModel() throws MetaModelException {
		MetaClass metaClass = mock(MetaClass.class);
		when(metaClass.canonicalClassName()).thenReturn("NEW_META_CLASS");
		
		MetaModel metaModel = new MetaModel();
		
		assertFalse(metaModel.metaClasses.values().contains(metaClass));
		
		metaModel.add(metaClass);
		
		assertTrue(metaModel.metaClasses.values().contains(metaClass));
			
	}

	@Test
	public void test_add_MetaClass_throws_MetaClassAlreadyExists() throws MetaModelException {
		MetaClass metaClass = mock(MetaClass.class);
		when(metaClass.canonicalClassName()).thenReturn("metaClassB");
		
		MetaClass metaClassA = mock(MetaClass.class);
		MetaClass metaClassB = mock(MetaClass.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaClasses.put("metaClassA", metaClassA);
		metaModel.metaClasses.put("metaClassB", metaClassB);
		
		exceptionRule.expect(MetaClassAlreadyExists.class);
		exceptionRule.expectMessage("A MetaClass with name: 'metaClassB' already exists in the metaModel");
		
		metaModel.add(metaClass);
		
	}

	@Test
	public void test_add_MetaDomain_adds_MetaDomain_to_metaModel() throws MetaModelException {
		MetaDomain metaDomain = mock(MetaDomain.class);
		when(metaDomain.getName()).thenReturn("NEW_META_DOMAIN");
		
		MetaModel metaModel = new MetaModel();
		
		assertFalse(metaModel.metaDomains.values().contains(metaDomain));
		
		metaModel.add(metaDomain);
		
		assertTrue(metaModel.metaDomains.values().contains(metaDomain));
			
	}

	@Test
	public void test_add_MetaDomain_throws_MetaDomainAlreadyExists() throws MetaModelException {
		MetaDomain metaDomain = mock(MetaDomain.class);
		when(metaDomain.getName()).thenReturn("metaDomainB");
		
		MetaDomain metaDomainA = mock(MetaDomain.class);
		MetaDomain metaDomainB = mock(MetaDomain.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaDomains.put("metaDomainA", metaDomainA);
		metaModel.metaDomains.put("metaDomainB", metaDomainB);
		
		exceptionRule.expect(MetaDomainAlreadyExists.class);
		exceptionRule.expectMessage("A MetaDomain with name: 'metaDomainB' already exists in the metaModel");
		
		metaModel.add(metaDomain);
		
	}

	@Test
	public void test_add_MetaPackage_adds_MetaPackage_to_metaModel() throws MetaModelException {
		MetaPackage metaPackage = mock(MetaPackage.class);
		when(metaPackage.getPackageName()).thenReturn("NEW_META_PACKAGE");
		
		MetaModel metaModel = new MetaModel();
		
		assertFalse(metaModel.metaPackages.values().contains(metaPackage));
		
		metaModel.add(metaPackage);
		
		assertTrue(metaModel.metaPackages.values().contains(metaPackage));
			
	}

	@Test
	public void test_add_MetaPackage_throws_MetaPackageAlreadyExists() throws MetaModelException {
		MetaPackage metaPackage = mock(MetaPackage.class);
		when(metaPackage.getPackageName()).thenReturn("metaPackageB");
		
		MetaPackage metaPackageA = mock(MetaPackage.class);
		MetaPackage metaPackageB = mock(MetaPackage.class);

		MetaModel metaModel = new MetaModel();
		
		metaModel.metaPackages.put("metaPackageA", metaPackageA);
		metaModel.metaPackages.put("metaPackageB", metaPackageB);
		
		exceptionRule.expect(MetaPackageAlreadyExists.class);
		exceptionRule.expectMessage("A MetaPackage with name: 'metaPackageB' already exists in the metaModel");
		
		metaModel.add(metaPackage);
		
	}

	
	

}
