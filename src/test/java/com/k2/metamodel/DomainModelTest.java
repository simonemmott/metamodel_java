package com.k2.metamodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;

import com.k2.metamodel.exception.DomainModelDoesNotExist;
import com.k2.metamodel.exception.MetaModelException;
import com.k2.test.utils.PrivateFieldAccessor;

@RunWith(MockitoJUnitRunner.class)
public class DomainModelTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	private static PrivateFieldAccessor<DomainModel,MetaDomain> metaDomainField = new PrivateFieldAccessor<>(DomainModel.class, "metaDomain", MetaDomain.class);
	private static PrivateFieldAccessor<DomainModel,MetaClass> metaClassField = new PrivateFieldAccessor<>(DomainModel.class, "metaClass", MetaClass.class);

	
	@Test
	public void test_new_instance() {
		
		MetaDomain metaDomain = mock(MetaDomain.class);
		MetaClass metaClass = mock(MetaClass.class);
		DomainModel domainModel = new DomainModel(metaDomain, metaClass);
		
		assertEquals(metaDomain, metaDomainField.get(domainModel));
		assertEquals(metaClass, metaClassField.get(domainModel));
		
	}
	
	@Test
	public void test_instance_returns_existing_instance() throws MetaModelException {
		MetaDomain metaDomain = mock(MetaDomain.class);
		MetaClass metaClass = mock(MetaClass.class);
		DomainModel domainModel = mock(DomainModel.class);
		
		when(metaDomain.model(metaClass)).thenReturn(domainModel);
		
		assertEquals(domainModel, DomainModel.instance(metaDomain, metaClass));
		
	}

	@Test
	public void test_instance_creates_new_instance() throws MetaModelException {
		MetaDomain metaDomain = mock(MetaDomain.class);
		MetaClass metaClass = mock(MetaClass.class);
		
		
		when(metaDomain.model(metaClass)).thenThrow(DomainModelDoesNotExist.class);
		
		DomainModel domainModel = DomainModel.instance(metaDomain, metaClass);
		
		assertEquals(metaDomain, domainModel.getMetaDomain());
		assertEquals(metaClass, domainModel.getMetaClass());
		
		ArgumentCaptor<DomainModel> domainModelCaptor = ArgumentCaptor.forClass(DomainModel.class);
		verify(metaDomain, times(1)).add(domainModelCaptor.capture());
		
		assertEquals(domainModel, domainModelCaptor.getValue());
		
	}
	
	@Test
	public void test_canonicalClassName_returns_canonicalClassName_of_metaClass() {
		MetaDomain metaDomain = mock(MetaDomain.class);
		MetaClass metaClass = mock(MetaClass.class);
		when(metaClass.canonicalClassName()).thenReturn("CANONICAL_CLASS_NAME");
		DomainModel domainModel = new DomainModel(metaDomain, metaClass);
		
		assertEquals("CANONICAL_CLASS_NAME", domainModel.canonicalClassName());

		verify(metaClass, times(1)).canonicalClassName();
		
	}

}
