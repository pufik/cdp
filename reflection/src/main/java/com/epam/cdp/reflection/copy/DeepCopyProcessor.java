package com.epam.cdp.reflection.copy;

import java.lang.reflect.Field;

public class DeepCopyProcessor {

	public Object copy(Object original) throws Exception {
		Class<?> clazz = original.getClass();
		Object copy = null;

		copy = clazz.newInstance();
		copyFields(clazz, original, copy);

		return copy;
	}

	private void copyFields(Class<?> src, Object original, Object dest) throws Exception {
		for (Field field : src.getDeclaredFields()) {
			field.setAccessible(true);

			Object fieldValue = field.get(original);
			field.set(dest, fieldValue);
		}

		// Recursion
		if (!src.getSuperclass().equals(Object.class)) {
			copyFields(src.getSuperclass(), original, dest);
		}
	}

}
