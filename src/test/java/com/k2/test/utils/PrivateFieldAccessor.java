package com.k2.test.utils;

import java.lang.reflect.Field;

import com.k2.metamodel.MetaDomain;

public class PrivateFieldAccessor <T,F>{
	
	final Field field;

	public PrivateFieldAccessor(Class<T> cls, String name, Class<F> returnType) {
		try {
			this.field = MetaDomain.class.getDeclaredField(name);
			this.field.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public F get(T obj) {
		try {
			return (F)this.field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void set(T obj, F value) {
		try {
			this.field.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
