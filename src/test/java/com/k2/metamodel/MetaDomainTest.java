package com.k2.metamodel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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

}
