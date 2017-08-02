package com.zy.httpclient.http;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpUtils {

    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
}
