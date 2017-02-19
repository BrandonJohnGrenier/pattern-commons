package fm.pattern.commons.util;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings({ "rawtypes" })
public final class ReflectionUtils {

	private ReflectionUtils() {

	}

	public static String getString(Object instance, String property) {
		return (String) getObject(instance, property);
	}

	public static Object getObject(Object instance, String property) {
		try {
			return PropertyUtils.getProperty(instance, property);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setValue(Object instance, String attribute, Object value) {
		setValue(instance, attribute, value, 0);
	}

	public static void setValueOnSuperclass(Object instance, String attribute, Object value) {
		setValue(instance, attribute, value, 1);
	}	
	
	public static void setValue(Object instance, String attribute, Object value, int ancestor) {
		String[] fields = attribute.contains(".") ? attribute.split("\\.") : new String[] { attribute };
		try {
			Field field = getTargetField(instance.getClass(), fields, 0, ancestor);
			Object object = attribute.contains(".") ? getTargetObject(instance, fields, 0, ancestor) : instance;
			field.set(object, value);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Field getTargetField(Class c, String[] fields, int index, int generations) {
		try {
			Class targetClass = targetClass(c, generations);
			Field field = targetClass.getDeclaredField(fields[index]);
			field.setAccessible(true);
			return (index + 1) == fields.length ? field : getTargetField(field.getType(), fields, index + 1, 0);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Object getTargetObject(Object object, String[] fields, int index, int ancestors) {
		try {
			Field field = targetClass(object.getClass(), ancestors).getDeclaredField(fields[index]);
			field.setAccessible(true);
			return (index + 2) == fields.length ? field.get(object) : getTargetObject(field.get(object), fields, index + 1, ancestors);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Class targetClass(Class c, int generations) {
		if (generations == 0) {
			return c;
		}

		Class ancestor = null;
		for (int i = 0; i < generations; i++) {
			ancestor = (ancestor == null ? c.getSuperclass() : ancestor.getSuperclass());
		}
		return ancestor;
	}

}
