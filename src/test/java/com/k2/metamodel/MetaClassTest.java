package com.k2.metamodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

import com.k2.metamodel.exception.MetaClassDoesNotExist;
import com.k2.metamodel.exception.MetaModelException;
import com.k2.test.utils.PrivateFieldAccessor;

@RunWith(MockitoJUnitRunner.class)
public class MetaClassTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	private static PrivateFieldAccessor<MetaClass,MetaModel> metaModelField = new PrivateFieldAccessor<>(MetaClass.class, "metaModel", MetaModel.class);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PrivateFieldAccessor<MetaClass,Class<?>> clsField = new PrivateFieldAccessor(MetaClass.class, "cls", Class.class);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PrivateFieldAccessor<MetaClass,Map<String,DomainModel>> domainModelsField = new PrivateFieldAccessor(MetaClass.class, "domainModels", Map.class);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PrivateFieldAccessor<MetaClass,Set<MetaClass>> subMetaClassesField = new PrivateFieldAccessor(MetaClass.class, "subMetaClasses", Set.class);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PrivateFieldAccessor<MetaClass,Set<MetaField>> declaredMetaFieldsField = new PrivateFieldAccessor(MetaClass.class, "declaredMetaFields", Set.class);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static PrivateFieldAccessor<MetaClass,Set<MetaMethod>> declaredMetaMethodsField = new PrivateFieldAccessor(MetaClass.class, "declaredMetaMethods", Set.class);
	private static PrivateFieldAccessor<MetaClass,Boolean> scannedField = new PrivateFieldAccessor<>(MetaClass.class, "scanned", Boolean.class);
	private static PrivateFieldAccessor<MetaClass,MetaPackage> metaPackageField = new PrivateFieldAccessor<>(MetaClass.class, "metaPackage", MetaPackage.class);
	private static PrivateFieldAccessor<MetaClass,MetaClass> superMetaClassField = new PrivateFieldAccessor<>(MetaClass.class, "superMetaClass", MetaClass.class);


	@Test
	public void test_new_instance() {
		
		MetaModel metaModel = mock(MetaModel.class);
		
		MetaClass metaClass = new MetaClass(metaModel, Integer.class);
		
		assertEquals(metaModel, metaModelField.get(metaClass));
		assertEquals(Integer.class, clsField.get(metaClass));
		assertNotNull(domainModelsField.get(metaClass));
		assertNotNull(subMetaClassesField.get(metaClass));
		assertNotNull(declaredMetaFieldsField.get(metaClass));
		assertNotNull(declaredMetaMethodsField.get(metaClass));
		assertFalse(scannedField.get(metaClass));
		assertNull(metaPackageField.get(metaClass));
		assertNull(superMetaClassField.get(metaClass));
		
	}
	
	@Test
	public void test_instance_returns_existing_instance() throws MetaModelException {
		
		MetaModel metaModel = mock(MetaModel.class);
		MetaClass integerMetaClass = mock(MetaClass.class);
		when(metaModel.metaClass(Integer.class)).thenReturn(integerMetaClass);
		
		assertEquals(integerMetaClass, MetaClass.instance(metaModel, Integer.class));
		verify(metaModel, times(1)).metaClass(Integer.class);
		
	}
	
	@Test
	public void test_instance_returns_new_instance() throws MetaModelException {

		MetaModel metaModel = mock(MetaModel.class);
		when(metaModel.metaClass(Integer.class)).thenThrow(MetaClassDoesNotExist.class);

		MetaClass metaClass = MetaClass.instance(metaModel, Integer.class);

		ArgumentCaptor<MetaClass> metaClassCaptor = ArgumentCaptor.forClass(MetaClass.class);
		verify(metaModel, times(1)).add(metaClassCaptor.capture());
		assertEquals(metaClass, metaClassCaptor.getValue());
		
		assertEquals(Integer.class, metaClass.getModelledClass());
		
		

	}

}
