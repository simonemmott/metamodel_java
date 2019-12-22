package com.k2.metamodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

import com.k2.metamodel.exception.DomainModelDoesNotExist;
import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaDomainDoesNotExist;
import com.k2.metamodel.exception.MetaModelException;
import com.k2.test.utils.PrivateFieldAccessor;

@RunWith(MockitoJUnitRunner.class)
public class MetaDomainTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	private static PrivateFieldAccessor<MetaDomain,MetaModel> metaModelField = new PrivateFieldAccessor<>(MetaDomain.class, "metaModel", MetaModel.class);
	private static PrivateFieldAccessor<MetaDomain,String> nameField = new PrivateFieldAccessor<>(MetaDomain.class, "name", String.class);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PrivateFieldAccessor<MetaDomain,Map<String,DomainModel>> domainModelsField = new PrivateFieldAccessor(MetaDomain.class, "domainModels", Map.class);
	
	@Test
	public void testNewInstance() throws Exception {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		
		assertEquals(metaModel, metaModelField.get(metaDomain));
		assertEquals("META_DOMAIN", nameField.get(metaDomain));
		assertNotNull(domainModelsField.get(metaDomain));
		
	}
	
	@Test
	public void test_instance_new_domain() throws MetaModelException {
		MetaModel metaModel = mock(MetaModel.class);
		when(metaModel.domain("NEW_DOMAIN")).thenThrow(MetaDomainDoesNotExist.class);
		
		MetaDomain newDomain = MetaDomain.instance(metaModel, "NEW_DOMAIN");
		
		verify(metaModel, times(1)).domain("NEW_DOMAIN");
		
		ArgumentCaptor<MetaDomain> metaDomainCaptor = ArgumentCaptor.forClass(MetaDomain.class);
		verify(metaModel, times(1)).add(metaDomainCaptor.capture());
		
		assertEquals(newDomain, metaDomainCaptor.getValue());
		
	}
	
	@Test
	public void test_instance_existing_domain() throws MetaModelException {
		MetaDomain metaDomain = mock(MetaDomain.class);
		
		MetaModel metaModel = mock(MetaModel.class);
		when(metaModel.domain("EXISTING_DOMAIN")).thenReturn(metaDomain);
		
		MetaDomain newDomain = MetaDomain.instance(metaModel, "EXISTING_DOMAIN");
		
		verify(metaModel, times(1)).domain("EXISTING_DOMAIN");
		
		assertEquals(newDomain, metaDomain);
	}
	
	@Test
	public void test_model_with_String_returns_expected_DomainModel() throws MetaModelException {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		DomainModel domainModel = mock(DomainModel.class);
		domainModelsField.get(metaDomain).put("DOMAIN_MODEL", domainModel);
		
		DomainModel result = metaDomain.model("DOMAIN_MODEL");
		
		assertEquals(domainModel, result);
		
	}
	
	@Test
	public void test_model_with_String_throws_DomainModelDoesNotExist() throws MetaModelException {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		
		exceptionRule.expect(DomainModelDoesNotExist.class);
		exceptionRule.expectMessage("No DomainModel with name: 'DOES_NOT_EXIST' exists in the metaDomain");
		
		metaDomain.model("DOES_NOT_EXIST");
		
	}
	
	@Test
	public void test_model_with_Class_returns_expected_DomainModel() throws MetaModelException {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		DomainModel integerDomainModel = mock(DomainModel.class);
		domainModelsField.get(metaDomain).put("java.lang.Integer", integerDomainModel);
		
		assertEquals(integerDomainModel, metaDomain.model(Integer.class));
		
	}
	
	@Test
	public void test_model_with_MetaClass_returns_expected_DomainModel() throws MetaModelException {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		DomainModel mockDomainModel = mock(DomainModel.class);
		domainModelsField.get(metaDomain).put("MOCK_CLASS", mockDomainModel);
		MetaClass metaClass = mock(MetaClass.class);
		when(metaClass.canonicalClassName()).thenReturn("MOCK_CLASS");
		
		assertEquals(mockDomainModel, metaDomain.model(metaClass));
		
	}
	
	@Test
	public void test_modelNames_returns_set_of_model_names() {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		DomainModel domainModelA = mock(DomainModel.class);
		DomainModel domainModelB = mock(DomainModel.class);
		domainModelsField.get(metaDomain).put("DOMAIN_MODEL_A", domainModelA);
		domainModelsField.get(metaDomain).put("DOMAIN_MODEL_B", domainModelB);
		
		Set<String> expected = new HashSet<>(Arrays.asList("DOMAIN_MODEL_A", "DOMAIN_MODEL_B"));
		
		assertEquals(expected, metaDomain.modelNames());
		
	}
	
	@Test
	public void test_metaClasses_returns_set_of_all_MetaClasses_in_metaDomain() {
		MetaModel metaModel = mock(MetaModel.class);
		MetaDomain metaDomain = new MetaDomain(metaModel, "META_DOMAIN");
		
		DomainModel domainModelA = mock(DomainModel.class);
		MetaClass metaClassA = mock(MetaClass.class);
		when(domainModelA.getMetaClass()).thenReturn(metaClassA);
		
		DomainModel domainModelB = mock(DomainModel.class);
		MetaClass metaClassB = mock(MetaClass.class);
		when(domainModelB.getMetaClass()).thenReturn(metaClassB);

		domainModelsField.get(metaDomain).put("DOMAIN_MODEL_A", domainModelA);
		domainModelsField.get(metaDomain).put("DOMAIN_MODEL_B", domainModelB);
		
		Set<MetaClass> expected = new HashSet<>(Arrays.asList(metaClassA, metaClassB));
		
		assertEquals(expected, metaDomain.metaClasses());

	}
	

}
