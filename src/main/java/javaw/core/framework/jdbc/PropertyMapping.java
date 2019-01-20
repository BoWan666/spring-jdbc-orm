package javaw.core.framework.jdbc;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
* @Description: 设定对应字段的属性值
* @Author:         wanbo
* @CreateDate:     2018/10/05 13:01
* @UpdateRemark:
* @Version:        1.0.0
*/
class PropertyMapping {

    final boolean insertable;
    final boolean updatable;
    final String columnName;
    final boolean id;
    final Method getter;
    final Method setter;
	final Class enumClass;
    final String fieldName;

    /**
     * 构造初始化对应字段的属性值
     *
     * @auntbor wanbo
     */
    public PropertyMapping(Method getter, Method setter, Field field) {
        this.getter = getter;
        this.setter = setter;
        this.enumClass = getter.getReturnType().isEnum() ? getter.getReturnType() : null;
        Column column = field.getAnnotation(Column.class);
        this.insertable = column == null || column.insertable();
        this.updatable = column == null || column.updatable();
        this.columnName = column == null ? ClassMappings.getGetterName(getter) : ("".equals(column.name()) ? ClassMappings.getGetterName(getter) : column.name());
        this.id = field.isAnnotationPresent(Id.class);
        this.fieldName = field.getName();
    }

    Object get(Object target) throws Exception {
        Object r = getter.invoke(target);
        return enumClass == null ? r : Enum.valueOf(enumClass, (String) r);
    }

    void set(Object target, Object value) throws Exception {
        if (enumClass != null && value != null) {
            value = Enum.valueOf(enumClass, (String) value);
        }
        //BeanUtils.setProperty(target, fieldName, value);
        try {
        	 if(value != null){
             	 setter.invoke(target, setter.getParameterTypes()[0].cast(value));
             }
		} catch (Exception e) {
			e.printStackTrace();
			/**
			 * 出错原因如果是boolean字段 mysql字段类型 设置tinyint(1)
			 */
			System.err.println(fieldName + "--" + value);
		}

    }
}
