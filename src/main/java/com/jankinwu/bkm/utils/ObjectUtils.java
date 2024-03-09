package com.jankinwu.bkm.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jankinwu
 * @description 对象工具类
 * @date 2024/3/8 17:13
 */
public class ObjectUtils {

    public static Map<String, Object> objectToMap(Object obj, String... propertyNames) {
        Map<String, Object> map = new HashMap<>();

        // 获取对象的所有字段
        Field[] fields = obj.getClass().getDeclaredFields();

        // 遍历字段，并将字段名和字段值放入Map中
        for (Field field : fields) {
            field.setAccessible(true); // 设置可访问私有字段
            try {
                Object value = field.get(obj);

                // 若未指定属性名，则将所有字段都放入Map中
                if (propertyNames.length == 0 || contains(propertyNames, field.getName())) {
                    map.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    // 判断数组中是否包含指定元素
    private static boolean contains(String[] arr, String element) {
        for (String str : arr) {
            if (str.equals(element)) {
                return true;
            }
        }
        return false;
    }
}
