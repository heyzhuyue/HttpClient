package com.zy.httpclient.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpUtils {

    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private OkHttpClient mOkHttpClient;
    private static HttpUtils mInstance;

    private HttpUtils(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public static HttpUtils getInstance() {
        return initClient(null);
    }

    private static HttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

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
