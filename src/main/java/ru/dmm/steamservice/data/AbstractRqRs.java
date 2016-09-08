package ru.dmm.steamservice.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Dmitry
 */
public abstract class AbstractRqRs {

    public Map<String, String> getFieldMap() {

        Map<String, String> map = new HashMap<>();
        final Class<?> clazz = this.getClass();

        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            String value;
            try {
                value = field.get(this) == null ? "" : ConvertUtils.convert(field.get(this));
                map.put(field.getName(), value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
            map.put(field.getName(), value);
        }
        return map;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
