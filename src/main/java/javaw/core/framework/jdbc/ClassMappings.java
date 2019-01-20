package javaw.core.framework.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @Description:
* @Author:         wanbo
* @CreateDate:     2018/10/05 11:45
* @UpdateRemark:
* @Version:        1.0.0
*/
public class ClassMappings {

	private ClassMappings(){}
	
	 static final Set<Class<?>> SUPPORTED_SQL_OBJECTS = new HashSet<Class<?>>();

	    static {
	    	//只要这里写了的，默认支持自动类型转换
	        Class<?>[] classes = {
	                boolean.class, Boolean.class,
	                short.class, Short.class,
	                int.class, Integer.class,
	                long.class, Long.class,
	                float.class, Float.class,
	                double.class, Double.class,
	                String.class,
	                Date.class,
	                Timestamp.class,
	                BigDecimal.class
	        };
	        SUPPORTED_SQL_OBJECTS.addAll(Arrays.asList(classes));
	    }

	    static boolean isSupportedSQLObject(Class<?> clazz) {
	        return clazz.isEnum() || SUPPORTED_SQL_OBJECTS.contains(clazz);
	    }

	/**
	 * @Description 找出clazz中public修饰的get方法并将，<字段名，方法>存入map中
	 * @Author wanbo
	 **/
	public static Map<String, Method> findPublicGetters(Class<?> clazz) {
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (Modifier.isStatic(method.getModifiers()))
				continue;
			if (method.getParameterTypes().length != 0)
				continue;
			if (method.getName().equals("getClass"))
				continue;
			Class<?> returnType = method.getReturnType();
			if (void.class.equals(returnType))
				continue;
			if(!isSupportedSQLObject(returnType)){
				continue;
			}
			if ((returnType.equals(boolean.class)
					|| returnType.equals(Boolean.class))
					&& method.getName().startsWith("is")
					&& method.getName().length() > 2) {
				map.put(getGetterName(method), method);
				continue;
			}
			if ( ! method.getName().startsWith("get"))
				continue;
			if (method.getName().length() < 4)
				continue;
			map.put(getGetterName(method), method);
		}
		return map;
	}

	/**
	 * 获取Class所有的字段
	 *
	 * @auntbor wanbo
	 */
	public static Field[] findFields(Class<?> clazz){
		return clazz.getDeclaredFields();
	}

	/**
	 * @Description 找出clazz中public修饰的set方法并将，<字段名，方法>存入map中
	 * @Author wanbo
	 **/
	public static Map<String, Method> findPublicSetters(Class<?> clazz) {
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (Modifier.isStatic(method.getModifiers()))
				continue;
			if ( ! void.class.equals(method.getReturnType()))
				continue;
			if (method.getParameterTypes().length != 1)
				continue;
			if ( ! method.getName().startsWith("set"))
				continue;
			if (method.getName().length() < 4)
				continue;
			if(!isSupportedSQLObject(method.getParameterTypes()[0])){
				continue;
			}
			map.put(getSetterName(method), method);
		}
		return map;
	}

	/**
	 * 通过get方法获取该get方法吗修饰的字段名
	 *
	 * @auntbor wanbo
	 */
	public static String getGetterName(Method getter) {
		String name = getter.getName();
		if (name.startsWith("is"))
			name = name.substring(2);
		else
			name = name.substring(3);
		return Character.toLowerCase(name.charAt(0)) + name.substring(1);
	}

	/**
	 * 通过set方法获取该set方法吗修饰的字段名
	 *
	 * @auntbor wanbo
	 */
	private static String getSetterName(Method setter) {
		String name = setter.getName().substring(3);
		return Character.toLowerCase(name.charAt(0)) + name.substring(1);
	}
}
